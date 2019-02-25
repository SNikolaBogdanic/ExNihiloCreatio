package exnihilocreatio.utils

import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.registries.ForgeRegistries

enum class VanillaWoodTypes(val text: String) {
    OAK("oak"),
    BIRCH("birch"),
    SPRUCE("spruce"),
    JUNGLE("jungle"),
    ACACIA("acacia"),
    DARK_OAK("dark_oak");

    fun getPlanksResource(): ResourceLocation {
        return ResourceLocation("minecraft:block/%s_planks".format(getTextureName()))
    }

    fun getBarkResource(): ResourceLocation {
        return ResourceLocation("minecraft:block/%s_bark".format(getTextureName()))
    }

    fun getTextureName(): String {
        if(this != DARK_OAK)
            return text
        else
            return "big_oak" // Seriously "big" oak?
    }

    fun getName(): String {
        return text
    }

    fun suffix(s: String): String {
        return text + s
    }

    fun prefix(s: String): String {
        return s + text
    }

    fun getLog(): Block {
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation("minecraft", text + "_log")) ?: Blocks.OAK_LOG
    }

    fun getLeaves(): Block {
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation("minecraft", text + "_leaves")) ?: Blocks.OAK_LEAVES
    }

    fun getPlanks(): Block {
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation("minecraft", text + "_planks")) ?: Blocks.OAK_PLANKS
    }

    fun getSapling(): Block {
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation("minecraft", text + "_sapling")) ?: Blocks.OAK_SAPLING
    }
}