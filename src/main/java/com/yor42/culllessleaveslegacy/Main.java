package com.yor42.culllessleaveslegacy;

import com.yor42.culllessleaveslegacy.util.BlockConstantRandom;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.taumc.celeritas.api.OptionGUIConstructionEvent;

import java.util.function.Predicate;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION, clientSideOnly = true)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        if (Loader.isModLoaded("celeritas")) {
            try {
                Class.forName("org.taumc.celeritas.api.OptionGUIConstructionEvent");
                // Register option GUI construction listener
                OptionGUIConstructionEvent.BUS.addListener(com.yor42.culllessleaveslegacy.gui.CullLessLeavesOptionsListener::onCeleritasOptionsConstruct);
                LOGGER.info("Successfully registered CullLessLeaves options with Celeritas GUI");
            } catch (Throwable t) {
                if (t instanceof NoClassDefFoundError) {
                    LOGGER.error("Celeritas version is too old, use 2.4.0 or newer");
                } else {
                    LOGGER.error("Unable to check if Celeritas is up-to-date", t);
                }
            }
        } else {
            LOGGER.info("Celeritas not found - GUI integration disabled");
        }
    }

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello From {}!", Tags.MOD_NAME);
    }

    public static boolean shouldCullSide(int depth, BlockPos pos, IBlockAccess access, EnumFacing facing, Predicate<Block> blockCheck) {
        boolean cull = true;
        float rejectionChance = (float) Config.getEffectiveRandomRejection();

        boolean outerBlock = false;
        for (int i = 1; i <= depth; i++) {
            IBlockState state = access.getBlockState(pos.offset(facing, i));
            cull &= blockCheck.test(state.getBlock());

            if (!cull && i == 1)
                outerBlock = true;
        }

        if (!outerBlock && !cull && BlockConstantRandom.getConstantRandomSeeded(pos) <= rejectionChance)
            cull = true;

        return cull;
    }

}
