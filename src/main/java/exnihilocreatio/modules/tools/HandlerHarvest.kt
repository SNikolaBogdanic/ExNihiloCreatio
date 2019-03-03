package exnihilocreatio.modules.tools

import exnihilocreatio.api.ExNihiloCreatioAPI
import exnihilocreatio.api.registries.IToolRegistry
import exnihilocreatio.utils.ItemUtils
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber
object HandlerHarvest {
    @SubscribeEvent(priority = EventPriority.LOW)
    fun onToolHarvest(event: BlockEvent.HarvestDropsEvent) {
        if(event.world.isRemote || event.isSilkTouching || event.harvester == null)
            return
        if(ItemUtils.isCrook(event.harvester.heldItemMainhand))
            lootHarvest(event, ExNihiloCreatioAPI.CROOK_REGISTRY)
        else if(ItemUtils.isHammer(event.harvester.heldItemMainhand))
            lootHarvest(event, ExNihiloCreatioAPI.HAMMER_REGISTRY)
    }

    private fun lootHarvest(event: BlockEvent.HarvestDropsEvent, toolRegistry: IToolRegistry) {
        // Why do events pass IWorld instead of just a world?
        val loot = toolRegistry.getLoot(event.world.world, event.state, event.harvester, event.harvester.heldItemMainhand)
        if(loot.size > 0){
            event.drops.clear()
            event.drops.addAll(loot)
        }
    }
}