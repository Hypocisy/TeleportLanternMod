package com.kumoe.LeatherHat.block.entity;

import com.kumoe.LeatherHat.api.ITeleportData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public class TeleportBlockEntity extends BlockEntity implements ITeleportData {
    private UUID uid;
    private Component menuName;
    private int waitTime;
    private BlockPos teleportBePos;
    private BlockPos targetPos;

    public TeleportBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TELEPORT_BE.get(), pPos, pBlockState);
    }

    public TeleportBlockEntity(BlockPos pPos, BlockState pBlockState, FriendlyByteBuf buf) {
        super(ModBlockEntities.TELEPORT_BE.get(), pPos, pBlockState);
        this.uid = buf.readUUID();
        this.menuName = buf.readComponent();
        this.waitTime = buf.readInt();
        this.targetPos = buf.readBlockPos();
    }

    @Override
    public void load(CompoundTag pTag) {
        this.uid = pTag.getUUID("uid");
        this.waitTime = pTag.getInt("wait_time");
        this.teleportBePos = BlockPos.of(pTag.getLong("teleport_be_pos"));
        this.targetPos = BlockPos.of(pTag.getLong("target_pos"));
        this.menuName = Component.nullToEmpty(pTag.getString("menu_name"));

        super.load(pTag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putUUID("uid", this.uid);
        pTag.putInt("wait_time", this.waitTime);
        pTag.putLong("teleport_be_pos", this.teleportBePos.asLong());
        pTag.putLong("target_pos", this.targetPos.asLong());
        pTag.putString("menu_name", this.menuName.getString());

        super.saveAdditional(pTag);
    }

    public void setTargetPos(BlockPos targetPos) {
        this.targetPos = targetPos;
    }

    @Override
    public int getWaitTime() {
        return this.waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }


    public void setMenuName(Component menuName) {
        this.menuName = menuName;
    }

    @Override
    public BlockPos getTeleportBePos() {
        return super.getBlockPos();
    }

    @Override
    public BlockPos getTargetPos() {
        return this.targetPos;
    }

    @Override
    public UUID getUid() {
        return this.uid;
    }

    @Override
    public Component getMenuName() {
        return this.menuName;
    }
}
