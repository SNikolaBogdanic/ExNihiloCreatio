package exnihilocreatio.modules.base.items

import exnihilocreatio.ENCRegistry
import exnihilocreatio.ExNihiloCreatio
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock

class ItemBlockBase(block: Block) : ItemBlock(block, Item.Properties().group(ExNihiloCreatio.itemGroup)) {
    init {
        registryName = block.registryName
        ENCRegistry.items.add(this)
    }

}