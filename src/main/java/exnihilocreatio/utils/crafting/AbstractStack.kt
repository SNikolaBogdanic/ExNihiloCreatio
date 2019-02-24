package exnihilocreatio.utils.crafting

import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

/**
 * This class can represent one of: ItemStack, FluidStack, EntityStack
 */
class AbstractStack {
    val itemStack: ItemStack
    val fluidStack: FluidStack? //Why is there not a FluidStack.EMPTY
    val entityStack: EntityStack

    constructor(stack: ItemStack){
        itemStack = stack
        fluidStack = null
        entityStack = EntityStack.EMPTY
    }
    constructor(stack: FluidStack){
        itemStack = ItemStack.EMPTY
        fluidStack = stack
        entityStack = EntityStack.EMPTY
    }
    constructor(stack: EntityStack){
        itemStack = ItemStack.EMPTY
        fluidStack = null
        entityStack = stack
    }

    fun isItemStack(): Boolean{
        return !itemStack.isEmpty
    }

    fun isFluidStack(): Boolean{
        return (fluidStack?.amount ?: 0) != 0
    }

    fun isEntityStack(): Boolean{
        return entityStack.isEmpty()
    }

    fun grow(amount: Int){
        if(isItemStack()) itemStack.count += amount
        else if(isFluidStack()) fluidStack!!.amount += amount
        else if(isEntityStack()) entityStack.count += amount
    }
    fun shrink(amount: Int){
        grow(-amount)
    }

    fun isEmpty(): Boolean{
        return isItemStack() || isFluidStack() || isEntityStack()
    }

    companion object {
        val EMPTY = AbstractStack(ItemStack.EMPTY)
    }
}