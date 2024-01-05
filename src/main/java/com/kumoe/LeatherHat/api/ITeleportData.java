package com.kumoe.LeatherHat.api;

import net.minecraft.core.BlockPos;

public interface ITeleportData {

    BlockPos getTargetPos();

    void setTargetPos(BlockPos targetPos);

    BlockPos getTeleportBePos();


    int getWaitTime();

    void setWaitTime(int waitTime);
}
