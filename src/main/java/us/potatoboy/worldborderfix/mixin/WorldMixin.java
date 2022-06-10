package us.potatoboy.worldborderfix.mixin;

import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.potatoboy.worldborderfix.BorderWithWorld;

import java.util.function.Supplier;

@Mixin(World.class)
public abstract class WorldMixin {
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/DimensionType;coordinateScale()D"))
    private double setBorderCoordinateScale(DimensionType dimensionType) {
        return 1.0D;
    }

    @Inject(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Ljava/lang/Thread;currentThread()Ljava/lang/Thread;")
    )
    private void setWorldBorderWorld(MutableWorldProperties properties, RegistryKey<World> registryRef, RegistryEntry<DimensionType> registryEntry, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed, int maxChainedNeighborUpdates, CallbackInfo ci) {
        World world = (World) (Object) this;
        ((BorderWithWorld) world.getWorldBorder()).setWorld(world);
    }
}
