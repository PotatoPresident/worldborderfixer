package us.potatoboy.worldborderfix;

import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.border.WorldBorder;

public class PerWorldBorderListener implements net.minecraft.world.border.WorldBorderListener {
    private final ServerWorld world;

    public PerWorldBorderListener(ServerWorld world) {
        this.world = world;
    }

    @Override
    public void onSizeChange(WorldBorder border, double size) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderSizeChangedS2CPacket(border))));
    }

    @Override
    public void onInterpolateSize(WorldBorder border, double fromSize, double toSize, long time) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderInitializeS2CPacket(border))));
    }

    @Override
    public void onCenterChanged(WorldBorder border, double centerX, double centerZ) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderCenterChangedS2CPacket(border))));
    }

    @Override
    public void onWarningTimeChanged(WorldBorder border, int warningTime) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderWarningTimeChangedS2CPacket(border))));
    }

    @Override
    public void onWarningBlocksChanged(WorldBorder border, int warningBlockDistance) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderWarningBlocksChangedS2CPacket(border))));
    }

    @Override
    public void onDamagePerBlockChanged(WorldBorder border, double damagePerBlock) {
    }

    @Override
    public void onSafeZoneChanged(WorldBorder border, double safeZoneRadius) {
    }
}
