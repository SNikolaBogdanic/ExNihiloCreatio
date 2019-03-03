@file:Suppress("INACCESSIBLE_TYPE")

package exnihilocreatio.networking

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.networking.packets.PacketNBTUpdate
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.dimension.DimensionType
import net.minecraftforge.fml.network.NetworkRegistry
import net.minecraftforge.fml.network.PacketDistributor

object PacketHandler {
    private val PROTOCOL_VERSION = (1.0).toString()
    val HANDLER = NetworkRegistry.ChannelBuilder
            .named(ExNihiloCreatio.createResource("main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion {PROTOCOL_VERSION}
            .simpleChannel()

    init {
        var disc = 0
        HANDLER.registerMessage(disc++, PacketNBTUpdate::class.java, PacketNBTUpdate::encode, PacketNBTUpdate.Companion::decode, PacketNBTUpdate.Companion::handle)
    }

    fun sendToAllAround(msg: Any, pos: Vec3d, range: Double, dimension: DimensionType?) {
        HANDLER.send(PacketDistributor.NEAR.with{PacketDistributor.TargetPoint(pos.x, pos.y, pos.z, range, dimension)}, msg)
    }

    fun sendNBTUpdate(te: TileEntity){
        sendToAllAround(PacketNBTUpdate(te), Vec3d(te.pos), 32.0, te.world?.dimension?.type)
    }
}