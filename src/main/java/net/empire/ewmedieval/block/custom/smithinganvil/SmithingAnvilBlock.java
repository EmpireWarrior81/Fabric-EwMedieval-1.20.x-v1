package net.empire.ewmedieval.block.custom.smithinganvil;

import net.empire.ewmedieval.block.entity.custom.SmithingAnvilBlockEntity;
import net.empire.ewmedieval.forging.SmithingMinigameState;
import net.empire.ewmedieval.item.SmithingHammerItem;
import net.empire.ewmedieval.network.AnvilCancelMinigamePacket;
import net.empire.ewmedieval.network.AnvilStartMinigamePacket;
import net.empire.ewmedieval.network.AnvilStrikePacket;
import net.empire.ewmedieval.recipe.ForgingRecipe;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class SmithingAnvilBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public SmithingAnvilBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SmithingAnvilBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getStackInHand(hand);
        boolean isHammer = held.getItem() instanceof SmithingHammerItem;

        if (world.isClient()) {
            if (isHammer && SmithingMinigameState.active) {
                if (player.isSneaking()) {
                    SmithingMinigameState.reset();
                    AnvilCancelMinigamePacket.send(pos);
                    return ActionResult.SUCCESS;
                }
                // Cooldown — prevent hold-right-click from firing multiple strikes
                long now = world.getTime();
                if (now - SmithingMinigameState.lastStrikeTick < SmithingMinigameState.STRIKE_COOLDOWN) {
                    return ActionResult.SUCCESS;
                }
                SmithingMinigameState.lastStrikeTick = now;

                int hitType = SmithingMinigameState.getHitType();
                SmithingMinigameState.onStrike(hitType);
                AnvilStrikePacket.send(pos, hitType);
                return ActionResult.SUCCESS;
            }
            return ActionResult.SUCCESS;
        }

        // Server-side
        if (!(world.getBlockEntity(pos) instanceof SmithingAnvilBlockEntity anvil)) {
            return ActionResult.PASS;
        }

        ServerPlayerEntity serverPlayer = player instanceof ServerPlayerEntity sp ? sp : null;
        boolean hammerAction = isHammer && !player.isSneaking() && serverPlayer != null;

        // Simple forging in progress — each hammer click counts as a perfect hit
        if (hammerAction && anvil.isMinigameActive() && anvil.isSimpleForging()) {
            anvil.recordHit(SmithingAnvilBlockEntity.HIT_PERFECT, serverPlayer);
            return ActionResult.SUCCESS;
        }

        // Complex minigame in progress — client handles strikes via AnvilStrikePacket
        if (anvil.isMinigameActive()) {
            return ActionResult.SUCCESS;
        }

        if (hammerAction && anvil.hasRecipe()) {
            ForgingRecipe recipe = anvil.startMinigame(serverPlayer);
            if (recipe != null) {
                if (recipe.isSimple()) {
                    // Simple: first click records hit 1 directly, no minigame overlay sent
                    anvil.recordHit(SmithingAnvilBlockEntity.HIT_PERFECT, serverPlayer);
                } else {
                    AnvilStartMinigamePacket.send(serverPlayer, pos, recipe.getHits(),
                            1.5f, 60f, 20f, 0.9f, 0.2f, 4.5f);
                }
                return ActionResult.SUCCESS;
            }
        }

        player.openHandledScreen(anvil);
        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos,
                                BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof SmithingAnvilBlockEntity anvil) {
                ItemScatterer.spawn(world, pos, anvil);
                world.updateComparators(pos, this);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
