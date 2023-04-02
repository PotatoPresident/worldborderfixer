package us.potatoboy.worldborderfix.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.border.WorldBorderListener;
import net.minecraft.world.dimension.DimensionOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.potatoboy.worldborderfix.PerWorldBorderListener;
import us.potatoboy.worldborderfix.WorldBorderState;

import java.util.Map;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@Shadow
	@Final
	private Map<RegistryKey<World>, ServerWorld> worlds;

	@Inject(method = "createWorlds", at = @At(value = "TAIL"))
	private void loadOtherBorder(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo ci) {
		worlds.forEach((registryKey, world) -> {
			WorldBorder worldBorder = world.getWorldBorder();

			if (registryKey.getValue() != DimensionOptions.OVERWORLD.getValue()) {
				WorldBorderState worldBorderState = world.getPersistentStateManager().getOrCreate(WorldBorderState::fromNbt, WorldBorderState::new, "worldBorder");

				worldBorder.setCenter(worldBorderState.getCenterX(), worldBorderState.getCenterZ());
				worldBorder.setSize(worldBorderState.getSize());
				worldBorder.setSafeZone(worldBorderState.getBuffer());
				worldBorder.setDamagePerBlock(worldBorderState.getDamagePerBlock());
				worldBorder.setWarningBlocks(worldBorderState.getWarningBlocks());
				worldBorder.setWarningTime(worldBorderState.getWarningTime());
			}

			worldBorder.addListener(new PerWorldBorderListener(world));
		});
	}

	@Redirect(method = "createWorlds", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/border/WorldBorder;addListener(Lnet/minecraft/world/border/WorldBorderListener;)V"))
	private void addListener(WorldBorder worldBorder, WorldBorderListener listener) {}

	@Redirect(method = "createWorlds", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;setMainWorld(Lnet/minecraft/server/world/ServerWorld;)V"))
	private void setBorderListeners(PlayerManager playerManager, ServerWorld world) {}
}
