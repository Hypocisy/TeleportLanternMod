package com.kumoe.LeatherHat.network.messages;

import com.kumoe.LeatherHat.api.ITeleportData;
import com.kumoe.LeatherHat.block.custom.TeleportBlock;
import com.kumoe.LeatherHat.block.entity.TeleportBlockEntity;
import com.kumoe.LeatherHat.gui.ModMenus;
import com.kumoe.LeatherHat.gui.TeleportBlockContainerMenu;
import com.kumoe.LeatherHat.gui.TeleportSettingScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.UUID;
import java.util.function.Supplier;

public record TeleportMessage(UUID getUid, int getWaitTime, BlockPos getTeleportBePos, BlockPos getTargetPos,
                              Component getMenuName) implements ITeleportData {

    public static void handle(TeleportMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // 处理接收到的数据
            Player player = context.getSender();
            if (player instanceof ServerPlayer serverPlayer) {
                Level level = player.getLevel();
                BlockEntity blockEntity = level.getBlockEntity(message.getTeleportBePos());
                if (blockEntity instanceof TeleportBlockEntity teleportBlockEntity) {
                    teleportBlockEntity.setTargetPos(message.getTargetPos());
                    teleportBlockEntity.setWaitTime(message.getWaitTime());
                    teleportBlockEntity.setMenuName(message.getMenuName());
                    teleportBlockEntity.setChanged();
                }
            }
        });
        context.setPacketHandled(true);
    }

    public static TeleportMessage decode(FriendlyByteBuf buf) {
        UUID uid = buf.readUUID();
        int waitTime = buf.readInt();
        BlockPos teleportBePos = buf.readBlockPos();
        BlockPos targetPos = buf.readBlockPos();
        Component menuName = buf.readComponent();
        return new TeleportMessage(uid, waitTime, teleportBePos, targetPos, menuName);
    }

    public static void encode(TeleportMessage message, FriendlyByteBuf buf) {
        buf.writeUUID(message.getUid());
        buf.writeInt(message.getWaitTime());
        buf.writeLong(message.getTeleportBePos().asLong());
        buf.writeLong(message.getTargetPos().asLong());
        buf.writeComponent(message.getMenuName());
    }
}
