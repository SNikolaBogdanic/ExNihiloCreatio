package exnihilocreatio.registries.sieve

import exnihilocreatio.api.registries.ISieveRegistry
import exnihilocreatio.modules.sieve.ItemMesh
import exnihilocreatio.modules.sieve.properties.EnumMeshType
import exnihilocreatio.registries.base.BaseRegistryMap
import exnihilocreatio.registries.base.Lootable
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.init.Enchantments
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.IItemProvider
import net.minecraft.util.NonNullList
import net.minecraft.world.World

class SieveRegistry: BaseRegistryMap<EnumMeshType, MutableMap<Ingredient, NonNullList<Lootable>>>(), ISieveRegistry {
    override fun register(mesh: EnumMeshType, toSieve: Ingredient, vararg loot: Lootable) {
        if(!registry.containsKey(mesh))
            registry[mesh] = HashMap()
        val finalLoot = NonNullList.create<Lootable>()
        finalLoot.addAll(loot)
        registry[mesh]?.put(toSieve, finalLoot)
    }

    /**
     * Alternative register functions, all eventually call the above
     */
    override fun register(mesh: EnumMeshType, toSieve: Ingredient, drop: ItemStack, chance: Float) {
        register(mesh, toSieve, Lootable(drop, chance))
    }
    override fun register(mesh: EnumMeshType, toSieve: Ingredient, drop: IItemProvider, chance: Float) {
        register(mesh, toSieve, Lootable(drop, chance))
    }
    override fun register(mesh: EnumMeshType, toSieve: ItemStack, drop: ItemStack, chance: Float) {
        register(mesh, Ingredient.fromStacks(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: EnumMeshType, toSieve: ItemStack, drop: IItemProvider, chance: Float) {
        register(mesh, Ingredient.fromStacks(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: EnumMeshType, toSieve: IItemProvider, drop: ItemStack, chance: Float) {
        register(mesh, Ingredient.fromItems(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: EnumMeshType, toSieve: IItemProvider, drop: IItemProvider, chance: Float) {
        register(mesh, Ingredient.fromItems(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: Item, toSieve: Ingredient, drop: ItemStack, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, toSieve, Lootable(drop, chance))
    }
    override fun register(mesh: Item, toSieve: Ingredient, drop: IItemProvider, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, toSieve, Lootable(drop, chance))
    }
    override fun register(mesh: Item, toSieve: ItemStack, drop: ItemStack, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, Ingredient.fromStacks(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: Item, toSieve: ItemStack, drop: IItemProvider, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, Ingredient.fromStacks(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: Item, toSieve: IItemProvider, drop: ItemStack, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, Ingredient.fromItems(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: Item, toSieve: IItemProvider, drop: IItemProvider, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, Ingredient.fromItems(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: ItemStack, toSieve: Ingredient, drop: ItemStack, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, toSieve, Lootable(drop, chance))
    }
    override fun register(mesh: ItemStack, toSieve: Ingredient, drop: IItemProvider, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, toSieve, Lootable(drop, chance))
    }
    override fun register(mesh: ItemStack, toSieve: ItemStack, drop: ItemStack, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, Ingredient.fromStacks(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: ItemStack, toSieve: ItemStack, drop: IItemProvider, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, Ingredient.fromStacks(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: ItemStack, toSieve: IItemProvider, drop: ItemStack, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, Ingredient.fromItems(toSieve), Lootable(drop, chance))
    }
    override fun register(mesh: ItemStack, toSieve: IItemProvider, drop: IItemProvider, chance: Float) {
        if(mesh.item is ItemMesh)
            register((mesh.item as ItemMesh).meshType, Ingredient.fromItems(toSieve), Lootable(drop, chance))
    }

    override fun canSieve(stack: ItemStack, mesh: ItemStack): Boolean {
        return if(mesh.item is ItemMesh) canSieve(stack, (mesh.item as ItemMesh).meshType) else false
    }

    override fun canSieve(stack: ItemStack, mesh: EnumMeshType): Boolean {
        for(ingredient in registry[mesh]?.keys ?: return false) {
            if(ingredient.test(stack))
                return true
        }
        return false
    }

    override fun getAllDrops(mesh: EnumMeshType, toSieve: ItemStack): NonNullList<Lootable> {
        val drops = NonNullList.create<Lootable>()
        // Get the sub-registry or a blank registry
        val meshRegistry = registry[mesh] ?: HashMap()
        for(entry in meshRegistry.entries)
            if(entry.key.test(toSieve))
                drops.addAll(entry.value)
        return drops
    }

    override fun getAllDrops(mesh: Item, toSieve: ItemStack): NonNullList<Lootable> {
        return if(mesh !is ItemMesh) NonNullList.create() else getAllDrops(mesh.meshType, toSieve)
    }

    override fun getAllDrops(mesh: ItemStack, toSieve: ItemStack): NonNullList<Lootable> {
        return getAllDrops(mesh.item, toSieve)
    }

    override fun getLoot(world: World, mesh: ItemStack, toSieve: ItemStack, waterLogged: Boolean): NonNullList<ItemStack> {
        val maxCount = 1 + EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, mesh)
        val drops = NonNullList.create<ItemStack>()
        for(loot in getAllDrops(mesh.item, toSieve)) {
            for(i in 0 until maxCount){
                if(world.random.nextFloat() < loot.chance)
                    drops.add(loot.drop.copy())
            }
        }
        return drops
    }

}