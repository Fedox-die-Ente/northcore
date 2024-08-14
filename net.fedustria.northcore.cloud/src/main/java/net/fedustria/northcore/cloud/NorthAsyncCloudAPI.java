package net.fedustria.northcore.cloud;

import eu.metacloudservice.CloudAPI;
import eu.metacloudservice.player.async.entrys.AsyncCloudPlayer;
import eu.metacloudservice.service.async.entrys.AsyncCloudService;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * The NorthAsyncCloudAPI class provides asynchronous methods to interact with cloud players and services.
 */
public class NorthAsyncCloudAPI {

	/**
	 * Retrieves a cloud player by their name.
	 *
	 * @param name The name of the player
	 * @return {@link CompletableFuture} with the {@link AsyncCloudPlayer} object
	 */
	public static CompletableFuture<AsyncCloudPlayer> getCloudPlayer(String name) {
		return CloudAPI.getInstance().getAsyncPlayerPool().getPlayer(name);
	}

	/**
	 * Retrieves a cloud player by their UUID.
	 *
	 * @param uuid The UUID of the player
	 * @return {@link CompletableFuture} with the {@link AsyncCloudPlayer} object
	 */
	public static CompletableFuture<AsyncCloudPlayer> getCloudPlayer(UUID uuid) {
		return CloudAPI.getInstance().getAsyncPlayerPool().getPlayer(uuid);
	}

	/**
	 * Retrieves a cloud service by its name.
	 *
	 * @param name The name of the service
	 * @return {@link CompletableFuture} with the {@link AsyncCloudService} object
	 */
	public static CompletableFuture<AsyncCloudService> getCloudService(String name) {
		return CloudAPI.getInstance().getAsyncServicePool().getService(name);
	}
}