package exnihilocreatio.modules.tools

import exnihilocreatio.modules.base.items.ToolBase
import net.minecraft.block.Block
import net.minecraft.item.IItemTier
import net.minecraft.item.Item

class ToolHammer(name : String, tier : IItemTier) : ToolBase(name,6f, -1f, tier, effective,
        Item.Properties().maxStackSize(1).addToolType(ToolTypeSmashing ,tier.harvestLevel)) {
    companion object {
        val effective = HashSet<Block>()
    }
}