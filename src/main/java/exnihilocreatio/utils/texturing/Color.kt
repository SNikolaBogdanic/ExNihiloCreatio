package exnihilocreatio.utils.texturing

data class Color(val r: Float, val b: Float, val g: Float, val a: Float) {

    constructor(color: Int, ignoreAlpha: Boolean = true): this(
        (color shr 16 and 255) / 255.0f,
        (color shr 8 and 255) / 255.0f,
        (color and 255) / 255.0f,
        if (ignoreAlpha) 1.0f else (color shr 24 and 255) / 255.0f)

    constructor(hex: String): this(Integer.parseInt(hex, 16))

    fun toInt(): Int {
        var color = toIntNoAlpha()
        color = color or ((a * 255) as Int shl 24)
        return color
    }

    fun toIntNoAlpha(): Int {
        var color = 0
        color = color or ((r * 255) as Int shl 16)
        color = color or ((g * 255) as Int shl 8)
        color = color or (b * 255) as Int
        return color
    }

    override fun toString(): String {
        return "Color{r=%f, g=%f, b=%f, a=%f}".format(r, g, b, a)
    }

    fun getAsHexNoAlpha(): String {
        return Integer.toHexString(toIntNoAlpha())
    }

    companion object {
        @JvmStatic
        fun average(left: Color, right: Color, percentage: Float): Color {
            val opposite = 1 - percentage
            val avgR = Math.sqrt(left.r*left.r*opposite + right.r*right.r*percentage as Double) as Float
            val avgB = Math.sqrt(left.b*left.b*opposite + right.b*right.b*percentage as Double) as Float
            val avgG = Math.sqrt(left.g*left.g*opposite + right.g*right.g*percentage as Double) as Float
            val avgA = Math.sqrt(left.a*left.a*opposite + right.a*right.a*percentage as Double) as Float
            return Color(avgR, avgB, avgG, avgA)
        }
        @JvmStatic
        val DARK_RED = Color("AA0000")
        @JvmStatic
        val RED = Color("FF5555")
        @JvmStatic
        val GOLD = Color("FFAA00")
        @JvmStatic
        val YELLOW = Color("FFFF55")
        @JvmStatic
        val DARK_GREEN = Color("00AA00")
        @JvmStatic
        val GREEN = Color("55FF55")
        @JvmStatic
        val AQUA = Color("55FFFF")
        @JvmStatic
        val DARK_AQUA = Color("00AAAA")
        @JvmStatic
        val DARK_BLUE = Color("0000AA")
        @JvmStatic
        val BLUE = Color("5555FF")
        @JvmStatic
        val LIGHT_PURPLE = Color("FF55FF")
        @JvmStatic
        val DARK_PURPLE = Color("AA00AA")
        @JvmStatic
        val WHITE = Color("FFFFFF")
        @JvmStatic
        val GRAY = Color("AAAAAA")
        @JvmStatic
        val DARK_GRAY = Color("555555")
        @JvmStatic
        val BLACK = Color("000000")

    }
}