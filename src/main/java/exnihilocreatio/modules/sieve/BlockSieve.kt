package exnihilocreatio.modules.sieve

import exnihilocreatio.modules.base.blocks.BlockNonCubeBase
import exnihilocreatio.modules.sieve.properties.EnumMeshType
import exnihilocreatio.utils.VoxelShapeHelper
import net.minecraft.block.Block
import net.minecraft.block.IBucketPickupHandler
import net.minecraft.block.ILiquidContainer
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.IFluidState
import net.minecraft.init.Fluids
import net.minecraft.state.EnumProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.util.text.TextComponentString
import net.minecraft.world.IBlockReader
import net.minecraft.world.IWorld
import net.minecraft.world.World

class BlockSieve(name: String, properties: Properties = sieveProperties): BlockNonCubeBase(name, properties), IBucketPickupHandler, ILiquidContainer {
    init {
        defaultState = this.stateContainer.baseState
                .with(MESH, EnumMeshType.EMPTY)
                .with(WATERLOGGED, false)
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, IBlockState>) {
        builder.add(MESH, WATERLOGGED)
    }

    /**
     * TileEntity related functions
     */
    override fun hasTileEntity(state: IBlockState?): Boolean {
        return true
    }

    override fun createTileEntity(state: IBlockState?, world: IBlockReader?): TileEntity? {
        return TileSieve()
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getRenderType(state: IBlockState): EnumBlockRenderType {
        return EnumBlockRenderType.MODEL
    }

    @Suppress("OverridingDeprecatedMember")
    override fun onReplaced(state: IBlockState, world: World, pos: BlockPos, newState: IBlockState, isMoving: Boolean) {
        // this is needed to keep the mesh from being destroyed by the block update when swapping meshes
        if (state.block != newState.block) {
            super.onReplaced(state, world, pos, newState, isMoving)
            world.removeTileEntity(pos)
        }
    }

    @Suppress("OverridingDeprecatedMember")
    override fun onBlockActivated(state: IBlockState, world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, face: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if(world.isRemote)
            return true
        // Keep all the logic in the TE.
        val te = world.getTileEntity(pos)
        if(te == null)
            player.sendMessage(TextComponentString("TE is null"))
        if(te is TileSieve)
            return te.onBlockActivated(state, player, hand)
        return true
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getShape(state: IBlockState?, worldIn: IBlockReader?, pos: BlockPos?): VoxelShape {
        return SHAPE
    }

    /**
     * Water logged functions copied from BlockChest.
     */
    override fun pickupFluid(world: IWorld, pos: BlockPos, state: IBlockState): Fluid {
        if (state.get(WATERLOGGED)) {
            world.setBlockState(pos, state.with(WATERLOGGED, false), 3)
            return Fluids.WATER
        } else {
            return Fluids.EMPTY
        }
    }

    override fun canContainFluid(reader: IBlockReader, pos: BlockPos, state: IBlockState, fluid: Fluid): Boolean {
        return !state.get(WATERLOGGED) && fluid === Fluids.WATER
    }

    override fun receiveFluid(world: IWorld, pos: BlockPos, blockState: IBlockState, fluidState: IFluidState): Boolean {
        if (!blockState.get(WATERLOGGED) && fluidState.getFluid() === Fluids.WATER) {
            if (!world.isRemote()) {
                world.setBlockState(pos, blockState.with(WATERLOGGED, true), 3)
                world.pendingFluidTicks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
            }
            return true
        }
        else {
            return false
        }
    }


    companion object {
        @JvmStatic
        val WATERLOGGED = BlockStateProperties.WATERLOGGED
        @JvmStatic
        val MESH = EnumProperty.create("mesh", EnumMeshType::class.java)

        val SUB_SHAPES = arrayOf<VoxelShape>(
                Block.makeCuboidShape(0.0, 0.0, 0.0, 2.0, 12.0, 2.0),
                Block.makeCuboidShape(14.0, 0.0, 0.0, 16.0, 12.0, 2.0),
                Block.makeCuboidShape(0.0, 0.0, 14.0, 2.0, 12.0, 16.0),
                Block.makeCuboidShape(14.0, 0.0, 14.0, 16.0, 12.0, 16.0),
                Block.makeCuboidShape(0.0, 8.0, 0.0, 16.0, 12.0, 16.0)
        )
        val SHAPE = VoxelShapeHelper.union(*SUB_SHAPES)

        val sieveProperties = Properties.create(Material.WOOD).hardnessAndResistance(2.0f)
    }
}