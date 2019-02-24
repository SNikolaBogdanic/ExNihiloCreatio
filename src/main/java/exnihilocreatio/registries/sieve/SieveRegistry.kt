package exnihilocreatio.registries.sieve

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.api.registries.ISieveRegistry
import exnihilocreatio.modules.sieve.ItemMesh
import exnihilocreatio.registries.base.BaseRegistryMap
import exnihilocreatio.registries.base.Lootable
import net.minecraft.block.Block
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.init.Enchantments
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.NonNullList
import net.minecraft.world.World

class SieveRegistry: BaseRegistryMap<ItemMesh, Map<Ingredient, NonNullList<Lootable>>>(), ISieveRegistry {

    override fun register(mesh: Item, toSieve: Ingredient, vararg loot: Lootable) {
        if(mesh !is ItemMesh) {
            ExNihiloCreatio.logger.error("Attempted to register sieve drops for non-mesh: %s".format(mesh.toString()))
            return
        }

    }

    /**
     * Alternative register functions, all eventually call the above
     */
    override fun register(mesh: Item, toSieve: Ingredient, drop: ItemStack, chance: Float) {
        register(mesh, toSieve, Lootable(drop, chance))
    }
    override fun register(mesh: Item, toSieve: Block, drop: ItemStack, chance: Float) {
        register(mesh, Ingredient.fromItems(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: Item, toSieve: ItemStack, drop: ItemStack, chance: Float) {
        register(mesh, Ingredient.fromStacks(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: ItemStack, toSieve: Ingredient, drop: ItemStack, chance: Float) {
        register(mesh.item, toSieve, Lootable(drop, chance))
    }
    override fun register(mesh: ItemStack, toSieve: Block, drop: ItemStack, chance: Float) {
        register(mesh.item, Ingredient.fromItems(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: ItemStack, toSieve: ItemStack, drop: ItemStack, chance: Float) {
        register(mesh.item, Ingredient.fromStacks(toSieve), Lootable(drop, chance))
    }

    override fun getAllDrops(mesh: Item, toSieve: ItemStack): NonNullList<Lootable> {
        val drops = NonNullList.create<Lootable>()
        // Get the sub-registry or a blank registry
        val meshRegistry = registry[mesh] ?: HashMap<Ingredient, NonNullList<Lootable>>()
        for(entry in meshRegistry.entries)
            if(entry.key.test(toSieve))
                drops.addAll(entry.value)
        return drops
    }

    override fun getAllDrops(mesh: ItemStack, toSieve: ItemStack): NonNullList<Lootable> {
        return getAllDrops(mesh.item, toSieve)
    }

    override fun getLoot(world: World, mesh: ItemStack, toSieve: ItemStack, waterLogged: Boolean, simulate: Boolean): NonNullList<ItemStack> {
        val maxCount = 1 + EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, mesh)
        val drops = NonNullList.create<ItemStack>()
        for(loot in getAllDrops(mesh.item, toSieve)) {
            for(i in 0..maxCount){
                if(world.random.nextFloat() < loot.chance)
                    drops.add(loot.drop.copy())
            }
        }
        return drops
    }

}