package exnihilocreatio.client.renderers

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.modules.sieve.TileSieve
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.model.ItemCameraTransforms
import net.minecraft.client.renderer.tileentity.TileEntityRenderer
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.item.ItemBlock
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import org.lwjgl.opengl.GL11

@OnlyIn(Dist.CLIENT)
class TileSieveRenderer: TileEntityRenderer<TileSieve>() {
    private val xzScale = 0.875
    private val yMin = 0.0625
    private val yMax = 0.3750

    override fun render(te: TileSieve, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int) {
        if(te.inventory.isEmpty)
            return

        val yScale = yMax - (yMax - yMin) * te.progress

        GlStateManager.pushMatrix()
        GlStateManager.translated(x+0.5,y+0.625+yScale/2, z+0.5)
        GlStateManager.scaled(xzScale,yScale,xzScale)

        Minecraft.getInstance().itemRenderer.renderItem(te.inventory, ItemCameraTransforms.TransformType.NONE)

        GlStateManager.popMatrix()
    }
}