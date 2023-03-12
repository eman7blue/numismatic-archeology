package io.github.eman7blue.numis_arch.item;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NumisArchItems {
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
    public static final Item COIN_COLLECTOR_TROPHY =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "coin_collector_trophy"),
                    new BlockItem(NumisArchBlocks.COIN_COLLECTOR_TROPHY, new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item SUSPICIOUS_RED_SAND =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "suspicious_red_sand"),
                    new BlockItem(NumisArchBlocks.SUSPICIOUS_RED_SAND, new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item SUSPICIOUS_GRAVEL =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "suspicious_gravel"),
                    new BlockItem(NumisArchBlocks.SUSPICIOUS_GRAVEL, new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item SUSPICIOUS_SOUL_SAND =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "suspicious_soul_sand"),
                    new BlockItem(NumisArchBlocks.SUSPICIOUS_SOUL_SAND, new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    public static final Item SUSPICIOUS_END_STONE =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "suspicious_end_stone"),
                    new BlockItem(NumisArchBlocks.SUSPICIOUS_END_STONE, new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));

    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier("numis_arch", "numismatic_archeology"))
            .displayName(Text.translatable("itemGroup.numis_arch.numismaticArcheology"))
            .icon(() -> new ItemStack(NumisArchItems.BEE_COIN))
            .entries((displayContext, entries) -> {
                entries.add(Items.SUSPICIOUS_SAND);
                entries.add(SUSPICIOUS_RED_SAND);
                entries.add(SUSPICIOUS_GRAVEL);
                entries.add(SUSPICIOUS_SOUL_SAND);
                entries.add(SUSPICIOUS_END_STONE);
                entries.add(Items.BRUSH);
                entries.add(ANIMAL_COIN);
                entries.add(BEE_COIN);
                entries.add(ENDER_COIN);
                entries.add(PARROT_COIN);
                entries.add(PIGLIN_COIN);
                entries.add(SNIFFER_COIN);
                entries.add(TURTLE_COIN);
                entries.add(VILLAGER_COIN);
                entries.add(COIN_COLLECTOR_TROPHY);
            })
            .build();
}
