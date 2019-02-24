package exnihilocreatio.modules.barrel

import exnihilocreatio.modules.base.blocks.BlockBase
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.world.IBlockReader

class BlockBarrel(name: String, mat: Material, val texture: ResourceLocation): BlockBase(name, mat) {
    override fun createTileEntity(state: IBlockState?, world: IBlockReader?): TileEntity? {
        return TileBarrel()
    }
}