package io.github.eman7blue.numis_arch.village;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.world.poi.PointOfInterestType;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchPointOfInterestType {

    public static PointOfInterestType ARCHEOLOGIST_POI;

    public static void registerPOI() {
        ARCHEOLOGIST_POI = PointOfInterestHelper.register(id("archeologist"), 1, 1, NumisArchBlocks.NUMISMATIC_DESK);
    }

}
