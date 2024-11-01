package vttp.batch5.sdf.task01;

import java.io.FileNotFoundException;
import java.io.IOException;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) {

		// Hardcode the csv file path in variable
		String csvPath = "day.csv";

		// Initialise BikeFileProcessor
		BikeFileProcessor bikeFileProcessor = new BikeFileProcessor();

		try {
			// Read the csv file
			bikeFileProcessor.read(csvPath);
		}

		catch (FileNotFoundException fe) {
			System.err.printf("CSV file does not exist! : %s", fe.getMessage());
		}

		catch (IOException ie) {
			System.err.printf("Error occured when reading CSV file! : %s", ie.getMessage());
		}

		// Initialise template processor
		TemplateProcessor templateProcessor = new TemplateProcessor();

		// Get the top five days and print the filled template
		templateProcessor.printFilled(bikeFileProcessor.getTopFive());
	}
}
