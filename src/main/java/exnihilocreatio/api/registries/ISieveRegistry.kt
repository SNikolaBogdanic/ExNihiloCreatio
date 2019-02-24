package exnihilocreatio.api.registries

import exnihilocreatio.registries.base.Lootable
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.NonNullList
import net.minecraft.world.World

interface ISieveRegistry {
    fun clear()

    fun register(mesh: Item, toSieve: Ingredient, vararg loot: Lootable)

    fun register(mesh: Item, toSieve: Ingredient, drop: ItemStack, chance: Float)
    fun register(mesh: Item, toSieve: Block, drop: ItemStack, chance: Float)
    fun register(mesh: Item, toSieve: ItemStack, drop: ItemStack, chance: Float)
    fun register(mesh: ItemStack, toSieve: Ingredient, drop: ItemStack, chance: Float)
    fun register(mesh: ItemStack, toSieve: Block, drop: ItemStack, chance: Float)
    fun register(mesh: ItemStack, toSieve: ItemStack, drop: ItemStack, chance: Float)

    fun getAllDrops(mesh: Item, toSieve: ItemStack): NonNullList<Lootable>
    fun getAllDrops(mesh: ItemStack, toSieve: ItemStack): NonNullList<Lootable>
    fun getLoot(world: World, mesh: ItemStack, toSieve: ItemStack, waterLogged: Boolean = false, simulate: Boolean = false): NonNullList<ItemStack>
}