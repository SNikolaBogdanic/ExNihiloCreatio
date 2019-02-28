package exnihilocreatio.utils.texturing

import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.model.BakedQuad
import net.minecraft.client.renderer.model.BakedQuadRetextured
import net.minecraft.client.renderer.texture.MissingTextureSprite
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.VertexFormatElement
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad
import net.minecraftforge.client.model.pipeline.VertexTransformer
import net.minecraftforge.common.model.TRSRTransformation
import net.minecraftforge.fluids.FluidStack
import javax.vecmath.Vector4f

@OnlyIn(Dist.CLIENT)
object TextureUtils {
    val missingTextureSprite = MissingTextureSprite.getLocation()

    fun getMissingTexture(): TextureAtlasSprite {
        return TextureUtils.getTexture(missingTextureSprite)
    }

    fun getTexture(block: Block): TextureAtlasSprite {
        return getTexture(block.registryName ?: missingTextureSprite)
    }
    fun getTexture(item: Item): TextureAtlasSprite {
        return getTexture(item.registryName ?: missingTextureSprite)
    }
    fun getTexture(stack: ItemStack): TextureAtlasSprite {
        if(stack.isEmpty)
            return getTexture(missingTextureSprite)
        return getTexture(stack.item.registryName ?: missingTextureSprite)
    }
    fun getTexture(stack: FluidStack): TextureAtlasSprite {
        return getTexture(stack?.fluid.still ?: missingTextureSprite)
    }
    fun getTexture(location: ResourceLocation): TextureAtlasSprite {
        return Minecraft.getInstance().textureMap.getSprite(location)
    }

    fun retextureQuads(quads: MutableList<BakedQuad>, texture: TextureAtlasSprite): MutableList<BakedQuad> {
        return (quads.map { quad -> BakedQuadRetextured(quad, getMissingTexture()) }).toMutableList()
    }

    /**
     * Based on the Botania GunModel$transform function
     *
     * https://github.com/Vazkii/Botania/blob/master/src/main/java/vazkii/botania/client/model/GunModel.java#L86-L110
     */
    fun transformBakedQuad(quad: BakedQuad, transform: TRSRTransformation): BakedQuad {
        val builder = UnpackedBakedQuad.Builder(quad.format)
        val consumer = object : VertexTransformer(builder) {
            override fun put(element: Int, vararg data: Float) {
                val formatElement = quad.format.getElement(element)
                when(formatElement.usage) {
                    VertexFormatElement.EnumUsage.POSITION -> {
                        val newData = FloatArray(4)
                        val vec = Vector4f(data)
                        vec.w = 1f // Allows Translations
                        transform.matrixVec.transform(vec)
                        vec.get(newData)
                        parent.put(element, *newData)
                    }
                    else -> {
                        parent.put(element, *data)
                    }
                }
            }
        }
        quad.pipe(consumer)
        return builder.build()
    }
}