package us.potatoboy.worldborderfix.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.potatoboy.worldborderfix.WorldBorderState;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @Inject(method = "save", at = @At("HEAD"))
    private void saveBorder(CallbackInfo ci) {
        ServerWorld world = (ServerWorld) (Object) this;
        // Requires fabric-object-builder-api-v1 (see https://github.com/FabricMC/fabric/issues/3327 for more information, why we are passing null as the dataFixTypes)
        WorldBorderState worldBorderState = world.getPersistentStateManager().getOrCreate(new PersistentState.Type<>(WorldBorderState::new, WorldBorderState::fromNbt, null), "worldBorder");

        worldBorderState.fromBorder(world.getWorldBorder());
    }
}
