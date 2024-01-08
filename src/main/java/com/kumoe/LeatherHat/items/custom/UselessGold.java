package com.kumoe.LeatherHat.items.custom;

import com.google.common.collect.ImmutableMap;
import com.kumoe.LeatherHat.items.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UselessGold extends ArmorItem {
    // effects, tick, level
    private final MobEffectInstance SATURATION = new MobEffectInstance(MobEffects.SATURATION, 4000, 4);
    private final MobEffectInstance REGENERATION = new MobEffectInstance(MobEffects.REGENERATION, 4000, 4);
    private final MobEffectInstance DAMAGE_RESISTANCE = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 4000, 4);

    public UselessGold(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }


    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide && pStack.getItem() == ModItems.USELESS_GOLD.get()) {
            if (pEntity instanceof ServerPlayer player && player.gameMode.isSurvival()) {
                if (pIsSelected && player.getMainHandItem().getItem() == ModItems.USELESS_GOLD.get()) {
                    player.getAbilities().mayfly = true;
                    player.onUpdateAbilities();
                    player.addEffect(SATURATION);
                    player.addEffect(REGENERATION);
                    player.addEffect(DAMAGE_RESISTANCE);
                    EnchantmentHelper.setEnchantments(ImmutableMap.of(Enchantments.FALL_PROTECTION, 4, Enchantments.RESPIRATION, 4), pStack);
                } else {
                    player.getAbilities().flying = false;
                    player.getAbilities().mayfly = false;
                    player.onUpdateAbilities();
                    player.removeEffect(SATURATION.getEffect());
                    player.removeEffect(REGENERATION.getEffect());
                    player.removeEffect(DAMAGE_RESISTANCE.getEffect());
                    EnchantmentHelper.setEnchantments(ImmutableMap.of(), pStack);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.leather_mod.useless_gold.tooltip.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.leather_mod.useless_gold.tooltip.2").withStyle(ChatFormatting.GREEN));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
