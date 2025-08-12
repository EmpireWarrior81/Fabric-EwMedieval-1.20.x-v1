package net.empire.ewmedieval.datagen;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;
import net.minecraft.data.client.TextureMap;

import java.util.List;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }


    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILVER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CUT_SILVER);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_SILVER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.KHAZAD_STEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BURZUM_STEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EDHEL_STEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRUDE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LEAD_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CUT_LEAD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_LEAD_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_LEAD_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MITHRIL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_MITHRIL_BLOCK);

        // Polished Stone
        BlockStateModelGenerator.BlockTexturePool polishedStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_STONE);
        polishedStonePool.slab(ModBlocks.POLISHED_STONE_SLAB);
        polishedStonePool.stairs(ModBlocks.POLISHED_STONE_STAIRS);
        polishedStonePool.wall(ModBlocks.POLISHED_STONE_WALL);

// Mossy Polished Stone
        BlockStateModelGenerator.BlockTexturePool mossyPolishedStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_POLISHED_STONE);
        mossyPolishedStonePool.slab(ModBlocks.MOSSY_POLISHED_STONE_SLAB);
        mossyPolishedStonePool.stairs(ModBlocks.MOSSY_POLISHED_STONE_STAIRS);
        mossyPolishedStonePool.wall(ModBlocks.MOSSY_POLISHED_STONE_WALL);

// Cracked Polished Stone
        BlockStateModelGenerator.BlockTexturePool crackedPolishedStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_POLISHED_STONE);
        crackedPolishedStonePool.slab(ModBlocks.CRACKED_POLISHED_STONE_SLAB);
        crackedPolishedStonePool.stairs(ModBlocks.CRACKED_POLISHED_STONE_STAIRS);
        crackedPolishedStonePool.wall(ModBlocks.CRACKED_POLISHED_STONE_WALL);

// Stone Tiles
        BlockStateModelGenerator.BlockTexturePool stoneTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.STONE_TILES);
        stoneTilesPool.slab(ModBlocks.STONE_TILES_SLAB);
        stoneTilesPool.stairs(ModBlocks.STONE_TILES_STAIRS);
        stoneTilesPool.wall(ModBlocks.STONE_TILES_WALL);

// Mossy Stone Tiles
        BlockStateModelGenerator.BlockTexturePool mossyStoneTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_STONE_TILES);
        mossyStoneTilesPool.slab(ModBlocks.MOSSY_STONE_TILES_SLAB);
        mossyStoneTilesPool.stairs(ModBlocks.MOSSY_STONE_TILES_STAIRS);
        mossyStoneTilesPool.wall(ModBlocks.MOSSY_STONE_TILES_WALL);

// Cracked Stone Tiles
        BlockStateModelGenerator.BlockTexturePool crackedStoneTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_STONE_TILES);
        crackedStoneTilesPool.slab(ModBlocks.CRACKED_STONE_TILES_SLAB);
        crackedStoneTilesPool.stairs(ModBlocks.CRACKED_STONE_TILES_STAIRS);
        crackedStoneTilesPool.wall(ModBlocks.CRACKED_STONE_TILES_WALL);

// Mossy Smooth Stone
        BlockStateModelGenerator.BlockTexturePool mossySmoothStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_SMOOTH_STONE);
        mossySmoothStonePool.slab(ModBlocks.MOSSY_SMOOTH_STONE_SLAB);
        mossySmoothStonePool.stairs(ModBlocks.MOSSY_SMOOTH_STONE_STAIRS);
        mossySmoothStonePool.wall(ModBlocks.MOSSY_SMOOTH_STONE_WALL);

// Cracked Smooth Stone
        BlockStateModelGenerator.BlockTexturePool crackedSmoothStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_SMOOTH_STONE);
        crackedSmoothStonePool.slab(ModBlocks.CRACKED_SMOOTH_STONE_SLAB);
        crackedSmoothStonePool.stairs(ModBlocks.CRACKED_SMOOTH_STONE_STAIRS);
        crackedSmoothStonePool.wall(ModBlocks.CRACKED_SMOOTH_STONE_WALL);

