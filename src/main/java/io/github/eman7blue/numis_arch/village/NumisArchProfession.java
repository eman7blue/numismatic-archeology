package io.github.eman7blue.numis_arch.village;

import com.google.common.collect.ImmutableSet;
import io.github.eman7blue.numis_arch.sound.NumisArchSoundEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.village.VillagerProfession;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchProfession {

    public static final VillagerProfession ARCHEOLOGIST;

    public static void registerProfessions() {
        Registry.register(Registries.VILLAGER_PROFESSION, id("archeologist"), ARCHEOLOGIST);
    }

    static {
        ARCHEOLOGIST = new VillagerProfession("archeologist",
                entry -> entry.value().equals(NumisArchPointOfInterestType.ARCHEOLOGIST_POI),
                entry -> entry.value().equals(NumisArchPointOfInterestType.ARCHEOLOGIST_POI),
                ImmutableSet.of(), ImmutableSet.of(), NumisArchSoundEvents.ENTITY_VILLAGER_WORK_ARCHEOLOGIST);
    }
}
