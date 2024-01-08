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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class LeatherCommand {
    private static int setTpPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        BlockPos blockPos = BlockPosArgument.getLoadedBlockPos(context, "block_pos");
        BlockPos targetPos = BlockPosArgument.getLoadedBlockPos(context, "target_pos");
        int waitTime = IntegerArgumentType.getInteger(context, "wait_time_sec") * 20;

        Level world = context.getSource().getLevel();

        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        if (blockEntity instanceof TeleportBlockEntity teleportBlockEntity && teleportBlockEntity.getActive()) {
            teleportBlockEntity.setTargetPos(targetPos);
            teleportBlockEntity.setWaitTime(waitTime);
            teleportBlockEntity.setChanged();
            context.getSource().sendSuccess(Component.translatable("command.leather_mod.setting.position.success", blockPos.getX(), blockPos.getY(), blockPos.getZ(), targetPos.getX(), targetPos.getY(), targetPos.getZ(), waitTime).withStyle(ChatFormatting.BLUE), true);
            return 1;
        } else {
            // 如果 BlockEntity 不存在，通知玩家
            context.getSource().sendFailure(Component.translatable("command.leather_mod.setting.position.failure", blockPos.getX(), blockPos.getY(), blockPos.getZ(), targetPos.getX(), targetPos.getY(), targetPos.getZ()).withStyle(ChatFormatting.GRAY));
            return 0;
        }
    }

    public void accept(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("leather_mod").requires(source -> source.hasPermission(2))
                        .redirect(dispatcher.register(Commands.literal("lm")
                                .then(Commands.argument("wait_time_sec", IntegerArgumentType.integer())
                                .then(Commands.argument("block_pos", BlockPosArgument.blockPos())
                                                .then(Commands.argument("target_pos", BlockPosArgument.blockPos())
                                                        .executes(LeatherCommand::setTpPos))))))
        );
    }
}