// Old Stone
        BlockStateModelGenerator.BlockTexturePool oldStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.OLD_STONE);
        oldStonePool.slab(ModBlocks.OLD_STONE_SLAB);
        oldStonePool.stairs(ModBlocks.OLD_STONE_STAIRS);
        oldStonePool.wall(ModBlocks.OLD_STONE_WALL);

// Stone Brickwork
        BlockStateModelGenerator.BlockTexturePool stoneBrickworkPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.STONE_BRICKWORK);
        stoneBrickworkPool.slab(ModBlocks.STONE_BRICKWORK_SLAB);
        stoneBrickworkPool.stairs(ModBlocks.STONE_BRICKWORK_STAIRS);
        stoneBrickworkPool.wall(ModBlocks.STONE_BRICKWORK_WALL);

// Slate
        BlockStateModelGenerator.BlockTexturePool slatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SLATE);
        BlockStateModelGenerator.BlockTexturePool cobbledslatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.COBBLED_SLATE);
        BlockStateModelGenerator.BlockTexturePool polishedslatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_SLATE);
        BlockStateModelGenerator.BlockTexturePool slatebricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SLATE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SLATE_TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SLATE_COPPER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SLATE_COAL_ORE);

        slatePool.stairs(ModBlocks.SLATE_STAIRS);
        slatePool.slab(ModBlocks.SLATE_SLAB);
        slatePool.button(ModBlocks.SLATE_BUTTON);
        slatePool.pressurePlate(ModBlocks.SLATE_PRESSURE_PLATE);
        slatePool.wall(ModBlocks.SLATE_WALL);

        polishedslatePool.stairs(ModBlocks.POLISHED_SLATE_STAIRS);
        polishedslatePool.slab(ModBlocks.POLISHED_SLATE_SLAB);
        polishedslatePool.wall(ModBlocks.POLISHED_SLATE_WALL);

        slatebricksPool.stairs(ModBlocks.SLATE_BRICKS_STAIRS);
        slatebricksPool.slab(ModBlocks.SLATE_BRICKS_SLAB);
        slatebricksPool.wall(ModBlocks.SLATE_BRICKS_WALL);

        cobbledslatePool.stairs(ModBlocks.COBBLED_SLATE_STAIRS);
        cobbledslatePool.slab(ModBlocks.COBBLED_SLATE_SLAB);
        cobbledslatePool.wall(ModBlocks.COBBLED_SLATE_WALL);

        blockStateModelGenerator.registerTrapdoor(ModBlocks.SLATE_TRAPDOOR);

        BlockStateModelGenerator.BlockTexturePool tuffbricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.TUFF_BRICKS);
        tuffbricksPool.stairs(ModBlocks.TUFF_BRICK_STAIRS);
        tuffbricksPool.slab(ModBlocks.TUFF_BRICK_SLAB);
        tuffbricksPool.wall(ModBlocks.TUFF_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedtuffPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_TUFF);
        polishedtuffPool.stairs(ModBlocks.POLISHED_TUFF_STAIRS);
        polishedtuffPool.slab(ModBlocks.POLISHED_TUFF_SLAB);
        polishedtuffPool.wall(ModBlocks.POLISHED_TUFF_WALL);

        blockStateModelGenerator.registerTrapdoor(ModBlocks.TUFF_TRAPDOOR);

        BlockStateModelGenerator.BlockTexturePool tuffPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.TUFF);
        tuffPool.slab(ModBlocks.TUFF_SLAB);
        tuffPool.stairs(ModBlocks.TUFF_STAIRS);
        tuffPool.wall(ModBlocks.TUFF_WALL);

