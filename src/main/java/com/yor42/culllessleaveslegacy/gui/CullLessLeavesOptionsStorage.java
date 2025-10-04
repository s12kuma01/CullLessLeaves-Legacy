package com.yor42.culllessleaveslegacy.gui;

import com.yor42.culllessleaveslegacy.CullLessLeavesConfig;
import com.yor42.culllessleaveslegacy.Main;
import org.taumc.celeritas.api.options.structure.OptionStorage;

/**
 * Storage wrapper for Celeritas options system
 */
public class CullLessLeavesOptionsStorage implements OptionStorage<CullLessLeavesConfig> {

    @Override
    public CullLessLeavesConfig getData() {
        return Main.config();
    }

    @Override
    public void save() {
        Main.config().save();
        Main.LOGGER.info("Saved CullLessLeaves config from GUI");
    }
}
