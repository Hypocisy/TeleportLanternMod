package com.kumoe.LeatherHat.api;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import java.util.UUID;

public interface ITeleportData {

    BlockPos getTeleportBePos();

    BlockPos getTargetPos();

    UUID getUid();

    Component getMenuName();

    int getWaitTime();

}
