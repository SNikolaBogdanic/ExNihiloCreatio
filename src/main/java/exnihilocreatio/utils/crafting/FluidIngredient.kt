package exnihilocreatio.utils.crafting

import exnihilocreatio.utils.ItemUtils
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import java.util.*

class FluidIngredient(val fluid : FluidStack) :
        Ingredient(Arrays.stream(arrayOf(FluidUtil.getFilledBucket(fluid)))
                .map { t : ItemStack -> Ingredient.SingleItemList(t) }) {

    override fun isSimple() = false

    override fun test(stack : ItemStack?) : Boolean {
        return fluid.isFluidEqual(ItemUtils.StackOfOne(stack))
    }
}