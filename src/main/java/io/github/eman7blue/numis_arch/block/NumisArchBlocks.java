package io.github.eman7blue.numis_arch.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class NumisArchBlocks {
    public static final CoinCollectorTrophyBlock COIN_COLLECTOR_TROPHY;
    public static final BrushableBlock SUSPICIOUS_RED_SAND;
    public static final SuspiciousSoulSandBlock SUSPICIOUS_SOUL_SAND;
    public static final SuspiciousEndStoneBlock SUSPICIOUS_END_STONE;
    public static final Block NUMISMATIC_DESK;

    public static void init() {
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "coin_collector_trophy"), COIN_COLLECTOR_TROPHY);
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "suspicious_red_sand"), SUSPICIOUS_RED_SAND);
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "suspicious_soul_sand"), SUSPICIOUS_SOUL_SAND);
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "suspicious_end_stone"), SUSPICIOUS_END_STONE);
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "numismatic_desk"), NUMISMATIC_DESK);
    }

    static {
        COIN_COLLECTOR_TROPHY = new CoinCollectorTrophyBlock(FabricBlockSettings.of(Material.GENERIC, MapColor.GOLD).hardness(2)
                .resistance(6).requiresTool().sounds(BlockSoundGroup.METAL));
        SUSPICIOUS_RED_SAND = new BrushableBlock(Blocks.RED_SAND, FabricBlockSettings.of(Material.AGGREGATE, MapColor.ORANGE)
                .strength(0.25f).sounds(BlockSoundGroup.SUSPICIOUS_SAND),
                SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE);
        SUSPICIOUS_SOUL_SAND = new SuspiciousSoulSandBlock(Blocks.SOUL_SAND, FabricBlockSettings.of(Material.AGGREGATE, MapColor.BROWN)
                .strength(0.5f).velocityMultiplier(0.4f).sounds(BlockSoundGroup.SUSPICIOUS_SAND),
                SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE);
        SUSPICIOUS_END_STONE = new SuspiciousEndStoneBlock(Blocks.END_STONE, FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW)
                .requiresTool().strength(3.0f, 9.0f).sounds(BlockSoundGroup.STONE),
                SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL, SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL_COMPLETE);
        NUMISMATIC_DESK = new Block(FabricBlockSettings.of(Material.GENERIC, MapColor.BROWN).strength(1F).sounds(BlockSoundGroup.WOOD));
    }
}
