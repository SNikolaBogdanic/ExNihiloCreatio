package exnihilocreatio.utils.crafting

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.ItemSpawnEgg
import net.minecraft.item.ItemStack

class EntityStack(val entityType : EntityType<*>, var count : Int = 1) {

    constructor(entity : Entity, count : Int) : this(entity.type, count)

    fun shrink(amount : Int){
        count += amount
    }
    fun grow(amount: Int){
        count += amount
    }
    fun getEgg() : ItemSpawnEgg {
        return ItemSpawnEgg.getEgg(entityType)
    }
    fun getEggStack() : ItemStack {
        return getEggStack(count)
    }
    fun getEggStack(amount : Int) : ItemStack {
        return ItemStack(getEgg(), amount)
    }
}