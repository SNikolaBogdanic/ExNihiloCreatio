package exnihilocreatio

import exnihilocreatio.modules.barrel.BlockBarrel
import exnihilocreatio.modules.base.blocks.BlockFallingBase
import exnihilocreatio.modules.crucible.BlockCrucible
import exnihilocreatio.modules.sieve.BlockSieve
import exnihilocreatio.utils.VanillaWoodTypes
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.util.ResourceLocation

object ENCBlocks {
    val blocks = ArrayList<Block>()

    val SILT = BlockFallingBase("silt", Block.Properties.create(Material.SAND).sound(SoundType.SAND))
    val DUST = BlockFallingBase("dust", Block.Properties.create(Material.SAND).sound(SoundType.SAND))

    val CRUSHED_DIORITE = BlockFallingBase("crushed_diorite", Block.Properties.create(Material.SAND).sound(SoundType.GROUND))
    val CRUSHED_ANDESITE = BlockFallingBase("crushed_andesite", Block.Properties.create(Material.SAND).sound(SoundType.GROUND))
    val CRUSHED_GRANITE = BlockFallingBase("crushed_granite", Block.Properties.create(Material.SAND).sound(SoundType.GROUND))

    val CRUSHED_NETHERRACK = BlockFallingBase("crushed_netherrack", Block.Properties.create(Material.SAND).sound(SoundType.GROUND))
    val CRUSHED_ENDSTONE = BlockFallingBase("crushed_endstone", Block.Properties.create(Material.SAND).sound(SoundType.GROUND))
    val CRUSHED_PRISMARINE = BlockFallingBase("crushed_prismarine", Block.Properties.create(Material.SAND).sound(SoundType.GROUND))

    val SIEVES = ArrayList<Block>()
    val BARRELS = ArrayList<Block>()
    val CRUCIBLES = ArrayList<Block>()

    init {
        // Loop over the vanilla wood types to create Sieves/Barrels/Crucibles
        for(wood in VanillaWoodTypes.values()){
            SIEVES.add(BlockSieve(wood.prefix("sieve_"), Material.WOOD))
            BARRELS.add(BlockBarrel(wood.prefix("barrel_"), Material.WOOD))
            CRUCIBLES.add(BlockCrucible(wood.prefix("crucible_"), Material.WOOD))
        }
        BARRELS.add(BlockBarrel("barrel_stone", Material.ROCK))
        CRUCIBLES.add(BlockCrucible("crucible_stone", Material.ROCK))
        CRUCIBLES.add(BlockCrucible("crucible_stone_unfired", Material.ROCK))
    }
}