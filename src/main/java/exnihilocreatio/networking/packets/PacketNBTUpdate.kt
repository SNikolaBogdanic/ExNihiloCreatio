package exnihilocreatio.networking.packets

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.modules.sieve.TileSieve
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier
import net.minecraft.client.Minecraft


/**
 * Simple TE update
 */
class PacketNBTUpdate(val pos: BlockPos, val tag: NBTTagCompound) {

    constructor(te: TileEntity): this(te.pos, te.write(NBTTagCompound()))
    fun encode(buffer: PacketBuffer) {
        buffer.writeBlockPos(pos)
        buffer.writeCompoundTag(tag)
    }
    companion object {
        @JvmStatic
        fun encode(packet: PacketNBTUpdate, buffer: PacketBuffer) {
            buffer.writeBlockPos(packet.pos)
            buffer.writeCompoundTag(packet.tag)
        }
        @JvmStatic
        fun decode(buffer: PacketBuffer): PacketNBTUpdate {
            val pos = buffer.readBlockPos()
            val tag = buffer.readCompoundTag() ?: NBTTagCompound()
            return PacketNBTUpdate(pos, tag)
        }
        @JvmStatic
        fun handle(packet: PacketNBTUpdate, ctx: Supplier<NetworkEvent.Context>) {
            ctx.get().enqueueWork<NetworkEvent.Context> {
                Minecraft.getInstance().world.getTileEntity(packet.pos)?.read(packet.tag)
            }
            ctx.get().packetHandled = true
        }
    }
}