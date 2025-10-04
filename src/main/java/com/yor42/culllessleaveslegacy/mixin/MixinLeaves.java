package com.yor42.culllessleaveslegacy.mixin;

import com.yor42.culllessleaveslegacy.Icullable;
import com.yor42.culllessleaveslegacy.Main;
import com.yor42.culllessleaveslegacy.compat.Compat;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockLeaves.class)
public class MixinLeaves implements Icullable {

    @Override
    @Unique
    public boolean cll$shouldCullSide(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing facing) {
        return  (Main.shouldCullSide(Compat.isFancyLeaves() ? Main.config().getEffectiveDepth() : 1, pos, access, facing, (block) -> block instanceof BlockLeaves));
    }
}
