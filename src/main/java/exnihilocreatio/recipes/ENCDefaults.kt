package exnihilocreatio.recipes

import exnihilocreatio.ENCBlocks
import exnihilocreatio.ENCItems
import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.api.events.CrookRegistryEvent
import exnihilocreatio.api.events.HammerRegistryEvent
import exnihilocreatio.api.events.SieveRegistryEvent
import exnihilocreatio.modules.sieve.properties.EnumMeshType
import exnihilocreatio.utils.VanillaWoodTypes
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.EnumDyeColor
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ForgeRegistries

@Mod.EventBusSubscriber
object ENCDefaults {
    @SubscribeEvent
    fun registerHammer(event: HammerRegistryEvent){
        val registry = event.registry
        // Normal Stone
        registry.register(Blocks.STONE, Blocks.COBBLESTONE)
        registry.register(Blocks.COBBLESTONE, Blocks.GRAVEL)
        registry.register(Blocks.GRAVEL, Blocks.SAND)
        registry.register(Blocks.SAND, ENCBlocks.DUST)

        // Diorite
        registry.register(Blocks.POLISHED_DIORITE, Blocks.DIORITE)
        registry.register(Blocks.DIORITE, ENCBlocks.CRUSHED_DIORITE)
        registry.register(ENCBlocks.CRUSHED_DIORITE, Blocks.WHITE_CONCRETE_POWDER)
        // Powder to dust happens in color loop

        // Andesite
        registry.register(Blocks.POLISHED_ANDESITE, Blocks.ANDESITE)
        registry.register(Blocks.ANDESITE, ENCBlocks.CRUSHED_ANDESITE)
        registry.register(ENCBlocks.CRUSHED_ANDESITE, ENCBlocks.SILT)
        registry.register(ENCBlocks.SILT, ENCBlocks.DUST)

        // Granite
        registry.register(Blocks.POLISHED_GRANITE, Blocks.GRANITE)
        registry.register(Blocks.GRANITE, ENCBlocks.CRUSHED_GRANITE)
        registry.register(ENCBlocks.CRUSHED_GRANITE, Blocks.RED_SAND)
        registry.register(Blocks.RED_SAND, ENCBlocks.DUST)

        // Prismarine
        registry.register(Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE)
        registry.register(Blocks.PRISMARINE, ENCBlocks.CRUSHED_PRISMARINE)

        // Netherrack
        registry.register(Blocks.NETHER_BRICKS, Blocks.NETHERRACK)
        registry.register(Blocks.NETHERRACK, ENCBlocks.CRUSHED_NETHERRACK)

        // End Stone
        registry.register(Blocks.END_STONE_BRICKS, Blocks.END_STONE)
        registry.register(Blocks.END_STONE, ENCBlocks.CRUSHED_ENDSTONE)

        // Concrete to Concrete Powder
        for(color in EnumDyeColor.values()) {
            val concrete = ForgeRegistries.BLOCKS.getValue(ResourceLocation("minecraft", color.name.toLowerCase() + "_concrete")) ?: continue
            val powder = ForgeRegistries.BLOCKS.getValue(ResourceLocation("minecraft", color.name.toLowerCase() + "_concrete_powder")) ?: continue
            registry.register(concrete, powder)
            registry.register(powder, ENCBlocks.DUST)
        }
        // Wool to String
        for(color in EnumDyeColor.values()){
            val wool = ForgeRegistries.BLOCKS.getValue(ResourceLocation("minecraft", color.name.toLowerCase() + "_wool")) ?: continue
            registry.register(wool, ItemStack(Items.STRING, 4))
        }

        //TODO consider adding sawdust from logs/planks

        ExNihiloCreatio.logger.info("Registered Hammer Recipes")
    }

    @SubscribeEvent
    fun registerCrook(event: CrookRegistryEvent){
        val registry = event.registry

        // TODO figure out tags
        for(wood in VanillaWoodTypes.values()) {
            registry.register(wood.getLeaves(), ENCItems.SILKWORM_RAW, 0.2f)
            registry.register(wood.getLeaves(), ENCItems.SILKWORM_RAW, 0.1f)
            registry.register(wood.getLeaves(), ENCItems.SILKWORM_RAW, 0.05f)
            registry.register(wood.getLeaves(), wood.getSapling(), 0.2f)
            registry.register(wood.getLeaves(), wood.getSapling(), 0.2f)
            registry.register(wood.getLeaves(), wood.getSapling(), 0.2f)
        }
        registry.register(Blocks.OAK_LEAVES, Items.APPLE, 0.1f)
        registry.register(Blocks.DARK_OAK_LEAVES, Items.APPLE, 0.3f)


        ExNihiloCreatio.logger.info("Registered Crook Recipes")
    }

