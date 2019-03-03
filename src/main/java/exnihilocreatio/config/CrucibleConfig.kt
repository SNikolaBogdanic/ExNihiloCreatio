package exnihilocreatio.config

import net.minecraftforge.common.ForgeConfigSpec

class CrucibleConfig {
    val ENABLED: ForgeConfigSpec.BooleanValue
    val WOOD_SPEED: ForgeConfigSpec.IntValue

    constructor(SERVER_BUILDER: ForgeConfigSpec.Builder, CLIENT_BUILDER: ForgeConfigSpec.Builder) {
        SERVER_BUILDER.comment("Crucibles")

        ENABLED = SERVER_BUILDER
                .comment("Enable the Crucible Module")
                .define("crucible.enabled", true)

        WOOD_SPEED = SERVER_BUILDER
                .comment("Speed of a wooden crucible")
                .defineInRange("crucible.wood)speed", 4, 1, Int.MAX_VALUE)
    }
}