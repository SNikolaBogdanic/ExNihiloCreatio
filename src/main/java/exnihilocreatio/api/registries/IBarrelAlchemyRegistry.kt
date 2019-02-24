package exnihilocreatio.api.registries

import exnihilocreatio.utils.crafting.AbstractStack
import exnihilocreatio.utils.crafting.EntityStack
import net.minecraft.entity.EntityType
import net.minecraft.entity.monster.EntityMob
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.NonNullList
import net.minecraftforge.fluids.FluidStack

interface IBarrelAlchemyRegistry {
    fun clear()

    fun register(reactant: Ingredient, catalyst: Ingredient, product: FluidStack, reactantConsumed: Boolean = true, catalystConsumed: Boolean = true)
    fun register(reactant: Ingredient, catalyst: Ingredient, product: ItemStack, reactantConsumed: Boolean = true, catalystConsumed: Boolean = true)
    fun register(reactant: Ingredient, catalyst: Ingredient, product: EntityStack, reactantConsumed: Boolean = true, catalystConsumed: Boolean = true)
    fun register(reactant: Ingredient, catalyst: Ingredient, product: EntityType<EntityMob>, reactantConsumed: Boolean = true, catalystConsumed: Boolean = true)

    fun register(reactant: FluidStack, catalyst: ItemStack, product: FluidStack, reactantConsumed: Boolean = true, catalystConsumed: Boolean = true)
    fun register(reactant: FluidStack, catalyst: ItemStack, product: ItemStack, reactantConsumed: Boolean = true, catalystConsumed: Boolean = true)
    fun register(reactant: FluidStack, catalyst: ItemStack, product: EntityStack, reactantConsumed: Boolean = true, catalystConsumed: Boolean = true)
    fun register(reactant: FluidStack, catalyst: ItemStack, product: EntityType<EntityMob>, reactantConsumed: Boolean = true, catalystConsumed: Boolean = true)

    fun getResult(reactant: ItemStack, catalyst: ItemStack, simulate: Boolean = false): NonNullList<AbstractStack>
    fun getResult(reactant: FluidStack, catalyst: ItemStack, simulate: Boolean = false): NonNullList<AbstractStack>
}