// Old Deepslate
        BlockStateModelGenerator.BlockTexturePool oldDeepslatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.OLD_DEEPSLATE);
        oldDeepslatePool.stairs(ModBlocks.OLD_DEEPSLATE_STAIRS);
        oldDeepslatePool.slab(ModBlocks.OLD_DEEPSLATE_SLAB);
        oldDeepslatePool.wall(ModBlocks.OLD_DEEPSLATE_WALL);

// Smooth Deepslate
        BlockStateModelGenerator.BlockTexturePool smoothDeepslatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SMOOTH_DEEPSLATE);
        smoothDeepslatePool.stairs(ModBlocks.SMOOTH_DEEPSLATE_STAIRS);
        smoothDeepslatePool.slab(ModBlocks.SMOOTH_DEEPSLATE_SLAB);
        smoothDeepslatePool.wall(ModBlocks.SMOOTH_DEEPSLATE_WALL);

// Mossy Smooth Deepslate
        BlockStateModelGenerator.BlockTexturePool mossySmoothDeepslatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_SMOOTH_DEEPSLATE);
        mossySmoothDeepslatePool.stairs(ModBlocks.MOSSY_SMOOTH_DEEPSLATE_STAIRS);
        mossySmoothDeepslatePool.slab(ModBlocks.MOSSY_SMOOTH_DEEPSLATE_SLAB);
        mossySmoothDeepslatePool.wall(ModBlocks.MOSSY_SMOOTH_DEEPSLATE_WALL);

// Cracked Smooth Deepslate
        BlockStateModelGenerator.BlockTexturePool crackedSmoothDeepslatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_SMOOTH_DEEPSLATE);
        crackedSmoothDeepslatePool.stairs(ModBlocks.CRACKED_SMOOTH_DEEPSLATE_STAIRS);
        crackedSmoothDeepslatePool.slab(ModBlocks.CRACKED_SMOOTH_DEEPSLATE_SLAB);
        crackedSmoothDeepslatePool.wall(ModBlocks.CRACKED_SMOOTH_DEEPSLATE_WALL);

// Mossy Cobbled Deepslate
        BlockStateModelGenerator.BlockTexturePool mossyCobbledDeepslatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_COBBLED_DEEPSLATE);
        mossyCobbledDeepslatePool.stairs(ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS);
        mossyCobbledDeepslatePool.slab(ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB);
        mossyCobbledDeepslatePool.wall(ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL);


// Mossy Deepslate Bricks
        BlockStateModelGenerator.BlockTexturePool mossyDeepslateBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_DEEPSLATE_BRICKS);
        mossyDeepslateBricksPool.stairs(ModBlocks.MOSSY_DEEPSLATE_BRICKS_STAIRS);
        mossyDeepslateBricksPool.slab(ModBlocks.MOSSY_DEEPSLATE_BRICKS_SLAB);
        mossyDeepslateBricksPool.wall(ModBlocks.MOSSY_DEEPSLATE_BRICKS_WALL);

// Mossy Deepslate Tiles
        BlockStateModelGenerator.BlockTexturePool mossyDeepslateTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_DEEPSLATE_TILES);
        mossyDeepslateTilesPool.stairs(ModBlocks.MOSSY_DEEPSLATE_TILES_STAIRS);
        mossyDeepslateTilesPool.slab(ModBlocks.MOSSY_DEEPSLATE_TILES_SLAB);
        mossyDeepslateTilesPool.wall(ModBlocks.MOSSY_DEEPSLATE_TILES_WALL);

// Mossy Polished Deepslate
        BlockStateModelGenerator.BlockTexturePool mossyPolishedDeepslatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_POLISHED_DEEPSLATE);
        mossyPolishedDeepslatePool.stairs(ModBlocks.MOSSY_POLISHED_DEEPSLATE_STAIRS);
        mossyPolishedDeepslatePool.slab(ModBlocks.MOSSY_POLISHED_DEEPSLATE_SLAB);
        mossyPolishedDeepslatePool.wall(ModBlocks.MOSSY_POLISHED_DEEPSLATE_WALL);

