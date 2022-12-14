package personal.y22.m09;

import java.util.Scanner;

public class TicTacToe {
    // board is 3x3 array of chars-- null when game starts, x or o as game progresses, remains
    // null if space not used in-game
    private static final char[][] board = new char[3][3];
    // 2 players always alternate, so parity can track whose move it is-- so track the number of moves
    // that have already been made
    private static int numMoves = 0;
    // the game continues until this is changed to true
    private static boolean gameCanContinue = true;

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
    public static void setGamePos(char player, int rowPos, int colPos) {
        board[rowPos][colPos] = player;
        numMoves++;
    }


    // x moves first, third, fifth, etc.-- when move counts are odd
    // if this is false this is the second, fourth, sixth, etc. move-- when it is o's turn
    public static boolean isXTurn() {
        return numMoves % 2 == 0;
    }

    // there is a winner if there is a single
    // character that occupies 3 adjacent
    // (horizontal, vertical, diagonal) spaces on the board
    // that winner is x if any of the 3 adjacent characters that triggerred the win was an x
    // that winner is o if any of the 3 adjacent characters that triggerred the win was an o
    public static char declareWinner() {
        boolean rowWin;
        boolean colWin;
        boolean diagWin;
        for (int i = 0; i < board.length; i++) {
            // win by row if, for any given row, all the characters in that row are the same
            // as long as the winning character is not 0000 in the ASCII table
            rowWin = ((board[i][0] == board[i][1]) && (board[i][1] == board[i][2])) && (board[i][0] != '\u0000');
            // win by column if, for any given column, all the characters in that column are the same
            // as long as the winning character is not 0000 in the ASCII table
            colWin = ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) && (board[0][i] != '\u0000');
            if (rowWin) {
                gameCanContinue = false;
                System.out.println("Win by row!");
                // end the program when the game ends after informing the players of the result
                if (board[i][0] == 'X') {
                    return 'X';
                }
                return 'O';
            } else if (colWin) {
                gameCanContinue = false;
                System.out.println("Win by column!");
                // end the program when the game ends after informing the players of the result
                if (board[0][i] == 'X') {
                    return 'X';
                }
                return 'O';
            }
        }

        // 2 ways to win by diagonal: if the left-to-right diagonal is the same,
        // or if the right-to-left diagonal is the same
        // as long as the winning character is not 0000 in the ASCII table

        boolean leftDiagWin = (board[0][0] == 'X' || board[0][0] == 'O') && (board[1][1] == board[0][0]) && (board[1][1] == board[2][2]);
        boolean rightDiagWin = (board[0][2] == 'X' || board[0][2] == 'O') && (board[1][1] == board[0][2]) && (board[1][1] == board[2][0]);
        // use XOR operator ^
        // cannot win by both diagonals in same game-- either win by right or by left
        diagWin = (leftDiagWin ^ rightDiagWin);

        if (diagWin) {
            gameCanContinue = false;
            System.out.println("Win by diagonal!");
            if (((board[0][0] == board[1][1]) || (board[0][2] == board[1][1])) && (board[1][1] == 'X')) {
                System.out.println("X wins!");
                return 'X';
            } else {
                System.out.println("O wins!");
                return 'O';
            }
        }

        // if the game ends and no one has won, then there has been a draw
        if (isBoardFull()) {
            gameCanContinue = false;
            System.out.println("Draw! No one wins!");
            return 'D';
        }
        gameCanContinue = true;
        return 0;
    }

    // a move is legal if it doesn't overwrite a previous move,
    // i.e., if the space contains the null ASCII character before the move
    public static boolean canMakeMove(int row, int col) {
        return board[row][col] == '\u0000';
    }

    public static boolean isBoardFull() {
        boolean row1full = (board[0][0] == 'X' || board[0][0] == 'O') && (board[0][1] == 'X' || board[0][1] == 'O') && (board[0][2] == 'X' || board[0][2] == 'O');
        boolean row2full = (board[1][0] == 'X' || board[1][0] == 'O') && (board[1][1] == 'X' || board[1][1] == 'O') && (board[1][2] == 'X' || board[1][2] == 'O');
        boolean row3full = (board[2][0] == 'X' || board[2][0] == 'O') && (board[2][1] == 'X' || board[2][1] == 'O') && (board[2][2] == 'X' || board[2][2] == 'O');

        return row1full && row2full && row3full;
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
        while (gameCanContinue) {
            if (isXTurn()) {
                executeTurn('X');
            } else {
                executeTurn('O');
            }
            declareWinner();
            if (isBoardFull()) {
                gameCanContinue = false;
            }
            printBoard();
        }
    }

    private static void executeTurn(char player) {
        System.out.println(player + "'s turn!");
        int desiredRow;
        int desiredCol;
        desiredRow = getRow();
        desiredCol = getCol();
        if (canMakeMove(desiredRow, desiredCol)) {
            setGamePos(player, desiredRow, desiredCol);
        } else {
            System.out.println("Cannot move to row " + desiredRow + " and column " + desiredCol);
        }
    }

    // ask the player if they want to play again
    // if the answer is yes, reset the board
    public static boolean wantToPlayAgain() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to play again?");
        String doYouWantToPlayAgain = input.nextLine();
        return doYouWantToPlayAgain.equalsIgnoreCase("yes");
    }

    // reset the board to all nulls
    private static void resetBoard() {
        board[0] = new char[]{'\u0000', '\u0000', '\u0000'};
        board[1] = new char[]{'\u0000', '\u0000', '\u0000'};
        board[2] = new char[]{'\u0000', '\u0000', '\u0000'};
    }

    public static void main(String[] args) {

        // store how many wins X has
        // before any games played, win total is 0
        int xWinCount = 0;
        // store how many wins O has
        // before any games played, win total is 0
        int oWinCount = 0;
        // store how many draws
        // before any games played, draw total is 0
        int drawCount = 0;

        boolean playAgain;
        do {
            // play once, for sure
            playGame();
            // if X won, give X credit
            if (declareWinner() == 'X') {
                xWinCount++;
            }// if O won, give O credit
            if (declareWinner() == 'O') {
                oWinCount++;
            }// if result is a draw, credit draw
            if (declareWinner() == 'D') {
                drawCount++;
            }// print out win-loss-draw record
            String wldRecord = xWinCount + " wins for X, " + oWinCount + " wins for O, and " + drawCount + " draws so far";
            System.out.println(wldRecord);
            // check whether to play again
            playAgain = wantToPlayAgain();
            // clear the board
            resetBoard();
        } while (playAgain);
    }
}