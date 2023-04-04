package com.yor42.justenoughtimeless.jeiplugin;

import com.tac.guns.init.ModBlocks;
import com.yor42.justenoughtimeless.constants;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import static com.tac.guns.crafting.RecipeType.WORKBENCH;


@SuppressWarnings("unused")
@JeiPlugin
public class Plugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(constants.MODID, "jei_addon");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        registration.addRecipes(recipeManager.getAllRecipesFor(WORKBENCH), WorkbenchRecipePlugin.UID);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.WORKBENCH.get()), WorkbenchRecipePlugin.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new WorkbenchRecipePlugin(registration.getJeiHelpers().getGuiHelper()));
    }
}
