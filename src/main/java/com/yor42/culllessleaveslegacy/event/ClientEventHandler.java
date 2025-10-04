package com.yor42.culllessleaveslegacy.event;

import com.yor42.culllessleaveslegacy.Config;
import com.yor42.culllessleaveslegacy.Tags;
import com.yor42.culllessleaveslegacy.mixin.MixinGuiOptionsRowList;
import com.yor42.culllessleaveslegacy.mixin.MixinGuiVideoSetting;
import net.minecraft.client.gui.*;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.config.GuiSlider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
@Mod.EventBusSubscriber
public class ClientEventHandler {

    @SubscribeEvent
    public static void InitGUI(GuiScreenEvent.InitGuiEvent.Post event){
        GuiScreen screen = event.getGui();
        if(!(screen instanceof GuiVideoSettings settingsscreen)){
            return;
        }

        GuiListExtended optionlist = ((MixinGuiVideoSetting)settingsscreen).getOptionsRowList();
        List<GuiOptionsRowList.Row> row = ((MixinGuiOptionsRowList)optionlist).getOptions();
        row.add(new GuiOptionsRowList.Row(new GuiSlider(row.size(), settingsscreen.width / 2 - 155, 0, 150, 20, new TextComponentTranslation("cll.option.depth").getFormattedText()+": ", "", 1, 64, Config.depth, false, true, slider -> {
            Config.depth = slider.getValueInt();
            ConfigManager.sync(Tags.MOD_ID, net.minecraftforge.common.config.Config.Type.INSTANCE);
        }), new GuiSlider(row.size()+1, settingsscreen.width / 2 - 155 + 160, 0, 150, 20, new TextComponentTranslation("cll.option.randomrejection").getFormattedText()+": ", "", 0, 1, Config.randomRejection, true, true, slider -> {
            Config.randomRejection = slider.getValue();
            ConfigManager.sync(Tags.MOD_ID, net.minecraftforge.common.config.Config.Type.INSTANCE);
        })));
    }

}
