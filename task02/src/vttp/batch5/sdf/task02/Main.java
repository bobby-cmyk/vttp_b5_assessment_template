package vttp.batch5.sdf.task02;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		// Validate the command line argument
		if (args.length != 1) {
			System.err.println("Proper Usage: <TTT/boardORfigure<n>.txt>");
			System.exit(-1);
		}

		// Get the path of the board from the command line
		String tttFilePath = args[0]; 

		// Instantiate the TTTEngine
		TTTEngine tttEngine = new TTTEngine();

		try {
			tttEngine.start(tttFilePath);
		}

		catch (FileNotFoundException fe) {
			System.err.printf("Unable to find txt file: %s\n", fe.getMessage());
		}

		catch (IOException ie) {
			System.err.printf("Error occured when reading txt file: %s\n", ie.getMessage());
		}

	}
}