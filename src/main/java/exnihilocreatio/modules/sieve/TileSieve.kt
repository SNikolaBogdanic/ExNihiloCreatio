package exnihilocreatio.modules.sieve

import exnihilocreatio.ENCConfig
import exnihilocreatio.modules.base.tiles.BaseTileEntity
import exnihilocreatio.utils.ItemUtils
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Enchantments
import net.minecraft.init.MobEffects
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.Vec3d
import net.minecraftforge.items.ItemStackHandler

class TileSieve: BaseTileEntity(TileTypeSieve) {
    private var progress = 0.0
    private var mesh = ItemStack.EMPTY
    private var inventory = ItemStack.EMPTY

    /**
     * Handles player interaction logic
     */
    fun onBlockActivated(state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if(world.isRemote)
            return true

        val held = player.getHeldItem(hand)
        if(player.isSneaking) {
            if(!mesh.isEmpty && (held.isEmpty || held.item is ItemMesh)){
                // Removing a mesh
                val location = Vec3d(pos).add(0.0, 0.5, 0.0)
                val direction = Vec3d(player.position).subtract(location).normalize()
                ItemUtils.dropInWorld(world, location, direction, mesh)
                mesh = ItemStack.EMPTY
                return true

            }
            return false
        }

        val adjacent = getNearbySieves()

        // Do progress
        if(!inventory.isEmpty) {
            doProgress(player)
            for(sieve in adjacent)
                sieve.doProgress(player)
            return true
        }

        // Adding an item/mesh use handler logic for future expansion to items with inventories
        val handler = ItemStackHandler(NonNullList.from(held))

        for(stack in 0..handler.slots) {

        }

        return true
    }

    /**
     * Increments sieving progress.
     */
    private fun doProgress(player: EntityPlayer) {
        // TODO add fakePlayer check

        var delta = ENCConfig.Sieve.progress + EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, mesh) / 5.0
        delta *= 1.0 + 0.3 * (player.getActivePotionEffect(MobEffects.HASTE)?.amplifier ?: 0)
        progress += delta
        if(progress > 1.0){
            // TODO Get drops

            // TODO Drop drops in world

            // Reset sieve
            inventory = ItemStack.EMPTY
            progress = 0.0
        }
        // TODO Spawn some particles particles under the sieve
    }

    /**
     * Determines if another sieve is similar enough to work together.
     *
     * Similarity is based on the meshes being the same type of mesh with the same enchantments
     *
     */
    private fun isSieveSimilar(otherSieve: TileSieve): Boolean {
        return ItemUtils.areEquivalent(mesh, otherSieve.mesh)
    }

    private fun getNearbySieves(): NonNullList<TileSieve> {
        val sieves = NonNullList.create<TileSieve>()
        for(dx in -ENCConfig.Sieve.radius..ENCConfig.Sieve.radius) {
            for(dz in -ENCConfig.Sieve.radius..ENCConfig.Sieve.radius) {
                if(dx == 0 && dz == 0)
                    continue
                val otherSieve = world.getTileEntity(pos.add(dx, 0, dz)) as? TileSieve ?: continue
                if(isSieveSimilar(otherSieve))
                    sieves.add(otherSieve)
            }
        }
        return sieves
    }
}