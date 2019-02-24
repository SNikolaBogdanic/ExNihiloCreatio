package exnihilocreatio.registries.base

import net.minecraft.item.ItemStack
import net.minecraft.util.IItemProvider

data class Lootable(val drop: ItemStack, val chance: Float) {
    constructor(drop: IItemProvider, chance: Float): this(ItemStack(drop), chance)
}