package io.github.eman7blue.numis_arch.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_TYPE, id(AncientActivatingRecipe.Type.ID), AncientActivatingRecipe.Type.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER, AncientActivatingRecipeSerializer.ID, AncientActivatingRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, id(NumismaticGradingRecipe.Type.ID), NumismaticGradingRecipe.Type.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER, NumismaticGradingRecipeSerializer.ID, NumismaticGradingRecipeSerializer.INSTANCE);
    }
}
