package com.kumoe.LeatherHat.gui;

import com.kumoe.LeatherHat.LeatherMod;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, LeatherMod.MODID);
    public static final RegistryObject<MenuType<TeleportBlockContainerMenu>> TELEPORT_BLOCK_MENU = REGISTER.register("teleport_block_menu", () -> IForgeMenuType.create(TeleportBlockContainerMenu::new));

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
