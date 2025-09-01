package net.empire.ewmedieval.item.utils.armor;

import net.empire.ewmedieval.client.model.equipment.chest.*;
import net.empire.ewmedieval.client.model.equipment.head.helmets.*;
import net.empire.ewmedieval.item.ModItems;
//import net.empire.ewmedieval.item.utils.armor.capes.ModCapeModels;
//import net.empire.ewmedieval.item.utils.armor.capes.ModCapes;
//import net.empire.ewmedieval.item.utils.armor.hoods.ModHoodModels;
//import net.empire.ewmedieval.item.utils.armor.hoods.ModHoods;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.StringIdentifiable;

public class ModArmorModels {

    public enum ModHelmetModels {

        GONDORIAN_FOUNTAIN_GUARD_HELMET(ModItems.GONDORIAN_FOUNTAIN_GUARD_HELMET, new GondorianHelmetModel<>(GondorianHelmetModel.getTexturedModelData().createModel()));

        private final Item item;
        private final HelmetAddonModel<LivingEntity> model;

        ModHelmetModels(Item item, HelmetAddonModel<LivingEntity> model) {
            this.item = item;
            this.model = model;
        }

        public Item getItem() {
            return item;
        }

        public HelmetAddonModel<LivingEntity> getModel() {
            return model;
        }
    }

    public enum ModChestplateModels {
        GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE(ModItems.GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE, new HaltChestplateModel<>(HaltChestplateModel.getTexturedModelData().createModel()));

        private final Item item;
        private final ChestplateAddonModel<LivingEntity> model;

        ModChestplateModels(Item item, ChestplateAddonModel<LivingEntity> model) {
            this.item = item;
            this.model = model;
        }

        public Item getItem() {
            return item;
        }

        public ChestplateAddonModel<LivingEntity> getModel() {
            return model;
        }
    }
}



