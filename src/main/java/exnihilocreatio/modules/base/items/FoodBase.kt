package exnihilocreatio.modules.base.items

import exnihilocreatio.ENCItems
import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.MODID
import net.minecraft.item.Item
import net.minecraft.item.ItemFood

class FoodBase(name: String, healAmountIn: Int, saturation: Float, meat: Boolean) :
        ItemFood(healAmountIn, saturation, meat, Item.Properties().group(ExNihiloCreatio.itemGroup)) {
    init {
        this.setRegistryName(MODID, name)
        ENCItems.items.add(this)
    }
}