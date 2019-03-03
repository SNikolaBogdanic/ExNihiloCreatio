package exnihilocreatio.utils

import net.minecraft.item.ItemStack
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

/**
 * Intelligently creates an item handler for a stack
 */
object ItemHandlerFactory {
    fun getHandler(stack: ItemStack): IItemHandler {
        // Check special handlers first
        // TODO /dank/null, shulker boxes, IE crates ...

        // Check the stack for a capability or default to a SingleItemStackHandler
        return stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(SingleItemStackHandler(stack))
    }

    class SingleItemStackHandler(private var inv: ItemStack, private val limit: Int): IItemHandler {

        constructor(inv: ItemStack): this(inv, inv.maxStackSize)

        override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
            if(!ItemUtils.areEquivalent(stack, inv))
                return stack
            val returnStack = stack.copy()
            val amt = minOf(stack.count, limit - inv.count)
            returnStack.count = stack.count - amt
            if(!simulate)
                inv.count += amt
            return returnStack
        }

        override fun getStackInSlot(slot: Int): ItemStack {
            return if(slot != 0) ItemStack.EMPTY else inv
        }

        override fun getSlotLimit(slot: Int): Int {
            return if(slot != 0) 0 else limit
        }

        override fun getSlots(): Int {
            return 1
        }

        override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack {
            if(slot != 0)
                return ItemStack.EMPTY
            val returnStack = inv.copy()
            returnStack.count = minOf(inv.count, amount)
            if(!simulate)
                inv.shrink(returnStack.count)
            if(inv.count == 0)
                inv = ItemStack.EMPTY
            return returnStack
        }

    }
}