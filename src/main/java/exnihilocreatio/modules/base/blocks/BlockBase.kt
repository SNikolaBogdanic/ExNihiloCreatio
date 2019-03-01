package exnihilocreatio.modules.base.blocks

import exnihilocreatio.ENCBlocks
import exnihilocreatio.modules.base.items.ItemBlockBase
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.ItemBlock

abstract class BlockBase(name: String, properties: Properties): Block(properties) {
    val blockItem: ItemBlock
    init {
        this.setRegistryName(name)

        // Create a BlockItem
        blockItem = ItemBlockBase(this)
        ENCBlocks.blocks.add(this)
    }
}