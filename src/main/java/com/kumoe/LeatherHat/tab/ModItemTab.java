package com.kumoe.LeatherHat.tab;

import com.kumoe.LeatherHat.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemTab extends CreativeModeTab {

    public ModItemTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModBlocks.TELEPORT_BLOCK.get());
    }
}
