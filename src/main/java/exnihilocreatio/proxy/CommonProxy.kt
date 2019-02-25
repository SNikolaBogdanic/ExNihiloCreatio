package exnihilocreatio.proxy

import exnihilocreatio.ENCBlocks
import exnihilocreatio.ENCItems
import exnihilocreatio.ENCTiles
import exnihilocreatio.api.ExNihiloCreatioAPI
import exnihilocreatio.api.events.CrookRegistryEvent
import exnihilocreatio.api.events.HammerRegistryEvent
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

abstract class CommonProxy {
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        ENCBlocks.blocks.forEach{
            event.registry.register(it)
        }
    }

    @SubscribeEvent
    fun registerTiles(event: RegistryEvent.Register<TileEntityType<*>>) {
        ENCTiles.tiles.forEach{
            event.registry.register(it)
        }
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        ENCItems.items.forEach{
            event.registry.register(it)
        }

        // TODO move this a proper place
        MinecraftForge.EVENT_BUS.post(HammerRegistryEvent())
        MinecraftForge.EVENT_BUS.post(CrookRegistryEvent())
    }
}