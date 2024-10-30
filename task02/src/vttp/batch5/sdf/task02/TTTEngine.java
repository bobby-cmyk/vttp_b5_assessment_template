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
        board = new char[Constants.BOARD_LENGTH][Constants.BOARD_LENGTH];
    }

    public void start(String tttFilePath) throws FileNotFoundException, IOException {
        // Print out the file name that is being processed
		System.out.printf("Processing: %s\n", tttFilePath);
        
        System.out.println("");

        // Read the file
        readBoard(tttFilePath);

        // Print the board
        printBoard();

        System.out.println("------------------------------------");

        // Get the list of legal positions that can be played
        ArrayList<int[]> legalPositions = getLegalPos();

        // Loop through all the playable positions
        for (int[] legalPos : legalPositions) {

            // Initialise a new copy of the board
            char[][] newBoard = deepCopy(this.board);

            // Get the y position and x position
            int y = legalPos[0];
            int x = legalPos[1];

            // Place X in the position
            newBoard[y][x] = Constants.X;

            // Evaluate the utility of the move 
            int utility = getUtility(newBoard);

            // Print the y and x position with the utility of this move
            System.out.printf("y=%d, x=%d, utility=%d\n", y, x, utility);
        }
    }

    private void readBoard(String tttFilePath) throws FileNotFoundException, IOException {
        // Throw exceptions, to be handled in main file
        FileReader fr = new FileReader(tttFilePath);
        BufferedReader br = new BufferedReader(fr);

        for (int i = 0; i < Constants.BOARD_LENGTH; i++) {
            // Read row by row
            String row = br.readLine();

            // Split to its char
            char[] parts = row.toCharArray();

            for (int j = 0; j < Constants.BOARD_LENGTH; j++) {

                // Place the character into its position
                board[i][j] = parts[j];
            }
        }

        // Close readers
        br.close();
        fr.close();
    }

    private void printBoard() {
        
        System.out.println("Board:");

        for (int i = 0; i < Constants.BOARD_LENGTH; i++) {
            for (int j = 0; j < Constants.BOARD_LENGTH; j++) {
                System.out.print(board[i][j]);
            }
            // New line after each row (3 characters)
            System.out.printf("\n");
        }
    }

    private ArrayList<int[]> getLegalPos() {

        ArrayList<int[]> legalPos = new ArrayList<>();

        for (int i = 0; i < Constants.BOARD_LENGTH; i++) {
            for (int j = 0; j < Constants.BOARD_LENGTH; j++) {
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

    private char[][] deepCopy(char[][] originalBoard) {
        
        final char[][] newBoard = new char[originalBoard.length][];
        for (int i = 0; i < originalBoard.length; i++) {
            newBoard[i] = Arrays.copyOf(originalBoard[i], originalBoard[i].length);
        }
        return newBoard;
    }

    private int getUtility(char[][] newBoard) {

        ArrayList<Integer> values = new ArrayList<>();

        // Rows
        for (int y = 0; y < Constants.BOARD_LENGTH; y++) {
            int rowValue = newBoard[y][0] + newBoard[y][1] + newBoard[y][2];
            values.add(rowValue);
        }

        // Columns
        for (int x = 0; x < Constants.BOARD_LENGTH; x++) {
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
            if (value == Constants.POSITIVE_MOVE) {
                // If yes, the utility of the board is 1
                return 1;
            }
        }

        // Check if there's any two Os and one space for horizontal, vertical, and diagonal
        for (int value : values) {
            if (value == Constants.NEGATIVE_MOVE) {
                // If yes, the utility of the board is -1 
                return -1;
            }
        }

        // Else it is a neutral move
        return 0;
    }
}
