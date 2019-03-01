package exnihilocreatio

import exnihilocreatio.modules.barrel.TileTypeBarrel
import exnihilocreatio.modules.crucible.TileTypeCrucible
import exnihilocreatio.modules.sieve.TileTypeSieve
import net.minecraft.tileentity.TileEntityType

object ENCTiles {
    val tiles = ArrayList<TileEntityType<*>>()

    init {
        tiles.add(TileTypeSieve)
        tiles.add(TileTypeBarrel)
        tiles.add(TileTypeCrucible)
    }
}