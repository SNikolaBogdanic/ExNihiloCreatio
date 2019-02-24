package exnihilocreatio.registries

import exnihilocreatio.api.ToolTypes
import exnihilocreatio.api.registries.IBarrelBlackList
import exnihilocreatio.api.registries.IMeshRegistry
import exnihilocreatio.api.registries.ISieveRegistry
import exnihilocreatio.api.registries.IToolRegistry
import exnihilocreatio.registries.barrel.BarrelBlackListRegistry
import exnihilocreatio.registries.sieve.MeshRegistry
import exnihilocreatio.registries.sieve.SieveRegistry
import exnihilocreatio.registries.tool.ToolRegistry

/**
 * Holds the registry objects and handles loading/parsing/saving them
 *
 * All held registries are cast to their API interface.
 */
object RegistryManager {
//    val ALCHEMY_REGISTRY =  as IBarrelAlchemyRegistry
    val WOOD_BARREL_BLACKLIST = BarrelBlackListRegistry() as IBarrelBlackList

    val CROOK_REGISTRY = ToolRegistry(ToolTypes.CROOK) as IToolRegistry
    val HAMMER_REGISTRY = ToolRegistry(ToolTypes.HAMMER) as IToolRegistry
//
//    val STONE_CRUCIBLE_REGISTRY =  as ICrucibleRegistry
//    val WOOD_CRUCIBLE_REGISTRY =  as ICrucibleRegistry
//    val CRUCIBLE_HEAT_REGISTRY =  as ICrucibleHeatRegistry

    val MESH_REGISTRY = MeshRegistry() as IMeshRegistry
    val SIEVE_REGISTRY = SieveRegistry() as ISieveRegistry
}