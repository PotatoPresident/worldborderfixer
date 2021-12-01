package us.potatoboy.worldborderfix.mixin;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.WorldBorderCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldBorderCommand.class)
public abstract class WorldBorderCommandMixin {
    @Redirect(
            method = {"executeBuffer", "executeDamage", "executeWarningTime", "executeWarningDistance", "executeGet", "executeCenter", "executeSet"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getWorldBorder()Lnet/minecraft/world/border/WorldBorder;")
    )
    private static WorldBorder test(ServerWorld instance, ServerCommandSource source) {
        return source.getWorld().getWorldBorder();
    }
}
