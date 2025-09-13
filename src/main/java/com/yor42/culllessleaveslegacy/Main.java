package com.yor42.culllessleaveslegacy;

import com.yor42.culllessleaveslegacy.util.BlockConstantRandom;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

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
        float rejectionChance = (float) Config.randomRejection;

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
