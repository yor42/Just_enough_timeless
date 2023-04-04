package com.yor42.justenoughtimeless.jeiplugin;

import com.tac.guns.Reference;
import com.tac.guns.crafting.WorkbenchRecipe;
import com.tac.guns.init.ModBlocks;
import com.yor42.justenoughtimeless.constants;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkbenchRecipePlugin implements IRecipeCategory<WorkbenchRecipe> {
    private final IDrawable icon;
    private final IDrawable background;
    public static final ResourceLocation UID = new ResourceLocation(constants.MODID, "jei_workbench");

    public WorkbenchRecipePlugin(IGuiHelper helper){
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.WORKBENCH.get()));
        ResourceLocation TEXTURE = new ResourceLocation(constants.MODID, "textures/gui/jeiworkbench.png");
        this.background = helper.drawableBuilder(TEXTURE, 0, 0, 148, 86).setTextureSize(148, 86).build();
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends WorkbenchRecipe> getRecipeClass() {
        return WorkbenchRecipe.class;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public ITextComponent getTitleAsTextComponent() {
        return new TranslationTextComponent("recipe.gunworkbench");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(WorkbenchRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> input = new ArrayList<>();
        recipe.getMaterials().forEach((ingredient)->{
            List<ItemStack> stack = new ArrayList<>();
            for (ItemStack item: ingredient.getFirst().getItems()){
                item.setCount(ingredient.getSecond());
                stack.add(item);
            }
            input.add(stack);
        });

        ingredients.setInputLists(VanillaTypes.ITEM,input);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, WorkbenchRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemstacks = recipeLayout.getItemStacks();
        List<List<ItemStack>> list = ingredients.getInputs(VanillaTypes.ITEM);
        int size = list.size();
        int index = 0;
        for(int j=0; j<Math.min(size, 5); j++){
            for(int k=0; k<Math.min(4, size % 4); k++){
                index = j*4+k;
                itemstacks.init(index, true, 3+18*k, 3+18*j);
            }
        }
        itemstacks.init(index+1, false, 109, 34);
        itemstacks.set(ingredients);
    }
}
