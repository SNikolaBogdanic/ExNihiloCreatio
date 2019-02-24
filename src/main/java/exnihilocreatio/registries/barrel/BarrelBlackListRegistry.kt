package exnihilocreatio.registries.barrel

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.api.registries.IBarrelBlackList
import exnihilocreatio.registries.base.BaseRegistryList
import exnihilocreatio.utils.crafting.AbstractStack
import exnihilocreatio.utils.crafting.FluidIngredient
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

class BarrelBlackListRegistry: BaseRegistryList<Ingredient>(), IBarrelBlackList {
    override fun add(ingredient: Ingredient) {
        registry.add(ingredient)
    }

    override fun add(fluid: Fluid) {
        add(FluidIngredient(fluid))
    }

    override fun add(block: Block) {
        add(Ingredient.fromItems(block))
    }

    override fun add(item: Item) {
        add(Ingredient.fromItems(item))
    }

    override fun add(fluidStack: FluidStack) {
        add(FluidIngredient(fluidStack))
    }

    override fun add(itemStack: ItemStack) {
        if(!itemStack.isEmpty)
            add(Ingredient.fromStacks(itemStack))
    }

    override fun add(abstractStack: AbstractStack) {
        if(abstractStack.isItemStack())
            add(abstractStack.itemStack)
        if(abstractStack.isEntityStack()){
            ExNihiloCreatio.logger.error("Attempting to blacklist an entitystack, %s, makes no sense".format(abstractStack.entityStack.toString()))
            return
        }
        if(abstractStack.isFluidStack() && abstractStack.fluidStack != null){
            // A lack of a FluidStack.EMPTY strikes again
            add(abstractStack.fluidStack)
        }
    }

    override fun remove(fluidStack: FluidStack): Boolean {
        val newregistry = registry.filter { ingredient -> !(ingredient is FluidIngredient && ingredient.test(fluidStack)) }
        if(newregistry.size == registry.size)
            return false
        registry.clear()
        registry.addAll(newregistry)
        return true
    }

    override fun remove(itemStack: ItemStack): Boolean {
        val newregistry = registry.filter { ingredient -> !ingredient.test(itemStack) }
        if(newregistry.size == registry.size)
            return false
        registry.clear()
        registry.addAll(newregistry)
        return true
    }

    override fun remove(abstractStack: AbstractStack): Boolean {
        if(abstractStack.isItemStack())
            return remove(abstractStack.itemStack)
        if(abstractStack.isEntityStack()){
            ExNihiloCreatio.logger.error("Attempting to un-blacklist an entitystack, %s, makes no sense".format(abstractStack.entityStack.toString()))
            return false
        }
        if(abstractStack.isFluidStack() && abstractStack.fluidStack != null){
            // A lack of a FluidStack.EMPTY strikes again
            return remove(abstractStack.fluidStack)
        }
        ExNihiloCreatio.logger.error("Failed to understand abstractStack %s".format(abstractStack.toString()))
        return false
    }

    override fun contains(fluidStack: FluidStack): Boolean {
        return registry.any { ingredient -> (ingredient is FluidIngredient && ingredient.test(fluidStack)) }
    }

    override fun contains(itemStack: ItemStack): Boolean {
        return registry.any { ingredient -> ingredient.test(itemStack) }
    }

    override fun contains(abstractStack: AbstractStack): Boolean {
        if(abstractStack.isItemStack())
            return contains(abstractStack.itemStack)
        if(abstractStack.isEntityStack()){
            ExNihiloCreatio.logger.error("Attempting to check the blacklist for an entitystack, %s, makes no sense".format(abstractStack.entityStack.toString()))
            return false
        }
        if(abstractStack.isFluidStack() && abstractStack.fluidStack != null){
            // A lack of a FluidStack.EMPTY strikes again
            return contains(abstractStack.fluidStack)
        }
        ExNihiloCreatio.logger.error("Failed to understand abstractStack %s".format(abstractStack.toString()))
        return false
    }

}