package exnihilocreatio.proxy

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.client.renderers.TileSieveRenderer
import exnihilocreatio.modules.sieve.TileSieve
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent


class ClientProxy : CommonProxy() {
    override fun setup(event: FMLCommonSetupEvent) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileSieve::class.java, TileSieveRenderer())
        ExNihiloCreatio.logger.debug("Registered Sieve TESR")
    }
}