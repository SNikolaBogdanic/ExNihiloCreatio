package exnihilocreatio.config

import net.minecraftforge.common.ForgeConfigSpec

class SieveConfig {
    val ENABLED: ForgeConfigSpec.BooleanValue

    val FLATTEN_MESH_TIERS: ForgeConfigSpec.BooleanValue
    val ALLOW_FAKE_PLAYERS: ForgeConfigSpec.BooleanValue
    val RADIUS: ForgeConfigSpec.IntValue
    val PROGRESS: ForgeConfigSpec.DoubleValue

    val MESH_STACK_LIMIT: ForgeConfigSpec.IntValue
    val MESH_DAMAGE: ForgeConfigSpec.BooleanValue

    val HASTE_MULTIPLIER: ForgeConfigSpec.DoubleValue
    val MESH_EFFICIENCY: ForgeConfigSpec.BooleanValue
    val MESH_FORTUNE: ForgeConfigSpec.BooleanValue

    constructor(SERVER_BUILDER: ForgeConfigSpec.Builder, CLIENT_BUILDER: ForgeConfigSpec.Builder) {
        SERVER_BUILDER.comment("Sieves")
        ENABLED = SERVER_BUILDER
                .comment("Enable the Sieve Module")
                .define("sieve.enabled", true)
        FLATTEN_MESH_TIERS = SERVER_BUILDER
                .comment("Allow each mesh tier to produce the results of lower tiers")
                .define("sieve.flatten_meshes", true)

        ALLOW_FAKE_PLAYERS = SERVER_BUILDER
                .comment("Allow fake players to use sieves")
                .define("sieve.allow_fake_players", true)

        RADIUS = SERVER_BUILDER
                .comment("Radius of sieving operations")
                .defineInRange("sieve.radius", 2, 0, 15)

        PROGRESS = SERVER_BUILDER
                .comment("How much to increment progress with each click")
                .defineInRange("sieve.progress", 0.1, 0.01, 1.0)

        MESH_STACK_LIMIT = SERVER_BUILDER
                .comment("How many meshes can be in one stack")
                .defineInRange("sieve.mesh_stack_limit", 64, 1, 64)

        MESH_DAMAGE = SERVER_BUILDER
                .comment("Do meshes take damage when sieving")
                .define("sieve.mesh_damage", false)

        HASTE_MULTIPLIER = SERVER_BUILDER
                .comment("Multiplier for haste beacon speed increase")
                .defineInRange("sieve.haste", 1.6, 1.0, 100.0)

        MESH_EFFICIENCY = SERVER_BUILDER
                .comment("Efficiency can be applied to meshes")
                .define("sieve.efficiency", true)

        MESH_FORTUNE = SERVER_BUILDER
                .comment("Fortune can be applied to meshes")
                .define("sieve.fortune", true)
    }
}