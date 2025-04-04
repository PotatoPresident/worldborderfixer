package us.potatoboy.worldborderfix;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateType;
import net.minecraft.world.border.WorldBorder;

public class WorldBorderState extends PersistentState {
    private double centerX = WorldBorder.DEFAULT_BORDER.getCenterX();
    private double centerZ = WorldBorder.DEFAULT_BORDER.getCenterZ();
    private double size = WorldBorder.DEFAULT_BORDER.getSize();
    private double buffer = WorldBorder.DEFAULT_BORDER.getSafeZone();
    private double damagePerBlock = WorldBorder.DEFAULT_BORDER.getDamagePerBlock();
    private int warningBlocks = WorldBorder.DEFAULT_BORDER.getWarningBlocks();
    private int warningTime = WorldBorder.DEFAULT_BORDER.getWarningTime();

    public WorldBorderState(Context context) {
        super();
    }

    public WorldBorderState(Context context, double centerX, double centerZ, double size, double buffer, double damagePerBlock, int warningBlocks, int warningTime) {
        this(context);
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.size = size;
        this.buffer = buffer;
        this.damagePerBlock = damagePerBlock;
        this.warningBlocks = warningBlocks;
        this.warningTime = warningTime;
    }

    public static final PersistentStateType<WorldBorderState> TYPE = new PersistentStateType<>(
        "worldBorder",
        WorldBorderState::new,
        WorldBorderState::createCodec,
        DataFixTypes.LEVEL
    );

    private static Codec<WorldBorderState> createCodec(Context context) {
        return RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("BorderCenterX").forGetter(WorldBorderState::getCenterX),
            Codec.DOUBLE.fieldOf("BorderCenterZ").forGetter(WorldBorderState::getCenterZ),
            Codec.DOUBLE.fieldOf("BorderSize").forGetter(WorldBorderState::getSize),
            Codec.DOUBLE.fieldOf("BorderSafeZone").forGetter(WorldBorderState::getBuffer),
            Codec.DOUBLE.fieldOf("BorderDamagePerBlock").forGetter(WorldBorderState::getDamagePerBlock),
            Codec.INT.fieldOf("BorderWarningBlocks").forGetter(WorldBorderState::getWarningBlocks),
            Codec.INT.fieldOf("BorderWarningTime").forGetter(WorldBorderState::getWarningTime)
        ).apply(instance, (centerX, centerZ, size, buffer, damagePerBlock, warningBlocks, warningTime) ->
            new WorldBorderState(context, centerX, centerZ, size, buffer, damagePerBlock, warningBlocks, warningTime)
        ));
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
