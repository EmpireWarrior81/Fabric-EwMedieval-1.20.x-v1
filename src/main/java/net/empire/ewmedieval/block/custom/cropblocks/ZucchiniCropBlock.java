package net.empire.ewmedieval.block.custom.cropblocks;

import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class ZucchiniCropBlock extends CropBlock {

    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = Properties.AGE_7;

    public ZucchiniCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.ZUCCHINI_SEEDS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        int currentAge = state.get(AGE);

        if (currentAge == MAX_AGE) {
            if (!world.isClient) {
                int quantity = 1 + world.random.nextInt(2);
                Block.dropStack(world, pos, new ItemStack(ModItems.ZUCCHINI, quantity));
                world.playSound(null, pos,
                        SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,
                        SoundCategory.BLOCKS, 1.0F,
                        0.8F + world.random.nextFloat() * 0.4F);
                world.setBlockState(pos, state.with(AGE, currentAge - 3), 2);
            }
            return ActionResult.success(world.isClient);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}