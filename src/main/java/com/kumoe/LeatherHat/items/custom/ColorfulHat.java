package com.kumoe.LeatherHat.items.custom;

import com.kumoe.LeatherHat.items.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public record ColorfulHat(ResourceLocation id, DyeColor color) {
    public ItemStack toStack() {
        var stack = new ItemStack(ModItems.USELESS_HAT.get());
        stack.getOrCreateTag().putInt("custom_model_data", color.getId());
        var display = stack.getOrCreateTagElement("display");
        display.putInt("color", color.getTextColor());
        display.putString("Name", String.format("{\"translate\":\"item.%s.%s\",\"italic\":\"false\"}", id().getNamespace(), id().getPath()));
        return stack;
    }
}