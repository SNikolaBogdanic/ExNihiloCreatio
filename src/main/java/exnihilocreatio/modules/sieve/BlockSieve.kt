package exnihilocreatio.modules.sieve

import exnihilocreatio.modules.base.blocks.BlockNonCubeBase
import exnihilocreatio.utils.VoxelShapeHelper
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader
import net.minecraft.world.World

class BlockSieve(name: String, matIn: Material, val texture: ResourceLocation): BlockNonCubeBase(name, matIn) {
    val WATERLOGGED = BlockStateProperties.WATERLOGGED

    override fun createTileEntity(state: IBlockState?, world: IBlockReader?): TileEntity? {
        return TileSieve()
    }

    override fun onBlockActivated(state: IBlockState, world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, face: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if(world.isRemote)
            return true;
        // Keep all the sieving logic in the TE.
        val te = world.getTileEntity(pos)
        if(te is TileSieve)
            return te.onBlockActivated(state, player, hand, face, hitX, hitY, hitZ)
        return true
    }

    override fun getShape(state: IBlockState?, worldIn: IBlockReader?, pos: BlockPos?): VoxelShape {
        return SHAPE
    }

    companion object {
        val SUB_SHAPES = arrayOf<VoxelShape>(
                Block.makeCuboidShape(0.0, 0.0, 0.0, 2.0, 12.0, 2.0),
                Block.makeCuboidShape(14.0, 0.0, 0.0, 16.0, 12.0, 2.0),
                Block.makeCuboidShape(0.0, 0.0, 14.0, 2.0, 12.0, 16.0),
                Block.makeCuboidShape(14.0, 0.0, 14.0, 16.0, 12.0, 16.0),
                Block.makeCuboidShape(0.0, 8.0, 0.0, 16.0, 12.0, 16.0)
        )
        val SHAPE = VoxelShapeHelper.union(*SUB_SHAPES)
    }
}