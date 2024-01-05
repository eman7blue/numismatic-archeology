package io.github.eman7blue.numis_arch.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchRecipes {

    public static final RecipeSerializer<AncientActivatingRecipe> ANCIENT_ACTIVATING = Registry.register(Registries.RECIPE_SERIALIZER, "ancient_activating", new AncientActivatingRecipe.Serializer(AncientActivatingRecipe::new));
    public static final RecipeSerializer<NumismaticGradingRecipe> NUMISMATIC_GRADING = Registry.register(Registries.RECIPE_SERIALIZER, "numismatic_grading", new NumismaticGradingRecipe.Serializer(NumismaticGradingRecipe::new));

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_TYPE, id(AncientActivatingRecipe.Type.ID), AncientActivatingRecipe.Type.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, id(NumismaticGradingRecipe.Type.ID), NumismaticGradingRecipe.Type.INSTANCE);
    }
}
