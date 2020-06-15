package tfar.fastfurnaceminusreplacement.mixin;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.fastfurnaceminusreplacement.Duck;
import tfar.fastfurnaceminusreplacement.Hooks;

import java.util.Optional;

@Mixin(AbstractFurnaceTileEntity.class)

public class AbstractFurnaceTileEntityMixin implements Duck {

	protected AbstractCookingRecipe cachedRecipe = null;
	protected ItemStack failedMatch = ItemStack.EMPTY;

	@Redirect(method = "tick",
					at = @At(value = "INVOKE",
									target = "Lnet/minecraft/item/crafting/RecipeManager;getRecipe(Lnet/minecraft/item/crafting/IRecipeType;Lnet/minecraft/inventory/IInventory;Lnet/minecraft/world/World;)Ljava/util/Optional;"))
	private Optional<? extends AbstractCookingRecipe> getCachedRecipe(RecipeManager recipeManager, IRecipeType<? extends AbstractCookingRecipe> type, IInventory inventoryIn, World worldIn) {
		return Hooks.lookUpRecipe((AbstractFurnaceTileEntity) (Object) this, recipeManager, type);
	}

	@Override
	public AbstractCookingRecipe getRecipe() {
		return cachedRecipe;
	}

	@Override
	public void setRecipe(AbstractCookingRecipe recipe) {
		this.cachedRecipe = recipe;
	}

	@Override
	public ItemStack getFailedMatch() {
		return failedMatch;
	}

	@Override
	public void setFailedMatch(ItemStack stack) {
		this.failedMatch = stack;
	}
}

