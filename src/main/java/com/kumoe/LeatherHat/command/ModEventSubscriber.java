package com.kumoe.LeatherHat.command;

import com.kumoe.LeatherHat.LeatherMod;
import com.kumoe.LeatherHat.items.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ItemStackedOnOtherEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LeatherMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventSubscriber {

    // 注册指令
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        new UseLessCommand().accept(event.getDispatcher());
        new LeatherCommand().accept(event.getDispatcher());
    }

//    @SubscribeEvent
//    public static void onItemSwitch(Event event) {
//        Player player = event.getEntity();
//        if (player != null && player.isAlive()) {
//            // 检查玩家手持的物品是否为 BUFF_ITEM
//            if (player.getMainHandItem().getItem() == ModItems.USELESS_GOLD.get()) {
//                player.getAbilities().mayfly = true;
//            }else {
//                player.getAbilities().mayfly = false;
//            }
//        }
//    }
}