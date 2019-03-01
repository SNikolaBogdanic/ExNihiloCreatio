package exnihilocreatio.modules.base.tiles

import exnihilocreatio.networking.PacketHandler
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType

abstract class BaseTileEntity(TETypeIn: TileEntityType<*>): TileEntity(TETypeIn) {

    /**
     * Mark the TileEntity as dirty and sync its data with the client
     */
    fun markDirtyClient(){
        markDirty()
        PacketHandler.sendNBTUpdate(this)
    }

    /**
     * Mark the TileEntity as dirty and call a chunk redraw, use sparingly
     */
    fun markDirtyChunk() {
        markDirtyClient()
        val state = world.getBlockState(pos)
        world.notifyBlockUpdate(pos, state, state, 3)
    }

    fun markDirtyChunk(newState: IBlockState) {
        markDirty()
        world.notifyBlockUpdate(pos, world.getBlockState(pos), newState, 3)
    }
}