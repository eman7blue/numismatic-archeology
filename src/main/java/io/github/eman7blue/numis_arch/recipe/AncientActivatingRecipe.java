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

public class AncientActivatingRecipe implements Recipe<Inventory> {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final RecipeSerializer<AncientActivatingRecipe> serializer = NumisArchRecipes.ANCIENT_ACTIVATING;
    private final String group;

    public AncientActivatingRecipe(String group, Ingredient ingredient, ItemStack result) {
        this.ingredient = ingredient;
        this.result = result;
        this.group = group;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return this.ingredient.test(inventory.getStack(0));
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
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    public static class Serializer implements RecipeSerializer<AncientActivatingRecipe> {
        private final RecipeFactory<AncientActivatingRecipe> recipeFactory;
        private final Codec<AncientActivatingRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codecs.createStrictOptionalFieldCodec(Codec.STRING, "group", "").forGetter(recipe -> recipe.group),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                Registries.ITEM.getCodec().xmap(ItemStack::new, ItemStack::getItem).fieldOf("result").forGetter(recipe -> recipe.result))
                .apply(instance, AncientActivatingRecipe::new));

        public Serializer(RecipeFactory<AncientActivatingRecipe> recipeFactory) {
            this.recipeFactory = recipeFactory;
        }

        @Override
        public Codec<AncientActivatingRecipe> codec() {
            return this.CODEC;
        }

        @Override
        public AncientActivatingRecipe read(PacketByteBuf buf) {
            String string = buf.readString();
            Ingredient ingredient = Ingredient.fromPacket(buf);
            ItemStack itemStack = buf.readItemStack();
            return this.recipeFactory.create(string, ingredient, itemStack);
        }

        @Override
        public void write(PacketByteBuf buf, AncientActivatingRecipe recipe) {
            buf.writeString(recipe.group);
            recipe.getIngredient().write(buf);
            buf.writeItemStack(recipe.getResult(DynamicRegistryManager.EMPTY));
        }
        public interface RecipeFactory<AncientActivatingRecipe> {
            AncientActivatingRecipe create(String var1, Ingredient var2, ItemStack var3);
        }
    }

    public static class Type implements RecipeType<AncientActivatingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "ancient_activating";
        private Type() {}
    }
}
