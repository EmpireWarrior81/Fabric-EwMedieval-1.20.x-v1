package net.empire.ewmedieval.world.feature;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.block.custom.cropblocks.WildRiceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/**
 * ewmedieval:wild_rice
 *
 * Places wild rice (a double-tall waterlogged plant) in shallow water — rivers,
 * swamps, etc. Uses the OCEAN_FLOOR_WG heightmap to start from the first water
 * layer above the river/ocean floor rather than the land surface.
 *
 * Per try:
 *   pos   = basePos (first water above floor) + random scatter offset
 *   Check: pos is a still water source AND pos.up() is air
 *   Then : place lower half (waterlogged) at pos, upper half (dry) at pos.up()
 */
public class EwWildRiceFeature extends Feature<EwWildRiceConfig> {

    public EwWildRiceFeature() {
        super(EwWildRiceConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<EwWildRiceConfig> context) {
        EwWildRiceConfig config    = context.getConfig();
        StructureWorldAccess world = context.getWorld();
        BlockPos origin            = context.getOrigin();
        Random random              = context.getRandom();

        // Start from the first water block directly above the river/ocean floor.
        // OCEAN_FLOOR_WG heightmap ignores fluids and returns the solid floor y,
        // and getTopPosition adds +1, so we land at the first water layer.
        BlockPos basePos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, origin);

        boolean placed = false;

        for (int i = 0; i < config.tries(); i++) {
            // Triangular distribution (same as FD) — denser near the centre.
            int dx = random.nextInt(config.xzSpread() + 1) - random.nextInt(config.xzSpread() + 1);
            int dy = random.nextInt(config.ySpread()  + 1) - random.nextInt(config.ySpread()  + 1);
            int dz = random.nextInt(config.xzSpread() + 1) - random.nextInt(config.xzSpread() + 1);

            BlockPos pos       = basePos.add(dx, dy, dz);
            FluidState fluid   = world.getFluidState(pos);

            // Only place in still water (level 8 = source block) with air directly above.
            if (fluid.isIn(FluidTags.WATER) && fluid.getLevel() == 8
                    && world.getBlockState(pos.up()).isAir()) {

                BlockState lowerState = ModBlocks.WILD_RICE.getDefaultState()
                        .with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER)
                        .with(WildRiceBlock.WATERLOGGED, true);

                if (lowerState.canPlaceAt(world, pos)) {
                    world.setBlockState(pos, lowerState, 3);
                    world.setBlockState(pos.up(),
                            ModBlocks.WILD_RICE.getDefaultState()
                                    .with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER)
                                    .with(WildRiceBlock.WATERLOGGED, false),
                            3);
                    placed = true;
                }
            }
        }
        return placed;
    }
}