package com.kumoe.LeatherHat.block.custom;

import com.kumoe.LeatherHat.block.entity.TeleportBlockEntity;
import com.kumoe.LeatherHat.gui.TeleportBlockContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class TeleportBlock extends Block {
    private ContainerLevelAccess access;
    private int waitTime = 0;
    private TeleportBlock TeleportBlock;

    public TeleportBlock(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof Player player) {
            waitTime++;
            if (waitTime > 60) {
                if (pLevel.getBlockEntity(pPos) instanceof TeleportBlockEntity teleportBlockEntity) {
                    BlockPos targetPos = teleportBlockEntity.getTargetPos();
                    player.teleportTo(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                }
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (!pLevel.isClientSide && pPlayer instanceof ServerPlayer serverPlayer) {
//            NetworkHooks.openScreen(serverPlayer, pState.getMenuProvider(pLevel, pPos));
            this.access = ContainerLevelAccess.create(pLevel, pPos);
            NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, playerInventory, player) -> new TeleportBlockContainerMenu(containerId, playerInventory, this.access),
                    Component.translatable("menu.title.leather_mod.teleport_menu")), pPos);
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }
}
