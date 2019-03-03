package exnihilocreatio.config

import net.minecraftforge.common.ForgeConfigSpec

class SilkConfig {

    val ENABLED: ForgeConfigSpec.BooleanValue
    val TRANSFORM_TIME: ForgeConfigSpec.IntValue
    val UPDATED_FREQUENCY: ForgeConfigSpec.IntValue
    val MINIMUM_SPREAD: ForgeConfigSpec.DoubleValue
    val SPREAD_CHANCE: ForgeConfigSpec.DoubleValue

    constructor(SERVER_BUILDER: ForgeConfigSpec.Builder, CLIENT_BUILDER: ForgeConfigSpec.Builder) {
        SERVER_BUILDER.comment("Silkworms")
        ENABLED = SERVER_BUILDER
                .comment("Enable the Silkworms Module")
                .define("silk.enabled", true)

        TRANSFORM_TIME = SERVER_BUILDER
                .comment("How many ticks does it take for infested leaves to fully infest")
                .defineInRange("silk.transform_time", 300, 0, 36000)

        UPDATED_FREQUENCY = SERVER_BUILDER
                .comment("How many ticks between progress updates/syncs")
                .defineInRange("silk.updated_frequency", 5, 1, 20)

        MINIMUM_SPREAD = SERVER_BUILDER
                .comment("Minimum progress needed before the infestation spreads")
                .defineInRange("silk.minimum_spread", 0.15, 0.0, 1.0)

        SPREAD_CHANCE = SERVER_BUILDER
                .comment("How likely is an infestion to succeed at spreading when it attempts to")
                .defineInRange("silk.spread_chance", 0.5, 0.0, 1.0)

    }
}