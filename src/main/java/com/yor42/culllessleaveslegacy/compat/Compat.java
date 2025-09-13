package com.yor42.culllessleaveslegacy.compat;

import net.minecraft.client.Minecraft;

public class Compat {
    public static boolean isFancyLeaves() {
        return Minecraft.isFancyGraphicsEnabled();
    }
}
