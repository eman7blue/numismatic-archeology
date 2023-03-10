package io.github.eman7blue.numis_arch.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.SuspiciousSandBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class NumisArchBlocks {
    public static final CoinCollectorTrophyBlock COIN_COLLECTOR_TROPHY;
    public static final SuspiciousSandBlock SUSPICIOUS_RED_SAND;
    public static final SuspiciousSandBlock SUSPICIOUS_GRAVEL;
    public static final SuspiciousSoulSandBlock SUSPICIOUS_SOUL_SAND;
    public static final SuspiciousEndStoneBlock SUSPICIOUS_END_STONE;

    public static void init() {
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "coin_collector_trophy"), COIN_COLLECTOR_TROPHY);
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "suspicious_red_sand"), SUSPICIOUS_RED_SAND);
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "suspicious_gravel"), SUSPICIOUS_GRAVEL);
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "suspicious_soul_sand"), SUSPICIOUS_SOUL_SAND);
        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "suspicious_end_stone"), SUSPICIOUS_END_STONE);
    }

    static {
        COIN_COLLECTOR_TROPHY = new CoinCollectorTrophyBlock(FabricBlockSettings.of(Material.METAL, MapColor.GOLD).hardness(2)
                .resistance(6).requiresTool().requires(FeatureFlags.UPDATE_1_20));
        SUSPICIOUS_RED_SAND = new SuspiciousSandBlock(FabricBlockSettings.of(Material.AGGREGATE, MapColor.ORANGE)
                .strength(0.25f).sounds(BlockSoundGroup.SUSPICIOUS_SAND).requires(FeatureFlags.UPDATE_1_20));
        SUSPICIOUS_GRAVEL = new SuspiciousSandBlock(FabricBlockSettings.of(Material.AGGREGATE, MapColor.STONE_GRAY)
                .strength(0.6f).sounds(BlockSoundGroup.SUSPICIOUS_SAND).requires(FeatureFlags.UPDATE_1_20));
        SUSPICIOUS_SOUL_SAND = new SuspiciousSoulSandBlock(FabricBlockSettings.of(Material.AGGREGATE, MapColor.BROWN)
                .strength(0.5f).velocityMultiplier(0.4f).sounds(BlockSoundGroup.SUSPICIOUS_SAND).requires(FeatureFlags.UPDATE_1_20));
        SUSPICIOUS_END_STONE = new SuspiciousEndStoneBlock(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW)
                .requiresTool().strength(3.0f, 9.0f).sounds(BlockSoundGroup.STONE).requires(FeatureFlags.UPDATE_1_20));
    }
}
