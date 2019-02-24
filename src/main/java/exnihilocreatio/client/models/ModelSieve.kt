package exnihilocreatio.client.models

import com.google.common.collect.ImmutableMap
import exnihilocreatio.ExNihiloCreatio
import net.minecraft.client.renderer.model.IBakedModel
import net.minecraft.client.renderer.model.IUnbakedModel
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.model.IModelState
import java.util.function.Function

class ModelSieve(val texture: ResourceLocation) : IUnbakedModel {
    override fun bake(modelGetter: Function<ResourceLocation, IUnbakedModel>, spriteGetter: Function<ResourceLocation, TextureAtlasSprite>, state: IModelState, uvlock: Boolean, format: VertexFormat): IBakedModel? {
        return modelGetter.apply(baseModel).retexture(ImmutableMap.of("texture", texture.toString())).bake(modelGetter,spriteGetter,state,uvlock,format)
    }

    override fun getTextures(modelGetter: Function<ResourceLocation, IUnbakedModel>, missingTextureErrors: MutableSet<String>): MutableCollection<ResourceLocation> {
        return mutableSetOf(texture)
    }

    override fun getDependencies(): MutableCollection<ResourceLocation> {
        return mutableSetOf(baseModel)
    }

    companion object {
        val baseModel = ExNihiloCreatio.createResource("block/sieve")
    }
}