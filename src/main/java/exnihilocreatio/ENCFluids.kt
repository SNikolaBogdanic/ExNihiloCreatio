package exnihilocreatio

import net.minecraftforge.fluids.Fluid

object ENCFluids {
    val WITCH_WATER = Fluid("witchwater",
            ExNihiloCreatio.createResource("block/witchwater_still"),
            ExNihiloCreatio.createResource("block/witchwater_flow"))
    val MILK = Fluid("milk",
            ExNihiloCreatio.createResource("block/milk_still"),
            ExNihiloCreatio.createResource("block/milk_flow"))
}