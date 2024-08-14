package net.fedustria.northcore.setup.utils;

import net.fedustria.northcore.setup.NorthSetup;
import net.fedustria.northcore.setup.types.EAnsiColor;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static net.fedustria.northcore.setup.utils.Constants.ERROR_PREFIX;
import static net.fedustria.northcore.setup.utils.Constants.PREFIX;

public class StringUtils {

	/**
	 * ANSI color code for blue.
	 */
	static String BLUE = "\u001B[34m";

	/**
	 * Reads the content of the "credits.txt" file, applies ANSI blue color to each line,
	 * replaces variables and colors, and returns the formatted string.
	 *
	 * @return The formatted content of the "credits.txt" file
	 * @throws IOException If the "credits.txt" file is not found or cannot be read
	 */
	public static String getTopText() throws IOException {
		ClassLoader classLoader = NorthSetup.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("credits.txt");

		if (inputStream == null) {
			throw new FileNotFoundException("credits.txt");
		}

		InputStreamReader streamReader   = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		BufferedReader    bufferedReader = new BufferedReader(streamReader);

		StringBuilder stringBuilder = new StringBuilder();
		String        line;

		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(BLUE).append(line).append("\n");
		}

		stringBuilder.append("\u001B[0m");

		stringBuilder = replaceVariables(stringBuilder);
		stringBuilder = replaceColors(stringBuilder);

		return stringBuilder.toString();
	}

	/**
	 * Replaces placeholders in the given StringBuilder with actual values.
	 *
	 * @param stringBuilder The StringBuilder containing placeholders
	 * @return The StringBuilder with placeholders replaced by actual values
	 */
	private static StringBuilder replaceVariables(StringBuilder stringBuilder) {
		stringBuilder.replace(stringBuilder.indexOf("${version}"), stringBuilder.indexOf("${version}") + "${version}".length(), Constants.VERSION);
		stringBuilder.replace(stringBuilder.indexOf("${license}"), stringBuilder.indexOf("${license}") + "${license}".length(), Constants.LICENSE);
		stringBuilder.replace(stringBuilder.indexOf("${author}"), stringBuilder.indexOf("${author}") + "${author}".length(), Constants.AUTHOR);
		stringBuilder.replace(stringBuilder.indexOf("${os}"), stringBuilder.indexOf("${os}") + "${os}".length(), OperatingSystemCheck.getOperatingSystemType().name());
		stringBuilder.replace(stringBuilder.indexOf("${java}"), stringBuilder.indexOf("${java}") + "${java}".length(), System.getProperty("java.version"));

		return stringBuilder;
	}

	/**
	 * Replaces color placeholders in the given StringBuilder with actual ANSI color codes.
	 *
	 * @param stringBuilder The StringBuilder containing color placeholders
	 * @return The StringBuilder with color placeholders replaced by actual ANSI color codes
	 */
	private static StringBuilder replaceColors(StringBuilder stringBuilder) {
		for (int i = 0; i < stringBuilder.length(); i++) {
			if (stringBuilder.charAt(i) == '(') {
				int    start     = i;
				int    end       = stringBuilder.indexOf(")", i);
				String color     = stringBuilder.substring(start + 1, end);
				String ansiColor = EAnsiColor.getAnsiColor(color);
				stringBuilder.replace(start, end + 1, ansiColor);
			}
		}

		return stringBuilder;
	}

	/**
	 * Prints the given text to the console with a prefix.
	 *
	 * @param text The text to print
	 */
	public static void prefixText(String text) {
		System.out.println(PREFIX + text);
	}

	/**
	 * Prints the given text to the error console with an error prefix.
	 *
	 * @param text The text to print
	 */
	public static void errorText(String text) {
		System.err.println(ERROR_PREFIX + text);
	}
}