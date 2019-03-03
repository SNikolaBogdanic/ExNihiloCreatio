package exnihilocreatio

import exnihilocreatio.config.Config
import exnihilocreatio.recipes.ENCDefaults
import exnihilocreatio.modules.tools.HandlerHarvest
import exnihilocreatio.networking.PacketHandler
import exnihilocreatio.proxy.ClientProxy
import exnihilocreatio.proxy.CommonProxy
import exnihilocreatio.proxy.ServerProxy
import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.loading.FMLPaths
import org.apache.logging.log4j.LogManager
import java.util.function.Supplier


const val MODID = "exnihilocreatio"

@Mod(MODID)
object ExNihiloCreatio {
    val proxy: CommonProxy = DistExecutor.runForDist<CommonProxy>({Supplier{ClientProxy()}}, {Supplier{ServerProxy()}})
    val itemGroup = object : ItemGroup(MODID) {
        override fun createIcon() = ItemStack(ENCItems.HAMMER_DIAMOND)
    }
    val logger = LogManager.getLogger()

    val packetHandler = PacketHandler // Force lazy initialization

    init {
        FMLKotlinModLoadingContext.get().modEventBus.register(proxy)
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG)
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG)



        registerEventListeners()
        registerENCRegistryListeners()

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("$MODID-client.toml"))
        Config.loadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("$MODID-server.toml"))
    }

    private fun registerEventListeners(){
        MinecraftForge.EVENT_BUS.register(HandlerHarvest)
        // MinecraftForge.EVENT_BUS.register(this::setup)
        FMLKotlinModLoadingContext.get().modEventBus.addListener<FMLCommonSetupEvent>{setup(it)}
        // TODO: Anvil listener for mesh enchanting
    }

    private fun registerENCRegistryListeners() {
        MinecraftForge.EVENT_BUS.register(ENCDefaults)
    }


    fun setup(event: FMLCommonSetupEvent) {
        proxy.setup(event)
    }

    fun createResource(path: String): ResourceLocation = ResourceLocation(MODID, path)
}