package io.github.eman7blue.numis_arch.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.FeatureConfig;

public record BuriedHoardFeatureConfig() implements FeatureConfig {
    public static final BuriedHoardFeatureConfig INSTANCE = new BuriedHoardFeatureConfig();
    public static final Codec<BuriedHoardFeatureConfig> CODEC = Codec.unit(() -> INSTANCE);

}
