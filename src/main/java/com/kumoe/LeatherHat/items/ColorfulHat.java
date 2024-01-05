package com.kumoe.LeatherHat.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public record ColorfulHat(ResourceLocation id, int color) {
    public ItemStack toStack() {
        var stack = new ItemStack(ModItems.USELESS_HAT.get());
        var display = stack.getOrCreateTagElement("display");
        display.putInt("color", color);
        display.putString("Name", String.format("{\"translate\":\"item.%s.%s\",\"italic\":\"false\"}", id().getNamespace(), id().getPath()));
        return stack;
    }
}
