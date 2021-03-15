package us.potatoboy.worldborderfix;

import net.minecraft.world.World;

public interface BorderWithWorld {
	World getWorld();

	void setWorld(World world);
}
