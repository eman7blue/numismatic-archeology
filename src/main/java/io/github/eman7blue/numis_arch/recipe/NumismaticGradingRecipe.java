package io.github.eman7blue.numis_arch.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

public class NumismaticGradingRecipe implements Recipe<Inventory> {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int poor;
    private final int fine;
    private final int superb;
    private final RecipeSerializer<NumismaticGradingRecipe> serializer = NumisArchRecipes.NUMISMATIC_GRADING;
    private final String group;

    public NumismaticGradingRecipe(String group, Ingredient input, ItemStack result, int poor, int fine, int superb) {
        this.group = group;
        this.ingredient = input;
        this.result = result;
        this.poor = poor;
        this.fine = fine;
        this.superb = superb;
    }

    public Ingredient getIngredient() {
        return ingredient;
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
        return ingredient.test(inventory.getStack(0));
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
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return result.copyWithCount(1);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    @Override
    public RecipeType<?> getType() {
        return NumismaticGradingRecipe.Type.INSTANCE;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    public static class Serializer implements RecipeSerializer<NumismaticGradingRecipe> {
        private final NumismaticGradingRecipe.Serializer.RecipeFactory<NumismaticGradingRecipe> recipeFactory;
        private final Codec<NumismaticGradingRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codecs.createStrictOptionalFieldCodec(Codec.STRING, "group", "").forGetter(recipe -> recipe.group),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                Registries.ITEM.getCodec().xmap(ItemStack::new, ItemStack::getItem).fieldOf("result").forGetter(recipe -> recipe.result),
                Codec.INT.fieldOf("poor").orElse(6).forGetter(recipe -> recipe.poor),
                Codec.INT.fieldOf("fine").orElse(13).forGetter(recipe -> recipe.fine),
                Codec.INT.fieldOf("superb").orElse(1).forGetter(recipe -> recipe.superb))
                .apply(instance, NumismaticGradingRecipe::new));

        public Serializer(NumismaticGradingRecipe.Serializer.RecipeFactory<NumismaticGradingRecipe> recipeFactory) {
            this.recipeFactory = recipeFactory;
        }

        @Override
        public Codec<NumismaticGradingRecipe> codec() {
            return this.CODEC;
        }

        @Override
        public NumismaticGradingRecipe read(PacketByteBuf buf) {
            String string = buf.readString();
            Ingredient ingredient = Ingredient.fromPacket(buf);
            ItemStack itemStack = buf.readItemStack();
            int poor = buf.readInt();
            int fine = buf.readInt();
            int superb = buf.readInt();
            return this.recipeFactory.create(string, ingredient, itemStack, poor, fine, superb);
        }

        @Override
        public void write(PacketByteBuf buf, NumismaticGradingRecipe recipe) {
            buf.writeString(recipe.group);
            recipe.getIngredient().write(buf);
            buf.writeItemStack(recipe.getResult(DynamicRegistryManager.EMPTY));
            buf.writeInt(recipe.getPoor());
            buf.writeInt(recipe.getFine());
            buf.writeInt(recipe.getSuperb());
        }

        public interface RecipeFactory<NumismaticGradingRecipe> {
            NumismaticGradingRecipe create(String var1, Ingredient var2, ItemStack var3, int var4, int var5, int var6);
        }
    }

    public static class Type implements RecipeType<NumismaticGradingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "numismatic_grading";
        private Type() {}
    }
}
