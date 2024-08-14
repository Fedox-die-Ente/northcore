package net.fedustria.northcore.setup.utils;

import net.fedustria.northcore.setup.types.EEnvironment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Utility class for system-related operations.
 */
public class SystemUtil {

	/**
	 * Sets an environment variable in the user's shell configuration file.
	 *
	 * @param environment The environment (LINUX or MAC) to determine the shell configuration file
	 * @param key         The environment variable key
	 * @param value       The environment variable value
	 * @return true if the environment variable was successfully set, false otherwise
	 */
	public static boolean setEnv(EEnvironment environment, String key, String value) {

		// Determine the path to the shell configuration file based on the environment
		Path configBash = switch (environment) {
			case LINUX -> Path.of(System.getProperty("user.home") + "/.bashrc");
			case MAC -> Path.of(System.getProperty("user.home") + "/.zshrc");
		};

		try {
			// Construct the environment variable string
			String env = "export " + key + "=" + value + "\n";
			// Write the environment variable to the shell configuration file
			Files.writeString(configBash, env, Files.exists(configBash) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}