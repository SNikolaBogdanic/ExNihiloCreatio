package exnihilocreatio.registries.base

abstract class BaseRegistryMap<K, V>: BaseRegistry<Map<K, V>>() {
    protected val registry: MutableMap<K,V> = HashMap<K,V>()

    override fun clear() {
        registry.clear()
    }

}