    @SubscribeEvent
    fun registerSieve(event: SieveRegistryEvent) {
        val registry = event.registry

        // Pebbles
        registry.register(EnumMeshType.STRING, Blocks.DIRT, ENCItems.PEBBLE_STONE, 0.2f)
        registry.register(EnumMeshType.FLINT, Blocks.DIRT, ENCItems.PEBBLE_ANDESITE, 0.1f)
        registry.register(EnumMeshType.FLINT, Blocks.DIRT, ENCItems.PEBBLE_GRANITE, 0.1f)
        registry.register(EnumMeshType.FLINT, Blocks.DIRT, ENCItems.PEBBLE_DIORITE, 0.1f)

        registry.register(EnumMeshType.STRING, Blocks.COARSE_DIRT, ENCItems.PEBBLE_STONE, 0.5f)
        registry.register(EnumMeshType.FLINT, Blocks.COARSE_DIRT, ENCItems.PEBBLE_ANDESITE, 0.3f)
        registry.register(EnumMeshType.FLINT, Blocks.COARSE_DIRT, ENCItems.PEBBLE_GRANITE, 0.3f)
        registry.register(EnumMeshType.FLINT, Blocks.COARSE_DIRT, ENCItems.PEBBLE_DIORITE, 0.3f)

        // Flint
        registry.register(EnumMeshType.STRING, Blocks.GRAVEL, Items.FLINT, 0.3f)
        registry.register(EnumMeshType.FLINT, Blocks.GRAVEL, Items.FLINT, 0.3f)
        registry.register(EnumMeshType.FLINT, Blocks.GRAVEL, Items.FLINT, 0.2f)

        // Vanilla Seeds
        registry.register(EnumMeshType.STRING, Blocks.DIRT, Items.WHEAT_SEEDS, 0.05f)
        registry.register(EnumMeshType.STRING, Blocks.DIRT, Items.BEETROOT_SEEDS, 0.05f)
        registry.register(EnumMeshType.IRON, Blocks.DIRT, Items.MELON_SEEDS, 0.05f)
        registry.register(EnumMeshType.IRON, Blocks.DIRT, Items.PUMPKIN_SEEDS, 0.05f)

        // Tree Seeds
        registry.register(EnumMeshType.STRING, Blocks.DIRT, ENCItems.SEED_OAK, 0.05f)
        registry.register(EnumMeshType.STRING, Blocks.DIRT, ENCItems.SEED_BIRCH, 0.05f)
        registry.register(EnumMeshType.FLINT, Blocks.DIRT, ENCItems.SEED_SPRUCE, 0.05f)
        registry.register(EnumMeshType.FLINT, Blocks.DIRT, ENCItems.SEED_JUNGLE, 0.05f)
        registry.register(EnumMeshType.IRON, Blocks.DIRT, ENCItems.SEED_ACACIA, 0.05f)
        registry.register(EnumMeshType.IRON, Blocks.DIRT, ENCItems.SEED_DARK_OAK, 0.05f)

        // Other Seeds
        registry.register(EnumMeshType.FLINT, Blocks.DIRT, ENCItems.SEED_POTATO, 0.05f)
        registry.register(EnumMeshType.FLINT, Blocks.DIRT, ENCItems.SEED_CARROT, 0.05f)
        registry.register(EnumMeshType.FLINT, Blocks.SAND, ENCItems.SEED_CACTUS, 0.05f)
        registry.register(EnumMeshType.FLINT, Blocks.SAND, ENCItems.SEED_REEDS, 0.05f)
        registry.register(EnumMeshType.FLINT, Blocks.DIRT, ENCItems.SEED_GRASS, 0.05f)
        registry.register(EnumMeshType.FLINT, Blocks.DIRT, ENCItems.SEED_MYCELIUM, 0.05f)

        ExNihiloCreatio.logger.info("Registered Sieve Recipes")
    }
}