package us.potatoboy.worldborderfix;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;
import net.minecraft.world.border.WorldBorder;

public class WorldBorderState extends PersistentState {
    private double centerX = WorldBorder.DEFAULT_BORDER.getCenterX();
    private double centerZ = WorldBorder.DEFAULT_BORDER.getCenterZ();
    private double size = WorldBorder.DEFAULT_BORDER.getSize();
    private double buffer = WorldBorder.DEFAULT_BORDER.getSafeZone();
    private double damagePerBlock = WorldBorder.DEFAULT_BORDER.getDamagePerBlock();
    private int warningBlocks = WorldBorder.DEFAULT_BORDER.getWarningBlocks();
    private int warningTime = WorldBorder.DEFAULT_BORDER.getWarningTime();

    public WorldBorderState() {

    }

    public WorldBorderState(double centerX, double centerZ, double size, double buffer, double damagePerBlock, int warningBlocks, int warningTime) {
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.size = size;
        this.buffer = buffer;
        this.damagePerBlock = damagePerBlock;
        this.warningBlocks = warningBlocks;
        this.warningTime = warningTime;
    }

    public static WorldBorderState fromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup) {
        return new WorldBorderState(
                tag.getDouble("BorderCenterX"),
                tag.getDouble("BorderCenterZ"),
                tag.getDouble("BorderSize"),
                tag.getDouble("BorderSafeZone"),
                tag.getDouble("BorderDamagePerBlock"),
                tag.getInt("BorderWarningBlocks"),
                tag.getInt("BorderWarningTime")
        );
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup) {
        tag.putDouble("BorderCenterX", centerX);
        tag.putDouble("BorderCenterZ", centerZ);
        tag.putDouble("BorderSize", size);
        tag.putDouble("BorderSafeZone", buffer);
        tag.putDouble("BorderDamagePerBlock", damagePerBlock);
        tag.putInt("BorderWarningBlocks", warningBlocks);
        tag.putInt("BorderWarningTime", warningTime);

        return tag;
    }

    public double getCenterX() {
        return centerX;
    }


    public double getCenterZ() {
        return centerZ;
    }

    public double getSize() {
        return size;
    }

    public double getBuffer() {
        return buffer;
    }

    public double getDamagePerBlock() {
        return damagePerBlock;
    }

    public int getWarningBlocks() {
        return warningBlocks;
    }

    public int getWarningTime() {
        return warningTime;
    }

    public void fromBorder(WorldBorder worldBorder) {
        centerX = worldBorder.getCenterX();
        centerZ = worldBorder.getCenterZ();
        size = worldBorder.getSize();
        buffer = worldBorder.getSafeZone();
        damagePerBlock = worldBorder.getDamagePerBlock();
        warningBlocks = worldBorder.getWarningBlocks();
        warningTime = worldBorder.getWarningTime();

        markDirty();
    }
}
