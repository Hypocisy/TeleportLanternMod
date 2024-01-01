package com.kumoe.LeatherHat;

import com.kumoe.LeatherHat.block.ModBlocks;
import com.kumoe.LeatherHat.config.LeatherConfig;
import com.kumoe.LeatherHat.gui.ModMenus;
import com.kumoe.LeatherHat.gui.TeleportSettingScreen;
import com.kumoe.LeatherHat.items.ModItems;
import com.kumoe.LeatherHat.network.NetworkManager;
import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

@Mod(LeatherMod.MODID)
public class LeatherMod {
    public static final String MODID = "leather_mod";

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(MODID));
    public static ForgeConfigSpec.Builder builder;
    public static LeatherConfig.Common config;
    public static SimpleChannel channel;

    public LeatherMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModMenus.register(modEventBus);
        SimpleChannel channel = new NetworkManager(new ResourceLocation(LeatherMod.MODID, "channel")).get();
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEventSubs {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.debug("——————————————————");
            LOGGER.debug("test inv debug");
            LOGGER.debug("——————————————————");
            event.enqueueWork(() -> MenuScreens.register(ModMenus.TELEPORT_BLOCK_MENU.get(), TeleportSettingScreen::new));
        }
    }

}
