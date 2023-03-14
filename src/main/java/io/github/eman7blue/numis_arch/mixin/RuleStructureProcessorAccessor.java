package io.github.eman7blue.numis_arch.mixin;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RuleStructureProcessor.class)
public interface RuleStructureProcessorAccessor {
    @Accessor(value = "rules")
    ImmutableList<StructureProcessorRule> getRules();

    @Accessor(value = "rules")
    void setRules(ImmutableList<StructureProcessorRule> rules);
}
