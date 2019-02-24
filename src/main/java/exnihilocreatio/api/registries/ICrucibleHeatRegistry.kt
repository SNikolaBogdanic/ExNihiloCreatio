package exnihilocreatio.api.registries

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.item.crafting.Ingredient

interface ICrucibleHeatRegistry {
    fun clear()

    fun register(ingredient: Ingredient, heat: Int)
    fun register(block: Block, heat: Int) //
    fun register(blockState: IBlockState, heat: Int) // A specific state, i.e. flowing fluids; use Block or Ingredient instead

    fun getHeat(blockState: IBlockState): Int

}