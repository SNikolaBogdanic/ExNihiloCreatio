package exnihilocreatio

import exnihilocreatio.modules.barrel.BlockBarrel
import exnihilocreatio.modules.base.blocks.BlockFallingBase
import exnihilocreatio.modules.crucible.BlockCrucible
import exnihilocreatio.modules.sieve.BlockSieve
import exnihilocreatio.utils.VanillaWoodTypes
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.util.ResourceLocation

object ENCBlocks {
    val SILT = BlockFallingBase("silt", Material.SAND)
    val DUST = BlockFallingBase("dust", Material.SAND)

    val CRUSHED_DIORITE = BlockFallingBase("crushed_diorite", Material.GROUND)
    val CRUSHED_ANDESITE = BlockFallingBase("crushed_andesite", Material.GROUND)
    val CRUSHED_GRANITE = BlockFallingBase("crushed_granite", Material.GROUND)

    val CRUSHED_NETHERRACK = BlockFallingBase("crushed_netherrack", Material.GROUND)
    val CRUSHED_ENDSTONE = BlockFallingBase("crushed_endstone", Material.GROUND)

    val SIEVES = ArrayList<Block>()
    val BARRELS = ArrayList<Block>()
    val CRUCIBLES = ArrayList<Block>()

    init {
        // Loop over the vanilla wood types to create Sieves/Barrels/Crucibles
        for(wood in VanillaWoodTypes.values()){
            SIEVES.add(BlockSieve(wood.suffix("_sieve"), Material.WOOD, wood.getPlanksResource()))
            BARRELS.add(BlockBarrel(wood.suffix("_barrel"), Material.WOOD, wood.getPlanksResource()))
            CRUCIBLES.add(BlockCrucible(wood.suffix("_crucible"), Material.WOOD, wood.getBarkResource()))
        }
        BARRELS.add(BlockBarrel("stone_barrel", Material.ROCK, ResourceLocation("minecraft:block/stone")))
        CRUCIBLES.add(BlockBarrel("stone_crucible", Material.ROCK, ExNihiloCreatio.createResource("block/stone_crucible")))
    }
}