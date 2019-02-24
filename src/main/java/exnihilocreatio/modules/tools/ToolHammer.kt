package exnihilocreatio.modules.tools

import exnihilocreatio.api.ExNihiloCreatioAPI
import exnihilocreatio.api.ToolTypes
import exnihilocreatio.modules.base.items.ToolBase
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.item.IItemTier
import net.minecraft.item.Item

class ToolHammer(name: String, tier: IItemTier): ToolBase(name,6f, -1f, tier, effective,
        Item.Properties().maxStackSize(1).addToolType(ToolTypes.HAMMER ,tier.harvestLevel)) {

    override fun canHarvestBlock(state: IBlockState): Boolean {
        return ExNihiloCreatioAPI.HAMMER_REGISTRY.isTarget(state)
    }

    companion object {
        val effective = HashSet<Block>() // Unused
    }
}