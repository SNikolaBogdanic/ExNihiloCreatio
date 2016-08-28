package exnihiloadscensio.client.renderers;

import exnihiloadscensio.items.ore.ItemOre;
import exnihiloadscensio.items.ore.Ore;
import exnihiloadscensio.registries.OreRegistry;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class RenderOrePiece implements IItemColor {

	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex) {
		if (stack == null)
			return 0;
		
		if (stack.getItem() instanceof ItemOre) {
			Ore ore = OreRegistry.ORES.getObjectById(stack.getItemDamage());
			return ore.getColor().toInt();
		}
		return 0;
	}

}
