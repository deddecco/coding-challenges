package personal.y22.m09;

import java.util.Scanner;

public class TicTacToe {
    // board is 3x3 array of chars-- null when game starts, x or o as game progresses, remains null if space not used in-game
    public static char[][] board = new char[3][3];
    // 2 players always alternate, so parity can track whose move it is-- so track the number of moves that have already been made
    public static int numMoves = 0;
    // the maximum number of allowable moves is the number of moves required to fill every cell on the board exactly once
    public static final int MAX_MOVE_COUNT = (int) Math.pow(board.length, 2);

    // print gridlines and board values
    public static void printBoard() {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("- - - ");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("- - - ");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }

    // set an x at a given position
    // increment move counter
    public static void setX(int rowPos, int colPos) {
        board[rowPos][colPos] = 'x';
        numMoves++;
    }

    // set an o at a given position
    // increment move counter
    public static void setO(int rowPos, int colPos) {
        board[rowPos][colPos] = 'O';
        numMoves++;
    }

    // x moves first, third, fifth, etc.-- when move counts are odd
    public static boolean isXTurn() {
        return numMoves % 2 == 0;
    }

    // it is O's turn to move when it is not X's turn-- when move counts are even
    public static boolean isOTurn() {
        return numMoves % 2 == 1;
    }

    // there is a winner if one there is a single
    // character that occupies 3 adjacent
    // (horizontal, vertical, diagonal) spaces on the board
    // that winner is x if any of the 3 adjacent characters that triggerred the win was an x
    // that winner is o if any of the 3 adjacent characters that triggerred the win was an o
    public static boolean declareWinner() {
        boolean rowWin;
        boolean colWin;
        boolean diagWin;
        boolean xWin;
        for (int i = 0; i < board.length; i++) {
            // win by row if, for any given row, all the characters in that row are the same
            // as long as the winning character is not 0000 in the ASCII table
            rowWin = (board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && (board[i][0] != '\u0000');
            xWin = board[i][0] == 'x';
            if (rowWin) {
                System.out.println("Win by row!");
                if (xWin) {
                    System.out.println("X wins!");
                } else {
                    System.out.println("O wins!");
                }
                // end the program when the game ends after informing the players of teh result
                return true;
            }
            // win by column if, for any given column, all the characters in that column are the same
            // as long as the winning character is not 0000 in the ASCII table
            colWin = (board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && (board[0][i] != '\u0000');
            if (colWin) {
                System.out.println("Win by column!");
                if (xWin) {
                    System.out.println("X wins!");
                } else {
                    System.out.println("O wins!");
                }
                return true;
            }
        }

        // 2 ways to win by diagonal: if the left-to-right diagonal is the same, or if the right-to-left diagonal is the same
        // as long as the winning character is not 0000 in the ASCII table
        diagWin = ((board[0][0] != '\u0000' || (board[0][2] != '\u0000')) && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2])
                || ((board[0][2] == board[1][1]) && (board[1][1] == board[2][0])));

        if (diagWin) {
            System.out.println("Win by diagonal!");
            if (((board[0][0] == board[1][1]) || (board[0][2] == board[1][1])) && (board[1][1] == 'x')) {
                System.out.println("X wins!");
            } else {
                System.out.println("O wins!");
            }
            return true;
        }

        // if the game ends and no one has won, then there has been a draw
        System.out.println("Draw! No one wins!");
        return false;
    }

    // a move is legal if it doesn't overwrite a previous move,
    // i.e., if the space contains the null ASCII character before the move
    public static boolean canMakeMove(int row, int col) {
        return board[row][col] == '\u0000';
    }

    // prompt the user to provide the row coordinate
    // receive the user's input
    private static int getRow() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a row");
        return input.nextInt();
    }

    private static int getCol() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a column");
        return input.nextInt();
    }

    private static void playGame() {
        int desiredRow;
        int desiredCol;

        for (int move = 0; move < MAX_MOVE_COUNT; move++) {
            if (isXTurn()) {
                System.out.println("X's turn!");
                desiredRow = getRow();
                desiredCol = getCol();
                if (canMakeMove(desiredRow, desiredCol)) {
                    setX(desiredRow, desiredCol);
                }

            }
            if (isOTurn()) {
                System.out.println("O's turn!");
                desiredRow = getRow();
                desiredCol = getCol();
                if (canMakeMove(desiredRow, desiredCol)) {
                    setO(desiredRow, desiredCol);
                }
            }
        }
        if (declareWinner()) {
            printBoard();
        }


        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        //playGame();
        printBoard();
    }
}