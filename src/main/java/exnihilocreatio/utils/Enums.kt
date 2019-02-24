package exnihilocreatio.utils

import net.minecraft.util.ResourceLocation

enum class VanillaWoodTypes(val text: String) {
    OAK("oak"),
    BIRCH("birch"),
    SPRUCE("spruce"),
    JUNGLE("jungle"),
    ACACIA("acacia"),
    DARK_OAK("dark_oak");

    fun getPlanksResource(): ResourceLocation {
        return ResourceLocation("minecraft:block/planks_%s".format(getTextureName()))
    }

    fun getBarkResource(): ResourceLocation {
        return ResourceLocation("minecraft:block/bark_%s".format(getTextureName()))
    }

    fun getTextureName(): String {
        if(this != DARK_OAK)
            return text
        else
            return "big_oak" // Seriously "big" oak?
    }

    fun getName(): String {
        return this.text
    }

    fun suffix(s: String): String {
        return this.text + s
    }

    fun prefix(s: String): String {
        return s + this.text
    }
}