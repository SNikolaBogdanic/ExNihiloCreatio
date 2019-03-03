package exnihilocreatio.proxy

import exnihilocreatio.ENCBlocks
import exnihilocreatio.ENCItems
import exnihilocreatio.ENCTiles
import exnihilocreatio.api.events.CrookRegistryEvent
import exnihilocreatio.api.events.HammerRegistryEvent
import exnihilocreatio.api.events.SieveRegistryEvent
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

abstract class CommonProxy {
    open fun setup(event: FMLCommonSetupEvent) {

    }

    @SubscribeEvent
    open fun registerBlocks(event: RegistryEvent.Register<Block>) {
        ENCBlocks.blocks.forEach{
            event.registry.register(it)
        }
    }

    @SubscribeEvent
    open fun registerTiles(event: RegistryEvent.Register<TileEntityType<*>>) {
        ENCTiles.tiles.forEach{
            event.registry.register(it)
        }
    }

    @SubscribeEvent
    open fun registerItems(event: RegistryEvent.Register<Item>) {
        ENCItems.items.forEach{
            event.registry.register(it)
        }

        // TODO move this a proper place
        MinecraftForge.EVENT_BUS.post(HammerRegistryEvent())
        MinecraftForge.EVENT_BUS.post(CrookRegistryEvent())
        MinecraftForge.EVENT_BUS.post(SieveRegistryEvent())
    }
}