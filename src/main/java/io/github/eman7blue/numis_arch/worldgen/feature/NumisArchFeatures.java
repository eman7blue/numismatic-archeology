package io.github.eman7blue.numis_arch.worldgen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.Feature;

public class NumisArchFeatures {
    public static final Identifier BURIED_HOARD_FEATURE_ID = new Identifier("numis_arch", "buried_hoard");
    public static Feature<BuriedHoardFeatureConfig> BURIED_HOARD_FEATURE = new BuriedHoardFeature(BuriedHoardFeatureConfig.CODEC);

    public static void init(){
        Registry.register(Registries.FEATURE, BURIED_HOARD_FEATURE_ID, BURIED_HOARD_FEATURE);
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.DESERT, BiomeKeys.BADLANDS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, BURIED_HOARD_FEATURE_ID)
        );
    }
}
