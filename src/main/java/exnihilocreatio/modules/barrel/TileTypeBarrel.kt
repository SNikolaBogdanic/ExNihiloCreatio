package exnihilocreatio.modules.barrel

import exnihilocreatio.MODID
import net.minecraft.tileentity.TileEntityType
import java.util.function.Supplier

object TileTypeBarrel : TileEntityType<TileBarrel>(Supplier { TileBarrel() }, null) {
    init {
        this.setRegistryName(MODID, "barrel")
    }
}