package exnihilocreatio.registries.base

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import java.util.stream.Collectors

class LootList : ArrayList<Lootable>() {
    fun getAllLootables() : List<Lootable>{
        return this as ArrayList<Lootable>
    }
    fun getRandomLoot(world : World, player : EntityPlayer, luckModifier : Float) : List<Lootable> {
        val luck = luckModifier * player.luck
        val drops = ArrayList<Lootable>()
        return this.stream().filter({loot -> loot.chance > world.random.nextFloat() * luck}).collect(Collectors.toList())
    }
}