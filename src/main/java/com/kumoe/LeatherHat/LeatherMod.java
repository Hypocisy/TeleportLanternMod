package com.kumoe.LeatherHat;

import com.kumoe.LeatherHat.block.ModBlocks;
import com.kumoe.LeatherHat.block.entity.ModBlockEntities;
import com.kumoe.LeatherHat.command.LeatherCommand;
import com.kumoe.LeatherHat.command.ModEventSubscriber;
import com.kumoe.LeatherHat.config.LeatherConfig;
import com.kumoe.LeatherHat.items.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(LeatherMod.MODID)
public class LeatherMod {
    public static final String MODID = "leather_mod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public LeatherMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
    }
    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }
}
