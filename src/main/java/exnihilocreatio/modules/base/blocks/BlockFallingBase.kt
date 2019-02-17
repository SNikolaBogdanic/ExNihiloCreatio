package exnihilocreatio.modules.base.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockFalling
import net.minecraft.block.material.Material

class BlockFallingBase(name : String, mat : Material) : BlockFalling(Block.Properties.create(mat, mat.color)) {
    init {
        this.setRegistryName(name)
    }
}