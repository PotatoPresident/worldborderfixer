package us.potatoboy.worldborderfix;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.PersistentState;
import net.minecraft.world.border.WorldBorder;

public class WorldBorderState extends PersistentState {
    private double centerX = WorldBorder.DEFAULT_BORDER.getCenterX();
    private double centerZ = WorldBorder.DEFAULT_BORDER.getCenterZ();
    private double size = WorldBorder.DEFAULT_BORDER.getSize();
    private double buffer = WorldBorder.DEFAULT_BORDER.getBuffer();
    private double damagePerBlock = WorldBorder.DEFAULT_BORDER.getDamagePerBlock();
    private int warningBlocks = WorldBorder.DEFAULT_BORDER.getWarningBlocks();
    private int warningTime = WorldBorder.DEFAULT_BORDER.getWarningTime();


    public WorldBorderState() {
        super("worldBorder");
    }

    @Override
    public void fromTag(CompoundTag tag) {
        centerX = tag.getDouble("BorderCenterX");
        centerZ = tag.getDouble("BorderCenterZ");
        size = tag.getDouble("BorderSize");
        buffer = tag.getDouble("BorderSafeZone");
        damagePerBlock = tag.getDouble("BorderDamagePerBlock");
        warningBlocks = tag.getInt("BorderWarningBlocks");
        warningTime = tag.getInt("BorderWarningTime");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
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
        buffer = worldBorder.getBuffer();
        damagePerBlock = worldBorder.getDamagePerBlock();
        warningBlocks = worldBorder.getWarningBlocks();
        warningTime = worldBorder.getWarningTime();

        markDirty();
    }
}
