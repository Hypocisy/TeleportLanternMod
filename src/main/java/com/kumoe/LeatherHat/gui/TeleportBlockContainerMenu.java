package com.kumoe.LeatherHat.gui;

import com.kumoe.LeatherHat.block.ModBlocks;
import com.kumoe.LeatherHat.network.messages.TeleportMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static com.kumoe.LeatherHat.gui.ModMenus.TELEPORT_BLOCK_MENU;


public class TeleportBlockContainerMenu extends AbstractContainerMenu implements MenuProvider {
    private Level level;
    private Component menuName;
    private int waitTime;
    private UUID uid;
    private BlockPos pos;
    private BlockPos targetPos;
    private ContainerLevelAccess access;


    public TeleportBlockContainerMenu(int pContainerId, Inventory pInventory, ContainerLevelAccess access) {
        super(TELEPORT_BLOCK_MENU.get(), pContainerId);
        this.level = pInventory.player.getLevel();
        this.access = access;
    }

    public TeleportBlockContainerMenu(int pContainerId, Inventory pInventory, FriendlyByteBuf buf) {
        this(pContainerId, pInventory, ContainerLevelAccess.NULL);
        TeleportMessage teleportMessage = TeleportMessage.decode(buf);

        // 然后在这里使用 teleportMessage 中的数据进行进一步处理
        this.uid = teleportMessage.getUid();
        this.waitTime = teleportMessage.getWaitTime();
        this.pos = teleportMessage.getTeleportBePos();
        this.targetPos = teleportMessage.getTargetPos();
        this.menuName = teleportMessage.getMenuName();
    }

    protected static boolean stillValid(ContainerLevelAccess pAccess, Player pPlayer, Block pTargetBlock) {
        return pAccess.evaluate((level, blockPos) -> level.getBlockState(blockPos).is(pTargetBlock) && pPlayer.distanceToSqr((double) blockPos.getX() + 0.5, (double) blockPos.getY() + 0.5, (double) blockPos.getZ() + 0.5) <= 64.0, true);
    }

    @Override

    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public void clicked(int pSlotId, int pButton, ClickType pClickType, Player pPlayer) {
        super.clicked(pSlotId, pButton, pClickType, pPlayer);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access, pPlayer, ModBlocks.TELEPORT_BLOCK.get());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.leather_mod.teleport_block_name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TeleportBlockContainerMenu(containerId, inventory, access);
    }
}
