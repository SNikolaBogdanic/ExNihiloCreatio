package exnihilocreatio.networking

import exnihilocreatio.ExNihiloCreatio
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.network.NetworkRegistry

object PacketHandler {
    private val PROTOCOL_VERSION = (1).toString()
    private val HANDLER = NetworkRegistry.ChannelBuilder
            .named(ExNihiloCreatio.createResource("main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion {PROTOCOL_VERSION}
            .simpleChannel()

    fun sendToAllAround(msg: Any, te: TileEntity, range: Int) {
        val pos = te.pos

    }

    fun sendNBTUpdate(te: TileEntity){

    }
}