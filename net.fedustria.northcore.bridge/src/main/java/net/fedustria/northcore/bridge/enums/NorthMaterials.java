package net.fedustria.northcore.bridge.enums;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public enum NorthMaterials {
	;

	private final Map<NorthVersion, String> alternatives;

	NorthMaterials() {
		this.alternatives = new HashMap<>();
	}

	NorthMaterials(Map<NorthVersion, String> alternatives) {
		this.alternatives = alternatives;
	}

	public Material asBukkitMaterial() {
		if (alternatives.isEmpty()) {
			return Material.valueOf(name());
		}

		String newSound = alternatives.getOrDefault(NorthVersion.getVersion(), name());
		return Material.valueOf(newSound);
	}
}
