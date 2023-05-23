package io.github.eman7blue.numis_arch.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.LOGGER;
import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumismaticGradingRecipeSerializer implements RecipeSerializer<NumismaticGradingRecipe> {
    public static final NumismaticGradingRecipeSerializer INSTANCE = new NumismaticGradingRecipeSerializer();
    public static final Identifier ID = id("numismatic_grading");

    @Override
    public NumismaticGradingRecipe read(Identifier id, JsonObject json) {
        NumismaticGradingRecipeJsonFormat recipeJson = new Gson().fromJson(json, NumismaticGradingRecipeJsonFormat.class);
        int poor = recipeJson.poor;
        int fine = recipeJson.fine;
        int superb = recipeJson.superb;
        if (recipeJson.output == null || recipeJson.input == null) {
            throw new JsonSyntaxException("Missing attributes for numismatic desk recipe!");
        }
        if (poor == 0 || fine == 0 || superb == 0) {
            LOGGER.info("Missing one or more odds for numismatic desk recipe " + recipeJson.output + ", using default odds (6 poor; 13 fine; 1 superb) instead");
            poor = 6;
            fine = 13;
            superb = 1;
        }
        Ingredient input = Ingredient.fromJson(recipeJson.input);
        Item outputItem = Registries.ITEM.getOrEmpty(new Identifier(recipeJson.output))
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.output + " for numismatic desk recipe"));
        ItemStack output = new ItemStack(outputItem);
        return new NumismaticGradingRecipe(input, output, poor, fine, superb, id);
    }

    @Override
    public NumismaticGradingRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient input = Ingredient.fromPacket(buf);
        ItemStack output = buf.readItemStack();
        int poor = buf.readInt();
        int fine = buf.readInt();
        int superb = buf.readInt();
        return new NumismaticGradingRecipe(input, output, poor, fine, superb, id);
    }

    @Override
    public void write(PacketByteBuf buf, NumismaticGradingRecipe recipe) {
        recipe.getInput().write(buf);
        buf.writeItemStack(recipe.getOutput(DynamicRegistryManager.EMPTY));
        buf.writeInt(recipe.getPoor());
        buf.writeInt(recipe.getFine());
        buf.writeInt(recipe.getSuperb());
    }

    static class NumismaticGradingRecipeJsonFormat {
        JsonObject input;
        String output;
        int poor;
        int fine;
        int superb;
    }

}
