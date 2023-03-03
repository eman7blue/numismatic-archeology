package io.github.eman7blue.numis_arch.item;

import io.github.eman7blue.numis_arch.NumismaticArcheology;
import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CoinItems {
    public static final Item ANIMAL_COIN =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "animal_coin"),
                    new Item(new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item BEE_COIN =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "bee_coin"),
                    new Item(new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item ENDER_COIN =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "ender_coin"),
                    new Item(new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item PARROT_COIN =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "parrot_coin"),
                    new Item(new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item PIGLIN_COIN =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "piglin_coin"),
                    new Item(new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item SNIFFER_COIN =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "sniffer_coin"),
                    new Item(new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item TURTLE_COIN =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "turtle_coin"),
                    new Item(new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item VILLAGER_COIN =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "villager_coin"),
                    new Item(new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));

    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier("numis_arch", "numismatic_archeology"))
            .displayName(Text.translatable("itemGroup.numis_arch.numismaticArcheology"))
            .icon(() -> new ItemStack(CoinItems.BEE_COIN))
            .entries((displayContext, entries) -> {
                entries.add(CoinItems.ANIMAL_COIN);
                entries.add(CoinItems.BEE_COIN);
                entries.add(CoinItems.ENDER_COIN);
                entries.add(CoinItems.PARROT_COIN);
                entries.add(CoinItems.PIGLIN_COIN);
                entries.add(CoinItems.SNIFFER_COIN);
                entries.add(CoinItems.TURTLE_COIN);
                entries.add(CoinItems.VILLAGER_COIN);
                entries.add(NumisArchBlocks.COIN_COLLECTOR_TROPHY);
            })
            .build();


    public static void init() {
        NumismaticArcheology.LOGGER.info("Items initialized");

    }
}
