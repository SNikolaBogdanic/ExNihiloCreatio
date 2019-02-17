package exnihilocreatio.modules.tools

import exnihilocreatio.modules.base.items.ToolBase
import net.minecraft.block.Block
import net.minecraft.item.IItemTier
import net.minecraft.item.Item

class ToolCrook(name : String, tier : IItemTier) : ToolBase(name, 0f, -3f, tier, effective,
        Item.Properties().maxStackSize(1).addToolType(ToolTypeCrook ,tier.harvestLevel)) {
    companion object {
        val effective = HashSet<Block>()
    }
}