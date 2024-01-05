package com.kumoe.LeatherHat.items;

import com.kumoe.LeatherHat.LeatherMod;
import com.kumoe.LeatherHat.items.custom.UseLessHat;
import com.kumoe.LeatherHat.items.custom.UselessGold;
import net.minecraft.server.commands.GameModeCommand;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LeatherMod.MODID);
    public static final RegistryObject<Item> USELESS_GOLD = ITEMS.register("useless_gold",
            () -> new UselessGold(ArmorMaterials.GOLD, EquipmentSlot.MAINHAND, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1)));
    public static final RegistryObject<Item> USELESS_HAT = ITEMS.register("useless_hat",
            () -> new UseLessHat(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static void register(IEventBus eventBus) {
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_black"), DyeColor.BLACK.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_blue"), DyeColor.BLUE.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_yellow"), DyeColor.YELLOW.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_cyan"), DyeColor.CYAN.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_gray"), DyeColor.GRAY.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_green"), DyeColor.GREEN.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_lightgray"), DyeColor.LIGHT_GRAY.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_magenta"), DyeColor.MAGENTA.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_pink"), DyeColor.PINK.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_red"), DyeColor.RED.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_orange"), DyeColor.ORANGE.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_lime"), DyeColor.LIME.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_purple"), DyeColor.PURPLE.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_brown"), DyeColor.BROWN.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_white"), DyeColor.WHITE.getTextColor()));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_lightblue"), DyeColor.LIGHT_BLUE.getTextColor()));
        ITEMS.register(eventBus);
    }

}
