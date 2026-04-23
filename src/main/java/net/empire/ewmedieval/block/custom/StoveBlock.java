package net.empire.ewmedieval.block.custom;

import net.empire.ewmedieval.block.entity.custom.StoveBlockEntity;
import net.empire.ewmedieval.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class StoveBlock extends BlockWithEntity {

    public static final BooleanProperty LIT = Properties.LIT;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public StoveBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(LIT, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getStackInHand(hand);
        Item heldItem = heldStack.getItem();

        if (state.get(LIT)) {
            // Shovel extinguishes the stove
            if (heldItem instanceof ShovelItem) {
                extinguish(state, world, pos);
                heldStack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                return ActionResult.SUCCESS;
            }
            // Water bucket extinguishes the stove
            if (heldStack.isOf(Items.WATER_BUCKET)) {
                if (!world.isClient) {
                    world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH,
                            SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                extinguish(state, world, pos);
                if (!player.getAbilities().creativeMode) {
                    player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                }
                return ActionResult.SUCCESS;
            }
        } else {
            // Flint and steel lights the stove
            if (heldItem instanceof FlintAndSteelItem) {
                world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE,
                        SoundCategory.BLOCKS, 1.0F,
                        world.getRandom().nextFloat() * 0.4F + 0.8F);
                world.setBlockState(pos, state.with(Properties.LIT, true), 11);
                heldStack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                return ActionResult.SUCCESS;
            }
            // Fire charge lights the stove
            if (heldItem instanceof FireChargeItem) {
                world.playSound(null, pos, SoundEvents.ITEM_FIRECHARGE_USE,
                        SoundCategory.BLOCKS, 1.0F,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2F + 1.0F);
                world.setBlockState(pos, state.with(Properties.LIT, true), 11);
                if (!player.getAbilities().creativeMode) {
                    heldStack.decrement(1);
                }
                return ActionResult.SUCCESS;
            }
        }

        // Try to place item on the stove for cooking
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof StoveBlockEntity stoveEntity) {
            int stoveSlot = stoveEntity.getNextEmptySlot();
            if (stoveSlot < 0 || stoveEntity.isStoveBlockedAbove()) {
                return ActionResult.PASS;
            }
            var recipe = stoveEntity.getMatchingRecipe(heldStack, stoveSlot);
            if (recipe.isPresent()) {
                if (!world.isClient && stoveEntity.addItem(
                        player.getAbilities().creativeMode ? heldStack.copy() : heldStack,
                        recipe.get(), stoveSlot)) {
                    return ActionResult.SUCCESS;
                }
                return ActionResult.CONSUME;
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    // Extinguish the stove and play fire extinguish sound
    public void extinguish(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(LIT, false), 2);
        double x = pos.getX() + 0.5D;
        double y = pos.getY();
        double z = pos.getZ() + 0.5D;
        world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH,
                SoundCategory.BLOCKS, 0.5F, 2.6F);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState()
                .with(FACING, context.getHorizontalPlayerFacing().getOpposite())
                .with(LIT, true);
    }

    // Burn entities that step on the lit stove
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        boolean isLit = world.getBlockState(pos).get(StoveBlock.LIT);
        if (isLit && !entity.isFireImmune()
                && entity instanceof LivingEntity livingEntity
                && !EnchantmentHelper.hasFrostWalker(livingEntity)) {
            entity.damage(world.getDamageSources().hotFloor(), 1.0F);
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos,
                                BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StoveBlockEntity stoveEntity) {
                stoveEntity.dropInventory(world, pos);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LIT, FACING);
    }

    // Smoke and flame particles when lit
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(StoveBlock.LIT)) {
            double x = pos.getX() + 0.5D;
            double y = pos.getY();
            double z = pos.getZ() + 0.5D;

            if (random.nextInt(10) == 0) {
                world.playSound(x, y, z, SoundEvents.BLOCK_CAMPFIRE_CRACKLE,
                        SoundCategory.BLOCKS, 0.5F, 1.0F, false);
            }

            Direction direction = state.get(Properties.HORIZONTAL_FACING);
            Direction.Axis axis = direction.getAxis();
            double hOffset = random.nextDouble() * 0.6D - 0.3D;
            double xOffset = axis == Direction.Axis.X ? direction.getOffsetX() * 0.52D : hOffset;
            double yOffset = random.nextDouble() * 6.0D / 16.0D;
            double zOffset = axis == Direction.Axis.Z ? direction.getOffsetZ() * 0.52D : hOffset;

            world.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0, 0, 0);
            world.addParticle(ParticleTypes.FLAME, x + xOffset, y + yOffset, z + zOffset, 0, 0, 0);
        }
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.STOVE.instantiate(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
                                                                  BlockEntityType<T> type) {
        if (state.get(LIT)) {
            return checkType(type, ModBlockEntities.STOVE,
                    world.isClient ? StoveBlockEntity::animationTick : StoveBlockEntity::cookingTick);
        }
        return null;
    }

    @Override
    public BlockState rotate(BlockState state, net.minecraft.util.BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, net.minecraft.util.BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}