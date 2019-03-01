package exnihilocreatio.utils

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.api.ToolTypes
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

object ItemUtils {
    fun StackOfOne(stack: ItemStack): ItemStack {
        val copy = stack.copy()
        if(!copy.isEmpty)
            copy.count = 1
        return copy
    }

    fun isCrook(stack: ItemStack): Boolean {
        return !stack.isEmpty && stack.toolTypes.contains(ToolTypes.CROOK)
    }

    fun isHammer(stack: ItemStack): Boolean {
        ExNihiloCreatio.logger.debug("Testing if hammer: "+stack.toString())
        ExNihiloCreatio.logger.debug("                 : "+stack.toolTypes.contains(ToolTypes.HAMMER))
        return !stack.isEmpty && stack.toolTypes.contains(ToolTypes.HAMMER)
    }

    /**
     * Based on ItemStack$isItemStackEqual ignoring count. Compares items and tags.
     */
    fun areEquivalent(left: ItemStack, right: ItemStack): Boolean {
        if(left.isEmpty xor right.isEmpty)
            return false
        else if (left.item !== right.item)
            return false
        else if (left.tag == null && right.tag != null)
            return false
        else
            return left.tag == null || left.tag == right.tag && left.areCapsCompatible(right)
    }

    fun dropInWorld(world: World, x: Number, y: Number, z: Number, vararg stacks: ItemStack) {
        for(stack in stacks){
            val e = EntityItem(world, x as Double, y as Double, z as Double, stack)
            world.spawnEntity(e)
        }
    }

    fun dropInWorld(world: World, location: Vec3d, direction: Vec3d, vararg stacks: ItemStack) {
        for(stack in stacks){
            val e = EntityItem(world, location.x, location.y, location.z, stack)
            e.setVelocity(direction.x, direction.y, direction.z)
            world.spawnEntity(e)
        }
    }

    fun dropInWorld(world: World, location: Vec3d, direction: Vec3d, stacks: NonNullList<ItemStack>) {
        for(stack in stacks){
            val e = EntityItem(world, location.x, location.y, location.z, stack)
            e.setVelocity(direction.x, direction.y, direction.z)
            world.spawnEntity(e)
        }
    }
}