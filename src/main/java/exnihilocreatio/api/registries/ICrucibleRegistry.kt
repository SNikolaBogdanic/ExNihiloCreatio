package exnihilocreatio.api.registries

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.fluids.FluidStack

interface ICrucibleRegistry {
    fun clear()

    fun register(ingredient: Ingredient, fluidStack: FluidStack)

    fun register(item: Item, fluidStack: FluidStack)
    fun register(block: Block, fluidStack: FluidStack)
    fun register(itemStack: ItemStack, fluidStack: FluidStack)

    fun getResult(itemStack: ItemStack): FluidStack

}