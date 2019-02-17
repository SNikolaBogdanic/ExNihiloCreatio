package exnihilocreatio.modules.farming

import exnihilocreatio.MODID
import exnihilocreatio.modules.base.items.ItemBase
import net.minecraft.block.Block
import net.minecraft.item.ItemUseContext
import net.minecraft.util.EnumActionResult

class ItemTransformer(name : String, private val blockMap : Map<Block,Block>) : ItemBase(name) {
    /**
     * Items which transform a block into another when used on it.
     */
    init {
        this.setRegistryName(MODID, name)
    }

    constructor (name : String, fromBlock : Block, toBlock : Block) :
            this(name, hashMapOf(Pair<Block, Block>(fromBlock, toBlock)))

    override fun onItemUse(context: ItemUseContext): EnumActionResult {
        if(context.isPlacerSneaking)
            return super.onItemUse(context)
        if(blockMap.containsKey(context.world.getBlockState(context.pos).block)){
            context.world.setBlockState(context.pos, blockMap.getValue(context.world.getBlockState(context.pos).block).defaultState)
            return EnumActionResult.SUCCESS
        }
        return super.onItemUse(context)
    }
}