package io.github.eman7blue.numis_arch.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class CoinItem extends Item {

    public static final TagKey<Item> COIN_ITEM_TAG = TagKey.of(RegistryKeys.ITEM, id("coins"));

    public CoinItem(Settings settings) {
        super(settings);
    }


    @Override
    public boolean hasGlint(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.getInt("condition") == Condition.SUPERB;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound == null) {
            tooltip.add(Text.translatable("coin.condition.unknown").formatted(Formatting.GRAY));
        } else {
            int condition = nbtCompound.getInt("condition");
            switch (condition) {
                case Condition.POOR -> tooltip.add(Text.translatable("coin.condition.poor").formatted(Formatting.DARK_GRAY));
                case Condition.FINE -> tooltip.add(Text.translatable("coin.condition.fine").formatted(Formatting.GRAY));
                case Condition.SUPERB -> tooltip.add(Text.translatable("coin.condition.superb").formatted(Formatting.AQUA));
                default -> tooltip.add(Text.translatable("coin.condition.unknown").formatted(Formatting.GRAY));
            }
        }
    }

    public static boolean isUnknown(ItemStack stack) {
        if (stack.isIn(COIN_ITEM_TAG)) {
            NbtCompound nbtCompound = stack.getNbt();
            if (nbtCompound == null) {
                return true;
            } else {
                int condition = nbtCompound.getInt("condition");
                return condition != Condition.FINE && condition != Condition.POOR && condition != Condition.SUPERB;
            }
        }
        return false;
    }

    public static boolean compareCondition(ItemStack itemStack1, ItemStack itemStack2) {
        if (!itemStack1.isOf(itemStack2.getItem())) {
            return false;
        }
        NbtCompound nbt1 = itemStack1.getNbt();
        NbtCompound nbt2 = itemStack2.getNbt();
        if (nbt1 == null || nbt2 == null) {
            return false;
        }
        return nbt1.getInt("condition") == nbt2.getInt("condition");
    }

    public static class Condition {
        public static final int POOR = 0;
        public static final int FINE = 1;
        public static final int SUPERB = 2;
    }
}
