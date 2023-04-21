package io.github.eman7blue.numis_arch.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchBlocks {
    public static final CoinCollectorTrophyBlock COIN_COLLECTOR_TROPHY;
    public static final BrushableBlock SUSPICIOUS_RED_SAND;
    public static final SuspiciousSoulSandBlock SUSPICIOUS_SOUL_SAND;
    public static final SuspiciousEndStoneBlock SUSPICIOUS_END_STONE;
    public static final Block NUMISMATIC_DESK;

    public static void registerBlocks() {
        Registry.register(Registries.BLOCK, id("coin_collector_trophy"), COIN_COLLECTOR_TROPHY);
        Registry.register(Registries.BLOCK, id("suspicious_red_sand"), SUSPICIOUS_RED_SAND);
        Registry.register(Registries.BLOCK, id("suspicious_soul_sand"), SUSPICIOUS_SOUL_SAND);
        Registry.register(Registries.BLOCK, id("suspicious_end_stone"), SUSPICIOUS_END_STONE);
        Registry.register(Registries.BLOCK, id("numismatic_desk"), NUMISMATIC_DESK);
    }

    static {
        COIN_COLLECTOR_TROPHY = new CoinCollectorTrophyBlock(FabricBlockSettings.of(Material.GENERIC, MapColor.GOLD)
                .strength(2.0F, 6.0F)
                .requiresTool()
                .sounds(BlockSoundGroup.METAL)
                .method_51368(Instrument.BELL));
        SUSPICIOUS_RED_SAND = new BrushableBlock(Blocks.RED_SAND, FabricBlockSettings.of(Material.GENERIC, MapColor.ORANGE)
                .strength(0.25f)
                .sounds(BlockSoundGroup.SUSPICIOUS_SAND)
                .method_51368(Instrument.SNARE),
                    SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE);
        SUSPICIOUS_SOUL_SAND = new SuspiciousSoulSandBlock(Blocks.SOUL_SAND, FabricBlockSettings.of(Material.GENERIC, MapColor.BROWN)
                .strength(0.5f)
                .velocityMultiplier(0.4f)
                .method_51368(Instrument.COW_BELL)
                .sounds(BlockSoundGroup.SUSPICIOUS_SAND),
                    SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE);
        SUSPICIOUS_END_STONE = new SuspiciousEndStoneBlock(Blocks.END_STONE, FabricBlockSettings.of(Material.GENERIC, MapColor.PALE_YELLOW)
                .strength(3.0f, 9.0f)
                .sounds(BlockSoundGroup.STONE)
                .method_51368(Instrument.BASEDRUM),
                    SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL, SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL_COMPLETE);
        NUMISMATIC_DESK = new NumismaticDeskBlock(FabricBlockSettings.of(Material.GENERIC, MapColor.OAK_TAN)
                .strength(2.5F)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
                .method_51368(Instrument.BASS));
    }
}
