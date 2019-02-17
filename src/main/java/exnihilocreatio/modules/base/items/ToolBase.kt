package exnihilocreatio.modules.base.items

import exnihilocreatio.MODID
import net.minecraft.block.Block
import net.minecraft.item.IItemTier
import net.minecraft.item.Item
import net.minecraft.item.ItemTool

abstract class ToolBase(name : String,
                        attackDamage : Float,
                        attackSpeed : Float,
                        tier : IItemTier,
                        effectiveBlocks : Set<Block>,
                        builder : Item.Properties) : ItemTool(attackDamage, attackSpeed, tier, effectiveBlocks, builder) {
    init {
        this.setRegistryName(MODID, name)
    }

}