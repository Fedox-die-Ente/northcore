package net.fedustria.northcore.setup.fields.password;

/**
 * The EraserThread class is responsible for masking user input in the console.
 */
class EraserThread implements Runnable {
	private boolean stop;

	/**
	 * Constructs an EraserThread with the specified prompt.
	 *
	 * @param prompt The prompt to display before masking input
	 */
	public EraserThread(String prompt) {
		System.out.print(prompt);
	}

	/**
	 * Runs the thread, which continuously prints a backspace character followed by an asterisk
	 * to mask user input until the stop flag is set to false.
	 */
	public void run() {
		stop = true;
		while (stop) {
			System.out.print("\010*");
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Stops the masking of user input by setting the stop flag to false.
	 */
	public void stopMasking() {
		this.stop = false;
	}
}