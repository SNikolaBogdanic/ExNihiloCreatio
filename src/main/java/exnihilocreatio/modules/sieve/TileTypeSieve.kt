package exnihilocreatio.modules.sieve

import exnihilocreatio.MODID
import net.minecraft.tileentity.TileEntityType
import java.util.function.Supplier

object TileTypeSieve: TileEntityType<TileSieve>(Supplier { TileSieve() }, null) {
    init {
        this.setRegistryName(MODID, "sieve")
    }
}