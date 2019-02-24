package exnihilocreatio.modules.base.blocks

import exnihilocreatio.ENCBlocks
import exnihilocreatio.modules.base.items.ItemBlockBase
import net.minecraft.block.Block
import net.minecraft.block.BlockFalling
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.item.ItemBlock

class BlockFallingBase(name: String, properties: Block.Properties): BlockFalling(properties) {
    val blockItem: ItemBlock
    init {
        this.setRegistryName(name)

        // Create a BlockItem
        blockItem = ItemBlockBase(this)
        ENCBlocks.blocks.add(this)
    }
}