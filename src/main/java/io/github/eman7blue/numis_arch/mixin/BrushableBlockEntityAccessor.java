package io.github.eman7blue.numis_arch.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BrushableBlockEntity.class)
public interface BrushableBlockEntityAccessor {
    @Accessor(value = "lootTable")
    Identifier getLootTable();
}