// Cracked Polished Deepslate
        BlockStateModelGenerator.BlockTexturePool crackedPolishedDeepslatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_POLISHED_DEEPSLATE);
        crackedPolishedDeepslatePool.stairs(ModBlocks.CRACKED_POLISHED_DEEPSLATE_STAIRS);
        crackedPolishedDeepslatePool.slab(ModBlocks.CRACKED_POLISHED_DEEPSLATE_SLAB);
        crackedPolishedDeepslatePool.wall(ModBlocks.CRACKED_POLISHED_DEEPSLATE_WALL);

// Deepslate Brickwork
        BlockStateModelGenerator.BlockTexturePool deepslateBrickworkPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEEPSLATE_BRICKWORK);
        deepslateBrickworkPool.stairs(ModBlocks.DEEPSLATE_BRICKWORK_STAIRS);
        deepslateBrickworkPool.slab(ModBlocks.DEEPSLATE_BRICKWORK_SLAB);
        deepslateBrickworkPool.wall(ModBlocks.DEEPSLATE_BRICKWORK_WALL);

        BlockStateModelGenerator.BlockTexturePool deepslatePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DEEPSLATE);
        deepslatePool.button(ModBlocks.DEEPSLATE_BUTTON);
        deepslatePool.pressurePlate(ModBlocks.DEEPSLATE_PRESSURE_PLATE);

        blockStateModelGenerator.registerTrapdoor(ModBlocks.DEEPSLATE_TRAPDOOR);


        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TUFF_CARVED_WINDOW);


// TextureMap paneTextures = TextureMap.texture(new Identifier("ewmedieval", "block/tuff_carved_window_pane"));
    //    BlockStateModelGenerator.TintType tint = BlockStateModelGenerator.TintType.NOT_TINTED;

   //     blockStateModelGenerator.registerGlassPane(
   //             ModBlocks.TUFF_CARVED_WINDOW_PANE,
 //               paneTextures,
 //               paneTextures
//        );



// Maak een helper-klasse om de gegevens op te slaan
        record CustomBlockTextures(Block block, String side, String top) { }

