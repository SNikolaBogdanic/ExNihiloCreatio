package exnihilocreatio.api.registries

import exnihilocreatio.utils.crafting.AbstractStack
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

interface IBarrelBlackList {
    fun clear()

    fun add(ingredient: Ingredient)

    fun add(fluid: Fluid)
    fun add(block: Block)
    fun add(item: Item)

    fun add(fluidStack: FluidStack)
    fun add(itemStack: ItemStack)
    fun add(abstractStack: AbstractStack)

    fun remove(fluidStack: FluidStack): Boolean
    fun remove(itemStack: ItemStack): Boolean
    fun remove(abstractStack: AbstractStack): Boolean

    fun contains(fluidStack: FluidStack): Boolean
    fun contains(itemStack: ItemStack): Boolean
    fun contains(abstractStack: AbstractStack): Boolean
}