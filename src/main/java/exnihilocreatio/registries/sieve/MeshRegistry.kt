package exnihilocreatio.registries.sieve

import exnihilocreatio.api.registries.IMeshRegistry
import exnihilocreatio.modules.sieve.ItemMesh
import exnihilocreatio.registries.base.BaseRegistry
import exnihilocreatio.utils.texturing.Color
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

class MeshRegistry: BaseRegistry<Set<Item>>(), IMeshRegistry {
    val registry = HashSet<Item>()

    override fun clear() {
        registry.clear()
    }
    fun register(mesh: Item) {
        if(mesh is ItemMesh) // TODO consider allowing other items to be used
            registry.add(mesh)
    }

    /**
     * Creates an ItemMesh and saves it. ItemMesh constructor handles registering it with the game
     */
    override fun register(name: String, color: Color) {
        register(ItemMesh(name, color))
    }

    override fun isMesh(item: Item): Boolean {
        return registry.contains(item)
    }

    override fun isMesh(stack: ItemStack): Boolean {
        return isMesh(stack.item)
    }

    override fun getAllMeshes(): NonNullList<Item> {
        val list = NonNullList.create<Item>()
        list.addAll(registry)
        return list
    }
}