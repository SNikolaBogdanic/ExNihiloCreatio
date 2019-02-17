package exnihilocreatio.api.registry

import net.minecraft.block.Block
import net.minecraft.item.ItemStack

interface ISieveRegistry {
    fun register(toSieve : Block, drop : ItemStack, chance : Float);
    fun register(toSieve : ItemStack, drop : ItemStack, chance : Float);
}