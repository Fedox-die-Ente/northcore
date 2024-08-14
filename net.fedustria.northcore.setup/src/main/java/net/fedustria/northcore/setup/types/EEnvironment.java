package net.fedustria.northcore.setup.types;

/**
 * Enum representing different environments.
 */
public enum EEnvironment {
	/**
	 * Represents the Linux environment.
	 */
	LINUX,

	/**
	 * Represents the Mac environment.
	 */
	MAC;

	/**
	 * Determines the current environment based on the operating system name.
	 *
	 * @return The corresponding EEnvironment value, or null if the environment is not recognized.
	 */
	public static EEnvironment getEnvironment() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
			return LINUX;
		} else if (os.contains("mac")) {
			return MAC;
		}
		return null;
	}
}