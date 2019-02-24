package exnihilocreatio

import exnihilocreatio.modules.base.blocks.BlockFallingBase
import exnihilocreatio.modules.sieve.BlockSieve
import exnihilocreatio.utils.VanillaWoodTypes
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material

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
        val wood = VanillaWoodTypes.OAK
        SIEVES.add(BlockSieve(wood.prefix("sieve_"), Material.WOOD, wood.getPlanksResource()))
        // Loop over the vanilla wood types to create Sieves/Barrels/Crucibles
        // for(wood in VanillaWoodTypes.values()){
        //     SIEVES.add(BlockSieve(wood.prefix("sieve_"), Material.WOOD, wood.getPlanksResource()))
        //     BARRELS.add(BlockBarrel(wood.prefix("barrel_"), Material.WOOD, wood.getPlanksResource()))
        //     CRUCIBLES.add(BlockCrucible(wood.prefix("crucible_"), Material.WOOD, wood.getBarkResource()))
        //     break;
        // }
        //BARRELS.add(BlockBarrel("barrel_stone", Material.ROCK, ResourceLocation("minecraft:block/stone")))
        //CRUCIBLES.add(BlockBarrel("crucible_stone", Material.ROCK, ExNihiloCreatio.createResource("block/crucible_stone")))
    }
}