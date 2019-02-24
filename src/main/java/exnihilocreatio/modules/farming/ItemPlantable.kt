package exnihilocreatio.modules.farming

import exnihilocreatio.modules.base.items.ItemBase
import net.minecraft.item.ItemUseContext
import net.minecraft.util.EnumActionResult
import net.minecraftforge.common.IPlantable

class ItemPlantable(name: String, val plants: List<IPlantable>): ItemBase(name) {

    /**
     * Items which place an IPlantable when used.
     */

    constructor(name: String, plant: IPlantable): this(name, listOf(plant))

    override fun onItemUse(context: ItemUseContext): EnumActionResult {
        if(context.world.isRemote)
            return EnumActionResult.SUCCESS
        val plantPos = context.pos.offset(context.face)
        var idx = random.nextInt(plants.size)
        for(i in 0..plants.size){
            idx = (134775813*idx + 1) % plants.size
            val plant = plants[idx]
            if(context.world.getBlockState(context.pos).canSustainPlant(context.world, context.pos, context.face, plant)){
                context.world.setBlockState(plantPos, plant.getPlant(context.world, plantPos))
                if(context.player?.isCreative != true)
                    context.item.shrink(1)
                return EnumActionResult.SUCCESS
            }
        }
        return EnumActionResult.PASS

    }
}