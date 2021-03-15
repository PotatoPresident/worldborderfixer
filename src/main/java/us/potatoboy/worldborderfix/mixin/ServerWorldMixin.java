package us.potatoboy.worldborderfix.mixin;

import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.potatoboy.worldborderfix.WorldBorderState;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
	@Inject(method = "saveLevel", at = @At("HEAD"))
	private void saveBorder(CallbackInfo ci) {
		ServerWorld world = (ServerWorld) (Object) this;
		WorldBorderState worldBorderState = world.getPersistentStateManager().getOrCreate(WorldBorderState::new, "worldBorder");

		worldBorderState.fromBorder(world.getWorldBorder());
	}
}
