package com.kumoe.LeatherHat.command;

import com.kumoe.LeatherHat.block.entity.TeleportBlockEntity;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class LeatherCommand {
    private static int setTpPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        BlockPos blockPos = BlockPosArgument.getLoadedBlockPos(context, "block_pos");
        BlockPos targetPos = BlockPosArgument.getLoadedBlockPos(context, "target_pos");
        int waitTime = IntegerArgumentType.getInteger(context, "wait_time");

        Level world = context.getSource().getLevel();

        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        if (blockEntity instanceof TeleportBlockEntity teleportBlockEntity && teleportBlockEntity.getActive()) {
            teleportBlockEntity.setTargetPos(targetPos);
            teleportBlockEntity.setWaitTime(waitTime);
            context.getSource().sendSuccess(Component.translatable("command.leather.setting.position.success", blockPos.getX(), blockPos.getY(), blockPos.getZ(), targetPos.getX(), targetPos.getY(), targetPos.getZ(), waitTime).withStyle(ChatFormatting.BLUE), true);
            return 1;
        } else {
            // 如果 BlockEntity 不存在，通知玩家
            context.getSource().sendFailure(Component.translatable("command.leather.setting.position.failure", blockPos.getX(), blockPos.getY(), blockPos.getZ(), targetPos.getX(), targetPos.getY(), targetPos.getZ()).withStyle(ChatFormatting.GRAY));
            return 0;
        }
    }
    private boolean hasItem(Inventory inventory, ItemStack item) {
        for (ItemStack stack : inventory.items) {
            // 检查物品是否非空且为指定物品
            if (!stack.isEmpty() && stack.is(item.getItem())) {
                return true; // 玩家拥有指定物品
            }
        }
        return false; // 玩家没有指定物品
    }

    public void accept(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("leather_mod").requires(source -> source.hasPermission(2))
                        .redirect(dispatcher.register(Commands.literal("lm")
                                .then(Commands.argument("block_pos", BlockPosArgument.blockPos())
                                        .then(Commands.argument("wait_time", IntegerArgumentType.integer())
                                                .then(Commands.argument("target_pos", BlockPosArgument.blockPos())
                                                        .executes(LeatherCommand::setTpPos))))))
        );
    }
}
