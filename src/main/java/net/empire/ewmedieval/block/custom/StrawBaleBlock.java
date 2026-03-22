package net.empire.ewmedieval.block.custom;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.HayBlock;

public class StrawBaleBlock extends HayBlock {

    public StrawBaleBlock(Settings settings) {
        super(settings);
        FlammableBlockRegistry.getDefaultInstance().add(this, 20, 60);
    }
}
