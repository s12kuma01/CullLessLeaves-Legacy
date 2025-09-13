package com.yor42.culllessleaveslegacy;

@net.minecraftforge.common.config.Config(modid = Tags.MOD_ID, name = Tags.MOD_NAME)
public class Config {

    @net.minecraftforge.common.config.Config.RangeInt(min = 0)
    @net.minecraftforge.common.config.Config.Comment("Maximum depth of visible leaves")
    public static int depth = 2;

    @net.minecraftforge.common.config.Config.RangeDouble(min = 0, max = 0.2)
    @net.minecraftforge.common.config.Config.Comment("Chance of random culling")
    public static double randomRejection = 0.2;

}
