package io.github.eman7blue.numis_arch.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;


public class NumisArchItemGroup {
    protected static final ItemGroup MAIN_ITEM_GROUP;
    protected static final ItemGroup COIN_ITEM_GROUP;

    public static void registerItemGroup(){
        //Registry.register(Registries.ITEM_GROUP, id("numismatic_archeology_coins"), COIN_ITEM_GROUP);
        Registry.register(Registries.ITEM_GROUP, id("numismatic_archeology"), MAIN_ITEM_GROUP);
    }

    private static ItemStack getCoinCondition(Item item, int condition) {
        ItemStack itemStack = item.getDefaultStack();
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        nbtCompound.putInt("condition", condition);
        return itemStack;
    }

    private static void addAllCoinEntries(ItemGroup.Entries entries, Item coin) {
        entries.add(coin);
        entries.add(getCoinCondition(coin, 0));
        entries.add(getCoinCondition(coin, 1));
        entries.add(getCoinCondition(coin, 2));
    }

    static {
        MAIN_ITEM_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup.numis_arch.numismaticArcheology")).icon(() -> new ItemStack(NumisArchItems.NUMISMATIC_DESK))
                .entries((displayContext, entries) -> {
                    entries.add(Items.SUSPICIOUS_SAND);
                    entries.add(Items.SUSPICIOUS_GRAVEL);
                    entries.add(Items.BRUSH);
                    entries.add(NumisArchItems.DIAMOND_BRUSH);
                    entries.add(NumisArchItems.ODD_GREEN_FIGURINE);
                    entries.add(NumisArchItems.FIGURINE_OF_JUMPING);
                    entries.add(NumisArchItems.THUNDERSTONE);
                    entries.add(NumisArchItems.CHARGED_THUNDERSTONE);
                    entries.add(NumisArchItems.MAGNIFYING_GLASS);
                    entries.add(NumisArchItems.SUSPICIOUS_RED_SAND);
                    entries.add(NumisArchItems.SUSPICIOUS_SOUL_SAND);
                    entries.add(NumisArchItems.SUSPICIOUS_END_STONE);
                    entries.add(NumisArchItems.THUNDERSTONE_BLOCK);
                    entries.add(NumisArchItems.ANCIENT_ACTIVATOR);
                    entries.add(NumisArchItems.NUMISMATIC_DESK);
//                    entries.add(NumisArchItems.ANIMAL_COIN);
//                    entries.add(NumisArchItems.BEE_COIN);
//                    entries.add(NumisArchItems.ENDER_COIN);
//                    entries.add(NumisArchItems.PARROT_COIN);
//                    entries.add(NumisArchItems.PIGLIN_COIN);
//                    entries.add(NumisArchItems.SNIFFER_COIN);
//                    entries.add(NumisArchItems.TURTLE_COIN);
//                    entries.add(NumisArchItems.VILLAGER_COIN);
                    entries.add(NumisArchItems.COIN_COLLECTOR_TROPHY);
                    addAllCoinEntries(entries, NumisArchItems.ANIMAL_COIN);
                    addAllCoinEntries(entries, NumisArchItems.BEE_COIN);
                    addAllCoinEntries(entries, NumisArchItems.ENDER_COIN);
                    addAllCoinEntries(entries, NumisArchItems.PARROT_COIN);
                    addAllCoinEntries(entries, NumisArchItems.PIGLIN_COIN);
                    addAllCoinEntries(entries, NumisArchItems.SNIFFER_COIN);
                    addAllCoinEntries(entries, NumisArchItems.TURTLE_COIN);
                    addAllCoinEntries(entries, NumisArchItems.VILLAGER_COIN);
                }).build();
        COIN_ITEM_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup.numis_arch.numismaticArcheologyCoins")).icon(() -> new ItemStack(NumisArchItems.PIGLIN_COIN))
                .entries((displayContext, entries) -> {
                    addAllCoinEntries(entries, NumisArchItems.ANIMAL_COIN);
                    addAllCoinEntries(entries, NumisArchItems.BEE_COIN);
                    addAllCoinEntries(entries, NumisArchItems.ENDER_COIN);
                    addAllCoinEntries(entries, NumisArchItems.PARROT_COIN);
                    addAllCoinEntries(entries, NumisArchItems.PIGLIN_COIN);
                    addAllCoinEntries(entries, NumisArchItems.SNIFFER_COIN);
                    addAllCoinEntries(entries, NumisArchItems.TURTLE_COIN);
                    addAllCoinEntries(entries, NumisArchItems.VILLAGER_COIN);
                }).build();
    }
}
