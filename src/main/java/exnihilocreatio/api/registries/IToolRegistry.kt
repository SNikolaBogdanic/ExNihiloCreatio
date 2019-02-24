package exnihilocreatio.api.registries

import exnihilocreatio.registries.base.Lootable
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.IItemProvider
import net.minecraft.util.NonNullList
import net.minecraft.world.World

interface IToolRegistry {
    fun clear()

    /**
     * This is the preferred register function, all other register functions eventually are reduced to this.
     */
    fun register(ingredient: Ingredient, vararg loot: Lootable)
    fun register(ingredient: Ingredient, stack: ItemStack, chance: Float = 1.0f)

    fun register(item: IItemProvider, vararg loot: Lootable)
    fun register(item: IItemProvider, stack: ItemStack, chance: Float = 1.0f)
    fun register(item: IItemProvider, result: IItemProvider, chance: Float = 1.0f)

    /**
     * Get all targets that have results. This function is used to check if a block can be harvested
     */
    fun isTarget(state: IBlockState): Boolean

    /**
     * Get all possible drops from a given IBlockState
     */
    fun getAllDrops(state: IBlockState): NonNullList<Lootable>

    /**
     * Get all possible drops from a given Block
     */
    fun getAllDrops(block: Block): NonNullList<Lootable>

    /**
     * Get a random selection of loot based on the player and tool used.
     */
    fun getLoot(world: World, state: IBlockState, player: EntityPlayer, tool: ItemStack): NonNullList<ItemStack>
}