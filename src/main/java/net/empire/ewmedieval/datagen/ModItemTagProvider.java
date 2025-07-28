package net.empire.ewmedieval.datagen;

import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "tin_ingots")))
                .add(ModItems.TIN_INGOT);
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "silver_ingots")))
                .add(ModItems.SILVER_INGOT);
    }

}



