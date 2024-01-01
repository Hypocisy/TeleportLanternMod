package com.kumoe.LeatherHat.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.kumoe.LeatherHat.config.LeatherConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;

import java.util.UUID;
public class ProLeatherHatItem extends DyeableArmorItem {
    private final EquipmentSlot slot;
    private final float knockbackResistance;
    private final ArmorMaterial material;
    private final int defense;
    private final float toughness;
    private Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ProLeatherHatItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
        this.slot = equipmentSlot;
        this.material = armorMaterial;
        this.defense = armorMaterial.getDefenseForSlot(equipmentSlot);
        this.toughness = armorMaterial.getToughness();
        this.knockbackResistance = armorMaterial.getKnockbackResistance();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "Armor max health addition", 20, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), "Armor value addition", 16, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == this.slot ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }
}
