package net.empire.ewmedieval.block.custom.feastblocks;

import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class FieryFonduePotBlock extends Block {

    public static final IntProperty LEVEL = IntProperty.of("level", 1 , 3);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public FieryFonduePotBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(LEVEL, 3)
                .with(FACING, Direction.NORTH));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                              Hand hand, BlockHitResult hit) {
        return takeServing(world, pos, state, player, hand);
    }

    private ActionResult takeServing(World world, BlockPos pos, BlockState state, PlayerEntity player,
                                     Hand hand) {
        ItemStack held = player.getStackInHand(hand);

        if (held.isOf(Items.BOWL)) {
            if (!player.getAbilities().creativeMode) {
                held.decrement(1);
            }

            ItemStack fondue = new ItemStack(ModItems.FIERY_FONDUE);

            if (!player.getInventory().insertStack(fondue)) {
                player.dropItem(fondue, false);
            }

            int level = state.get(LEVEL);
            if (level > 1) {
                world.setBlockState(pos, state.with(LEVEL, level - 1), 3);
            } else {
                world.setBlockState(pos, Blocks.CAULDRON.getDefaultState(), 3);
            }

            world.playSound(null, pos,
                    SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
                    SoundCategory.BLOCKS, 1.0F, 1.0F);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LEVEL, FACING);
    }
}
