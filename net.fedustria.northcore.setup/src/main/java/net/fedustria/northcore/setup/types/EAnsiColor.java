package net.fedustria.northcore.setup.types;

import lombok.Getter;

/**
 * Enum representing ANSI color codes.
 */
@Getter
public enum EAnsiColor {

	/**
	 * ANSI color code for white.
	 */
	WHITE("\u001B[37m"),

	/**
	 * ANSI color code for cyan.
	 */
	CYAN("\u001B[36m"),

	/**
	 * ANSI color code for blue.
	 */
	BLUE("\u001B[34m"),

	/**
	 * ANSI color code for red.
	 */
	RED("\u001B[31m");

	/**
	 * The ANSI color code.
	 */
	private final String color;

	/**
	 * Constructs an EAnsiColor with the specified ANSI color code.
	 *
	 * @param color The ANSI color code
	 */
	EAnsiColor(String color) {
		this.color = color;
	}

	/**
	 * Retrieves the ANSI color code associated with the specified key.
	 *
	 * @param key The name of the color
	 * @return The ANSI color code
	 */
	public static String getAnsiColor(String key) {
		return EAnsiColor.valueOf(key).getColor();
	}
}