package vttp.batch5.sdf.task02;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		// Validate the command line argument
		if (args.length != 1) {
			System.err.println("Proper Usage: <TTT/figure<n>.txt>");
			System.exit(-1);
		}

		String tttFilePath = args[0]; 

		// Print out the file that is being processed
		System.out.printf("Processing: %s\n", tttFilePath);

		//Test
		TTTEngine tttEngine = new TTTEngine();

		try {
			tttEngine.readBoard(tttFilePath);
		}

		catch (FileNotFoundException fe) {
			System.err.printf("Unable to find board txt file: %s", fe.getMessage());
		}

		catch (IOException ie) {
			System.out.printf("Error occured when reading txt file: %s", ie.getMessage());
		}
		
		System.out.printf("\n");

		tttEngine.printBoard();

		
	}
}


// Pseudocode 

// TTT File Reader
 // Read the file line by line
 // add each of the charcter to a 2D array inside


 // TTTEngine
	// method -> read file (String tttFile)

	// method -> print board

	// method -> isLegal(int x, int y) -> return boolean 
	
	// method -> getUtility (int x, int y) -> return int
