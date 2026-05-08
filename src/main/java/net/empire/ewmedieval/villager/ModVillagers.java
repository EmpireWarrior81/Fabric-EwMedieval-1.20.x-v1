package net.empire.ewmedieval.villager;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public class ModVillagers {

    public static void register() {
        registerFarmerTrades();
        registerToolsmithTrades();
    }

    private static void registerFarmerTrades() {
        // Level 1 — basic cool-season crops (farmer buys / sells seeds)
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add(buy(ModItems.CABBAGE,  10, 16, 2));
            factories.add(buy(ModItems.ONION,     8, 16, 2));
            factories.add(sell(1, ModItems.CABBAGE_SEEDS, 6, 16, 2));
            factories.add(sell(1, ModItems.TURNIP_SEEDS,  6, 16, 2));
        });

        // Level 2 — warm-season crops
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories -> {
            factories.add(buy(ModItems.TOMATO,    10, 12, 5));
            factories.add(buy(ModItems.CORN_COB,  10, 12, 5));
            factories.add(buy(ModItems.RICE,      14, 12, 5));
            factories.add(sell(1, ModItems.TOMATO_SEEDS,  6, 12, 5));
            factories.add(sell(1, ModItems.CORN_KERNELS,  5, 12, 5));
        });

        // Level 3 — more crops, straw economy
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 3, factories -> {
            factories.add(buy(ModItems.COTTON_BOLL, 12, 12, 10));
            factories.add(buy(ModItems.CHICKPEA,    12, 12, 10));
            factories.add(buy(ModItems.STRAW,       20, 12, 10));
            factories.add(sell(1, ModItems.CUCUMBER_SEEDS,  5, 12, 10));
            factories.add(sell(1, ModItems.BELL_PEPPER_SEEDS, 5, 12, 10));
            factories.add(sell(2, ModItems.ROPE,              1, 10, 10));
        });

        // Level 4 — exotic crops
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 4, factories -> {
            factories.add(buy(ModItems.AVOCADO,     6, 12, 15));
            factories.add(buy(ModItems.CASSAVA,     8, 12, 15));
            factories.add(sell(2, ModItems.EGGPLANT_SEEDS,     4, 10, 15));
            factories.add(sell(2, ModItems.COTTON_SEEDS,       5, 10, 15));
            factories.add(sell(3, ModBlocks.AVOCADO_PIT.asItem(), 1, 6, 15));
        });

        // Level 5 — rare tropical goods
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5, factories -> {
            factories.add(buy(ModItems.COFFEE_BEANS,    5, 10, 30));
            factories.add(buy(ModItems.AJI_AMARILLO,    4, 10, 30));
            factories.add(sell(3, ModItems.AJI_AMARILLO_SEEDS, 3, 8, 30));
        });
    }

    private static void registerToolsmithTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 1, factories -> {
            factories.add(sell(3, ModItems.FLINT_KNIFE, 1, 10, 5));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 3, factories -> {
            factories.add(sell(5, ModItems.IRON_KNIFE, 1, 8, 10));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 5, factories -> {
            factories.add(sell(10, ModItems.DIAMOND_KNIFE, 1, 4, 30));
        });
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /** Villager buys `count` of `item` from the player for 1 emerald. */
    private static TradeOffers.Factory buy(net.minecraft.item.Item item, int count, int maxUses, int xp) {
        return (entity, random) -> new TradeOffer(
                new ItemStack(item, count),
                new ItemStack(Items.EMERALD, 1),
                maxUses, xp, 0.05f
        );
    }

    /** Villager sells `count` of `sellItem` to the player for `price` emeralds. */
    private static TradeOffers.Factory sell(int price, net.minecraft.item.Item sellItem, int count,
                                            int maxUses, int xp) {
        return (entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, price),
                new ItemStack(sellItem, count),
                maxUses, xp, 0.05f
        );
    }
}
