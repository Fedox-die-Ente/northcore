package net.fedustria.northcore.setup.fields.password;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasswordField {

	/**
	 * Reads a password from the console with the specified prompt.
	 * The input is masked with asterisks as the user types.
	 *
	 * @param prompt The prompt to display before masking input
	 * @return The password entered by the user
	 */
	public static String readPassword(String prompt) {
		EraserThread et   = new EraserThread(prompt);
		Thread       mask = new Thread(et);
		mask.start();

		BufferedReader in       = new BufferedReader(new InputStreamReader(System.in));
		String         password = "";

		try {
			password = in.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		et.stopMasking();
		return password;
	}
}