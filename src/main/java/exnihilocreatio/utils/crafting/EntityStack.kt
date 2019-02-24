package exnihilocreatio.utils.crafting

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.ItemSpawnEgg
import net.minecraft.item.ItemStack

class EntityStack(val entityType: EntityType<*>, var count: Int = 1) {

    constructor(entity: Entity, count: Int): this(entity.type, count)

    fun shrink(amount: Int){
        grow(-amount)
    }

    fun grow(amount: Int){
        if(!isEmpty())
            count += amount
    }

    fun getEgg(): ItemSpawnEgg {
        return ItemSpawnEgg.getEgg(entityType)
    }

    fun getEggStack(): ItemStack {
        return getEggStack(count)
    }

    fun getEggStack(amount: Int): ItemStack {
        return ItemStack(getEgg(), amount)
    }

    fun isEmpty(): Boolean{
        return this == EMPTY || count == 0
    }

    override fun toString(): String {
        return "{\"entitystack\": \"%s\", \"count\": %s}".format(entityType.toString(), count)
    }

    companion object {
        @JvmStatic
        val EMPTY = EntityStack(EntityType.PIG, 0)
    }
}