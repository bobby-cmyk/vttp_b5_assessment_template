package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TTTEngine {
    
    private char[][] board;

    public TTTEngine() {
        board = new char[3][3];
    }

    public void readBoard(String tttFilePath) throws FileNotFoundException, IOException {
        // Throw exceptions, to be handled in main file
        FileReader fr = new FileReader(tttFilePath);
        BufferedReader br = new BufferedReader(fr);

        for (int i = 0; i < 3; i++) {
            // Read row by row
            String row = br.readLine();

            // Split to its char
            char[] parts = row.toCharArray();

            for (int j = 0; j < 3; j++) {

                // Place the character into its position
                board[i][j] = parts[j];
            }
        }

        // Close readers
        br.close();
        fr.close();
    }

    public void printBoard() {
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.printf("\n");
        }
    }
}
