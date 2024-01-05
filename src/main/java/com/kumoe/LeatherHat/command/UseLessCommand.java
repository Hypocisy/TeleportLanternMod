package com.kumoe.LeatherHat.command;

import com.kumoe.LeatherHat.items.ModItems;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.Slot;

import java.util.List;

public class UseLessCommand {
    private static int recycleUselessGold(CommandContext<CommandSourceStack> context) {
        List<ServerPlayer> playerList = context.getSource().getPlayer().getServer().getPlayerList().getPlayers();
        for (ServerPlayer player : playerList) {
            for (int i = 0; i < player.containerMenu.slots.size(); i++) {
                Slot slot = player.containerMenu.slots.get(i);
                if (slot.getItem().is(ModItems.USELESS_GOLD.get())) {
                    slot.getItem().setCount(0);
                }
            }
        }
        return 1;
    }

    public void accept(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("recycle_gold").requires(source -> source.hasPermission(2))
                        .redirect(dispatcher.register(Commands.literal("rg").requires(source -> source.hasPermission(2))
                                .executes(UseLessCommand::recycleUselessGold))));
    }
}
