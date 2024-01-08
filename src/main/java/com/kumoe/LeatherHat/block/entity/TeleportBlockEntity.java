package com.kumoe.LeatherHat.block.entity;

import com.kumoe.LeatherHat.api.ITeleportData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.entity.BeaconBlockEntity.playSound;

public class TeleportBlockEntity extends BlockEntity implements ITeleportData {
    private int waitTime = 120;
    private BlockPos targetPos = this.getBlockPos().above();
    private BlockPos blockPos = this.getBlockPos();
    private boolean active = false;

    public TeleportBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TELEPORT_BE.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        active = pTag.getBoolean("teleport_be.active");
        waitTime = pTag.getInt("teleport_be.wait_time");
        targetPos = BlockPos.of(pTag.getLong("teleport_be.target_pos"));
        blockPos = BlockPos.of(pTag.getLong("teleport_be.block_pos"));
        super.load(pTag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putBoolean("teleport_be.active", active);
        pTag.putInt("teleport_be.wait_time", waitTime);
        pTag.putLong("teleport_be.target_pos", targetPos.asLong());
        pTag.putLong("teleport_be.block_pos", blockPos.asLong());
        super.saveAdditional(pTag);
    }

    @Override
    public BlockPos getTargetPos() {
        return this.targetPos;
    }

    public void setTargetPos(BlockPos targetPos) {
        this.targetPos = targetPos;
        this.setChanged();
    }

    @Override
    public BlockPos getTeleportBePos() {
        return this.getBlockPos();
    }

    @Override
    public int getWaitTime() {
        return this.waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
        this.setChanged();
    }

    @Override
    public void setRemoved() {
        if (this.level != null) {
            playSound(this.level, this.worldPosition, SoundEvents.BEACON_DEACTIVATE);
        }
        super.setRemoved();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }
}
