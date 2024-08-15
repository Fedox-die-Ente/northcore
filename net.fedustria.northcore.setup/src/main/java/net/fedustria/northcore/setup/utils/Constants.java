package net.fedustria.northcore.setup.utils;

import net.fedustria.northcore.setup.types.EAnsiColor;

/**
 * Constants used throughout the setup.
 */
public class Constants {

	/**
	 * Prefix for standard output messages.
	 * Combines ANSI blue color for "NorthCore" and white color for ">>".
	 */
	public static String PREFIX = EAnsiColor.BLUE.getColor() + "NorthCore " + EAnsiColor.WHITE.getColor() + ">> ";

	/**
	 * Prefix for error messages.
	 * Combines ANSI red color for "ERROR" and white color for ">>".
	 */
	public static String ERROR_PREFIX = EAnsiColor.RED.getColor() + "ERROR " + EAnsiColor.WHITE.getColor() + ">> ";

	/**
	 * The version of the application.
	 */
	public static String VERSION = "1.0.0-SNAPSHOT";

	/**
	 * The license under which the application is distributed.
	 */
	public static String LICENSE = "AGPL-3.0";

	/**
	 * The author(s) of the application.
	 */
	public static String AUTHOR = "Fedox & Austria7";

}