package exnihilocreatio

import exnihilocreatio.proxy.ClientProxy
import exnihilocreatio.proxy.CommonProxy
import exnihilocreatio.proxy.ServerProxy
import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.common.Mod
import java.util.function.Supplier

const val MODID = "exnihilocreatio"

@Mod(MODID)
object ExNihiloCreatio {
    val proxy: CommonProxy = DistExecutor.runForDist<CommonProxy>({Supplier{ClientProxy()}}, {Supplier{ServerProxy()}})
    val itemGroup = object : ItemGroup(MODID) {
        override fun createIcon() = ItemStack(ENCItems.WOOD_CROOK)
    }

    init {
        FMLKotlinModLoadingContext.get().modEventBus.register(proxy)
        // Load modules as proxies?
    }

    fun createResource(path: String): ResourceLocation = ResourceLocation(MODID, path)
}