package io.github.eman7blue.numis_arch.item;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchItems {
    public static final Item ANIMAL_COIN;
    public static final Item BEE_COIN;
    public static final Item ENDER_COIN;
    public static final Item PARROT_COIN;
    public static final Item PIGLIN_COIN;
    public static final Item SNIFFER_COIN;
    public static final Item TURTLE_COIN;
    public static final Item VILLAGER_COIN;
    public static final Item ODD_GREEN_FIGURINE;
    public static final Item COIN_COLLECTOR_TROPHY;
    public static final Item SUSPICIOUS_RED_SAND;
    public static final Item SUSPICIOUS_SOUL_SAND;
    public static final Item SUSPICIOUS_END_STONE;
    public static final Item NUMISMATIC_DESK;

    static {
        ANIMAL_COIN = Registry.register(Registries.ITEM, id("animal_coin"), new Item(new FabricItemSettings()));
        BEE_COIN = Registry.register(Registries.ITEM, id("bee_coin"), new Item(new FabricItemSettings()));
        ENDER_COIN = Registry.register(Registries.ITEM, id("ender_coin"), new Item(new FabricItemSettings()));
        PARROT_COIN = Registry.register(Registries.ITEM, id("parrot_coin"), new Item(new FabricItemSettings()));
        PIGLIN_COIN = Registry.register(Registries.ITEM, id("piglin_coin"), new Item(new FabricItemSettings()));
        SNIFFER_COIN = Registry.register(Registries.ITEM, id("sniffer_coin"), new Item(new FabricItemSettings()));
        TURTLE_COIN = Registry.register(Registries.ITEM, id("turtle_coin"), new Item(new FabricItemSettings()));
        VILLAGER_COIN = Registry.register(Registries.ITEM, id("villager_coin"), new Item(new FabricItemSettings()));
        ODD_GREEN_FIGURINE = Registry.register(Registries.ITEM, id("odd_green_figurine"), new Item(new FabricItemSettings()));
        COIN_COLLECTOR_TROPHY = Registry.register(Registries.ITEM, id("coin_collector_trophy"), new BlockItem(NumisArchBlocks.COIN_COLLECTOR_TROPHY, new FabricItemSettings()));
        SUSPICIOUS_RED_SAND = Registry.register(Registries.ITEM, id("suspicious_red_sand"), new BlockItem(NumisArchBlocks.SUSPICIOUS_RED_SAND, new FabricItemSettings()));
        SUSPICIOUS_SOUL_SAND = Registry.register(Registries.ITEM, id("suspicious_soul_sand"), new BlockItem(NumisArchBlocks.SUSPICIOUS_SOUL_SAND, new FabricItemSettings()));
        SUSPICIOUS_END_STONE = Registry.register(Registries.ITEM, id("suspicious_end_stone"), new BlockItem(NumisArchBlocks.SUSPICIOUS_END_STONE, new FabricItemSettings()));
        NUMISMATIC_DESK = Registry.register(Registries.ITEM, id("numismatic_desk"), new BlockItem(NumisArchBlocks.NUMISMATIC_DESK, new FabricItemSettings()));

    }
}
