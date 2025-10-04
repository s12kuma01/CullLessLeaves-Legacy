package com.yor42.culllessleaveslegacy.gui;

import com.yor42.culllessleaveslegacy.Config;
import com.yor42.culllessleaveslegacy.Main;
import com.yor42.culllessleaveslegacy.Tags;
import net.minecraftforge.common.config.ConfigManager;
import org.taumc.celeritas.api.options.structure.OptionStorage;

/**
 * Storage wrapper for Celeritas options system
 */
public class CullLessLeavesOptionsStorage implements OptionStorage<Config> {

    @Override
    public Config getData() {
        return new Config(); // Dummy instance for static access
    }

    @Override
    public void save() {
        ConfigManager.sync(Tags.MOD_ID, net.minecraftforge.common.config.Config.Type.INSTANCE);
        Main.LOGGER.info("Saved CullLessLeaves config from GUI");
    }
}
