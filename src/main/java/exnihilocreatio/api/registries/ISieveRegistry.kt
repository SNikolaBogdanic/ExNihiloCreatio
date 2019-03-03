package exnihilocreatio.api.registries

import exnihilocreatio.modules.sieve.properties.EnumMeshType
import exnihilocreatio.registries.base.Lootable
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.IItemProvider
import net.minecraft.util.NonNullList
import net.minecraft.world.World

interface ISieveRegistry {
    fun clear()

    fun canSieve(stack: ItemStack, mesh: ItemStack): Boolean
    fun canSieve(stack: ItemStack, mesh: EnumMeshType): Boolean

    fun register(mesh: EnumMeshType, toSieve: Ingredient, vararg loot: Lootable)

    /**
     * Alternative register functions, all eventually call the above
     */
    fun register(mesh: EnumMeshType, toSieve: Ingredient,    drop: ItemStack,     chance: Float = 1.0f)
    fun register(mesh: EnumMeshType, toSieve: Ingredient,    drop: IItemProvider, chance: Float = 1.0f)
    fun register(mesh: EnumMeshType, toSieve: ItemStack,     drop: ItemStack,     chance: Float = 1.0f)
    fun register(mesh: EnumMeshType, toSieve: ItemStack,     drop: IItemProvider, chance: Float = 1.0f)
    fun register(mesh: EnumMeshType, toSieve: IItemProvider, drop: ItemStack,     chance: Float = 1.0f)
    fun register(mesh: EnumMeshType, toSieve: IItemProvider, drop: IItemProvider, chance: Float = 1.0f)

    fun register(mesh: Item, toSieve: Ingredient,    drop: ItemStack,     chance: Float = 1.0f)
    fun register(mesh: Item, toSieve: Ingredient,    drop: IItemProvider, chance: Float = 1.0f)
    fun register(mesh: Item, toSieve: ItemStack,     drop: ItemStack,     chance: Float = 1.0f)
    fun register(mesh: Item, toSieve: ItemStack,     drop: IItemProvider, chance: Float = 1.0f)
    fun register(mesh: Item, toSieve: IItemProvider, drop: ItemStack,     chance: Float = 1.0f)
    fun register(mesh: Item, toSieve: IItemProvider, drop: IItemProvider, chance: Float = 1.0f)

    fun register(mesh: ItemStack, toSieve: Ingredient,    drop: ItemStack,     chance: Float = 1.0f)
    fun register(mesh: ItemStack, toSieve: Ingredient,    drop: IItemProvider, chance: Float = 1.0f)
    fun register(mesh: ItemStack, toSieve: ItemStack,     drop: ItemStack,     chance: Float = 1.0f)
    fun register(mesh: ItemStack, toSieve: ItemStack,     drop: IItemProvider, chance: Float = 1.0f)
    fun register(mesh: ItemStack, toSieve: IItemProvider, drop: ItemStack,     chance: Float = 1.0f)
    fun register(mesh: ItemStack, toSieve: IItemProvider, drop: IItemProvider, chance: Float = 1.0f)

    fun getAllDrops(mesh: EnumMeshType, toSieve: ItemStack): NonNullList<Lootable>
    fun getAllDrops(mesh: Item, toSieve: ItemStack): NonNullList<Lootable>
    fun getAllDrops(mesh: ItemStack, toSieve: ItemStack): NonNullList<Lootable>
    fun getLoot(world: World, mesh: ItemStack, toSieve: ItemStack, waterLogged: Boolean = false): NonNullList<ItemStack>
}