package exnihilocreatio.api.registries

import exnihilocreatio.utils.texturing.Color
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

interface IMeshRegistry {
    fun clear()

    fun register(name:String, color: Color)

    fun isMesh(item: Item): Boolean
    fun isMesh(stack: ItemStack): Boolean

    fun getAllMeshes(): NonNullList<Item>
}