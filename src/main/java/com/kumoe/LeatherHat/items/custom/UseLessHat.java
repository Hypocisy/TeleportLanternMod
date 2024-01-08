package com.kumoe.LeatherHat.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UseLessHat extends DyeableArmorItem implements DyeableLeatherItem {
    public static final List<ColorfulHat> HATS = new ArrayList<>();
    private final EquipmentSlot slot;
    private final float knockbackResistance;
    private final ArmorMaterial material;
    private final int defense;
    private final float toughness;
    private Multimap<Attribute, AttributeModifier> defaultModifiers;

    public UseLessHat(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
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

    public static void addHats(ColorfulHat... colorfulHats) {
        HATS.addAll(Arrays.asList(colorfulHats));
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot) {
        return equipmentSlot == this.slot ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> stacks) {
        if (allowedIn(group) || group == CreativeModeTab.TAB_TOOLS) {
            for (ColorfulHat hat : HATS) {
                stacks.add(hat.toStack());
            }
        }
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }
}
