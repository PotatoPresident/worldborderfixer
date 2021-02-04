package us.potatoboy.worldborderfix.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
    @ModifyVariable(
            method = "sendWorldInfo",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/server/world/ServerWorld;getWorldBorder()Lnet/minecraft/world/border/WorldBorder;")
    )
    private WorldBorder getPlayerWorldBorder(WorldBorder worldBorder, ServerPlayerEntity player, ServerWorld world) {
        return world.getWorldBorder();
    }
}
