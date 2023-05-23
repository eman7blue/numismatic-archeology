package io.github.eman7blue.numis_arch.block.entity;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchBlockEntityTypes {
    public static final BlockEntityType<AncientActivatorBlockEntity> ANCIENT_ACTIVATOR;
    public static final BlockEntityType<NumismaticDeskBlockEntity> NUMISMATIC_DESK;

    public static void registerBlockEntities() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, id("ancient_activator"), ANCIENT_ACTIVATOR);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, id("numismatic_desk"), NUMISMATIC_DESK);
    }

    static {
        ANCIENT_ACTIVATOR = FabricBlockEntityTypeBuilder.create(AncientActivatorBlockEntity::new, NumisArchBlocks.ANCIENT_ACTIVATOR).build();
        NUMISMATIC_DESK = FabricBlockEntityTypeBuilder.create(NumismaticDeskBlockEntity::new, NumisArchBlocks.NUMISMATIC_DESK).build();
    }
}
