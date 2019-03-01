package exnihilocreatio.modules.crucible

import exnihilocreatio.modules.barrel.BlockBarrel
import exnihilocreatio.modules.base.blocks.BlockBase
import exnihilocreatio.modules.base.blocks.BlockNonCubeBase
import exnihilocreatio.utils.VoxelShapeHelper
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader

class BlockCrucible(name : String, properties: Block.Properties = woodProperties) : BlockNonCubeBase(name, properties) {
    override fun createTileEntity(state: IBlockState?, world: IBlockReader?): TileEntity? {
        return TileCrucible()
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getShape(state: IBlockState?, worldIn: IBlockReader?, pos: BlockPos?): VoxelShape {
        return SHAPE
    }

    companion object {
        val SUB_SHAPES = arrayOf<VoxelShape>(
                Block.makeCuboidShape(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
                Block.makeCuboidShape(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
                Block.makeCuboidShape(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
                Block.makeCuboidShape(13.0,0.0,13.0,16.0,3.0,16.0),
                Block.makeCuboidShape(0.0, 3.0, 0.0, 16.0, 16.0, 16.0)
        )
        val SHAPE = VoxelShapeHelper.union(*SUB_SHAPES)

        val woodProperties = Properties.create(Material.WOOD).hardnessAndResistance(2.0f)
        val stoneProperties = Properties.create(Material.WOOD).hardnessAndResistance(2.0f, 6.0f)
    }
}