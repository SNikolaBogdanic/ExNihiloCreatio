package exnihilocreatio.api

import exnihilocreatio.registries.RegistryManager

object ExNihiloCreatioAPI {
    // Barrel Registries
//    val ALCHEMY_REGISTRY: IBarrelAlchemyRegistry = RegistryManager.ALCHEMY_REGISTRY
    val WOOD_BARREL_BLACKLIST = RegistryManager.WOOD_BARREL_BLACKLIST

    // Tools
    val CROOK_REGISTRY = RegistryManager.CROOK_REGISTRY
    val HAMMER_REGISTRY = RegistryManager.HAMMER_REGISTRY

    // Crucibles
//    val STONE_CRUCIBLE_REGISTRY: ICrucibleRegistry = RegistryManager.STONE_CRUCIBLE_REGISTRY
//    val WOOD_CRUCIBLE_REGISTRY: ICrucibleRegistry = RegistryManager.WOOD_CRUCIBLE_REGISTRY
//    val CRUCIBLE_HEAT_REGISTRY: ICrucibleHeatRegistry = RegistryManager.CRUCIBLE_HEAT_REGISTRY

    val SIEVE_REGISTRY = RegistryManager.SIEVE_REGISTRY

    // These events are fired when recipes should be loaded. Add event listeners that listen for these.


}