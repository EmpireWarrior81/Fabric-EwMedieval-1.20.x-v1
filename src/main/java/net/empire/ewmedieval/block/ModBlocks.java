package net.empire.ewmedieval.block;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.block.custom.*;
import net.empire.ewmedieval.block.custom.cropblocks.*;
import net.empire.ewmedieval.block.custom.feastblocks.*;
import net.empire.ewmedieval.block.custom.VerticalSlabs.VerticalSlabBlock;
import net.empire.ewmedieval.block.custom.earlyforge.EarlyForgeBlock;
import net.empire.ewmedieval.block.custom.feastblocks.ExoticRollMedleyBlock;
import net.empire.ewmedieval.block.custom.forge.ForgeBlock;
import net.empire.ewmedieval.item.ModItems;
import net.empire.ewmedieval.sound.ModBlockSoundGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.BlockView;

public class ModBlocks {




    // Stone Pillars
    public static final Block STONE_PILLAR = registerBlock("stone_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).strength(1.5f, 6.0f).sounds(BlockSoundGroup.STONE)));
    public static final Block MOSSY_STONE_PILLAR = registerBlock("mossy_stone_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).strength(1.5f, 6.0f).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_STONE_PILLAR = registerBlock("cracked_stone_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).strength(1.5f, 6.0f).sounds(BlockSoundGroup.STONE)));

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

    public static final Block COBBLED_BLUE_TUFF = registerBlock("cobbled_blue_tuff",
            new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block COBBLED_BLUE_TUFF_SLAB = registerBlock("cobbled_blue_tuff_slab",
            new SlabBlock(FabricBlockSettings.copyOf(COBBLED_BLUE_TUFF)));
    public static final Block COBBLED_BLUE_TUFF_STAIRS = registerBlock("cobbled_blue_tuff_stairs",
            new StairsBlock(COBBLED_BLUE_TUFF.getDefaultState(), FabricBlockSettings.copyOf(COBBLED_BLUE_TUFF)));
    public static final Block COBBLED_BLUE_TUFF_WALL = registerBlock("cobbled_blue_tuff_wall",
            new WallBlock(FabricBlockSettings.copyOf(COBBLED_BLUE_TUFF)));
    public static final Block MOSSY_COBBLED_BLUE_TUFF = registerBlock("mossy_cobbled_blue_tuff",
            new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block MOSSY_COBBLED_BLUE_TUFF_SLAB = registerBlock("mossy_cobbled_blue_tuff_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_BLUE_TUFF)));
    public static final Block MOSSY_COBBLED_BLUE_TUFF_STAIRS = registerBlock("mossy_cobbled_blue_tuff_stairs",
            new StairsBlock(MOSSY_COBBLED_BLUE_TUFF.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_COBBLED_BLUE_TUFF)));
    public static final Block MOSSY_COBBLED_BLUE_TUFF_WALL = registerBlock("mossy_cobbled_blue_tuff_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_BLUE_TUFF)));
    public static final Block BLUE_TUFF = registerBlock("blue_tuff",
            new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block BLUE_TUFF_SLAB = registerBlock("blue_tuff_slab",
            new SlabBlock(FabricBlockSettings.copyOf(BLUE_TUFF)));
    public static final Block BLUE_TUFF_STAIRS = registerBlock("blue_tuff_stairs",
            new StairsBlock(BLUE_TUFF.getDefaultState(), FabricBlockSettings.copyOf(BLUE_TUFF)));
    public static final Block BLUE_TUFF_WALL = registerBlock("blue_tuff_wall",
            new WallBlock(FabricBlockSettings.copyOf(BLUE_TUFF)));
    public static final Block BLUE_TUFF_PILLAR = registerBlock("blue_tuff_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(BLUE_TUFF)));
    public static final Block MOSSY_BLUE_TUFF_PILLAR = registerBlock("mossy_blue_tuff_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(BLUE_TUFF)));
    public static final Block CRACKED_BLUE_TUFF_PILLAR = registerBlock("cracked_blue_tuff_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(BLUE_TUFF)));
    public static final Block BLUE_TUFF_BRICKS = registerBlock("blue_tuff_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block BLUE_TUFF_BRICKS_SLAB = registerBlock("blue_tuff_bricks_slab",
            new SlabBlock(FabricBlockSettings.copyOf(BLUE_TUFF_BRICKS)));
    public static final Block BLUE_TUFF_BRICKS_STAIRS = registerBlock("blue_tuff_bricks_stairs",
            new StairsBlock(BLUE_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(BLUE_TUFF_BRICKS)));
    public static final Block BLUE_TUFF_BRICKS_WALL = registerBlock("blue_tuff_bricks_wall",
            new WallBlock(FabricBlockSettings.copyOf(BLUE_TUFF_BRICKS)));
    public static final Block MOSSY_BLUE_TUFF_BRICKS = registerBlock("mossy_blue_tuff_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block MOSSY_BLUE_TUFF_BRICKS_SLAB = registerBlock("mossy_blue_tuff_bricks_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_BLUE_TUFF_BRICKS)));
    public static final Block MOSSY_BLUE_TUFF_BRICKS_STAIRS = registerBlock("mossy_blue_tuff_bricks_stairs",
            new StairsBlock(MOSSY_BLUE_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_BLUE_TUFF_BRICKS)));
    public static final Block MOSSY_BLUE_TUFF_BRICKS_WALL = registerBlock("mossy_blue_tuff_bricks_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_BLUE_TUFF_BRICKS)));
    public static final Block CRACKED_BLUE_TUFF_BRICKS = registerBlock("cracked_blue_tuff_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block CRACKED_BLUE_TUFF_BRICKS_SLAB = registerBlock("cracked_blue_tuff_bricks_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_BLUE_TUFF_BRICKS)));
    public static final Block CRACKED_BLUE_TUFF_BRICKS_STAIRS = registerBlock("cracked_blue_tuff_bricks_stairs", new StairsBlock(CRACKED_BLUE_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_BLUE_TUFF_BRICKS)));
    public static final Block CRACKED_BLUE_TUFF_BRICKS_WALL = registerBlock("cracked_blue_tuff_bricks_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_BLUE_TUFF_BRICKS)));
    public static final Block BLUE_TUFF_TILES = registerBlock("blue_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block BLUE_TUFF_TILES_SLAB = registerBlock("blue_tuff_tiles_slab", new SlabBlock(FabricBlockSettings.copyOf(BLUE_TUFF_TILES)));
    public static final Block BLUE_TUFF_TILES_STAIRS = registerBlock("blue_tuff_tiles_stairs", new StairsBlock(BLUE_TUFF_TILES.getDefaultState(), FabricBlockSettings.copyOf(BLUE_TUFF_TILES)));
    public static final Block BLUE_TUFF_TILES_WALL = registerBlock("blue_tuff_tiles_wall", new WallBlock(FabricBlockSettings.copyOf(BLUE_TUFF_TILES)));
    public static final Block MOSSY_BLUE_TUFF_TILES = registerBlock("mossy_blue_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block MOSSY_BLUE_TUFF_TILES_SLAB = registerBlock("mossy_blue_tuff_tiles_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_BLUE_TUFF_TILES)));
    public static final Block MOSSY_BLUE_TUFF_TILES_STAIRS = registerBlock("mossy_blue_tuff_tiles_stairs", new StairsBlock(MOSSY_BLUE_TUFF_TILES.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_BLUE_TUFF_TILES)));
    public static final Block MOSSY_BLUE_TUFF_TILES_WALL = registerBlock("mossy_blue_tuff_tiles_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_BLUE_TUFF_TILES)));
    public static final Block CRACKED_BLUE_TUFF_TILES = registerBlock("cracked_blue_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block CRACKED_BLUE_TUFF_TILES_SLAB = registerBlock("cracked_blue_tuff_tiles_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_BLUE_TUFF_TILES)));
    public static final Block CRACKED_BLUE_TUFF_TILES_STAIRS = registerBlock("cracked_blue_tuff_tiles_stairs", new StairsBlock(CRACKED_BLUE_TUFF_TILES.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_BLUE_TUFF_TILES)));
    public static final Block CRACKED_BLUE_TUFF_TILES_WALL = registerBlock("cracked_blue_tuff_tiles_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_BLUE_TUFF_TILES)));
    public static final Block POLISHED_BLUE_TUFF = registerBlock("polished_blue_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block POLISHED_BLUE_TUFF_SLAB = registerBlock("polished_blue_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_BLUE_TUFF)));
    public static final Block POLISHED_BLUE_TUFF_STAIRS = registerBlock("polished_blue_tuff_stairs", new StairsBlock(POLISHED_BLUE_TUFF.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_BLUE_TUFF)));
    public static final Block POLISHED_BLUE_TUFF_WALL = registerBlock("polished_blue_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_BLUE_TUFF)));
    public static final Block MOSSY_POLISHED_BLUE_TUFF = registerBlock("mossy_polished_blue_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block MOSSY_POLISHED_BLUE_TUFF_SLAB = registerBlock("mossy_polished_blue_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_BLUE_TUFF)));
    public static final Block MOSSY_POLISHED_BLUE_TUFF_STAIRS = registerBlock("mossy_polished_blue_tuff_stairs", new StairsBlock(MOSSY_POLISHED_BLUE_TUFF.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_POLISHED_BLUE_TUFF)));
    public static final Block MOSSY_POLISHED_BLUE_TUFF_WALL = registerBlock("mossy_polished_blue_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_BLUE_TUFF)));
    public static final Block CRACKED_POLISHED_BLUE_TUFF = registerBlock("cracked_polished_blue_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block CRACKED_POLISHED_BLUE_TUFF_SLAB = registerBlock("cracked_polished_blue_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_BLUE_TUFF)));
    public static final Block CRACKED_POLISHED_BLUE_TUFF_STAIRS = registerBlock("cracked_polished_blue_tuff_stairs", new StairsBlock(CRACKED_POLISHED_BLUE_TUFF.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_POLISHED_BLUE_TUFF)));
    public static final Block CRACKED_POLISHED_BLUE_TUFF_WALL = registerBlock("cracked_polished_blue_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_BLUE_TUFF)));
    public static final Block CHISELED_POLISHED_BLUE_TUFF = registerBlock("chiseled_polished_blue_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block CHISELED_BLUE_TUFF_BRICKS = registerBlock("chiseled_blue_tuff_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block BLUE_TUFF_PRESSURE_PLATE = registerBlock("blue_tuff_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copyOf(BLUE_TUFF), BlockSetType.STONE));
    public static final Block BLUE_TUFF_BUTTON = registerBlock("blue_tuff_button", new ButtonBlock(FabricBlockSettings.copyOf(BLUE_TUFF), BlockSetType.STONE, 20, false));
    public static final Block BLUE_TUFF_TRAPDOOR = registerBlock("blue_tuff_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(BLUE_TUFF), BlockSetType.STONE));
    public static final Block COBBLED_GREEN_TUFF = registerBlock("cobbled_green_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block COBBLED_GREEN_TUFF_SLAB = registerBlock("cobbled_green_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLED_GREEN_TUFF)));


    public static final Block COBBLED_GREEN_TUFF_STAIRS = registerBlock("cobbled_green_tuff_stairs", new StairsBlock(COBBLED_GREEN_TUFF.getDefaultState(), FabricBlockSettings.copyOf(COBBLED_GREEN_TUFF)));
    public static final Block COBBLED_GREEN_TUFF_WALL = registerBlock("cobbled_green_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLED_GREEN_TUFF)));
    public static final Block SMOOTH_GREEN_TUFF = registerBlock("smooth_green_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block SMOOTH_GREEN_TUFF_SLAB = registerBlock("smooth_green_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(SMOOTH_GREEN_TUFF)));
    public static final Block SMOOTH_GREEN_TUFF_STAIRS = registerBlock("smooth_green_tuff_stairs", new StairsBlock(SMOOTH_GREEN_TUFF.getDefaultState(), FabricBlockSettings.copyOf(SMOOTH_GREEN_TUFF)));
    public static final Block SMOOTH_GREEN_TUFF_WALL = registerBlock("smooth_green_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(SMOOTH_GREEN_TUFF)));
    public static final Block CRACKED_SMOOTH_GREEN_TUFF = registerBlock("cracked_smooth_green_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block CRACKED_SMOOTH_GREEN_TUFF_SLAB = registerBlock("cracked_smooth_green_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_SMOOTH_GREEN_TUFF)));
    public static final Block CRACKED_SMOOTH_GREEN_TUFF_STAIRS = registerBlock("cracked_smooth_green_tuff_stairs", new StairsBlock(CRACKED_SMOOTH_GREEN_TUFF.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_SMOOTH_GREEN_TUFF)));
    public static final Block CRACKED_SMOOTH_GREEN_TUFF_WALL = registerBlock("cracked_smooth_green_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_SMOOTH_GREEN_TUFF)));
    public static final Block MOSSY_COBBLED_GREEN_TUFF = registerBlock("mossy_cobbled_green_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block MOSSY_COBBLED_GREEN_TUFF_SLAB = registerBlock("mossy_cobbled_green_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_GREEN_TUFF)));
    public static final Block MOSSY_COBBLED_GREEN_TUFF_STAIRS = registerBlock("mossy_cobbled_green_tuff_stairs", new StairsBlock(MOSSY_COBBLED_GREEN_TUFF.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_COBBLED_GREEN_TUFF)));
    public static final Block MOSSY_COBBLED_GREEN_TUFF_WALL = registerBlock("mossy_cobbled_green_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_GREEN_TUFF)));
    public static final Block GREEN_TUFF = registerBlock("green_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block GREEN_TUFF_SLAB = registerBlock("green_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(GREEN_TUFF)));
    public static final Block GREEN_TUFF_STAIRS = registerBlock("green_tuff_stairs", new StairsBlock(GREEN_TUFF.getDefaultState(), FabricBlockSettings.copyOf(GREEN_TUFF)));
    public static final Block GREEN_TUFF_WALL = registerBlock("green_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(GREEN_TUFF)));
    public static final Block GREEN_TUFF_PILLAR = registerBlock("green_tuff_pillar", new PillarBlock(FabricBlockSettings.copyOf(GREEN_TUFF)));
    public static final Block MOSSY_GREEN_TUFF_PILLAR = registerBlock("mossy_green_tuff_pillar", new PillarBlock(FabricBlockSettings.copyOf(GREEN_TUFF)));
    public static final Block CRACKED_GREEN_TUFF_PILLAR = registerBlock("cracked_green_tuff_pillar", new PillarBlock(FabricBlockSettings.copyOf(GREEN_TUFF)));
    public static final Block GREEN_TUFF_BRICKS = registerBlock("green_tuff_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block GREEN_TUFF_BRICKS_SLAB = registerBlock("green_tuff_bricks_slab", new SlabBlock(FabricBlockSettings.copyOf(GREEN_TUFF_BRICKS)));
    public static final Block GREEN_TUFF_BRICKS_STAIRS = registerBlock("green_tuff_bricks_stairs", new StairsBlock(GREEN_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(GREEN_TUFF_BRICKS)));
    public static final Block GREEN_TUFF_BRICKS_WALL = registerBlock("green_tuff_bricks_wall", new WallBlock(FabricBlockSettings.copyOf(GREEN_TUFF_BRICKS)));
    public static final Block MOSSY_GREEN_TUFF_BRICKS = registerBlock("mossy_green_tuff_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block MOSSY_GREEN_TUFF_BRICKS_SLAB = registerBlock("mossy_green_tuff_bricks_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_GREEN_TUFF_BRICKS)));
    public static final Block MOSSY_GREEN_TUFF_BRICKS_STAIRS = registerBlock("mossy_green_tuff_bricks_stairs", new StairsBlock(MOSSY_GREEN_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_GREEN_TUFF_BRICKS)));
    public static final Block MOSSY_GREEN_TUFF_BRICKS_WALL = registerBlock("mossy_green_tuff_bricks_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_GREEN_TUFF_BRICKS)));
    public static final Block CRACKED_GREEN_TUFF_BRICKS = registerBlock("cracked_green_tuff_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block CRACKED_GREEN_TUFF_BRICKS_SLAB = registerBlock("cracked_green_tuff_bricks_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_GREEN_TUFF_BRICKS)));
    public static final Block CRACKED_GREEN_TUFF_BRICKS_STAIRS = registerBlock("cracked_green_tuff_bricks_stairs", new StairsBlock(CRACKED_GREEN_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_GREEN_TUFF_BRICKS)));
    public static final Block CRACKED_GREEN_TUFF_BRICKS_WALL = registerBlock("cracked_green_tuff_bricks_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_GREEN_TUFF_BRICKS)));
    public static final Block GREEN_TUFF_TILES = registerBlock("green_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block GREEN_TUFF_TILES_SLAB = registerBlock("green_tuff_tiles_slab", new SlabBlock(FabricBlockSettings.copyOf(GREEN_TUFF_TILES)));
    public static final Block GREEN_TUFF_TILES_STAIRS = registerBlock("green_tuff_tiles_stairs", new StairsBlock(GREEN_TUFF_TILES.getDefaultState(), FabricBlockSettings.copyOf(GREEN_TUFF_TILES)));
    public static final Block GREEN_TUFF_TILES_WALL = registerBlock("green_tuff_tiles_wall", new WallBlock(FabricBlockSettings.copyOf(GREEN_TUFF_TILES)));
    public static final Block MOSSY_GREEN_TUFF_TILES = registerBlock("mossy_green_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block MOSSY_GREEN_TUFF_TILES_SLAB = registerBlock("mossy_green_tuff_tiles_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_GREEN_TUFF_TILES)));
    public static final Block MOSSY_GREEN_TUFF_TILES_STAIRS = registerBlock("mossy_green_tuff_tiles_stairs", new StairsBlock(MOSSY_GREEN_TUFF_TILES.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_GREEN_TUFF_TILES)));
    public static final Block MOSSY_GREEN_TUFF_TILES_WALL = registerBlock("mossy_green_tuff_tiles_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_GREEN_TUFF_TILES)));
    public static final Block CRACKED_GREEN_TUFF_TILES = registerBlock("cracked_green_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block CRACKED_GREEN_TUFF_TILES_SLAB = registerBlock("cracked_green_tuff_tiles_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_GREEN_TUFF_TILES)));
    public static final Block CRACKED_GREEN_TUFF_TILES_STAIRS = registerBlock("cracked_green_tuff_tiles_stairs", new StairsBlock(CRACKED_GREEN_TUFF_TILES.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_GREEN_TUFF_TILES)));
    public static final Block CRACKED_GREEN_TUFF_TILES_WALL = registerBlock("cracked_green_tuff_tiles_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_GREEN_TUFF_TILES)));
    public static final Block POLISHED_GREEN_TUFF = registerBlock("polished_green_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block POLISHED_GREEN_TUFF_SLAB = registerBlock("polished_green_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_GREEN_TUFF)));
    public static final Block POLISHED_GREEN_TUFF_STAIRS = registerBlock("polished_green_tuff_stairs", new StairsBlock(POLISHED_GREEN_TUFF.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_GREEN_TUFF)));
    public static final Block POLISHED_GREEN_TUFF_WALL = registerBlock("polished_green_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_GREEN_TUFF)));
    public static final Block MOSSY_POLISHED_GREEN_TUFF = registerBlock("mossy_polished_green_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block MOSSY_POLISHED_GREEN_TUFF_SLAB = registerBlock("mossy_polished_green_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_GREEN_TUFF)));
    public static final Block MOSSY_POLISHED_GREEN_TUFF_STAIRS = registerBlock("mossy_polished_green_tuff_stairs", new StairsBlock(MOSSY_POLISHED_GREEN_TUFF.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_POLISHED_GREEN_TUFF)));
    public static final Block MOSSY_POLISHED_GREEN_TUFF_WALL = registerBlock("mossy_polished_green_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_GREEN_TUFF)));
    public static final Block CRACKED_POLISHED_GREEN_TUFF = registerBlock("cracked_polished_green_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block CRACKED_POLISHED_GREEN_TUFF_SLAB = registerBlock("cracked_polished_green_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_GREEN_TUFF)));
    public static final Block CRACKED_POLISHED_GREEN_TUFF_STAIRS = registerBlock("cracked_polished_green_tuff_stairs", new StairsBlock(CRACKED_POLISHED_GREEN_TUFF.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_POLISHED_GREEN_TUFF)));
    public static final Block CRACKED_POLISHED_GREEN_TUFF_WALL = registerBlock("cracked_polished_green_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_GREEN_TUFF)));
    public static final Block CHISELED_POLISHED_GREEN_TUFF = registerBlock("chiseled_polished_green_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block CHISELED_GREEN_TUFF = registerBlock("chiseled_green_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block CHISELED_GREEN_TUFF_BRICKS = registerBlock("chiseled_green_tuff_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block CHISELED_GREEN_TUFF_TILES = registerBlock("chiseled_green_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block CHISELED_SMOOTH_GREEN_TUFF = registerBlock("chiseled_smooth_green_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block GILDED_GREEN_TUFF = registerBlock("gilded_green_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block GILDED_GREEN_TUFF_SLAB = registerBlock("gilded_green_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(GILDED_GREEN_TUFF)));
    public static final Block GILDED_GREEN_TUFF_STAIRS = registerBlock("gilded_green_tuff_stairs", new StairsBlock(GILDED_GREEN_TUFF.getDefaultState(), FabricBlockSettings.copyOf(GILDED_GREEN_TUFF)));
    public static final Block GILDED_GREEN_TUFF_WALL = registerBlock("gilded_green_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(GILDED_GREEN_TUFF)));
    public static final Block GILDED_CHISELED_POLISHED_GREEN_TUFF = registerBlock("gilded_chiseled_polished_green_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block GILDED_CHISELED_GREEN_TUFF = registerBlock("gilded_chiseled_green_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block GILDED_CHISELED_GREEN_TUFF_BRICKS = registerBlock("gilded_chiseled_green_tuff_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block GILDED_CHISELED_GREEN_TUFF_TILES = registerBlock("gilded_chiseled_green_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block GILDED_CHISELED_SMOOTH_GREEN_TUFF = registerBlock("gilded_chiseled_smooth_green_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block GREEN_TUFF_PRESSURE_PLATE = registerBlock("green_tuff_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copyOf(GREEN_TUFF), BlockSetType.STONE));
    public static final Block GREEN_TUFF_BUTTON = registerBlock("green_tuff_button", new ButtonBlock(FabricBlockSettings.copyOf(GREEN_TUFF), BlockSetType.STONE, 20, false));
    public static final Block GREEN_TUFF_TRAPDOOR = registerBlock("green_tuff_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(GREEN_TUFF), BlockSetType.STONE));
    public static final Block GILDED_GREEN_TUFF_PRESSURE_PLATE = registerBlock("gilded_green_tuff_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copyOf(GILDED_GREEN_TUFF), BlockSetType.STONE));
    public static final Block GILDED_GREEN_TUFF_BUTTON = registerBlock("gilded_green_tuff_button", new ButtonBlock(FabricBlockSettings.copyOf(GILDED_GREEN_TUFF), BlockSetType.STONE, 20, false));
    public static final Block GILDED_GREEN_TUFF_TRAPDOOR = registerBlock("gilded_green_tuff_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(GILDED_GREEN_TUFF), BlockSetType.STONE));
    public static final Block OLD_TUFF = registerBlock("old_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block OLD_TUFF_SLAB = registerBlock("old_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(OLD_TUFF)));
    public static final Block OLD_TUFF_STAIRS = registerBlock("old_tuff_stairs", new StairsBlock(OLD_TUFF.getDefaultState(), FabricBlockSettings.copyOf(OLD_TUFF)));
    public static final Block OLD_TUFF_WALL = registerBlock("old_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(OLD_TUFF)));
    public static final Block SMOOTH_TUFF = registerBlock("smooth_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block SMOOTH_TUFF_SLAB = registerBlock("smooth_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(SMOOTH_TUFF)));
    public static final Block SMOOTH_TUFF_STAIRS = registerBlock("smooth_tuff_stairs", new StairsBlock(SMOOTH_TUFF.getDefaultState(), FabricBlockSettings.copyOf(SMOOTH_TUFF)));
    public static final Block SMOOTH_TUFF_WALL = registerBlock("smooth_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(SMOOTH_TUFF)));
    public static final Block MOSSY_SMOOTH_TUFF = registerBlock("mossy_smooth_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block MOSSY_SMOOTH_TUFF_SLAB = registerBlock("mossy_smooth_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_SMOOTH_TUFF)));
    public static final Block MOSSY_SMOOTH_TUFF_STAIRS = registerBlock("mossy_smooth_tuff_stairs", new StairsBlock(MOSSY_SMOOTH_TUFF.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_SMOOTH_TUFF)));
    public static final Block MOSSY_SMOOTH_TUFF_WALL = registerBlock("mossy_smooth_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_SMOOTH_TUFF)));
    public static final Block CRACKED_SMOOTH_TUFF = registerBlock("cracked_smooth_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block CRACKED_SMOOTH_TUFF_SLAB = registerBlock("cracked_smooth_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_SMOOTH_TUFF)));
    public static final Block CRACKED_SMOOTH_TUFF_STAIRS = registerBlock("cracked_smooth_tuff_stairs", new StairsBlock(CRACKED_SMOOTH_TUFF.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_SMOOTH_TUFF)));
    public static final Block CRACKED_SMOOTH_TUFF_WALL = registerBlock("cracked_smooth_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_SMOOTH_TUFF)));
    public static final Block COBBLED_TUFF = registerBlock("cobbled_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block COBBLED_TUFF_SLAB = registerBlock("cobbled_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLED_TUFF)));
    public static final Block COBBLED_TUFF_STAIRS = registerBlock("cobbled_tuff_stairs", new StairsBlock(COBBLED_TUFF.getDefaultState(), FabricBlockSettings.copyOf(COBBLED_TUFF)));
    public static final Block COBBLED_TUFF_WALL = registerBlock("cobbled_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLED_TUFF)));
    public static final Block MOSSY_COBBLED_TUFF = registerBlock("mossy_cobbled_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block MOSSY_COBBLED_TUFF_SLAB = registerBlock("mossy_cobbled_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_TUFF)));
    public static final Block MOSSY_COBBLED_TUFF_STAIRS = registerBlock("mossy_cobbled_tuff_stairs", new StairsBlock(MOSSY_COBBLED_TUFF.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_COBBLED_TUFF)));
    public static final Block MOSSY_COBBLED_TUFF_WALL = registerBlock("mossy_cobbled_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_TUFF)));
    public static final Block TUFF_PILLAR = registerBlock("tuff_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block MOSSY_TUFF_PILLAR = registerBlock("mossy_tuff_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block CRACKED_TUFF_PILLAR = registerBlock("cracked_tuff_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block MOSSY_TUFF_BRICKS = registerBlock("mossy_tuff_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block MOSSY_TUFF_BRICKS_SLAB = registerBlock("mossy_tuff_bricks_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_TUFF_BRICKS)));
    public static final Block MOSSY_TUFF_BRICKS_STAIRS = registerBlock("mossy_tuff_bricks_stairs", new StairsBlock(MOSSY_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_TUFF_BRICKS)));
    public static final Block MOSSY_TUFF_BRICKS_WALL = registerBlock("mossy_tuff_bricks_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_TUFF_BRICKS)));
    public static final Block CRACKED_TUFF_BRICKS = registerBlock("cracked_tuff_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block CRACKED_TUFF_BRICKS_SLAB = registerBlock("cracked_tuff_bricks_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_TUFF_BRICKS)));
    public static final Block CRACKED_TUFF_BRICKS_STAIRS = registerBlock("cracked_tuff_bricks_stairs", new StairsBlock(CRACKED_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_TUFF_BRICKS)));
    public static final Block CRACKED_TUFF_BRICKS_WALL = registerBlock("cracked_tuff_bricks_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_TUFF_BRICKS)));
    public static final Block TUFF_TILES = registerBlock("tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block TUFF_TILES_SLAB = registerBlock("tuff_tiles_slab", new SlabBlock(FabricBlockSettings.copyOf(TUFF_TILES)));
    public static final Block TUFF_TILES_STAIRS = registerBlock("tuff_tiles_stairs", new StairsBlock(TUFF_TILES.getDefaultState(), FabricBlockSettings.copyOf(TUFF_TILES)));
    public static final Block TUFF_TILES_WALL = registerBlock("tuff_tiles_wall", new WallBlock(FabricBlockSettings.copyOf(TUFF_TILES)));
    public static final Block MOSSY_TUFF_TILES = registerBlock("mossy_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block MOSSY_TUFF_TILES_SLAB = registerBlock("mossy_tuff_tiles_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_TUFF_TILES)));
    public static final Block MOSSY_TUFF_TILES_STAIRS = registerBlock("mossy_tuff_tiles_stairs", new StairsBlock(MOSSY_TUFF_TILES.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_TUFF_TILES)));
    public static final Block MOSSY_TUFF_TILES_WALL = registerBlock("mossy_tuff_tiles_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_TUFF_TILES)));
    public static final Block CRACKED_TUFF_TILES = registerBlock("cracked_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block CRACKED_TUFF_TILES_SLAB = registerBlock("cracked_tuff_tiles_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_TUFF_TILES)));
    public static final Block CRACKED_TUFF_TILES_STAIRS = registerBlock("cracked_tuff_tiles_stairs", new StairsBlock(CRACKED_TUFF_TILES.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_TUFF_TILES)));
    public static final Block CRACKED_TUFF_TILES_WALL = registerBlock("cracked_tuff_tiles_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_TUFF_TILES)));
    public static final Block MOSSY_POLISHED_TUFF = registerBlock("mossy_polished_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block MOSSY_POLISHED_TUFF_SLAB = registerBlock("mossy_polished_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_TUFF)));
    public static final Block MOSSY_POLISHED_TUFF_STAIRS = registerBlock("mossy_polished_tuff_stairs", new StairsBlock(MOSSY_POLISHED_TUFF.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_POLISHED_TUFF)));
    public static final Block MOSSY_POLISHED_TUFF_WALL = registerBlock("mossy_polished_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_POLISHED_TUFF)));
    public static final Block CRACKED_POLISHED_TUFF = registerBlock("cracked_polished_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block CRACKED_POLISHED_TUFF_SLAB = registerBlock("cracked_polished_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_TUFF)));
    public static final Block CRACKED_POLISHED_TUFF_STAIRS = registerBlock("cracked_polished_tuff_stairs", new StairsBlock(CRACKED_POLISHED_TUFF.getDefaultState(), FabricBlockSettings.copyOf(CRACKED_POLISHED_TUFF)));
    public static final Block CRACKED_POLISHED_TUFF_WALL = registerBlock("cracked_polished_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(CRACKED_POLISHED_TUFF)));
    public static final Block CHISELED_POLISHED_TUFF = registerBlock("chiseled_polished_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block CHISELED_TUFF_TILES = registerBlock("chiseled_tuff_tiles", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block CHISELED_SMOOTH_TUFF = registerBlock("chiseled_smooth_tuff", new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_TUFF)));
    public static final Block TUFF_BRICKWORK = registerBlock("tuff_brickwork", new Block(FabricBlockSettings.copyOf(ModBlocks.TUFF_BRICKS)));
    public static final Block TUFF_BRICKWORK_SLAB = registerBlock("tuff_brickwork_slab", new SlabBlock(FabricBlockSettings.copyOf(TUFF_BRICKWORK)));
    public static final Block TUFF_BRICKWORK_STAIRS = registerBlock("tuff_brickwork_stairs", new StairsBlock(TUFF_BRICKWORK.getDefaultState(), FabricBlockSettings.copyOf(TUFF_BRICKWORK)));
    public static final Block TUFF_BRICKWORK_WALL = registerBlock("tuff_brickwork_wall", new WallBlock(FabricBlockSettings.copyOf(TUFF_BRICKWORK)));


    public static final Block BLUE_TUFF_VERTICAL_SLAB = registerBlock("blue_tuff_vertical_slab",
            new VerticalSlabBlock(FabricBlockSettings.copyOf(ModBlocks.BLUE_TUFF)));


    public static final Block FORGE = registerBlock("forge",
            new ForgeBlock(FabricBlockSettings.copyOf(Blocks.BLAST_FURNACE)));

    public static final Block EARLY_FORGE = registerBlock("earlyforge",
            new EarlyForgeBlock(FabricBlockSettings.copyOf(Blocks.BLAST_FURNACE)));

    public static final Block CUTTING_BOARD = registerBlock("cutting_board",
            new CuttingBoardBlock(FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE)));

    public static final Block STOVE = registerBlock("stove",
            new StoveBlock(FabricBlockSettings.copyOf(Blocks.BRICKS)
                    .luminance(state -> state.get(StoveBlock.LIT) ? 13 : 0)));


    public static final Block STRAW_BALE = registerBlock("straw_bale",
            new StrawBaleBlock(FabricBlockSettings.copyOf(Blocks.HAY_BLOCK)));


    public static final Block STUFFED_PUMPKIN_BLOCK = registerBlock("stuffed_pumpkin_block",
            new FeastBlock(FabricBlockSettings.copyOf(Blocks.PUMPKIN), () -> ModItems.STUFFED_PUMPKIN, false),
                new FabricItemSettings().maxCount(1));

    public static final Block ROAST_CHICKEN_BLOCK = registerBlock("roast_chicken_block",
            new RoastChickenBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModItems.ROAST_CHICKEN, true),
                new FabricItemSettings().maxCount(1));

    public static final Block HONEY_GLAZED_HAM_BLOCK = registerBlock("honey_glazed_ham_block",
            new HoneyGlazedHamBlock(FabricBlockSettings.copyOf(Blocks.CAKE),
                    () -> ModItems.HONEY_GLAZED_HAM, true),
                new FabricItemSettings().maxCount(1));

    public static final Block RICE_ROLL_MEDLEY_BLOCK = registerBlock("rice_roll_medley_block",
            new RiceRollMedleyBlock(FabricBlockSettings.copyOf(Blocks.CAKE)),
                new FabricItemSettings().maxCount(1));

    public static final Block SHEPHERDS_PIE_BLOCK = registerBlock("shepherds_pie_block",
            new ShepherdsPieBlock(FabricBlockSettings.copyOf(Blocks.CAKE),
                    () -> ModItems.SHEPHERDS_PIE, true),
                new FabricItemSettings().maxCount(1));

    public static final Block APPLE_PIE = registerBlock("apple_pie",
            new PieBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModItems.APPLE_PIE_SLICE),
                new FabricItemSettings().maxCount(1));

    public static final Block CHOCOLATE_PIE = registerBlock("chocolate_pie",
            new PieBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModItems.CHOCOLATE_PIE_SLICE),
                new FabricItemSettings().maxCount(1));

    public static final Block SWEET_BERRY_CHEESECAKE = registerBlock("sweet_berry_cheesecake",
            new PieBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModItems.SWEET_BERRY_CHEESECAKE_SLICE),
                new FabricItemSettings().maxCount(1));


    public static final Block EGGPLANT_PARMESAN_BLOCK = registerBlock("eggplant_parmesan_block",
            new EggplantFeastBlock(FabricBlockSettings.copyOf(Blocks.CAKE),
                    () -> ModItems.EGGPLANT_PARMESAN, true),
            new FabricItemSettings().maxCount(1));

    public static final Block EXOTIC_ROLL_MEDLEY = registerBlock("exotic_roll_medley",
            new ExoticRollMedleyBlock(FabricBlockSettings.copyOf(Blocks.CAKE)), new FabricItemSettings().maxCount(1));

    public static final Block QUICHE = registerBlock("quiche",
            new PieBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModItems.QUICHE_SLICE),
            new FabricItemSettings().maxCount(1));

    public static final Block PIZZA = registerBlock("pizza",
            new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModItems.PIZZA_SLICE),
            new FabricItemSettings().maxCount(1));

    public static final Block FIERY_FONDUE_POT = registerBlock("fiery_fondue_pot",
            new FieryFonduePotBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON)), new FabricItemSettings().maxCount(1));

    public static final Block FLAXEN_CHEESE_WHEEL = registerBlock("flaxen_cheese_wheel",
            new CheeseWheelBlock(FabricBlockSettings.copyOf(Blocks.CAKE),
                    () -> ModItems.FLAXEN_CHEESE_WEDGE), new FabricItemSettings().maxCount(1));

    public static final Block SCARLET_CHEESE_WHEEL = registerBlock("scarlet_cheese_wheel",
            new CheeseWheelBlock(FabricBlockSettings.copyOf(Blocks.CAKE),
                    () -> ModItems.SCARLET_CHEESE_WEDGE), new FabricItemSettings().maxCount(1));

        public static final Block DORBLU_CHEESE_WHEEL = registerBlock("dorblu_cheese_wheel",
            new CheeseWheelBlock(FabricBlockSettings.copyOf(Blocks.CAKE),
                    () -> ModItems.DORBLU_CHEESE_WEDGE), new FabricItemSettings().maxCount(1));


    public static final Block UNRIPE_FLAXEN_CHEESE_WHEEL = registerBlock("unripe_flaxen_cheese_wheel",
            new UnripeCheeseWheelBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModBlocks.FLAXEN_CHEESE_WHEEL),
            new FabricItemSettings().maxCount(1));

    public static final Block UNRIPE_SCARLET_CHEESE_WHEEL = registerBlock("unripe_scarlet_cheese_wheel",
            new UnripeCheeseWheelBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModBlocks.SCARLET_CHEESE_WHEEL),
            new FabricItemSettings().maxCount(1));

    public static final Block UNRIPE_DORBLU_CHEESE_WHEEL = registerBlock("unripe_dorblu_cheese_wheel",
            new UnripeCheeseWheelBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModBlocks.DORBLU_CHEESE_WHEEL),
            new FabricItemSettings().maxCount(1));


    public static final Block SYRUP_CHEESECAKE = registerBlock("syrup_cheesecake",
            new PieBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModItems.SYRUP_CHEESECAKE_SLICE),
            new FabricItemSettings().maxCount(1));
    public static final Block CHERRY_BLOSSOM_CHEESECAKE = registerBlock("cherry_blossom_cheesecake",
            new PieBlock(FabricBlockSettings.copyOf(Blocks.CAKE), () -> ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE),
            new FabricItemSettings().maxCount(1));


    public static final Block PANCAKES = registerBlock("pancakes",
            new PancakeBlock(() -> ModItems.PANCAKE,
                    FabricBlockSettings.copyOf(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)),
            new FabricItemSettings().maxCount(1));
    public static final Block HONEY_PANCAKES = registerBlock("honey_pancakes",
            new PancakeBlock(() -> ModItems.HONEY_PANCAKE,
                    FabricBlockSettings.copyOf(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)),
            new FabricItemSettings().maxCount(1));
    public static final Block CHOCOLATE_PANCAKES = registerBlock("chocolate_pancakes",
            new PancakeBlock(() -> ModItems.CHOCOLATE_PANCAKE,
                    FabricBlockSettings.copyOf(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)),
            new FabricItemSettings().maxCount(1));
    public static final Block CHERRY_BLOSSOM_PANCAKES = registerBlock("cherry_blossom_pancakes",
            new PancakeBlock(() -> ModItems.CHERRY_BLOSSOM_PANCAKE,
                    FabricBlockSettings.copyOf(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)),
            new FabricItemSettings().maxCount(1));
    public static final Block VEGETABLE_PANCAKES = registerBlock("vegetable_pancakes",
            new PancakeBlock(() -> ModItems.VEGETABLE_PANCAKE,
                    FabricBlockSettings.copyOf(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)),
            new FabricItemSettings().maxCount(1));
    public static final Block PUMPKIN_PANCAKES = registerBlock("pumpkin_pancakes",
            new PancakeBlock(() -> ModItems.PUMPKIN_PANCAKE,
                    FabricBlockSettings.copyOf(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)),
            new FabricItemSettings().maxCount(1));


    public static final Block RICE_ROLL_ROYALE_MEDLEY = registerBlock("rice_roll_royale_medley",
            new RiceRollRoyaleMedleyBlock(FabricBlockSettings.copyOf(Blocks.CAKE)),
            new FabricItemSettings().maxCount(1));




    public static final Block WILD_CABBAGES = registerBlock("wild_cabbages",
            new WildCropBlock(StatusEffects.STRENGTH, 6,
                    FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_ONIONS = registerBlock("wild_onions",
            new WildCropBlock(StatusEffects.FIRE_RESISTANCE, 6,
                    FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_TOMATOES = registerBlock("wild_tomatoes",
            new WildCropBlock(StatusEffects.POISON, 10,
                    FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_CARROTS = registerBlock("wild_carrots",
            new WildCropBlock(StatusEffects.MINING_FATIGUE, 6,
                    FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_POTATOES = registerBlock("wild_potatoes",
            new WildCropBlock(StatusEffects.NAUSEA, 8,
                    FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_BEETROOTS = registerBlock("wild_beetroots",
            new WildCropBlock(StatusEffects.WATER_BREATHING, 8,
                    FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_RICE = registerBlock("wild_rice",
            new WildRiceBlock(FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_CUCUMBERS = registerBlock("wild_cucumbers",
            new WildCropBlock(StatusEffects.FIRE_RESISTANCE, 6, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_EGGPLANTS = registerBlock("wild_eggplants",
            new WildCropBlock(StatusEffects.STRENGTH, 6, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_CORN = registerBlock("wild_corn",
            new WildCropBlock(StatusEffects.HUNGER, 6, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_CORN_DRY = registerBlock("wild_corn_dry",
            new WildCropBlock(StatusEffects.HUNGER, 6, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));

    public static final Block WILD_COTTON = registerBlock("wild_cotton",
            new WildCropBlock(StatusEffects.SLOW_FALLING, 12, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_BELL_PEPPERS = registerBlock("wild_bell_peppers",
            new WildCropBlock(StatusEffects.FIRE_RESISTANCE, 10, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_COFFEE = registerBlock("wild_coffee",
            new WildCropBlock(StatusEffects.SPEED, 10, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));

    public static final Block WILD_BROCCOLI = registerBlock("wild_broccoli",
            new WildCropBlock(StatusEffects.LUCK, 5, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_CAULIFLOWERS = registerBlock("wild_cauliflowers",
            new WildCropBlock(StatusEffects.LUCK, 5, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_ZUCCHINIS = registerBlock("wild_zucchinis",
            new WildCropBlock(StatusEffects.LUCK, 5, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_SWEET_POTATOES = registerBlock("wild_sweet_potatoes",
            new WildCropBlock(StatusEffects.LUCK, 5, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision()));
    public static final Block WILD_TURNIPS = registerBlock("wild_turnips",
            new WildCropBlock(StatusEffects.LUCK, 5, FabricBlockSettings.copyOf(Blocks.TALL_GRASS).noCollision())

        {
            @Override
            protected boolean canPlantOnTop (BlockState floor, BlockView world, BlockPos pos){
            return super.canPlantOnTop(floor, world, pos)
                    || floor.isOf(Blocks.SAND)
                    || floor.isOf(Blocks.RED_SAND);

        }});



    public static final Block CABBAGE_CROP = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "cabbages"),
            new CabbageBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block ONION_CROP = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "onions"),
            new OnionBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block RICE_CROP = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "rice"),
            new RiceBlock(FabricBlockSettings.copyOf(Blocks.WHEAT).strength(0.2f)));
    public static final Block RICE_CROP_PANICLES = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "rice_panicles"),
            new RicePaniclesBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block BUDDING_TOMATO_CROP = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "budding_tomatoes"),
            new BuddingTomatoBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block TOMATO_CROP = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "tomatoes"),
            new TomatoVineBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block CUCUMBER_CROP = registerBlockWithoutItem("cucumbers",
            new CucumberBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block EGGPLANT_CROP = registerBlockWithoutItem("eggplants",
            new EggplantsBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block CORN_CROP = registerBlockWithoutItem("corn",
            new CornBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block CORN_UPPER_CROP = registerBlockWithoutItem("corn_upper",
            new CornUpperBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block COTTON_CROP = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "cotton"),
            new CottonCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT).noCollision()));

    public static final Block BELL_PEPPER_CROP = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "bell_peppers"),
            new BellPepperCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT).noCollision()));

    public static final Block COFFEE_CROP = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "coffee"),
            new CoffeeCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT).noCollision()));

    public static final Block SWEET_POTATO_CROP = registerBlockWithoutItem("sweet_potatoes",
            new SweetPotatoCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block CAULIFLOWER_CROP = registerBlockWithoutItem("cauliflower",
            new CauliflowerCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block BROCCOLI_CROP = registerBlockWithoutItem("broccoli",
            new BroccoliCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block ZUCCHINI_CROP = registerBlockWithoutItem("zucchini",
            new ZucchiniCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block TURNIP_CROP = registerBlockWithoutItem("turnip",
            new TurnipCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));


    public static final Block MATURE_DANDELION = registerBlock("mature_dandelion",
            new DandelionBlock(FabricBlockSettings.copyOf(Blocks.DANDELION)));


    public static final Block ROPE = Registry.register(Registries.BLOCK,
            new Identifier(EwMedieval.MOD_ID, "rope"),
            new RopeBlock(FabricBlockSettings.copyOf(Blocks.BROWN_CARPET)
                    .noCollision().nonOpaque().strength(0.2f).sounds(BlockSoundGroup.WOOL)));


    public static final Block COTTON_SEEDS_BAG = registerBlock("cotton_seeds_bag",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));
    public static final Block BELL_PEPPER_SEEDS_BAG = registerBlock("bell_pepper_seeds_bag",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));
    public static final Block COFFEE_BEANS_BAG = registerBlock("coffee_beans_bag",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));
    public static final Block ROASTED_COFFEE_BEANS_BAG = registerBlock("roasted_coffee_beans_bag",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));


    public static final Block COTTON_BOLL_CRATE = registerBlock("cotton_boll_crate",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_GREEN_CRATE = registerBlock("bell_pepper_green_crate",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_YELLOW_CRATE = registerBlock("bell_pepper_yellow_crate",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_RED_CRATE = registerBlock("bell_pepper_red_crate",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(EwMedieval.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, FabricItemSettings itemSettings) {
        Registry.register(Registries.ITEM, new Identifier(EwMedieval.MOD_ID, name),
                new BlockItem(block, itemSettings));
        return Registry.register(Registries.BLOCK, new Identifier(EwMedieval.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(EwMedieval.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        Registry.register(Registries.BLOCK, new Identifier(EwMedieval.MOD_ID, name), block);
        return block;
    }

    public static void registerModBlocks(){
        EwMedieval.LOGGER.info("Registering ModBlocks for" +  EwMedieval.MOD_ID);
    }
}
