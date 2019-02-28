package exnihilocreatio.client.models

import exnihilocreatio.ENCBlocks
import exnihilocreatio.ENCItems
import exnihilocreatio.ExNihiloCreatio
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.model.*
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import java.util.*
import kotlin.collections.ArrayList

class BakedModelSieve(val defaultModel: IBakedModel): IBakedModel {
    private val sieveQuads = defaultModel.getQuads(null, null, Random()).toList()
    private val modelCache = HashMap<ResourceLocation, MutableList<BakedQuad>>()
    init {
        for(mesh in ENCItems.MESHES) {
            val meshQuads = Minecraft.getInstance().modelManager
                    .getModel(ModelResourceLocation(mesh.registryName ?: continue,"inventory"))
                    .getQuads(null, null, Random())
            val quads = ArrayList<BakedQuad>()
            quads.addAll(meshQuads)
            quads.addAll(sieveQuads)
            modelCache[mesh.registryName as ResourceLocation] = quads.toMutableList()
        }
    }

    override fun getQuads(state: IBlockState?, side: EnumFacing?, rand: Random): MutableList<BakedQuad> {

        // TODO grab the mesh's model based on BlockState
        val meshName = ExNihiloCreatio.createResource("mesh_diamond")

        return modelCache[meshName] ?: sieveQuads.toMutableList()
    }


    /**
     * Pass everything else to the base model
     */
    override fun getParticleTexture(): TextureAtlasSprite {
        return defaultModel.particleTexture
    }

    override fun isBuiltInRenderer(): Boolean {
        return defaultModel.isBuiltInRenderer
    }

    override fun isAmbientOcclusion(): Boolean {
        return defaultModel.isAmbientOcclusion
    }

    override fun isGui3d(): Boolean {
        return defaultModel.isGui3d
    }

    override fun getOverrides(): ItemOverrideList {
        return defaultModel.overrides
    }

    companion object {
        val variantTags = ENCBlocks.SIEVES.map { ModelResourceLocation(it.registryName, "") }
    }
}