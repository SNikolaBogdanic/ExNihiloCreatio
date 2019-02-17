package exnihilocreatio.utils

import net.minecraft.item.ItemStack

object ItemUtils {
    fun StackOfOne(stack : ItemStack?) : ItemStack {
        val copy = stack?.copy() ?: ItemStack.EMPTY
        if(!copy.isEmpty) copy.count = 1
        return copy
    }
}