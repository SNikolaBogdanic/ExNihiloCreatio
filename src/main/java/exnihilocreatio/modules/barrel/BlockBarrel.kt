package exnihilocreatio.modules.barrel

import exnihilocreatio.modules.base.blocks.BlockNonCubeBase
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader

class BlockBarrel(name: String, properties: Properties = woodProperties): BlockNonCubeBase(name, properties) {
    override fun createTileEntity(state: IBlockState?, world: IBlockReader?): TileEntity? {
        return TileBarrel()
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getShape(state: IBlockState?, worldIn: IBlockReader?, pos: BlockPos?): VoxelShape {
        return SHAPE
    }

    companion object {
        val SHAPE = Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)

        val woodProperties = Properties.create(Material.WOOD).hardnessAndResistance(2.0f)
        val stoneProperties = Properties.create(Material.WOOD).hardnessAndResistance(2.0f, 6.0f)
    }
}