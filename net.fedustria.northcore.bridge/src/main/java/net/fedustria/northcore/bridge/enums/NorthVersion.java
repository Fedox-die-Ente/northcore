package net.fedustria.northcore.bridge.enums;

import lombok.Getter;

import static org.bukkit.Bukkit.getMinecraftVersion;

/**
 * The versions the NorthCore is compatible with.
 */
public enum NorthVersion {
	/**
	 * Version 1.21 of Minecraft.
	 */
	V_1_21;

	/**
	 * The current version of Minecraft.
	 */
	@Getter
	private static final NorthVersion version = NorthVersion.valueOf("V_" + getMinecraftVersion().replace(".", "_"));
}