package net.empire.ewmedieval.block.custom;

import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

@SuppressWarnings("deprecation")
public class SandyShrubBlock extends PlantBlock implements Fertilizable {

    protected static final VoxelShape SHAPE =
            Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public SandyShrubBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    /** Only allow placement on sand / red sand. */
    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.SAND);
    }

    // -------------------------------------------------------------------------
    // Shearing
    // -------------------------------------------------------------------------

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getStackInHand(hand);
        if (held.isOf(Items.SHEARS) || held.isOf(ModItems.BRONZE_SHEARS)) {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR,
                        SoundCategory.BLOCKS, 1.0F, 1.0F);
                Block.dropStack(world, pos, new ItemStack(this));
                world.removeBlock(pos, false);
                held.damage(1, player, p -> p.sendToolBreakStatus(hand));
            }
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    // -------------------------------------------------------------------------
    // Fertilizable (Yarn mapping of BonemealableBlock)
    // -------------------------------------------------------------------------

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    /**
     * Bonemeal places a small cluster of additional sandy shrubs nearby,
     * mirroring FD's behaviour (simplified — no world gen feature required).
     */
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        for (int i = 0; i < 8; i++) {
            BlockPos target = pos.add(
                    random.nextBetween(-2, 2),
                    random.nextBetween(-1, 1),
                    random.nextBetween(-2, 2));
            if (world.isAir(target) && this.getDefaultState().canPlaceAt(world, target)) {
                world.setBlockState(target, this.getDefaultState());
            }
        }
    }
}