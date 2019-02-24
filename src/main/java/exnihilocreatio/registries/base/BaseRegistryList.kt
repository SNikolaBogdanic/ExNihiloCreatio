package exnihilocreatio.registries.base

abstract class BaseRegistryList<T>: BaseRegistry<List<T>>() {
    protected val registry: MutableList<T> = ArrayList<T>()
    override fun clear(){
        registry.clear()
    }
}