package us.potatoboy.worldborderfix;

import net.minecraft.network.packet.s2c.play.WorldBorderS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.border.WorldBorder;

public class PerWorldBorderListener implements net.minecraft.world.border.WorldBorderListener {
    private final ServerWorld world;

    public PerWorldBorderListener(ServerWorld world) {
        this.world = world;
    }

    @Override
    public void onSizeChange(WorldBorder border, double size) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderS2CPacket(border, WorldBorderS2CPacket.Type.SET_SIZE))));
    }

    @Override
    public void onInterpolateSize(WorldBorder border, double fromSize, double toSize, long time) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderS2CPacket(border, WorldBorderS2CPacket.Type.LERP_SIZE))));
    }

    @Override
    public void onCenterChanged(WorldBorder border, double centerX, double centerZ) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderS2CPacket(border, WorldBorderS2CPacket.Type.SET_CENTER))));
    }

    @Override
    public void onWarningTimeChanged(WorldBorder border, int warningTime) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderS2CPacket(border, WorldBorderS2CPacket.Type.SET_WARNING_TIME))));
    }

    @Override
    public void onWarningBlocksChanged(WorldBorder border, int warningBlockDistance) {
        world.getPlayers().forEach(playerEntity -> playerEntity.networkHandler.sendPacket((new WorldBorderS2CPacket(border, WorldBorderS2CPacket.Type.SET_WARNING_BLOCKS))));
    }

    @Override
    public void onDamagePerBlockChanged(WorldBorder border, double damagePerBlock) {
    }

    @Override
    public void onSafeZoneChanged(WorldBorder border, double safeZoneRadius) {
    }
}
