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

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class AncientActivatingRecipeSerializer implements RecipeSerializer<AncientActivatingRecipe> {
    public static final AncientActivatingRecipeSerializer INSTANCE = new AncientActivatingRecipeSerializer();
    public static final Identifier ID = id("ancient_activating");
    @Override
    public AncientActivatingRecipe read(Identifier id, JsonObject json) {
        AncientActivatingRecipeJsonFormat recipeJson = new Gson().fromJson(json, AncientActivatingRecipeJsonFormat.class);
        if (recipeJson.output == null || recipeJson.input == null) {
            throw new JsonSyntaxException("Missing attributes for ancient activating recipe!");
        }
        Ingredient input = Ingredient.fromJson(recipeJson.input);
        Item outputItem = Registries.ITEM.getOrEmpty(new Identifier(recipeJson.output))
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.output + " for ancient activating recipe"));
        ItemStack output = new ItemStack(outputItem);
        return new AncientActivatingRecipe(input, output, id);
    }

    @Override
    public AncientActivatingRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient input = Ingredient.fromPacket(buf);
        ItemStack output = buf.readItemStack();
        return new AncientActivatingRecipe(input, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, AncientActivatingRecipe recipe) {
        recipe.getInput().write(buf);
        buf.writeItemStack(recipe.getOutput(DynamicRegistryManager.EMPTY));
    }
}
