package io.github.eman7blue.numis_arch.item;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BrushItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchItems {
    public static final Item ANCIENT_ACTIVATOR;
    public static final Item BEE_COIN;
    public static final Item CHARGED_THUNDERSTONE;
    public static final Item COIN_COLLECTOR_TROPHY;
    public static final Item DIAMOND_BRUSH;
    public static final Item ENDER_COIN;
    public static final Item FIGURINE_OF_JUMPING;
    public static final Item MAGNIFYING_GLASS;
    public static final Item NUMISMATIC_DESK;
    public static final Item ODD_GREEN_FIGURINE;
    public static final Item PARROT_COIN;
    public static final Item PIGLIN_COIN;
    public static final Item RABBIT_COIN;
    public static final Item SNIFFER_COIN;
    public static final Item SUSPICIOUS_END_STONE;
    public static final Item SUSPICIOUS_RED_SAND;
    public static final Item SUSPICIOUS_SOUL_SAND;
    public static final Item THUNDERSTONE;
    public static final Item THUNDERSTONE_BLOCK;
    public static final Item TURTLE_COIN;
    public static final Item VILLAGER_COIN;

    public static void registerItems() {
        Registry.register(Registries.ITEM, id("ancient_activator"), ANCIENT_ACTIVATOR);
        Registry.register(Registries.ITEM, id("bee_coin"), BEE_COIN);
        Registry.register(Registries.ITEM, id("charged_thunderstone"), CHARGED_THUNDERSTONE);
        Registry.register(Registries.ITEM, id("coin_collector_trophy"), COIN_COLLECTOR_TROPHY);
        Registry.register(Registries.ITEM, id("diamond_brush"), DIAMOND_BRUSH);
        Registry.register(Registries.ITEM, id("ender_coin"), ENDER_COIN);
        Registry.register(Registries.ITEM, id("figurine_of_jumping"), FIGURINE_OF_JUMPING);
        Registry.register(Registries.ITEM, id("magnifying_glass"), MAGNIFYING_GLASS);
        Registry.register(Registries.ITEM, id("numismatic_desk"), NUMISMATIC_DESK);
        Registry.register(Registries.ITEM, id("odd_green_figurine"), ODD_GREEN_FIGURINE);
        Registry.register(Registries.ITEM, id("parrot_coin"), PARROT_COIN);
        Registry.register(Registries.ITEM, id("piglin_coin"), PIGLIN_COIN);
        Registry.register(Registries.ITEM, id("rabbit_coin"), RABBIT_COIN);
        Registry.register(Registries.ITEM, id("sniffer_coin"), SNIFFER_COIN);
        Registry.register(Registries.ITEM, id("suspicious_end_stone"), SUSPICIOUS_END_STONE);
        Registry.register(Registries.ITEM, id("suspicious_red_sand"), SUSPICIOUS_RED_SAND);
        Registry.register(Registries.ITEM, id("suspicious_soul_sand"), SUSPICIOUS_SOUL_SAND);
        Registry.register(Registries.ITEM, id("thunderstone"), THUNDERSTONE);
        Registry.register(Registries.ITEM, id("thunderstone_block"), THUNDERSTONE_BLOCK);
        Registry.register(Registries.ITEM, id("turtle_coin"), TURTLE_COIN);
        Registry.register(Registries.ITEM, id("villager_coin"), VILLAGER_COIN);
    }

    static {
        ANCIENT_ACTIVATOR = new BlockItem(NumisArchBlocks.ANCIENT_ACTIVATOR, new FabricItemSettings());
        BEE_COIN =  new CoinItem(new FabricItemSettings());
        CHARGED_THUNDERSTONE = new ChargedThunderstoneItem(new FabricItemSettings());
        COIN_COLLECTOR_TROPHY = new BlockItem(NumisArchBlocks.COIN_COLLECTOR_TROPHY, new FabricItemSettings());
        DIAMOND_BRUSH = new BrushItem(new FabricItemSettings().maxDamage(96));
        ENDER_COIN =  new CoinItem(new FabricItemSettings());
        FIGURINE_OF_JUMPING = new FigurineOfJumpingItem(new FabricItemSettings().maxDamage(6));
        MAGNIFYING_GLASS = new MagnifyingGlassItem(new FabricItemSettings().maxCount(1));
        NUMISMATIC_DESK = new BlockItem(NumisArchBlocks.NUMISMATIC_DESK, new FabricItemSettings());
        ODD_GREEN_FIGURINE = new Item(new FabricItemSettings().maxCount(1));
        PARROT_COIN = new CoinItem(new FabricItemSettings());
        PIGLIN_COIN = new CoinItem(new FabricItemSettings());
        RABBIT_COIN = new CoinItem(new FabricItemSettings());
        SNIFFER_COIN = new CoinItem(new FabricItemSettings());
        SUSPICIOUS_END_STONE = new BlockItem(NumisArchBlocks.SUSPICIOUS_END_STONE, new FabricItemSettings());
        SUSPICIOUS_RED_SAND = new BlockItem(NumisArchBlocks.SUSPICIOUS_RED_SAND, new FabricItemSettings());
        SUSPICIOUS_SOUL_SAND = new BlockItem(NumisArchBlocks.SUSPICIOUS_SOUL_SAND, new FabricItemSettings());
        THUNDERSTONE = new Item(new FabricItemSettings());
        THUNDERSTONE_BLOCK = new BlockItem(NumisArchBlocks.THUNDERSTONE_BLOCK, new FabricItemSettings());
        TURTLE_COIN = new CoinItem(new FabricItemSettings());
        VILLAGER_COIN = new CoinItem(new FabricItemSettings());
    }
}
