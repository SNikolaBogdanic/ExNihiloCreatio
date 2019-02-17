package exnihilocreatio.modules.sieve

import exnihilocreatio.modules.base.blocks.BlockBase
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraft.world.World

class BlockSieve(name : String, matIn : Material, val texture: ResourceLocation) : BlockBase(name, matIn) {

    override fun createTileEntity(state: IBlockState?, world: IBlockReader?): TileEntity? {
        return TileSieve()
    }

    override fun onBlockActivated(state: IBlockState, world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, face: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if(world.isRemote)
            return true;
        val te = world.getTileEntity(pos)
        if(te is TileSieve)
            return te.onBlockActivated(world, pos, state, player, hand, face, hitX, hitY, hitZ)
        return true
    }
}