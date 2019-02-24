package exnihilocreatio.utils.crafting

import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import java.util.*

class EntityIngredient(entityStack: EntityStack):
        Ingredient(Arrays.stream(arrayOf(entityStack.getEggStack(1)))
                .map { t: ItemStack -> Ingredient.SingleItemList(t) }) {
    override fun isSimple() = false

    fun test(stack: EntityStack) {
        super.test(stack.getEggStack(1))
    }
    fun test(entity: Entity) {
        test(EntityStack(entity, 1))
    }
}