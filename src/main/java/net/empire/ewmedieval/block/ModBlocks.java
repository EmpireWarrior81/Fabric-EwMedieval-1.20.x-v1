package net.empire.ewmedieval.block;

import net.empire.ewmedieval.EwMedieval;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block SLATE = registerBlock("slate",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block COBBLED_SLATE = registerBlock("cobbled_slate",
            new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block POLISHED_SLATE = registerBlock("polished_slate",
            new Block(FabricBlockSettings.copyOf(ModBlocks.SLATE)));
    public static final Block SLATE_BRICKS = registerBlock("slate_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.COBBLED_SLATE)));

    public static final Block OLD_DEEPSLATE = registerBlock("old_deepslate",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)));
    public static final Block OLD_DEEPSLATE_SLAB = registerBlock("old_deepslate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(OLD_DEEPSLATE)));
    public static final Block OLD_DEEPSLATE_STAIRS = registerBlock("old_deepslate_stairs",
            new StairsBlock(OLD_DEEPSLATE.getDefaultState(), FabricBlockSettings.copyOf(OLD_DEEPSLATE)));
    public static final Block OLD_DEEPSLATE_WALL = registerBlock("old_deepslate_wall",
            new WallBlock(FabricBlockSettings.copyOf(OLD_DEEPSLATE)));

    public static final Block SMOOTH_DEEPSLATE = registerBlock("smooth_deepslate",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)));
    public static final Block SMOOTH_DEEPSLATE_SLAB = registerBlock("smooth_deepslate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(SMOOTH_DEEPSLATE)));
    public static final Block SMOOTH_DEEPSLATE_STAIRS = registerBlock("smooth_deepslate_stairs",
            new StairsBlock(SMOOTH_DEEPSLATE.getDefaultState(), FabricBlockSettings.copyOf(SMOOTH_DEEPSLATE)));
    public static final Block SMOOTH_DEEPSLATE_WALL = registerBlock("smooth_deepslate_wall",
            new WallBlock(FabricBlockSettings.copyOf(SMOOTH_DEEPSLATE)));

    public static final Block MOSSY_SMOOTH_DEEPSLATE = registerBlock("mossy_smooth_deepslate",
            new Block(FabricBlockSettings.copyOf(SMOOTH_DEEPSLATE)));
    public static final Block MOSSY_SMOOTH_DEEPSLATE_SLAB = registerBlock("mossy_smooth_deepslate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_SMOOTH_DEEPSLATE)));
    public static final Block MOSSY_SMOOTH_DEEPSLATE_STAIRS = registerBlock("mossy_smooth_deepslate_stairs",
            new StairsBlock(MOSSY_SMOOTH_DEEPSLATE.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_SMOOTH_DEEPSLATE)));
    public static final Block MOSSY_SMOOTH_DEEPSLATE_WALL = registerBlock("mossy_smooth_deepslate_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_SMOOTH_DEEPSLATE)));

    public static final Block CRACKED_SMOOTH_DEEPSLATE = registerBlock("cracked_smooth_deepslate",
            new Block(FabricBlockSettings.copyOf(SMOOTH_DEEPSLATE)));
    public static final Block CRACKED_SMOOTH_DEEPSLATE_SLAB = registerBlock("cracked_smooth_deepslate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(CRACKED_SMOOTH_DEEPSLATE)));
    public static final Block CRACKED_SMOOTH_DEEPSLATE_STAIRS = registerBlock("cracked_smooth_deepslate_stairs",
            new StairsBlock(CRACKED_SMOOTH_DEEPSLATE.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_SMOOTH_DEEPSLATE)));
    public static final Block CRACKED_SMOOTH_DEEPSLATE_WALL = registerBlock("cracked_smooth_deepslate_wall",
            new WallBlock(FabricBlockSettings.copyOf(CRACKED_SMOOTH_DEEPSLATE)));

    public static final Block DEEPSLATE_PILLAR = registerBlock("deepslate_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)));
    public static final Block MOSSY_DEEPSLATE_PILLAR = registerBlock("mossy_deepslate_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(DEEPSLATE_PILLAR)));
    public static final Block CRACKED_DEEPSLATE_PILLAR = registerBlock("cracked_deepslate_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(DEEPSLATE_PILLAR)));

    public static final Block CHISELED_POLISHED_DEEPSLATE = registerBlock("chiseled_polished_deepslate",
            new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DEEPSLATE)));
    public static final Block CHISELED_DEEPSLATE_BRICKS = registerBlock("chiseled_deepslate_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
    public static final Block CHISELED_DEEPSLATE_TILES = registerBlock("chiseled_deepslate_tiles",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_TILES)));
    public static final Block CHISELED_SMOOTH_DEEPSLATE = registerBlock("chiseled_smooth_deepslate",
            new Block(FabricBlockSettings.copyOf(SMOOTH_DEEPSLATE)));

    public static final Block MOSSY_DEEPSLATE_BRICKS = registerBlock("mossy_deepslate_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
    public static final Block MOSSY_DEEPSLATE_BRICKS_SLAB = registerBlock("mossy_deepslate_bricks_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_DEEPSLATE_BRICKS)));
    public static final Block MOSSY_DEEPSLATE_BRICKS_STAIRS = registerBlock("mossy_deepslate_bricks_stairs",
            new StairsBlock(MOSSY_DEEPSLATE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_DEEPSLATE_BRICKS)));
    public static final Block MOSSY_DEEPSLATE_BRICKS_WALL = registerBlock("mossy_deepslate_bricks_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_DEEPSLATE_BRICKS)));


    public static final Block MOSSY_DEEPSLATE_TILES = registerBlock("mossy_deepslate_tiles",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_TILES)));
    public static final Block MOSSY_DEEPSLATE_TILES_SLAB = registerBlock("mossy_deepslate_tiles_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_DEEPSLATE_TILES)));
    public static final Block MOSSY_DEEPSLATE_TILES_STAIRS = registerBlock("mossy_deepslate_tiles_stairs",
            new StairsBlock(MOSSY_DEEPSLATE_TILES.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_DEEPSLATE_TILES)));
    public static final Block MOSSY_DEEPSLATE_TILES_WALL = registerBlock("mossy_deepslate_tiles_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_DEEPSLATE_TILES)));

    public static final Block DEEPSLATE_BRICKWORK = registerBlock("deepslate_brickwork",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
    public static final Block DEEPSLATE_BRICKWORK_SLAB = registerBlock("deepslate_brickwork_slab",
            new SlabBlock(FabricBlockSettings.copyOf(DEEPSLATE_BRICKWORK)));
    public static final Block DEEPSLATE_BRICKWORK_STAIRS = registerBlock("deepslate_brickwork_stairs",
            new StairsBlock(DEEPSLATE_BRICKWORK.getDefaultState(), FabricBlockSettings.copyOf(DEEPSLATE_BRICKWORK)));
    public static final Block DEEPSLATE_BRICKWORK_WALL = registerBlock("deepslate_brickwork_wall",
            new WallBlock(FabricBlockSettings.copyOf(DEEPSLATE_BRICKWORK)));

    // MOSSY_COBBLED_DEEPSLATE
    public static final Block MOSSY_COBBLED_DEEPSLATE = registerBlock("mossy_cobbled_deepslate",
            new Block(FabricBlockSettings.copyOf(Blocks.COBBLED_DEEPSLATE)));
    public static final Block MOSSY_COBBLED_DEEPSLATE_SLAB = registerBlock("mossy_cobbled_deepslate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_DEEPSLATE)));
    public static final Block MOSSY_COBBLED_DEEPSLATE_STAIRS = registerBlock("mossy_cobbled_deepslate_stairs",
            new StairsBlock(MOSSY_COBBLED_DEEPSLATE.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_COBBLED_DEEPSLATE)));
    public static final Block MOSSY_COBBLED_DEEPSLATE_WALL = registerBlock("mossy_cobbled_deepslate_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_DEEPSLATE)));

    public static final Block MOSSY_POLISHED_DEEPSLATE = registerBlock("mossy_polished_deepslate",
            new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DEEPSLATE)));
    public static final Block MOSSY_POLISHED_DEEPSLATE_SLAB = registerBlock("mossy_polished_deepslate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_DEEPSLATE)));
    public static final Block MOSSY_POLISHED_DEEPSLATE_STAIRS = registerBlock("mossy_polished_deepslate_stairs",
            new StairsBlock(MOSSY_POLISHED_DEEPSLATE.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_POLISHED_DEEPSLATE)));
    public static final Block MOSSY_POLISHED_DEEPSLATE_WALL = registerBlock("mossy_polished_deepslate_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_DEEPSLATE)));

    public static final Block CRACKED_POLISHED_DEEPSLATE = registerBlock("cracked_polished_deepslate",
            new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DEEPSLATE)));
    public static final Block CRACKED_POLISHED_DEEPSLATE_SLAB = registerBlock("cracked_polished_deepslate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_DEEPSLATE)));
    public static final Block CRACKED_POLISHED_DEEPSLATE_STAIRS = registerBlock("cracked_polished_deepslate_stairs",
            new StairsBlock(CRACKED_POLISHED_DEEPSLATE.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_POLISHED_DEEPSLATE)));
    public static final Block CRACKED_POLISHED_DEEPSLATE_WALL = registerBlock("cracked_polished_deepslate_wall",
            new WallBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_DEEPSLATE)));


    public static final Block DEEPSLATE_BUTTON = registerBlock("deepslate_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE), BlockSetType.STONE, 20, false));
    public static final Block DEEPSLATE_PRESSURE_PLATE = registerBlock("deepslate_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS,
                    FabricBlockSettings.copyOf(Blocks.DEEPSLATE), BlockSetType.STONE));

    public static final Block DEEPSLATE_TRAPDOOR = registerBlock("deepslate_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE), BlockSetType.STONE));


    public static final Block POLISHED_TUFF = registerBlock("polished_tuff",
            new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));

    public static final Block TUFF_BRICKS = registerBlock("tuff_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));

    public static final Block POLISHED_TUFF_WALL = registerBlock("polished_tuff_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));

    public static final Block TUFF_BRICK_STAIRS = registerBlock("tuff_brick_stairs",
            new StairsBlock(ModBlocks.TUFF_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));

    public static final Block CHISELED_TUFF = registerBlock("chiseled_tuff",
            new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));

    public static final Block TUFF_BRICK_SLAB = registerBlock("tuff_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));

    public static final Block CHISELED_TUFF_BRICKS = registerBlock("chiseled_tuff_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));


    public static final Block POLISHED_TUFF_STAIRS = registerBlock("polished_tuff_stairs",
            new StairsBlock(ModBlocks.POLISHED_TUFF.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));


    public static final Block TUFF_BRICK_WALL = registerBlock("tuff_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));

    public static final Block POLISHED_TUFF_SLAB = registerBlock("polished_tuff_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));




    public static final Block TIN_BLOCK = registerBlock("tin_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block RAW_TIN_BLOCK = registerBlock("raw_tin_block",
            new Block(FabricBlockSettings.copyOf(Blocks.RAW_IRON_BLOCK)));
    public static final Block RAW_SILVER_BLOCK = registerBlock("raw_silver_block",
            new Block(FabricBlockSettings.copyOf(Blocks.RAW_IRON_BLOCK)));
    public static final Block SILVER_BLOCK = registerBlock("silver_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block KHAZAD_STEEL_BLOCK = registerBlock("khazad_steel_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block EDHEL_STEEL_BLOCK = registerBlock("edhel_steel_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block BURZUM_STEEL_BLOCK = registerBlock("burzum_steel_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block CRUDE_BLOCK = registerBlock("crude_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block LEAD_BLOCK = registerBlock("lead_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block MITHRIL_BLOCK = registerBlock("mithril_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block RAW_LEAD_BLOCK = registerBlock("raw_lead_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block RAW_MITHRIL_BLOCK = registerBlock("raw_mithril_block",
            new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)));

    public static final Block CUT_LEAD = registerBlock("cut_lead",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block CUT_SILVER = registerBlock("cut_silver",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block TIN_ORE = registerBlock("tin_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f), UniformIntProvider.create(2, 5)));
    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4f), UniformIntProvider.create(2, 5)));
    public static final Block SLATE_TIN_ORE = registerBlock("slate_tin_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE).strength(2f), UniformIntProvider.create(2, 5)));
    public static final Block SLATE_COPPER_ORE = registerBlock("slate_copper_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE).strength(2f), UniformIntProvider.create(2, 5)));
    public static final Block SLATE_COAL_ORE = registerBlock("slate_coal_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE).strength(2f), UniformIntProvider.create(1, 1)));

    public static final Block DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4f), UniformIntProvider.create(2, 4)));
    public static final Block DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4f), UniformIntProvider.create(1, 3)));


    public static final Block SLATE_STAIRS = registerBlock("slate_stairs",
            new StairsBlock(ModBlocks.SLATE.getDefaultState(),FabricBlockSettings.copyOf(ModBlocks.SLATE)));
    public static final Block SLATE_SLAB = registerBlock("slate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE)));

    public static final Block SLATE_BUTTON = registerBlock("slate_button",
            new ButtonBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE), BlockSetType.STONE, 20, false));
    public static final Block SLATE_PRESSURE_PLATE = registerBlock("slate_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS,
                    FabricBlockSettings.copyOf(ModBlocks.SLATE), BlockSetType.STONE));

    public static final Block SLATE_WALL = registerBlock("slate_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE)));

    public static final Block SLATE_TRAPDOOR = registerBlock("slate_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE), BlockSetType.STONE));

    public static final Block POLISHED_SLATE_STAIRS = registerBlock("polished_slate_stairs",
            new StairsBlock(ModBlocks.SLATE.getDefaultState(),FabricBlockSettings.copyOf(ModBlocks.SLATE)));
    public static final Block POLISHED_SLATE_SLAB = registerBlock("polished_slate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE)));
    public static final Block POLISHED_SLATE_WALL = registerBlock("polished_slate_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE)));

    public static final Block SLATE_BRICKS_STAIRS = registerBlock("slate_bricks_stairs",
            new StairsBlock(ModBlocks.SLATE.getDefaultState(),FabricBlockSettings.copyOf(ModBlocks.SLATE)));
    public static final Block SLATE_BRICKS_SLAB = registerBlock("slate_bricks_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE)));
    public static final Block SLATE_BRICKS_WALL = registerBlock("slate_bricks_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE)));

    public static final Block COBBLED_SLATE_STAIRS = registerBlock("cobbled_slate_stairs",
            new StairsBlock(ModBlocks.SLATE.getDefaultState(),FabricBlockSettings.copyOf(ModBlocks.SLATE)));
    public static final Block COBBLED_SLATE_SLAB = registerBlock("cobbled_slate_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE)));
    public static final Block COBBLED_SLATE_WALL = registerBlock("cobbled_slate_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.SLATE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(EwMedieval.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(EwMedieval.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        EwMedieval.LOGGER.info("Registering ModBlocks for" +  EwMedieval.MOD_ID);
    }
}
