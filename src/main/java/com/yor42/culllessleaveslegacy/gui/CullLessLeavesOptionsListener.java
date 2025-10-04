package com.yor42.culllessleaveslegacy.gui;

import com.yor42.culllessleaveslegacy.Main;
import org.taumc.celeritas.api.OptionGUIConstructionEvent;

/**
 * Listener for Celeritas GUI construction
 * Registers CullLessLeaves options page
 */
public class CullLessLeavesOptionsListener {

    public static void onCeleritasOptionsConstruct(OptionGUIConstructionEvent event) {
        Main.LOGGER.info("Registering CullLessLeaves options page with Celeritas GUI");
        event.addPage(CullLessLeavesOptionPages.cullLessLeaves());
    }
}
