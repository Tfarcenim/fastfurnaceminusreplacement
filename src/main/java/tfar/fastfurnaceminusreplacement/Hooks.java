package tfar.fastfurnaceminusreplacement;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod(Hooks.MODID)
public class Hooks {

	public static final String MODID = "fastfurnaceminusreplacement";

	public static Optional<? extends AbstractCookingRecipe> lookUpRecipe(AbstractFurnaceTileEntity furnace, RecipeManager recipeManager, IRecipeType<? extends AbstractCookingRecipe> recipeType) {
		ItemStack input = furnace.getStackInSlot(0);
		if (input.isEmpty() || input == ((Duck) furnace).getFailedMatch())
			return Optional.empty();

		if (curRecipe(furnace) != null && curRecipe(furnace).matches(furnace, furnace.getWorld()))
			return Optional.of(curRecipe(furnace));
		else {
			AbstractCookingRecipe rec = recipeManager.getRecipe(recipeType, furnace, furnace.getWorld()).orElse(null);
			if (rec == null) setFailedMatch(furnace, input);
			else setFailedMatch(furnace, ItemStack.EMPTY);
			setCurRecipe(furnace, rec);
			return Optional.ofNullable(curRecipe(furnace));
		}
	}

	public static void setFailedMatch(AbstractFurnaceTileEntity abstractFurnaceBlockEntity, ItemStack stack) {
		((Duck) abstractFurnaceBlockEntity).setFailedMatch(stack);
	}

	public static AbstractCookingRecipe curRecipe(AbstractFurnaceTileEntity abstractFurnaceBlockEntity) {
		return ((Duck) abstractFurnaceBlockEntity).getRecipe();
	}

	public static void setCurRecipe(AbstractFurnaceTileEntity abstractFurnaceBlockEntity, AbstractCookingRecipe recipe) {
		((Duck) abstractFurnaceBlockEntity).setRecipe(recipe);
	}
}
