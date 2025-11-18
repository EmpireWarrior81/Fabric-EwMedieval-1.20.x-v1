package net.empire.ewmedieval.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;



public class KnifeItem extends SwordItem {

    public KnifeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    // --- Entity interaction ---
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return super.postHit(stack, target, attacker);
    }

    // --- Block interaction ---
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        net.minecraft.util.math.Direction facing = context.getSide();

        // Cake snijden
        if (state.getBlock() == Blocks.CAKE && player != null && !world.isClient) {
            int bites = state.get(CakeBlock.BITES);
            if (bites < 6) {
                world.setBlockState(pos, state.with(CakeBlock.BITES, bites + 1), 3);
            } else {
                world.removeBlock(pos, false);
            }

            EquipmentSlot slot = context.getHand() == net.minecraft.util.Hand.MAIN_HAND
                    ? EquipmentSlot.MAINHAND
                    : EquipmentSlot.OFFHAND;
            stack.damage(1, player, e -> e.sendEquipmentBreakStatus(slot));

            world.playSound(null, pos, SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.BLOCKS, 0.8F, 1f);
            return ActionResult.SUCCESS;
        }


        // Pompoen uithollen
        if (state.getBlock() == Blocks.PUMPKIN && player != null && !world.isClient) {
            Direction carveDir = facing.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : facing;
            world.setBlockState(pos, Blocks.CARVED_PUMPKIN.getDefaultState().with(CarvedPumpkinBlock.FACING, carveDir), 3);

            EquipmentSlot slot = context.getHand() == net.minecraft.util.Hand.MAIN_HAND
                    ? EquipmentSlot.MAINHAND
                    : EquipmentSlot.OFFHAND;
            stack.damage(1, player, e -> e.sendEquipmentBreakStatus(slot));

            world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1f, 1f);
            return ActionResult.SUCCESS;
        }


        return ActionResult.PASS;
    }
}
