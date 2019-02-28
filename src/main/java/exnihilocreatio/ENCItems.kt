package exnihilocreatio

import exnihilocreatio.modules.base.items.FoodBase
import exnihilocreatio.modules.farming.BlockInfestedLeaves
import exnihilocreatio.modules.farming.ItemPlantable
import exnihilocreatio.modules.farming.ItemTransformer
import exnihilocreatio.modules.sieve.ItemMesh
import exnihilocreatio.modules.sieve.properties.EnumMeshType
import exnihilocreatio.modules.tools.ToolCrook
import exnihilocreatio.modules.tools.ToolHammer
import exnihilocreatio.utils.texturing.Color
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemTier
import net.minecraftforge.common.IPlantable

object ENCItems {
    val items = ArrayList<Item>()

    // Crop Seeds
    val SEED_POTATO = ItemPlantable("seed_potato", Blocks.CARROTS as IPlantable)
    val SEED_CARROT = ItemPlantable("seed_carrot", Blocks.POTATOES as IPlantable)
    // Vanilla, for ease of reference.
    val SEED_WHEAT = Items.WHEAT_SEEDS
    val SEED_BEETROOT = Items.BEETROOT_SEEDS

    // Special Plants
    val SEED_REEDS = ItemPlantable("seed_reeds", Blocks.SUGAR_CANE as IPlantable)
    val SEED_CACTUS = ItemPlantable("seed_cactus", Blocks.CACTUS as IPlantable)

    // TODO non-IPlantable seeds
    // val SEED_KELP = ItemPlantable("seed_kelp", Blocks.KELP_PLANT as IPlantable)
    // val SEED_CHORUS = ItemPlantable("seed_chorus", Blocks.CHORUS_FLOWER as IPlantable)

    // Tree Seeds
    val SEED_OAK = ItemPlantable("seed_oak", Blocks.OAK_SAPLING as IPlantable)
    val SEED_BIRCH = ItemPlantable("seed_birch", Blocks.BIRCH_SAPLING as IPlantable)
    val SEED_SPRUCE = ItemPlantable("seed_spruce", Blocks.SPRUCE_SAPLING as IPlantable)
    val SEED_JUNGLE = ItemPlantable("seed_jungle", Blocks.JUNGLE_SAPLING as IPlantable)
    val SEED_ACACIA = ItemPlantable("seed_acacia", Blocks.ACACIA_SAPLING as IPlantable)
    val SEED_DARK_AK = ItemPlantable("seed_dark_oak", Blocks.DARK_OAK_SAPLING as IPlantable)

    // Transformer Seeds
    val SEED_GRASS = ItemTransformer("seed_grass", Blocks.DIRT, Blocks.GRASS_BLOCK)
    val SEED_MYCELIUM = ItemTransformer("seed_mycelium", Blocks.DIRT, Blocks.MYCELIUM)


    // Crooks
    val CROOK_WOOD = ToolCrook("crook_wood", ItemTier.WOOD)
    val CROOK_BONE = ToolCrook("crook_bone", ItemTier.STONE)
    // TODO stone/iron/gold/diamond crooks?

    // Hammers
    val HAMMER_WOOD = ToolHammer("hammer_wood", ItemTier.WOOD)
    val HAMMER_STONE = ToolHammer("hammer_stone", ItemTier.STONE)
    val HAMMER_IRON = ToolHammer("hammer_iron", ItemTier.IRON)
    val HAMMER_GOLD = ToolHammer("hammer_gold", ItemTier.GOLD)
    val HAMMER_DIAMOND = ToolHammer("hammer_diamond", ItemTier.DIAMOND)

    // Silkworm
    val SILKWORM_RAW = ItemTransformer("silkworm_raw", Blocks.OAK_LEAVES, BlockInfestedLeaves)
    val SILKWORM_COOKED = FoodBase("silkworm_cooked", 0, 2.0f, false)

    // Meshes
    val MESHES: MutableList<ItemMesh> = ArrayList<ItemMesh>()

    init {
        // TODO use MeshRegistry
        MESHES.add(ItemMesh("mesh_string", EnumMeshType.STRING))
        MESHES.add(ItemMesh("mesh_flint", EnumMeshType.FLINT))
        MESHES.add(ItemMesh("mesh_iron", EnumMeshType.IRON))
        MESHES.add(ItemMesh("mesh_diamond", EnumMeshType.DIAMOND))
    }
}