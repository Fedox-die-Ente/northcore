package net.fedustria.northcore.bridge.enums;

import org.bukkit.Sound;

import java.util.HashMap;
import java.util.Map;

import static net.fedustria.northcore.bridge.enums.NorthVersion.V_1_21;

/**
 * The NorthSounds enum represents different sound effects in the game.
 * It maps different versions of the game to their corresponding sound names.
 */
public enum NorthSounds {
	BLOCK_NOTE_BLOCK_PLING(Map.of(V_1_21, Sound.BLOCK_NOTE_BLOCK_BELL.name()));

	/**
	 * A map of game versions to their corresponding sound names.
	 */
	private final Map<NorthVersion, String> alternatives;

	/**
	 * Default constructor initializing an empty map of alternatives.
	 */
	NorthSounds() {
		this.alternatives = new HashMap<>();
	}

	/**
	 * Constructor initializing the map of alternatives with the provided values.
	 *
	 * @param alternatives a map of game versions to their corresponding sound names
	 */
	NorthSounds(Map<NorthVersion, String> alternatives) {
		this.alternatives = alternatives;
	}

	/**
	 * Converts the current enum constant to a Bukkit Sound.
	 * If there are no alternatives, it returns the sound corresponding to the enum name.
	 * Otherwise, it returns the sound corresponding to the current game version or the enum name if not found.
	 *
	 * @return the Bukkit Sound corresponding to the current enum constant
	 */
	public Sound asBukkitSound() {
		if (alternatives.isEmpty()) {
			return Sound.valueOf(name());
		}

		String newSound = alternatives.getOrDefault(NorthVersion.getVersion(), name());
		return Sound.valueOf(newSound);
	}
}