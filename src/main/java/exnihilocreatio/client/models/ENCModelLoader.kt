package exnihilocreatio.client.models

import exnihilocreatio.ExNihiloCreatio
import exnihilocreatio.MODID
import exnihilocreatio.modules.sieve.BlockSieve
import net.minecraft.client.renderer.model.IUnbakedModel
import net.minecraft.resources.IResourceManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ICustomModelLoader
import net.minecraftforge.client.model.IModel
import net.minecraftforge.client.model.ModelLoaderRegistry
import net.minecraftforge.registries.ForgeRegistries

object ENCModelLoader : ICustomModelLoader {
    val cache = HashMap<ResourceLocation, IModel<*>>()
    private var manager: IResourceManager? = null
    override fun onResourceManagerReload(resourceManager: IResourceManager) {
        manager = resourceManager
        cache.clear()
    }

    override fun loadModel(modelLocation: ResourceLocation): IUnbakedModel {
        // Should not have gotten passed this, just give a missing model
        if(modelLocation.namespace != MODID){
            ExNihiloCreatio.logger.error("ENCModelLoader failed to load non-exnihilocreatio resource: ", modelLocation.toString())
            return ModelLoaderRegistry.getMissingModel()
        }
        // Sieve Models
        if(modelLocation.path.startsWith("sieve_")) {
            ExNihiloCreatio.logger.debug("ENCModelLoader loaded a sieve model".format(modelLocation.toString()))
            val sieve = ForgeRegistries.BLOCKS.getValue(modelLocation)
            val model = if(sieve is BlockSieve)  ModelSieve((sieve as BlockSieve).texture) else ModelLoaderRegistry.getMissingModel()
            cache.put(modelLocation, model)
            return model
        }
        // Barrel Models
        if(modelLocation.path.startsWith("barrel_"))
            TODO("not implemented")
        // Crucible Models
        if(modelLocation.path.startsWith("crucible_"))
            TODO("not implemented")
        // Unhandled model
        ExNihiloCreatio.logger.error("ENCModelLoader failed to load resource: ", modelLocation.toString())
        return ModelLoaderRegistry.getMissingModel()
    }

    override fun accepts(modelLocation: ResourceLocation): Boolean {
        // Ignore everyone else
        if(modelLocation.namespace != MODID)
            return false
        // Sieve Models
        if(modelLocation.path.startsWith("sieve_"))
            return true
        // Barrel Models
        if(modelLocation.path.startsWith("barrel_"))
            return true
        // Crucible Models
        if(modelLocation.path.startsWith("crucible_"))
            return true

        // Miscellaneous Ex Nihilo Creatio models.
        return false
    }

}