package com.kumoe.LeatherHat.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class LeatherConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();


    public static ForgeConfigSpec getConfigSpec(ForgeConfigSpec.Builder builder) {
        return BUILDER.build();
    }

    public static class Common {
        public ForgeConfigSpec.DoubleValue MAX_HEALTH;
        public ForgeConfigSpec.IntValue ARMOR_VALUE;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("General");
            MAX_HEALTH = builder.comment("盔甲增加的最大生命值").defineInRange("max_health", 20.0, 1.0, Double.MAX_VALUE);
            ARMOR_VALUE = BUILDER.comment("盔甲增加的护甲值").defineInRange("armor_value", 16, 1, Integer.MAX_VALUE);
            builder.pop();
        }
    }
}
