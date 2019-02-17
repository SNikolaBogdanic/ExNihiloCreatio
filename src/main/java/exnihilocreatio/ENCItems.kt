package exnihilocreatio

import exnihilocreatio.client.Color
import exnihilocreatio.modules.farming.ItemPlantable
import exnihilocreatio.modules.farming.ItemTransformer
import exnihilocreatio.modules.sieve.ItemMesh
import exnihilocreatio.modules.tools.ToolCrook
import exnihilocreatio.modules.tools.ToolHammer
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemTier
import net.minecraftforge.common.IPlantable

object ENCItems {
    // Crop Seeds
    val SEED_POTATO = ItemPlantable("seed_potato", Blocks.CARROTS as IPlantable)
    val SEED_CARROT = ItemPlantable("seed_carrot", Blocks.POTATOES as IPlantable)
    // Vanilla, for ease of reference.
    val SEED_WHEAT = Items.WHEAT_SEEDS
    val SEED_BEETROOT = Items.BEETROOT_SEEDS

    // Special Plants
    val SEED_KELP = ItemPlantable("seed_kelp", Blocks.KELP as IPlantable)
    val SEED_REEDS = ItemPlantable("seed_reeds", Blocks.SUGAR_CANE as IPlantable)
    val SEED_CACTUS = ItemPlantable("seed_cactus", Blocks.CACTUS as IPlantable)

    // Tree Seeds
    val SEED_OAK = ItemPlantable("seed_oak", Blocks.OAK_SAPLING as IPlantable)
    val SEED_BIRCH = ItemPlantable("seed_birch", Blocks.BIRCH_SAPLING as IPlantable)
    val SEED_SPRUCE = ItemPlantable("seed_spruce", Blocks.SPRUCE_SAPLING as IPlantable)
    val SEED_JUNGLE = ItemPlantable("seed_jungle", Blocks.JUNGLE_SAPLING as IPlantable)
    val SEED_ACACIA = ItemPlantable("seed_acacia", Blocks.ACACIA_SAPLING as IPlantable)
    val SEED_DARK_AK = ItemPlantable("seed_dark_oak", Blocks.DARK_OAK_SAPLING as IPlantable)

    // Transformer Seeds
    val SEED_GRASS = ItemTransformer("grass_seed", Blocks.DIRT, Blocks.GRASS_BLOCK)
    val SEED_MYCELIUM = ItemTransformer("mycelium_seed", Blocks.DIRT, Blocks.MYCELIUM)

    // Meshes
    val STRING_MESH = ItemMesh("string_mesh", Color.WHITE)
    val FLINT_MESH = ItemMesh("flint_mesh", Color.DARK_GRAY)
    val IRON_MESH = ItemMesh("iron_mesh", Color.GRAY)
    val DIAMOND_MESH = ItemMesh("diamond_mesh", Color.AQUA)

    // Crooks
    val WOOD_CROOK = ToolCrook("wood_crook", ItemTier.WOOD)
    val BONE_CROOK = ToolCrook("bone_crook", ItemTier.STONE)

    // Hammers
    val WOOD_HAMMER = ToolHammer("wood_hammer", ItemTier.WOOD)
    val STONE_HAMMER = ToolHammer("stone_hammer", ItemTier.STONE)
    val IRON_HAMMER = ToolHammer("iron_hammer", ItemTier.IRON)
    val GOLD_HAMMER = ToolHammer("gold_hammer", ItemTier.GOLD)
    val DIAMOND_HAMMER = ToolHammer("diamond_hammer", ItemTier.DIAMOND)

}