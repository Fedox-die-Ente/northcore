package net.fedustria.northcore.setup.utils;

import net.fedustria.northcore.setup.utils.os.OSType;

import java.util.Locale;

/**
 * Utility class for checking the operating system type.
 */
public class OperatingSystemCheck {

	/**
	 * The detected operating system type.
	 */
	protected static OSType detectedOS;

	/**
	 * Determines the operating system type.
	 *
	 * @return the detected {@link OSType}
	 */
	public static OSType getOperatingSystemType() {
		if (detectedOS == null) {
			String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if ((OS.contains("mac")) || (OS.contains("darwin"))) {
				detectedOS = OSType.MacOS;
			} else if (OS.contains("win")) {
				detectedOS = OSType.Windows;
			} else if (OS.contains("nux")) {
				detectedOS = OSType.Linux;
			} else {
				detectedOS = OSType.Other;
			}
		}
		return detectedOS;
	}

}