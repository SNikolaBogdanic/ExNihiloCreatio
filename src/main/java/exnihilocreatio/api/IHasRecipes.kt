package exnihilocreatio.api

import exnihilocreatio.api.registries.*

/**
 * An interface denoting that a class has a set of functions which alter the Ex Nihilo Creatio registries.
 */
interface IHasRecipes {
    /**
     * Has the potential to create items
     */
    fun registerMeshes(registry: IMeshRegistry){}
    fun registerSieve(registry: ISieveRegistry){}

    fun registerCrook(registry: IToolRegistry){}
    fun registerHammer(registry: IToolRegistry){}

    fun registerCrucibleHeat(registry: ICrucibleHeatRegistry){}
    fun registerWoodCrucible(registry: ICrucibleRegistry){}
    fun registerStoneCrucible(registry: ICrucibleRegistry){}

    fun registerBarrelBlackList(registry: IBarrelBlackList){}
    fun registerBarrelAlchemy(registry: IBarrelAlchemyRegistry){}
}