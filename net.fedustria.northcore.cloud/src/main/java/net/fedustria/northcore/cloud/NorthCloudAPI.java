package net.fedustria.northcore.cloud;

import eu.metacloudservice.CloudAPI;
import eu.metacloudservice.player.entrys.CloudPlayer;
import eu.metacloudservice.service.entrys.CloudService;

import java.util.UUID;

/**
 * The NorthCloudAPI class provides methods to interact with cloud players and services.
 */
public class NorthCloudAPI {
	/**
	 * Retrieves a cloud player by their name.
	 *
	 * @param name The name of the player
	 * @return {@link CloudPlayer} object representing the cloud player
	 */
	public static CloudPlayer getCloudPlayer(String name) {
		return CloudAPI.getInstance().getPlayerPool().getPlayer(name);
	}

	/**
	 * Retrieves a cloud player by their UUID.
	 *
	 * @param uuid The UUID of the player
	 * @return {@link CloudPlayer} object representing the cloud player
	 */
	public static CloudPlayer getCloudPlayer(UUID uuid) {
		return CloudAPI.getInstance().getPlayerPool().getPlayer(uuid);
	}

	/**
	 * Retrieves a cloud service by its name.
	 *
	 * @param name The name of the service
	 * @return {@link CloudService} object representing the cloud service
	 */
	public static CloudService getCloudService(String name) {
		return CloudAPI.getInstance().getServicePool().getService(name);
	}
}