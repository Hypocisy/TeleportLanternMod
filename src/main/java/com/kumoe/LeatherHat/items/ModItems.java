package com.kumoe.LeatherHat.items;

import com.kumoe.LeatherHat.LeatherMod;
import com.kumoe.LeatherHat.items.custom.ColorfulHat;
import com.kumoe.LeatherHat.items.custom.UseLessHat;
import com.kumoe.LeatherHat.items.custom.UselessGold;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LeatherMod.MODID);
    public static final RegistryObject<Item> USELESS_GOLD = ITEMS.register("useless_gold",
            () -> new UselessGold(ArmorMaterials.GOLD, EquipmentSlot.MAINHAND, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1)));
    public static final RegistryObject<Item> USELESS_HAT = ITEMS.register("useless_hat",
            () -> new UseLessHat(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static void register(IEventBus eventBus) {
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_black"), DyeColor.BLACK));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_blue"), DyeColor.BLUE));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_yellow"), DyeColor.YELLOW));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_cyan"), DyeColor.CYAN));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_gray"), DyeColor.GRAY));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_green"), DyeColor.GREEN));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_light_gray"), DyeColor.LIGHT_GRAY));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_magenta"), DyeColor.MAGENTA));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_pink"), DyeColor.PINK));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_red"), DyeColor.RED));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_orange"), DyeColor.ORANGE));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_lime"), DyeColor.LIME));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_purple"), DyeColor.PURPLE));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_brown"), DyeColor.BROWN));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_white"), DyeColor.WHITE));
        UseLessHat.addHats(new ColorfulHat(LeatherMod.rl("useless_hat_light_blue"), DyeColor.LIGHT_BLUE));
        ITEMS.register(eventBus);
    }

}
