package exnihilocreatio.client

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.client.models.BakedModelSieve
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

//@Mod.EventBusSubscriber
object RenderEvent {
    //@SubscribeEvent
    fun onModelBake(event: ModelBakeEvent) {
        for(variantTag in BakedModelSieve.variantTags) {
            val model = event.modelRegistry.get(variantTag)
            if(model != null) {
                val customModelSieve = BakedModelSieve(model)
                event.modelRegistry[variantTag] = customModelSieve
            }
        }
    }
}