package tfar.fastfurnaceminusreplacement;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;

public interface Duck {
	AbstractCookingRecipe getRecipe();
	void setRecipe(AbstractCookingRecipe recipe);
	ItemStack getFailedMatch();
	void setFailedMatch(ItemStack stack);
}
