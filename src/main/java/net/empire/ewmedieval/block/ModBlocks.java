package net.empire.ewmedieval.block;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.sound.ModBlockSoundGroups;
import net.empire.ewmedieval.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    // Stone Pillars
    public static final Block STONE_PILLAR = registerBlock("stone_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block MOSSY_STONE_PILLAR = registerBlock("mossy_stone_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(STONE_PILLAR)));
    public static final Block CRACKED_STONE_PILLAR = registerBlock("cracked_stone_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(STONE_PILLAR)));

    // Chiseled & Smooth Stone
    public static final Block CHISELED_SMOOTH_STONE = registerBlock("chiseled_smooth_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    // Polished Stone set
    public static final Block POLISHED_STONE = registerBlock("polished_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
    public static final Block POLISHED_STONE_SLAB = registerBlock("polished_stone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(POLISHED_STONE)));
    public static final Block POLISHED_STONE_STAIRS = registerBlock("polished_stone_stairs",
            new StairsBlock(POLISHED_STONE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_STONE)));
    public static final Block POLISHED_STONE_WALL = registerBlock("polished_stone_wall",
            new WallBlock(FabricBlockSettings.copyOf(POLISHED_STONE)));

    // Mossy Polished Stone set
    public static final Block MOSSY_POLISHED_STONE = registerBlock("mossy_polished_stone",
            new Block(FabricBlockSettings.copyOf(POLISHED_STONE)));
    public static final Block MOSSY_POLISHED_STONE_SLAB = registerBlock("mossy_polished_stone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_STONE)));
    public static final Block MOSSY_POLISHED_STONE_STAIRS = registerBlock("mossy_polished_stone_stairs",
            new StairsBlock(MOSSY_POLISHED_STONE.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_POLISHED_STONE)));
    public static final Block MOSSY_POLISHED_STONE_WALL = registerBlock("mossy_polished_stone_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_STONE)));

    // Cracked Polished Stone set
    public static final Block CRACKED_POLISHED_STONE = registerBlock("cracked_polished_stone",
            new Block(FabricBlockSettings.copyOf(POLISHED_STONE)));
    public static final Block CRACKED_POLISHED_STONE_SLAB = registerBlock("cracked_polished_stone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_STONE)));
    public static final Block CRACKED_POLISHED_STONE_STAIRS = registerBlock("cracked_polished_stone_stairs",
            new StairsBlock(CRACKED_POLISHED_STONE.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_POLISHED_STONE)));
    public static final Block CRACKED_POLISHED_STONE_WALL = registerBlock("cracked_polished_stone_wall",
            new WallBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_STONE)));

    // Stone Tiles set
    public static final Block STONE_TILES = registerBlock("stone_tiles",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block STONE_TILES_SLAB = registerBlock("stone_tiles_slab",
            new SlabBlock(FabricBlockSettings.copyOf(STONE_TILES)));
    public static final Block STONE_TILES_STAIRS = registerBlock("stone_tiles_stairs",
            new StairsBlock(STONE_TILES.getDefaultState(), FabricBlockSettings.copyOf(STONE_TILES)));
    public static final Block STONE_TILES_WALL = registerBlock("stone_tiles_wall",
            new WallBlock(FabricBlockSettings.copyOf(STONE_TILES)));

    // Mossy Stone Tiles set
    public static final Block MOSSY_STONE_TILES = registerBlock("mossy_stone_tiles",
            new Block(FabricBlockSettings.copyOf(STONE_TILES)));
    public static final Block MOSSY_STONE_TILES_SLAB = registerBlock("mossy_stone_tiles_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_STONE_TILES)));
    public static final Block MOSSY_STONE_TILES_STAIRS = registerBlock("mossy_stone_tiles_stairs",
            new StairsBlock(MOSSY_STONE_TILES.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_STONE_TILES)));
    public static final Block MOSSY_STONE_TILES_WALL = registerBlock("mossy_stone_tiles_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_STONE_TILES)));

    // Cracked Stone Tiles set
    public static final Block CRACKED_STONE_TILES = registerBlock("cracked_stone_tiles",
            new Block(FabricBlockSettings.copyOf(STONE_TILES)));
    public static final Block CRACKED_STONE_TILES_SLAB = registerBlock("cracked_stone_tiles_slab",
            new SlabBlock(FabricBlockSettings.copyOf(CRACKED_STONE_TILES)));
    public static final Block CRACKED_STONE_TILES_STAIRS = registerBlock("cracked_stone_tiles_stairs",
            new StairsBlock(CRACKED_STONE_TILES.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_STONE_TILES)));
    public static final Block CRACKED_STONE_TILES_WALL = registerBlock("cracked_stone_tiles_wall",
            new WallBlock(FabricBlockSettings.copyOf(CRACKED_STONE_TILES)));

    // Mossy Smooth Stone set
    public static final Block MOSSY_SMOOTH_STONE = registerBlock("mossy_smooth_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block MOSSY_SMOOTH_STONE_SLAB = registerBlock("mossy_smooth_stone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_SMOOTH_STONE)));
    public static final Block MOSSY_SMOOTH_STONE_STAIRS = registerBlock("mossy_smooth_stone_stairs",
            new StairsBlock(MOSSY_SMOOTH_STONE.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_SMOOTH_STONE)));
    public static final Block MOSSY_SMOOTH_STONE_WALL = registerBlock("mossy_smooth_stone_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_SMOOTH_STONE)));

    // Cracked Smooth Stone set
    public static final Block CRACKED_SMOOTH_STONE = registerBlock("cracked_smooth_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block CRACKED_SMOOTH_STONE_SLAB = registerBlock("cracked_smooth_stone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(CRACKED_SMOOTH_STONE)));
    public static final Block CRACKED_SMOOTH_STONE_STAIRS = registerBlock("cracked_smooth_stone_stairs",
            new StairsBlock(CRACKED_SMOOTH_STONE.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_SMOOTH_STONE)));
    public static final Block CRACKED_SMOOTH_STONE_WALL = registerBlock("cracked_smooth_stone_wall",
            new WallBlock(FabricBlockSettings.copyOf(CRACKED_SMOOTH_STONE)));

    // Old Stone set
    public static final Block OLD_STONE = registerBlock("old_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block OLD_STONE_SLAB = registerBlock("old_stone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(OLD_STONE)));
    public static final Block OLD_STONE_STAIRS = registerBlock("old_stone_stairs",
            new StairsBlock(OLD_STONE.getDefaultState(), FabricBlockSettings.copyOf(OLD_STONE)));
    public static final Block OLD_STONE_WALL = registerBlock("old_stone_wall",
            new WallBlock(FabricBlockSettings.copyOf(OLD_STONE)));

    // Stone Brickwork set
    public static final Block STONE_BRICKWORK = registerBlock("stone_brickwork",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));
    public static final Block STONE_BRICKWORK_SLAB = registerBlock("stone_brickwork_slab",
            new SlabBlock(FabricBlockSettings.copyOf(STONE_BRICKWORK)));
    public static final Block STONE_BRICKWORK_STAIRS = registerBlock("stone_brickwork_stairs",
            new StairsBlock(STONE_BRICKWORK.getDefaultState(), FabricBlockSettings.copyOf(STONE_BRICKWORK)));
    public static final Block STONE_BRICKWORK_WALL = registerBlock("stone_brickwork_wall",
            new WallBlock(FabricBlockSettings.copyOf(STONE_BRICKWORK)));

    // Chiseled Stone variants
    public static final Block CHISELED_STONE = registerBlock("chiseled_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block CHISELED_POLISHED_STONE = registerBlock("chiseled_polished_stone",
            new Block(FabricBlockSettings.copyOf(POLISHED_STONE)));
    public static final Block CHISELED_STONE_TILES = registerBlock("chiseled_stone_tiles",
            new Block(FabricBlockSettings.copyOf(STONE_TILES)));



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
            new Block(FabricBlockSettings.copyOf(Blocks.TUFF).sounds(ModBlockSoundGroups.POLISHED_TUFF).strength(1.5f, 6f)));
    public static final Block TUFF_BRICKS = registerBlock("tuff_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.TUFF).sounds(ModBlockSoundGroups.TUFF_BRICKS).strength(1.5f,6f)));

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

    public static final Block TUFF_TRAPDOOR = registerBlock("tuff_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.TUFF), BlockSetType.STONE));

    public static final Block TUFF_SLAB = registerBlock("tuff_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block TUFF_STAIRS = registerBlock("tuff_stairs",
            new StairsBlock(Blocks.TUFF.getDefaultState(), FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block TUFF_WALL = registerBlock("tuff_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.TUFF)));

    public static final Block TUFF_CARVED_WINDOW = registerBlock("tuff_carved_window",
            new GlassBlock(FabricBlockSettings.copyOf(Blocks.GLASS)));
    public static final Block TUFF_CARVED_WINDOW_PANE = registerBlock("tuff_carved_window_pane",
            new PaneBlock(FabricBlockSettings.copyOf(Blocks.GLASS_PANE)));


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
