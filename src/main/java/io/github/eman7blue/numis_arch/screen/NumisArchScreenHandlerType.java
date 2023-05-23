package io.github.eman7blue.numis_arch.screen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchScreenHandlerType {
    public static final ScreenHandlerType<NumismaticDeskScreenHandler> NUMISMATIC_DESK;

    public static void registerScreens() {
        Registry.register(Registries.SCREEN_HANDLER, id("numismatic_desk"), NUMISMATIC_DESK);
    }

    static {
        NUMISMATIC_DESK = new ScreenHandlerType<>(NumismaticDeskScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    }
}
