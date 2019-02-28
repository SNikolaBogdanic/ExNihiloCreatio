package exnihilocreatio

import exnihilocreatio.client.RenderEvent
import exnihilocreatio.modules.compatibility.ENCDefaults
import exnihilocreatio.modules.tools.HandlerHarvest
import exnihilocreatio.proxy.ClientProxy
import exnihilocreatio.proxy.CommonProxy
import exnihilocreatio.proxy.ServerProxy
import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.common.Mod
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

    init {
        FMLKotlinModLoadingContext.get().modEventBus.register(proxy)

        registerEventListeners()
        registerENCRegistryListeners()
    }

    private fun registerEventListeners(){
        MinecraftForge.EVENT_BUS.register(HandlerHarvest)
        // TODO: Anvil listener for mesh enchanting
    }

    private fun registerENCRegistryListeners() {

        MinecraftForge.EVENT_BUS.register(ENCDefaults)
    }

    fun createResource(path: String): ResourceLocation = ResourceLocation(MODID, path)
}