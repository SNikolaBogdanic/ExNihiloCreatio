package exnihilocreatio.modules.base.items

import exnihilocreatio.ENCItems
import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.MODID
import net.minecraft.item.Item

abstract class ItemBase(name: String): Item(Item.Properties().group(ExNihiloCreatio.itemGroup)) {
    init {
        this.setRegistryName(MODID, name)
        ENCItems.items.add(this)
    }
}