package com.yor42.culllessleaveslegacy;

import com.cleanroommc.configanytime.ConfigAnytime;

@net.minecraftforge.common.config.Config(modid = Tags.MOD_ID, name = Tags.MOD_NAME)
public class Config {

    static {
        ConfigAnytime.register(Config.class);
    }

    @net.minecraftforge.common.config.Config.Comment("Enable/Disable leaf culling")
    public static boolean enabled = true;

    @net.minecraftforge.common.config.Config.Comment("Culling preset: 0=Fast, 1=Balanced, 2=Fancy, 3=Custom")
    @net.minecraftforge.common.config.Config.RangeInt(min = 0, max = 3)
    public static int preset = 1; // BALANCED

    @net.minecraftforge.common.config.Config.RangeInt(min = 0, max = 5)
    @net.minecraftforge.common.config.Config.Comment("Maximum depth of visible leaves (Custom preset)")
    public static int depth = 2;

    @net.minecraftforge.common.config.Config.RangeDouble(min = 0, max = 0.5)
    @net.minecraftforge.common.config.Config.Comment("Chance of random culling (Custom preset)")
    public static double randomRejection = 0.2;

    public enum CullingPreset {
        FAST(1, 0.0),
        BALANCED(2, 0.2),
        FANCY(3, 0.1),
        CUSTOM(2, 0.2);

        public final int depth;
        public final double randomRejection;

        CullingPreset(int depth, double randomRejection) {
            this.depth = depth;
            this.randomRejection = randomRejection;
        }

        public static CullingPreset fromInt(int value) {
            if (value >= 0 && value < values().length) {
                return values()[value];
            }
            return BALANCED;
        }
    }

    public static CullingPreset getPreset() {
        return CullingPreset.fromInt(preset);
    }

    public static void setPreset(CullingPreset newPreset) {
        preset = newPreset.ordinal();
        if (newPreset != CullingPreset.CUSTOM) {
            depth = newPreset.depth;
            randomRejection = newPreset.randomRejection;
        }
    }

    public static int getEffectiveDepth() {
        if (!enabled) return 0;
        return depth;
    }

    public static double getEffectiveRandomRejection() {
        if (!enabled) return 0.0;
        return randomRejection;
    }
}
