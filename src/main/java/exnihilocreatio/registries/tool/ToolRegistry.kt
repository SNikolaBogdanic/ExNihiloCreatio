package exnihilocreatio.registries.tool

import exnihilocreatio.api.registries.IToolRegistry
import exnihilocreatio.registries.base.BaseRegistryMap
import exnihilocreatio.registries.base.Lootable
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.init.Enchantments
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.IItemProvider
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.common.ToolType

class ToolRegistry(val toolType: ToolType): BaseRegistryMap<Ingredient, MutableList<Lootable>>(), IToolRegistry {

    /**
     * Register an ingredient and its loot
     */
    override fun register(ingredient: Ingredient, vararg loot: Lootable) {
        if(registry[ingredient]?.addAll(loot) == null){
            registry[ingredient] = mutableListOf(*loot)
        }
    }

    // Alternative register functions
    override fun register(ingredient: Ingredient, stack: ItemStack, chance: Float) {
        register(ingredient, Lootable(stack, chance))
    }
    override fun register(ingredient: Ingredient, result: IItemProvider, chance: Float) {
        register(ingredient, Lootable(result, chance))
    }
    override fun register(item: IItemProvider, vararg loot: Lootable) {
        register(Ingredient.fromItems(item), *loot)
    }
    override fun register(item: IItemProvider, stack: ItemStack, chance: Float) {
        register(Ingredient.fromItems(item), Lootable(stack, chance))
    }
    override fun register(item: IItemProvider, result: IItemProvider, chance: Float) {
        register(Ingredient.fromItems(item), Lootable(result, chance))
    }

    override fun isTarget(state: IBlockState): Boolean {
        return registry.keys.stream().anyMatch { ingredient -> ingredient.test(ItemStack(state.block)) }
    }



    override fun getAllDrops(state: IBlockState): NonNullList<Lootable>{
        return getAllDrops(state.block)
    }

    override fun getAllDrops(block: Block): NonNullList<Lootable>{
        return getAllDrops(ItemStack(block))
    }

    fun getAllDrops(target: ItemStack): NonNullList<Lootable>{
        val loot = NonNullList.create<Lootable>()
        for(entry: MutableMap.MutableEntry<Ingredient, MutableList<Lootable>> in registry.entries){
            if(entry.key.test(target))
                loot.addAll(entry.value)
        }
        return loot
    }

    override fun getLoot(world: World, state: IBlockState, player: EntityPlayer, tool: ItemStack): NonNullList<ItemStack> {
        val drops = NonNullList.create<ItemStack>()
        if(!tool.toolTypes.contains(toolType))
            return drops

        val luck = (EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, tool) + 1.0f)
        for(loot in getAllDrops(state)){
            if(loot.chance * luck > world.random.nextFloat())
                drops.add(loot.drop)
        }
        return drops
    }
}