package exnihilocreatio.config

import net.minecraftforge.common.ForgeConfigSpec

class ToolConfig {
    val ENABLED_CROOK: ForgeConfigSpec.BooleanValue
    val ENABLED_HAMMER: ForgeConfigSpec.BooleanValue
    val CROOK_VANILLA_TRIES: ForgeConfigSpec.IntValue

    constructor(SERVER_BUILDER: ForgeConfigSpec.Builder, CLIENT_BUILDER: ForgeConfigSpec.Builder) {
        ENABLED_HAMMER = SERVER_BUILDER
                .comment("Enable the Hammer Modules")
                .define("hammer.enabled", true)

        ENABLED_CROOK = SERVER_BUILDER
                .comment("Enabled the Crook Module")
                .define("crook.enabled", true)
        CROOK_VANILLA_TRIES = SERVER_BUILDER
                .comment("How many times to attempt to drop vanilla drops with a crook")
                .defineInRange("crook.vanilla_tries", 3, 0, 15)
    }
}