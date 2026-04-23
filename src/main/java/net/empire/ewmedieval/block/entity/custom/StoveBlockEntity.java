package net.empire.ewmedieval.block.entity.custom;


import net.empire.ewmedieval.block.custom.StoveBlock;
import net.empire.ewmedieval.block.entity.ModBlockEntities;
import net.empire.ewmedieval.block.entity.SyncedBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.block.Block;

import java.util.Optional;

public class StoveBlockEntity extends SyncedBlockEntity {

    // The grilling area above the stove — if something blocks this, items are ejected
    private static final VoxelShape GRILLING_AREA =
            Block.createCuboidShape(3.0F, 0.0F, 3.0F, 13.0F, 1.0F, 13.0F);

    private static final int INVENTORY_SLOT_COUNT = 6;

    // 6 cooking slots — each holds one item, max stack size 1
    private final ItemStack[] inventory = new ItemStack[INVENTORY_SLOT_COUNT];
    private final int[] cookingTimes = new int[INVENTORY_SLOT_COUNT];
    private final int[] cookingTimesTotal = new int[INVENTORY_SLOT_COUNT];

    public StoveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STOVE, pos, state);
        for (int i = 0; i < INVENTORY_SLOT_COUNT; i++) {
            inventory[i] = ItemStack.EMPTY;
        }
    }

    // --- Serialization ---

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        // Reset all slots first so items removed on the server also clear on the client
        for (int i = 0; i < INVENTORY_SLOT_COUNT; i++) {
            inventory[i] = ItemStack.EMPTY;
        }
        NbtList itemList = nbt.getList("Inventory", NbtCompound.COMPOUND_TYPE);
        for (int i = 0; i < itemList.size(); i++) {
            NbtCompound slotNbt = itemList.getCompound(i);
            int slot = slotNbt.getByte("Slot") & 0xFF;
            if (slot < INVENTORY_SLOT_COUNT) {
                inventory[slot] = ItemStack.fromNbt(slotNbt);
            }
        }
        if (nbt.contains("CookingTimes", NbtCompound.INT_ARRAY_TYPE)) {
            int[] saved = nbt.getIntArray("CookingTimes");
            System.arraycopy(saved, 0, cookingTimes, 0,
                    Math.min(saved.length, cookingTimes.length));
        }
        if (nbt.contains("CookingTotalTimes", NbtCompound.INT_ARRAY_TYPE)) {
            int[] saved = nbt.getIntArray("CookingTotalTimes");
            System.arraycopy(saved, 0, cookingTimesTotal, 0,
                    Math.min(saved.length, cookingTimesTotal.length));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtList itemList = new NbtList();
        for (int i = 0; i < INVENTORY_SLOT_COUNT; i++) {
            if (!inventory[i].isEmpty()) {
                NbtCompound slotNbt = new NbtCompound();
                slotNbt.putByte("Slot", (byte) i);
                inventory[i].writeNbt(slotNbt);
                itemList.add(slotNbt);
            }
        }
        nbt.put("Inventory", itemList);
        nbt.putIntArray("CookingTimes", cookingTimes);
        nbt.putIntArray("CookingTotalTimes", cookingTimesTotal);
    }

    // --- Ticks ---

    public static void cookingTick(World world, BlockPos pos, BlockState state,
                                   StoveBlockEntity stove) {
        boolean isLit = state.get(StoveBlock.LIT);

        if (stove.isStoveBlockedAbove()) {
            // Something is blocking the grilling area — eject all items
            if (!stove.isInventoryEmpty()) {
                stove.dropInventory(world, pos);
                stove.inventoryChanged();
            }
        } else if (isLit) {
            stove.cookAndOutputItems(world, pos);
        } else {
            // Cool down cooking progress when stove is unlit
            for (int i = 0; i < INVENTORY_SLOT_COUNT; i++) {
                if (stove.cookingTimes[i] > 0) {
                    stove.cookingTimes[i] = MathHelper.clamp(
                            stove.cookingTimes[i] - 2, 0, stove.cookingTimesTotal[i]);
                }
            }
        }
    }

    public static void animationTick(World world, BlockPos pos, BlockState state,
                                     StoveBlockEntity stove) {
        for (int i = 0; i < INVENTORY_SLOT_COUNT; i++) {
            if (!stove.inventory[i].isEmpty() && world.random.nextFloat() < 0.2F) {
                Vec2f itemVector = stove.getStoveItemOffset(i);
                Direction direction = state.get(StoveBlock.FACING);
                int dirIndex = direction.getHorizontal();
                Vec2f offset = dirIndex % 2 == 0
                        ? itemVector
                        : new Vec2f(itemVector.y, itemVector.x);

                double x = pos.getX() + 0.5D
                        - direction.getOffsetX() * offset.x
                        + direction.rotateYClockwise().getOffsetX() * offset.x;
                double y = pos.getY() + 1.0D;
                double z = pos.getZ() + 0.5D
                        - direction.getOffsetZ() * offset.y
                        + direction.rotateYClockwise().getOffsetZ() * offset.y;

                for (int k = 0; k < 3; k++) {
                    world.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 5.0E-4D, 0);
                }
            }
        }
    }


    // --- Cooking logic ---

    private void cookAndOutputItems(World world, BlockPos pos) {
        boolean didInventoryChange = false;

        for (int i = 0; i < INVENTORY_SLOT_COUNT; i++) {
            ItemStack cookingStack = inventory[i];
            if (cookingStack.isEmpty()) continue;

            SimpleInventory wrapper = new SimpleInventory(cookingStack);
            Optional<CampfireCookingRecipe> recipe =
                    world.getRecipeManager().getFirstMatch(RecipeType.CAMPFIRE_COOKING, wrapper, world);

            if (recipe.isPresent()) {
                cookingTimesTotal[i] = recipe.get().getCookTime();
                cookingTimes[i]++;

                if (cookingTimes[i] >= cookingTimesTotal[i]) {
                    // Recipe finished — spawn result above the stove
                    cookingTimes[i] = 0;
                    ItemStack resultStack = recipe.get().getOutput(world.getRegistryManager()).copy();
                    if (!resultStack.isEmpty()) {
                        ItemEntity itemEntity = new ItemEntity(world,
                                pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                                resultStack);
                        itemEntity.setVelocity(
                                world.random.nextGaussian() * 0.01F,
                                0.1F,
                                world.random.nextGaussian() * 0.01F);
                        world.spawnEntity(itemEntity);
                    }
                    inventory[i] = ItemStack.EMPTY;
                    didInventoryChange = true;
                }
            } else {
                // No matching recipe — reset cooking progress
                cookingTimes[i] = 0;
            }
        }

        if (didInventoryChange) {
            inventoryChanged();
        }
    }

    // --- Utility ---

    public int getNextEmptySlot() {
        for (int i = 0; i < INVENTORY_SLOT_COUNT; i++) {
            if (inventory[i].isEmpty()) return i;
        }
        return -1;
    }

    public boolean addItem(ItemStack stack, CampfireCookingRecipe recipe, int slot) {
        if (slot >= 0 && slot < INVENTORY_SLOT_COUNT && inventory[slot].isEmpty()) {
            cookingTimesTotal[slot] = recipe.getCookTime();
            cookingTimes[slot] = 0;
            inventory[slot] = stack.split(1);
            inventoryChanged();
            return true;
        }
        return false;
    }

    public Optional<CampfireCookingRecipe> getMatchingRecipe(ItemStack stack, int slot) {
        if (world == null) return Optional.empty();
        SimpleInventory wrapper = new SimpleInventory(stack);
        return world.getRecipeManager().getFirstMatch(RecipeType.CAMPFIRE_COOKING, wrapper, world);
    }

    public boolean isStoveBlockedAbove() {
        if (world == null) return false;
        BlockState above = world.getBlockState(pos.up());
        return VoxelShapes.matchesAnywhere(GRILLING_AREA,
                above.getOutlineShape(world, pos.up()),
                BooleanBiFunction.AND);
    }

    public boolean isInventoryEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    // Drop all items in the inventory as item entities
    public void dropInventory(World world, BlockPos pos) {
        for (int i = 0; i < INVENTORY_SLOT_COUNT; i++) {
            if (!inventory[i].isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(world,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        inventory[i]);
                world.spawnEntity(itemEntity);
                inventory[i] = ItemStack.EMPTY;
            }
        }
    }

    public Vec2f getStoveItemOffset(int index) {
        final float X = 0.3F;
        final float Y = 0.2F;
        final Vec2f[] OFFSETS = {
                new Vec2f(X, Y),
                new Vec2f(0.0F, Y),
                new Vec2f(-X, Y),
                new Vec2f(X, -Y),
                new Vec2f(0.0F, -Y),
                new Vec2f(-X, -Y),
        };
        return OFFSETS[index];
    }

    public ItemStack[] getInventory() {
        return inventory;
    }
}