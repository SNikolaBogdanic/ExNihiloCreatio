package exnihilocreatio.proxy

import exnihilocreatio.ENCRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

abstract class CommonProxy {
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        ENCRegistry.blocks.forEach{event.registry.register(it)}
    }

    @SubscribeEvent
    fun registerTiles(event: RegistryEvent.Register<TileEntityType<*>>) {
        ENCRegistry.tiles.forEach{event.registry.register(it)}
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        ENCRegistry.items.forEach{event.registry.register(it)}
    }
}