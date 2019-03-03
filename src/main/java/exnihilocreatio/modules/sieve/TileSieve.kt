package exnihilocreatio.modules.sieve

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.api.ExNihiloCreatioAPI
import exnihilocreatio.config.Config
import exnihilocreatio.modules.base.tiles.BaseTileEntity
import exnihilocreatio.modules.sieve.properties.EnumMeshType
import exnihilocreatio.utils.ItemHandlerFactory
import exnihilocreatio.utils.ItemUtils
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Enchantments
import net.minecraft.init.MobEffects
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.Vec3d
import net.minecraft.util.text.TextComponentString
import net.minecraftforge.items.IItemHandler

class TileSieve: BaseTileEntity(TileTypeSieve) {
    var progress = 0.0
    var mesh: ItemStack = ItemStack.EMPTY
    var inventory: ItemStack = ItemStack.EMPTY

    val radius = Config.SIEVE_CONFIG.RADIUS.get()
    val PROGRES_STEP = Config.SIEVE_CONFIG.PROGRESS.get()

    /**
     * Handles player interaction logic
     */
    fun onBlockActivated(state: IBlockState, player: EntityPlayer, hand: EnumHand): Boolean {
        val held = player.getHeldItem(hand)
        val handler: IItemHandler = ItemHandlerFactory.getHandler(held)

        // Remove/Swap a mesh
        if((player.isSneaking && held.isEmpty) || (held.item is ItemMesh)) {
            // Removing/Adding a mesh
            if(!mesh.isEmpty)
                player.addItemStackToInventory(mesh.copy())
            mesh = ItemUtils.StackOfOne(held)
            if(!player.isCreative && !mesh.isEmpty)
                held.shrink(1)
            if(mesh.item is ItemMesh)
                world.setBlockState(pos, state.with(BlockSieve.MESH, (mesh.item as ItemMesh).meshType))
            return true
        }

        val adjacent = getNearbySieves()

        // Add an item
        if(inventory.isEmpty && !held.isEmpty && !mesh.isEmpty) {
            // Adding a block
            for(slot in 0 until handler.slots) {
                insert(handler, slot, player)
                for(adj in adjacent)
                    adj.insert(handler, slot, player)
                if(!inventory.isEmpty)
                    break
            }
            return true
        }
        // Do progress
        if(!inventory.isEmpty) {
            doProgress(player)
            for(sieve in adjacent)
                sieve.doProgress(player)
            return true
        }
        return true
    }

    /**
     * Increments sieving progress.
     */
    private fun doProgress(player: EntityPlayer) {
        if(inventory.isEmpty)
            return

        // TODO add fakePlayer check

        var delta = PROGRES_STEP + EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, mesh) / 5.0
        delta *= 1.0 + 0.3 * (player.getActivePotionEffect(MobEffects.HASTE)?.amplifier?.toDouble() ?: 0.0)
        progress += delta
        if(progress >= 1.0){
            val allLoot = ExNihiloCreatioAPI.SIEVE_REGISTRY.getLoot(world, mesh, inventory, world.getBlockState(pos)[BlockSieve.WATERLOGGED])
            ExNihiloCreatio.logger.debug("Got Loot: " + allLoot.size)
            for(loot in allLoot){
                ExNihiloCreatio.logger.debug("Dropping: $loot")
                ItemUtils.dropToPlayer(world, Vec3d(pos).add(0.5, 0.75,0.5), player, 0.5, loot)
            }
            inventory = ItemStack.EMPTY
            progress = 0.0
        }
        if(inventory.item is ItemBlock){
            val block = (inventory.item as ItemBlock).block
            world.playSound(player, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), block.getSoundType(block.defaultState, world, pos, player).hitSound, SoundCategory.BLOCKS, 1.0f, 1.0f)
        }


        markDirtyClient()
        // TODO Spawn some particles under the sieve
    }

    /**
     * Attempt to insert a block from slot
     */
    fun insert(handler: IItemHandler, slot: Int, player: EntityPlayer) {
        if(!inventory.isEmpty)
            return
        if(ExNihiloCreatioAPI.SIEVE_REGISTRY.canSieve(handler.getStackInSlot(slot), mesh)) {
            inventory = handler.extractItem(slot, 1, player.isCreative)
            progress = 0.0
            markDirtyClient()
        }
    }

    fun getMeshType(): EnumMeshType {
        if(mesh.isEmpty || mesh.item !is ItemMesh)
            return EnumMeshType.EMPTY
        return (mesh.item as ItemMesh).meshType
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
        for(dx in -radius..radius) {
            for(dz in -radius..radius) {
                if(dx == 0 && dz == 0)
                    continue
                val otherSieve = world.getTileEntity(pos.add(dx, 0, dz)) as? TileSieve ?: continue
                if(isSieveSimilar(otherSieve))
                    sieves.add(otherSieve)
            }
        }
        return sieves
    }

    override fun write(tag: NBTTagCompound): NBTTagCompound {
        tag.put("mesh", mesh.serializeNBT())
        tag.put("inventory", inventory.serializeNBT())
        tag.putDouble("progress", progress)
        return super.write(tag)
    }

    override fun read(tag: NBTTagCompound) {
        mesh = ItemStack.read(tag.getCompound("mesh"))
        inventory = ItemStack.read(tag.getCompound("inventory"))
        progress = tag.getDouble("progress")
        super.read(tag)
    }
}