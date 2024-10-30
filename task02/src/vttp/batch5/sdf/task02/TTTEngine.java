package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class TTTEngine {
    
    private char[][] board;

    public TTTEngine() {
        board = new char[3][3];
    }

    public void start(String tttFilePath) throws FileNotFoundException, IOException {
        // Print out the file that is being processed
		System.out.printf("Processing: %s\n", tttFilePath);
        
        System.out.println("");

        readBoard(tttFilePath);

        printBoard();

        System.out.println("------------------------------------");

        System.out.println("");

        ArrayList<int[]> legalPositions = getLegalPos();


        for (int[] legalPos : legalPositions) {

            char[][] newBoard = deepCopy(this.board);

            int y = legalPos[0];
            int x = legalPos[1];

            // Place X in the legal position
            newBoard[y][x] = Constants.X;

            int utility = getUtility(newBoard);

            System.out.printf("y=%d, x=%d, utility=%d\n", y, x, utility);

        }

        System.out.println("");
    }

    private char[][] deepCopy(char[][] originalBoard) {

        final char[][] newBoard = new char[originalBoard.length][];
        for (int i = 0; i < originalBoard.length; i++) {
            newBoard[i] = Arrays.copyOf(originalBoard[i], originalBoard[i].length);
        }
        return newBoard;
    }

    private void readBoard(String tttFilePath) throws FileNotFoundException, IOException {
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

    private void printBoard() {
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.printf("\n");
        }
    }

    private int getUtility(char[][] newBoard) {

        ArrayList<Integer> values = new ArrayList<>();

        // Rows
        for (int y = 0; y < 3; y++) {
            int rowValue = newBoard[y][0] + newBoard[y][1] + newBoard[y][2];
            values.add(rowValue);
        }

        // Columns
        for (int x = 0; x < 3; x++) {
            int colValue = newBoard[0][x] + newBoard[1][x] + newBoard[2][x];
            values.add(colValue);
        }
        
       // Diagonals
        int diag1 = newBoard[0][0] + newBoard[1][1] + newBoard[2][2];
        int diag2 = newBoard[2][0] + newBoard[1][1] + newBoard[0][2];

        values.add(diag1);
        values.add(diag2);

        // Check if there's any three Xs for horizontal, vertical, and diagonal
        for (int value : values) {
            if (value == Constants.POSITIVE) {
                // If yes, the utility of the board is 1
                return 1;
            }
        }

        // Check if there's any two Os and one space for horizontal, vertical, and diagonal
        for (int value : values) {
            if (value == Constants.NEGATIVE) {
                // If yes, the utility of the board is -1 
                return -1;
            }
        }

        // Else it is a neutral move
        return 0;
    }

    

    private ArrayList<int[]> getLegalPos() {

        ArrayList<int[]> legalPos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if it is legal position
                if (isLegal(i, j)) {
                    // If legal, add into list of legal positions
                    legalPos.add(new int[] {i ,j});
                }
            }
        }

        return legalPos;
    }

    private boolean isLegal(int y, int x) {

        // Get position on newBoard with y and x values
        char pos = board[y][x];

        // If position is empty, it is a legal position
        if (pos == Constants.EMPTY) {
            return true;
        }

        // If it is not empty, it is not legal
        return false;
    }
    
}
