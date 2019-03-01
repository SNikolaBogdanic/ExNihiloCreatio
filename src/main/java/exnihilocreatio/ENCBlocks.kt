package exnihilocreatio

import exnihilocreatio.modules.barrel.BlockBarrel
import exnihilocreatio.modules.base.blocks.BlockFallingBase
import exnihilocreatio.modules.crucible.BlockCrucible
import exnihilocreatio.modules.sieve.BlockSieve
import exnihilocreatio.utils.VanillaWoodTypes
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material

object ENCBlocks {
    val sandProperties = Block.Properties.create(Material.SAND).sound(SoundType.SAND)
    val crushedProperties = Block.Properties.create(Material.SAND).sound(SoundType.GROUND)

    val blocks = ArrayList<Block>()

    val SILT = BlockFallingBase("silt", sandProperties)
    val DUST = BlockFallingBase("dust", sandProperties)

    val CRUSHED_DIORITE = BlockFallingBase("crushed_diorite", crushedProperties)
    val CRUSHED_ANDESITE = BlockFallingBase("crushed_andesite", crushedProperties)
    val CRUSHED_GRANITE = BlockFallingBase("crushed_granite", crushedProperties)

    val CRUSHED_NETHERRACK = BlockFallingBase("crushed_netherrack", crushedProperties)
    val CRUSHED_ENDSTONE = BlockFallingBase("crushed_endstone",crushedProperties)
    val CRUSHED_PRISMARINE = BlockFallingBase("crushed_prismarine", crushedProperties)

    val SIEVES = ArrayList<Block>()
    val BARRELS = ArrayList<Block>()
    val CRUCIBLES = ArrayList<Block>()

    init {
        // Loop over the vanilla wood types to create Sieves/Barrels/Crucibles
        for(wood in VanillaWoodTypes.values()){
            SIEVES.add(BlockSieve(wood.prefix("sieve_")))
            BARRELS.add(BlockBarrel(wood.prefix("barrel_"), BlockBarrel.woodProperties))
            CRUCIBLES.add(BlockCrucible(wood.prefix("crucible_"), BlockCrucible.woodProperties))
        }
        BARRELS.add(BlockBarrel("barrel_stone", BlockBarrel.woodProperties))
        CRUCIBLES.add(BlockCrucible("crucible_stone", BlockCrucible.stoneProperties))
        CRUCIBLES.add(BlockCrucible("crucible_stone_unfired", BlockCrucible.stoneProperties.hardnessAndResistance(2.0f, 3.0f)))
    }
}