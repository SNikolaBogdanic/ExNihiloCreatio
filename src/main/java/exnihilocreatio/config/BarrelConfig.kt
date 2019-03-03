package exnihilocreatio.config

import net.minecraftforge.common.ForgeConfigSpec

class BarrelConfig {
    val ENABLED: ForgeConfigSpec.BooleanValue

    val FILL_WITH_RAIN: ForgeConfigSpec.BooleanValue
    val WOOD_MAX_TEMP: ForgeConfigSpec.IntValue

    val BARREL_LIGHT: ForgeConfigSpec.BooleanValue
    val BARREL_LIGHT_TRANSFORM: ForgeConfigSpec.BooleanValue

    constructor(SERVER_BUILDER: ForgeConfigSpec.Builder, CLIENT_BUILDER: ForgeConfigSpec.Builder) {
        SERVER_BUILDER.comment("Barrels")
        ENABLED = SERVER_BUILDER
                .comment("Enable the Barrel Module")
                .define("barrel.enabled", true)
        FILL_WITH_RAIN = SERVER_BUILDER
                .comment("Barrels fill with rain water")
                .define("barrel.fill_with_rain", true)

        WOOD_MAX_TEMP = SERVER_BUILDER
                .comment("Maximum fluid temperature that can go in a wooden barrel")
                .defineInRange("barrel.wood_max_temp", 350, 0, Int.MAX_VALUE)

        BARREL_LIGHT = CLIENT_BUILDER
                .comment("Do barrels emit light when their contents do")
                .define("barrel.light", true)

        BARREL_LIGHT_TRANSFORM = CLIENT_BUILDER
                .comment("Do barrels emit light while creating liquids that emit light")
                .define("barrel.light_transform", true)
    }
}