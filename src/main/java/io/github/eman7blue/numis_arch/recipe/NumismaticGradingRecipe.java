package io.github.eman7blue.numis_arch.recipe;

import net.minecraft.client.MinecraftClient;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class NumismaticGradingRecipe implements Recipe<Inventory> {
    private final Ingredient input;
    private final ItemStack output;
    private final int poor;
    private final int fine;
    private final int superb;

    private final Identifier id;

    public NumismaticGradingRecipe(Ingredient input, ItemStack output, int poor, int fine, int superb, Identifier id) {
        this.input = input;
        this.output = output;
        this.poor = poor;
        this.fine = fine;
        this.superb = superb;
        this.id = id;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getPoor(){
        return poor;
    }

    public int getFine() {
        return fine;
    }

    public int getSuperb() {
        return superb;
    }

    public int getWeightSum() {
        return poor + fine + superb;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return input.test(inventory.getStack(0));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copyWithCount(1);
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NumismaticGradingRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return NumismaticGradingRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<NumismaticGradingRecipe> {
        public static final NumismaticGradingRecipe.Type INSTANCE = new NumismaticGradingRecipe.Type();
        public static final String ID = "numismatic_grading";
        private Type() {}
    }
}
