package com.kumoe.LeatherHat.block.custom;

import com.kumoe.LeatherHat.block.entity.TeleportBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.entity.BeaconBlockEntity.playSound;


public class tpLantern extends Block implements EntityBlock {
    private int waitTime = 0;
    private BlockPos targetPos;
    private boolean isActive = false;

    public tpLantern(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TeleportBlockEntity(blockPos, blockState);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide && pEntity instanceof Player player && pLevel.getBlockEntity(pPos) instanceof TeleportBlockEntity entity) {
            waitTime++;
            if (waitTime % 20 == 0) {
                if (waitTime != 0) {
                    player.sendSystemMessage(Component.translatable("block.leather_mod.teleport_block.dont_move", (entity.getWaitTime() - waitTime) / 20 + 1, entity.getTargetPos().getX(), entity.getTargetPos().getY(), entity.getTargetPos().getZ()).withStyle(ChatFormatting.DARK_AQUA));
                }
                if (waitTime > entity.getWaitTime()) {
                    targetPos = entity.getTargetPos();
                    if (targetPos != null && entity.getActive()) {
                        player.teleportTo(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                        playSound(pLevel, pPos, SoundEvents.FOX_TELEPORT);
                        player.sendSystemMessage(Component.translatable("block.leather_mod.teleport.success", player.getDisplayName(), player.getLevel().dimension().location().getNamespace() + ":" + player.getLevel().dimension().location().getPath(), targetPos.getX(), targetPos.getY(), targetPos.getZ()).withStyle(ChatFormatting.GREEN));
                        waitTime = 0;
                    }
                }
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            if (pPlayer.hasPermissions(2) && pLevel.getBlockEntity(pPos) instanceof TeleportBlockEntity teleportBlockEntity) {
                if (!teleportBlockEntity.getActive()) {
                    teleportBlockEntity.setActive(true);
                    pPlayer.sendSystemMessage(Component.translatable("block.leather_mod.teleport_block.active", pPos.getX(), pPos.getY(), pPos.getZ()).withStyle(ChatFormatting.AQUA));
                    playSound(pLevel, pPos, SoundEvents.BEACON_ACTIVATE);
                } else {
                    pPlayer.sendSystemMessage(Component.translatable("block.leather_mod.teleport_block.deactivate", pPos.getX(), pPos.getY(), pPos.getZ()).withStyle(ChatFormatting.GRAY));
                    playSound(pLevel, pPos, SoundEvents.BEACON_DEACTIVATE);
                    teleportBlockEntity.setActive(false);
                }
            } else {
                pPlayer.sendSystemMessage(Component.translatable("block.leather_mod.teleport_block.no_permission").withStyle(ChatFormatting.DARK_RED));
                playSound(pLevel, pPos, SoundEvents.ANVIL_FALL);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
