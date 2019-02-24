package exnihilocreatio.compatibility

import exnihilocreatio.ENCBlocks
import exnihilocreatio.ENCItems
import exnihilocreatio.api.IHasRecipes
import exnihilocreatio.api.registries.*
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.EnumDyeColor
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.ForgeRegistries

object ExNihiloCreatioDefaults: IHasRecipes {
    override fun registerMeshes(registry: IMeshRegistry) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerSieve(registry: ISieveRegistry) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerCrook(registry: IToolRegistry) {
        registry.register(Ingredient.fromItems(Blocks.OAK_LEAVES), ItemStack(ENCItems.SILKWORM_RAW), 0.3f)
    }

    override fun registerHammer(registry: IToolRegistry) {
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
    }

    override fun registerCrucibleHeat(registry: ICrucibleHeatRegistry) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerWoodCrucible(registry: ICrucibleRegistry) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerStoneCrucible(registry: ICrucibleRegistry) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerBarrelBlackList(registry: IBarrelBlackList) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerBarrelAlchemy(registry: IBarrelAlchemyRegistry) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}