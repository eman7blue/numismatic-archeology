package io.github.eman7blue.numis_arch.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchSoundEvents {
    public static SoundEvent ENTITY_VILLAGER_WORK_ARCHEOLOGIST = SoundEvent.of(id("entity.villager.work_archeologist"));
    static {
        Registry.register(Registries.SOUND_EVENT, id("entity.villager.work_archeologist"), ENTITY_VILLAGER_WORK_ARCHEOLOGIST);
    }
}
