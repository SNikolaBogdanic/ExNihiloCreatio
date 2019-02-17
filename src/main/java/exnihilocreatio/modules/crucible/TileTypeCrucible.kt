package exnihilocreatio.modules.crucible

import exnihilocreatio.MODID
import net.minecraft.tileentity.TileEntityType
import java.util.function.Supplier

object TileTypeCrucible : TileEntityType<TileCrucible>(Supplier { TileCrucible() }, null) {
    init {
        this.setRegistryName(MODID, "crucible")
    }
}