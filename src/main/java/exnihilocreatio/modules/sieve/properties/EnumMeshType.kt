package exnihilocreatio.modules.sieve.properties

import net.minecraft.util.IStringSerializable

enum class EnumMeshType(val text: String): IStringSerializable {
    EMPTY("empty"),
    STRING("string"),
    FLINT("flint"),
    IRON("iron"),
    DIAMOND("diamond");

    override fun getName(): String {
        return text
    }

    override fun toString(): String {
        return text
    }
}