package exnihilocreatio

object ENCConfig {
    object Basic {
        var enableYAMLConfigs = false
    }
    object Client {

    }
    object Sieve {
        var enabled = true
        var flattenMeshTiers = false

        var fakePlayersCanUse = false
        var setFireToMacroUser = false

        var meshStackSize = 16
        var meshDamage = false

        var radius = 2

        // How much to increase sieving progress each click.
        var progress = 0.1

        // Water Logged Sieves use the fishing loot tables
        var waterLoggedSieves = true

        // Enchantments

        // Haste Beacons/Potions increase progress by level * this
        var hasteSpeedMultiplier = 1.6
        // Efficiency can be added to meshes
        var allowEfficiency = true
        // Fortune can be added to meshes
        var allowFortune = true
    }
    object Barrel {
        var enabled = true
        var barrelsFillWithRain = true

        // Default maximum temperature for wooden barrels (used to dynamically generate a blacklist)
        var woodBarrelMaxTemp = 301

        // Barrels containing liquids that emit light will emit light. Causes lighting updates. If disabled overrides B:enableBarrelTransformLighting
        var enableBarrelLighting = true
        // Barrels generating liquids that emit light will emit light. Causes lighting updates.
        var enableBarrelTransformLighting = true
    }
    object Crucible {
        var enabled = true
        var woodCrucibleSpeed = 4
    }
    object Crook {
        var enabled = true
        // How many times should drops from the vanilla drop function be called
        var vanillaDropTries = 3
    }
    object Hammer {
        var enabled = true
    }
    object InfestedLeaves {
        var enabled = true
        var ticksToTransform = 600
        // How many ticks between progress increments
        var updateFrequency = 5
        // Minimum progress a block needs to be infected in order to infect others
        var minimumProgressToSpread = 15
        // How likely is a block to succeed at spreading when it attempts to
        var spreadChance = 0.5f
    }
    object Compatibility {

    }
}