// In je datagen-class:
        List<CustomBlockTextures> customBlocks = List.of(
                new CustomBlockTextures(ModBlocks.CHISELED_DEEPSLATE_BRICKS,
                        "block/chiseled_deepslate_bricks",
                        "block/chiseled_deepslate_bricks_top"),
                new CustomBlockTextures(ModBlocks.CHISELED_DEEPSLATE_TILES,
                        "block/chiseled_deepslate_tiles",
                        "block/chiseled_deepslate_tiles_top"),
                new CustomBlockTextures(ModBlocks.CHISELED_POLISHED_DEEPSLATE,
                        "block/chiseled_polished_deepslate",
                        "block/chiseled_polished_deepslate_top"),
                new CustomBlockTextures(ModBlocks.CHISELED_SMOOTH_DEEPSLATE,
                        "block/chiseled_smooth_deepslate",
                        "block/chiseled_smooth_deepslate_top"),
                new CustomBlockTextures(ModBlocks.CHISELED_TUFF,
                        "block/chiseled_tuff",
                        "block/chiseled_tuff_top"),
                new CustomBlockTextures(ModBlocks.CHISELED_TUFF_BRICKS,
                        "block/chiseled_tuff_bricks",
                        "block/chiseled_tuff_bricks_top"),
                new CustomBlockTextures(ModBlocks.CHISELED_SMOOTH_STONE,
                        "block/chiseled_smooth_stone",
                        "block/chiseled_smooth_stone_top"),
                new CustomBlockTextures(ModBlocks.CHISELED_STONE,
                        "block/chiseled_stone",
                        "block/chiseled_stone_top"),
                new CustomBlockTextures(ModBlocks.CHISELED_POLISHED_STONE,
                        "block/chiseled_polished_stone",
                        "block/chiseled_polished_stone_top"),
                new CustomBlockTextures(ModBlocks.CHISELED_STONE_TILES,
                        "block/chiseled_stone_tiles",
                        "block/chiseled_stone_tiles_top")

        );

        for (CustomBlockTextures data : customBlocks) {
            blockStateModelGenerator.registerCubeWithCustomTextures(
                    data.block(),
                    data.block(), // tweede parameter is meestal hetzelfde block
                    (block1, block2) -> TextureMap.of(
                                    TextureKey.SIDE, new Identifier("ewmedieval", data.side())
                            ).put(TextureKey.UP, new Identifier("ewmedieval", data.top()))
                            .put(TextureKey.DOWN, new Identifier("ewmedieval", data.top()))
                            .put(TextureKey.PARTICLE, new Identifier("ewmedieval", data.side()))
            );
        }

        List<CustomBlockTextures> pillarBlocks = List.of(
                new CustomBlockTextures(ModBlocks.DEEPSLATE_PILLAR, "block/deepslate_pillar", "block/deepslate_pillar_top"),
                new CustomBlockTextures(ModBlocks.MOSSY_DEEPSLATE_PILLAR, "block/mossy_deepslate_pillar", "block/mossy_deepslate_pillar_top"),
                new CustomBlockTextures(ModBlocks.CRACKED_DEEPSLATE_PILLAR, "block/cracked_deepslate_pillar", "block/cracked_deepslate_pillar_top"),
                new CustomBlockTextures(ModBlocks.STONE_PILLAR, "block/stone_pillar" , "block/stone_pillar_top"),
                new CustomBlockTextures(ModBlocks.MOSSY_STONE_PILLAR, "block/mossy_stone_pillar" , "block/mossy_stone_pillar_top"),
                new CustomBlockTextures(ModBlocks.CRACKED_STONE_PILLAR, "block/cracked_stone_pillar" , "block/cracked_stone_pillar_top")
        );

        for (CustomBlockTextures data : pillarBlocks) {
            blockStateModelGenerator.registerCubeWithCustomTextures(
                    data.block(),
                    data.block(),
                    (block1, block2) -> TextureMap.of(
                                    TextureKey.SIDE, new Identifier("ewmedieval", data.side())
                            ).put(TextureKey.UP, new Identifier("ewmedieval", data.top()))
                            .put(TextureKey.DOWN, new Identifier("ewmedieval", data.top()))
                            .put(TextureKey.PARTICLE, new Identifier("ewmedieval", data.side()))
            );
        }




    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SILVER, Models.GENERATED);
        itemModelGenerator.register(ModItems.KHAZAD_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.KHAZAD_STEEL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BURZUM_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BURZUM_STEEL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.EDHEL_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.EDHEL_STEEL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRUDE_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRUDE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_LEAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEAD_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEAD_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.MITHRIL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MITHRIL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_MITHRIL, Models.GENERATED);

        itemModelGenerator.register(ModItems.RAW_HORSE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SWAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_GOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_HORSE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SWAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_GOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAT_WING, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_BAT_WING, Models.GENERATED);

        itemModelGenerator.register(ModItems.LEMBAS, Models.GENERATED);

        itemModelGenerator.register(ModItems.CACTUS_FLESH, Models.GENERATED);
        itemModelGenerator.register(ModItems.CACTUS_STEAK, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRANBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIG, Models.GENERATED);
        itemModelGenerator.register(ModItems.KIWI, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANGO, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORANGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAKED_PEAR, Models.GENERATED);

        itemModelGenerator.register(ModItems.GONDORIAN_FOUNTAIN_GUARD_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GONDORIAN_FOUNTAIN_GUARD_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.GONDORIAN_FOUNTAIN_GUARD_BOOTS, Models.GENERATED);

        itemModelGenerator.register(ModItems.ARKENSTONE, Models.GENERATED);
    }
}
