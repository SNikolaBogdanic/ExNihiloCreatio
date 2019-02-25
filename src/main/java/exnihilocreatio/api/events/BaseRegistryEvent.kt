package exnihilocreatio.api.events

import net.minecraftforge.eventbus.api.Event

abstract class BaseRegistryEvent<T>(val registry: T): Event()