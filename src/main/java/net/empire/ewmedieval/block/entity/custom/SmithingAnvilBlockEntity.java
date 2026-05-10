package net.empire.ewmedieval.block.entity.custom;

import net.empire.ewmedieval.block.entity.ImplementedInventory;
import net.empire.ewmedieval.block.entity.ModBlockEntities;
import net.empire.ewmedieval.forging.ForgingQuality;
import net.empire.ewmedieval.forging.ForgingQualityHelper;
import net.empire.ewmedieval.gui.smithinganvil.SmithingAnvilScreenHandler;
import net.empire.ewmedieval.recipe.ForgingRecipe;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class SmithingAnvilBlockEntity extends BlockEntity
        implements ExtendedScreenHandlerFactory, ImplementedInventory {

    public static final int INPUT_START    = 0;
    public static final int INPUT_END      = 9;  // exclusive
    public static final int RESERVED_SLOT  = 9;
    public static final int OUTPUT_SLOT    = 10;
    public static final int BLUEPRINT_SLOT = 11;
    public static final int INVENTORY_SIZE = 12;

    public static final int HIT_MISS    = 0;
    public static final int HIT_GOOD    = 1;
    public static final int HIT_PERFECT = 2;

    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);

    // Minigame state (server-side)
    private boolean minigameActive = false;
    private boolean simpleForging  = false;
    private boolean grindingMode   = false;
    private int totalHits      = 0;
    private int hitsRemaining  = 0;
    private int perfectHits    = 0;
    private int goodHits       = 0;
    private int missedHits     = 0;
    private @Nullable UUID currentPlayer = null;

    // Recipe cache
    private @Nullable ForgingRecipe currentRecipe = null;
    private boolean recipeDirty = true;

    // Properties: 0=hasRecipe, 1=minigameActive, 2=canGrind, 3=hitsRemaining, 4=totalHits
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override public int get(int index) {
            return switch (index) {
                case 0 -> hasRecipe() ? 1 : 0;
                case 1 -> minigameActive ? 1 : 0;
                case 2 -> canStartGrinding() ? 1 : 0;
                case 3 -> hitsRemaining;
                case 4 -> totalHits;
                default -> 0;
            };
        }
        @Override public void set(int index, int value) { /* client no-op */ }
        @Override public int size() { return 5; }
    };

    public SmithingAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SMITHING_ANVIL_BLOCK_ENTITY, pos, state);
    }

    @Override public DefaultedList<ItemStack> getItems() { return inventory; }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.ewmedieval.smithing_anvil");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SmithingAnvilScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putBoolean("minigame.active",      minigameActive);
        nbt.putBoolean("minigame.simple",      simpleForging);
        nbt.putBoolean("minigame.grinding",    grindingMode);
        nbt.putInt("minigame.totalHits",        totalHits);
        nbt.putInt("minigame.hitsRemaining",    hitsRemaining);
        nbt.putInt("minigame.perfectHits",      perfectHits);
        nbt.putInt("minigame.goodHits",         goodHits);
        nbt.putInt("minigame.missedHits",       missedHits);
        if (currentPlayer != null) nbt.putUuid("minigame.player", currentPlayer);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        minigameActive = nbt.getBoolean("minigame.active");
        simpleForging  = nbt.getBoolean("minigame.simple");
        grindingMode   = nbt.getBoolean("minigame.grinding");
        totalHits      = nbt.getInt("minigame.totalHits");
        hitsRemaining  = nbt.getInt("minigame.hitsRemaining");
        perfectHits    = nbt.getInt("minigame.perfectHits");
        goodHits       = nbt.getInt("minigame.goodHits");
        missedHits     = nbt.getInt("minigame.missedHits");
        if (nbt.containsUuid("minigame.player")) currentPlayer = nbt.getUuid("minigame.player");
        recipeDirty = true;
    }

    @Override
    public void markDirty() {
        super.markDirty();
        recipeDirty = true;
    }

    // ── Recipe ────────────────────────────────────────────────────────────────

    public boolean hasRecipe() {
        return getCurrentRecipe() != null;
    }

    public @Nullable ForgingRecipe getCurrentRecipe() {
        if (world == null) return null;
        if (recipeDirty) {
            SimpleInventory inv = getInputInventory();
            currentRecipe = world.getRecipeManager()
                    .getFirstMatch(ForgingRecipe.Type.INSTANCE, inv, world)
                    .orElse(null);
            recipeDirty = false;
        }
        return currentRecipe;
    }

    // ── Minigame ──────────────────────────────────────────────────────────────

    /** Starts a forging minigame. Returns the recipe, or null if not possible. */
    public @Nullable ForgingRecipe startMinigame(ServerPlayerEntity player) {
        if (minigameActive) return null;
        ForgingRecipe recipe = getCurrentRecipe();
        if (recipe == null) return null;

        ItemStack result   = recipe.getOutput(player.getWorld().getRegistryManager());
        ItemStack existing = getStack(OUTPUT_SLOT);
        if (!existing.isEmpty()) {
            if (existing.getItem() != result.getItem()) return null;
            if (existing.getCount() + result.getCount() > existing.getMaxCount()) return null;
        }

        grindingMode   = false;
        simpleForging  = recipe.isSimple();
        minigameActive = true;
        totalHits      = recipe.getHits();
        hitsRemaining  = totalHits;
        perfectHits    = 0;
        goodHits       = 0;
        missedHits     = 0;
        currentPlayer  = player.getUuid();
        markDirty();
        return recipe;
    }

    /** Starts a grinding minigame. Returns true if started. */
    public boolean startGrinding(ServerPlayerEntity player) {
        if (minigameActive) return false;
        if (!getStack(OUTPUT_SLOT).isEmpty()) return false;
        ItemStack target = findGrindingTarget();
        if (target.isEmpty()) return false;

        grindingMode   = true;
        minigameActive = true;
        totalHits      = 6;
        hitsRemaining  = totalHits;
        perfectHits    = 0;
        goodHits       = 0;
        missedHits     = 0;
        currentPlayer  = player.getUuid();
        markDirty();
        return true;
    }

    /** Returns true if there is a quality-tagged toolhead in the input that can be ground. */
    public boolean canStartGrinding() {
        if (hasRecipe()) return false; // forging takes priority
        return !findGrindingTarget().isEmpty();
    }

    private ItemStack findGrindingTarget() {
        for (int i = INPUT_START; i < INPUT_END; i++) {
            ItemStack s = getStack(i);
            if (ForgingQualityHelper.hasQuality(s)) return s;
        }
        return ItemStack.EMPTY;
    }

    /** Records a player's strike. hitType: 0=miss, 1=good, 2=perfect. */
    public void recordHit(int hitType, ServerPlayerEntity player) {
        if (!minigameActive) return;
        if (!player.getUuid().equals(currentPlayer)) return;

        switch (hitType) {
            case HIT_PERFECT -> perfectHits++;
            case HIT_GOOD    -> goodHits++;
            default          -> missedHits++;
        }
        hitsRemaining--;

        // Damage the hammer held in the player's hand
        ItemStack held = player.getMainHandStack();
        if (held.getItem() instanceof net.empire.ewmedieval.item.SmithingHammerItem) {
            held.damage(1, player, p -> p.sendEquipmentBreakStatus(net.minecraft.entity.EquipmentSlot.MAINHAND));
        }

        // Spawn forge particles visible to all nearby players
        if (world instanceof net.minecraft.server.world.ServerWorld serverWorld) {
            spawnForgeParticles(serverWorld);
        }

        if (hitsRemaining <= 0) complete(player);
        markDirty();
    }

    /** Cancels the active minigame. */
    public void cancelMinigame() {
        if (!minigameActive) return;
        minigameActive = false;
        simpleForging  = false;
        grindingMode   = false;
        hitsRemaining  = 0;
        currentPlayer  = null;
        markDirty();
    }

    private void complete(ServerPlayerEntity player) {
        minigameActive = false;
        simpleForging  = false;
        if (grindingMode) {
            completeGrinding();
        } else {
            float score = totalHits > 0
                    ? (perfectHits * 1.0f + goodHits * 0.6f) / totalHits
                    : 0f;
            craftItem(ForgingQuality.fromScore(score), player);
        }
        grindingMode = false;
    }

    private void completeGrinding() {
        ItemStack target = findGrindingTarget();
        if (target.isEmpty()) return;

        // Find and remove the toolhead from input
        for (int i = INPUT_START; i < INPUT_END; i++) {
            if (ForgingQualityHelper.hasQuality(getStack(i))) {
                ItemStack result = removeStack(i, 1);
                applyGrindResult(result);
                setStack(OUTPUT_SLOT, result);
                break;
            }
        }

        currentPlayer = null;
        recipeDirty   = true;
        markDirty();
    }

    private void applyGrindResult(ItemStack stack) {
        ForgingQuality current = ForgingQualityHelper.getQuality(stack);
        float perfectFraction = totalHits > 0 ? (float) perfectHits / totalHits : 0f;

        ForgingQuality newQuality;
        if (perfectFraction >= 0.75f) {
            newQuality = ForgingQualityHelper.upgradeQuality(current);
        } else {
            newQuality = current; // good/miss = keep quality
        }

        ForgingQualityHelper.setQuality(stack, newQuality);
        ForgingQualityHelper.setNeedsGrinding(stack, false);
    }

    private void craftItem(ForgingQuality quality, ServerPlayerEntity player) {
        if (world == null) return;
        ForgingRecipe recipe = getCurrentRecipe();
        if (recipe == null) return;

        // Consume ingredients
        for (Ingredient ing : recipe.getIngredients()) {
            if (ing.isEmpty()) continue;
            for (int i = INPUT_START; i < INPUT_END; i++) {
                if (ing.test(getStack(i))) {
                    removeStack(i, 1);
                    break;
                }
            }
        }

        // Produce result; only stamp quality/grinding/ForgedBy if the recipe tracks quality
        ItemStack result = recipe.getOutput(world.getRegistryManager()).copy();
        if (recipe.tracksQuality()) {
            ForgingQualityHelper.setQuality(result, quality);
            ForgingQualityHelper.setNeedsGrinding(result, true);
            result.getOrCreateNbt().putString("ForgedBy", player.getName().getString());
        }
        if (recipe.isOutputHeated()) {
            ForgingQualityHelper.setHeated(result, true, world.getTime());
        }

        ItemStack existing = getStack(OUTPUT_SLOT);
        if (existing.isEmpty()) {
            setStack(OUTPUT_SLOT, result);
        } else if (existing.getItem() == result.getItem()) {
            existing.increment(result.getCount());
        }

        currentPlayer = null;
        recipeDirty   = true;
        currentRecipe = null;
        markDirty();
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private SimpleInventory getInputInventory() {
        SimpleInventory inv = new SimpleInventory(INPUT_END - INPUT_START);
        for (int i = INPUT_START; i < INPUT_END; i++) {
            inv.setStack(i - INPUT_START, getStack(i));
        }
        return inv;
    }

    private void spawnForgeParticles(net.minecraft.server.world.ServerWorld world) {
        net.minecraft.util.math.random.Random rng = world.getRandom();
        for (int i = 0; i < 6; i++) {
            double ox = 0.5 + (rng.nextFloat() - 0.5);
            double oy = 1.0 + rng.nextFloat() * 0.5;
            double oz = 0.5 + (rng.nextFloat() - 0.5);
            double vx = (rng.nextFloat() - 0.5) * 0.1;
            double vy = rng.nextFloat() * 0.1;
            double vz = (rng.nextFloat() - 0.5) * 0.1;
            world.spawnParticles(
                    new net.minecraft.particle.DustParticleEffect(
                            new org.joml.Vector3f(1.0f, 0.5f, 0.0f), 1.0f),
                    pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz,
                    1, vx, vy, vz, 1.0);
            world.spawnParticles(
                    net.minecraft.particle.ParticleTypes.CRIT,
                    pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz,
                    1, vx, vy, vz, 1.0);
        }
    }

    public boolean isMinigameActive()  { return minigameActive; }
    public boolean isSimpleForging()   { return simpleForging; }
    public int getHitsRemaining()      { return hitsRemaining; }
    public int getTotalHits()          { return totalHits; }
    public @Nullable UUID getCurrentPlayer() { return currentPlayer; }

    public ItemStack getRenderStack(int slot) {
        return getStack(slot);
    }
}
