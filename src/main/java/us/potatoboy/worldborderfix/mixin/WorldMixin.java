package us.potatoboy.worldborderfix.mixin;

import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.potatoboy.worldborderfix.BorderWithWorld;

import java.util.function.Supplier;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow public abstract boolean isClient();

    @Shadow @Final public boolean isClient;

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/DimensionType;coordinateScale()D"))
    private double setBorderCoordinateScale(DimensionType dimensionType) {
        // We do not want to modify the interpretation of the world border on the client side, it should work on servers with and without worldborderfix
        if (this.isClient()) {
            return dimensionType.coordinateScale();
        } else {
            return 1.0D;
        }
    }

    @Inject(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Ljava/lang/Thread;currentThread()Ljava/lang/Thread;")
    )
    private void setWorldBorderWorld(MutableWorldProperties properties, RegistryKey registryRef, DynamicRegistryManager registryManager, RegistryEntry dimensionEntry, Supplier profiler, boolean isClient, boolean debugWorld, long biomeAccess, int maxChainedNeighborUpdates, CallbackInfo ci) {
        World world = (World) (Object) this;
        ((BorderWithWorld) world.getWorldBorder()).setWorld(world);
    }
}
