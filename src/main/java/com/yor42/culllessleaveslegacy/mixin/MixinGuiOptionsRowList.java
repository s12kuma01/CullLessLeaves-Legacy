package com.yor42.culllessleaveslegacy.mixin;

import net.minecraft.client.gui.GuiOptionsRowList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(GuiOptionsRowList.class)
public interface MixinGuiOptionsRowList {

    @Accessor
    List<GuiOptionsRowList.Row> getOptions();

}
