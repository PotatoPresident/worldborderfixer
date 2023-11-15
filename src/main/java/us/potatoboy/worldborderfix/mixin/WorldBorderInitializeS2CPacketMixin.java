package us.potatoboy.worldborderfix.mixin;

import net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import us.potatoboy.worldborderfix.BorderWithWorld;

@Mixin(WorldBorderInitializeS2CPacket.class)
public abstract class WorldBorderInitializeS2CPacketMixin {
    @Redirect(
            method = "<init>(Lnet/minecraft/world/border/WorldBorder;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/border/WorldBorder;getCenterX()D")
    )
    private double scaleCenterX(WorldBorder worldBorder) {
        World world = ((BorderWithWorld) worldBorder).getWorld();

        // The client is going to divide our world border center by the coordinate scale, so we need to multiply it by
        // the coordinate scale to compensate for this.
        final double centerX = worldBorder.getCenterX();
        return world == null || world.isClient ? centerX : centerX * world.getDimension().coordinateScale();
    }

    @Redirect(
            method = "<init>(Lnet/minecraft/world/border/WorldBorder;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/border/WorldBorder;getCenterZ()D")
    )
    private double scaleCenterZ(WorldBorder worldBorder) {
        World world = ((BorderWithWorld) worldBorder).getWorld();

        // The client is going to divide our world border center by the coordinate scale, so we need to multiply it by
        // the coordinate scale to compensate for this.
        final double centerZ = worldBorder.getCenterZ();
        return world == null || world.isClient ? centerZ : centerZ * world.getDimension().coordinateScale();
    }
}
