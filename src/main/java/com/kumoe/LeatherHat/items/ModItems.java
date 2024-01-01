package com.kumoe.LeatherHat.items;

import com.kumoe.LeatherHat.LeatherMod;
import com.kumoe.LeatherHat.items.custom.ProLeatherHatItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LeatherMod.MODID);
    public static final RegistryObject<Item> LEATHER_HAT = ITEMS.register("leather_hat_item",
            () -> new ProLeatherHatItem(ArmorMaterials.LEATHER,EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
