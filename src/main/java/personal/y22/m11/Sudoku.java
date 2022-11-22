package personal.y22.m11;

public class Sudoku {

     // grid is represented by a 2d array
     public static int[][] gameBoard;
     // array will always be 9x9
     static final int SUDOKU_BOARD_SIZE = 9;

     public static void main(String[] args) {
          Sudoku game = new Sudoku();

          // build a 2d array of size 9x9
          gameBoard = game.buildBoard();

          // save the number of spaces still unfilled when clues are all added
          int numberOfSpacesLeft = (int) (Math.pow(SUDOKU_BOARD_SIZE, 2) - game.addNecessaryCluesToMakeSolvable());

          // at this point, there must be at least 17 clues already filled in (out of 81)
          //game.printGameBoard();

          //        System.out.println(numberOfSpacesLeft);

          int tryThis = 1;
          boolean cellIsEmpty = true;
          // look at every cell
          /*for (int i = 0; i < 9; i++) {
               for (int j = 0; j < 9; j++) {
                    if (gameBoard[i][j] != 0) {
                         break;
                    }

                    // if the cell is empty, look through the row, column, and square, to see if you can place the number

                    //fixme: cells overflowing 9, not all cells filled
                    do {
                         boolean numberCanGoInRow = game.validateRowForNumber(tryThis, i);
                         boolean numberCanGoInCol = game.validateColumnForNumber(tryThis, j);
                         boolean numberCanGoInSquare = game.validateSquareForNumber(tryThis, i, j);
                         // try placing a number only if it can legally go in the row and the column and the 3x3 square
                         boolean shouldTryToPlace = numberCanGoInCol && numberCanGoInSquare && numberCanGoInRow;

                         // if you should try to place a number, do it
                         if (shouldTryToPlace) {
                              gameBoard[i][j] = tryThis;
                              cellIsEmpty = false;
                              break;
                         } else { // otherwise, increment the number you are trying to place and try again
                              tryThis++;
                         }
                    } while (cellIsEmpty);
               }
          }

          System.out.println(Arrays.deepToString(gameBoard));
*/

          game.printGameBoard();
     }


     // manually add starter clue values and coordinates
     // return the number of clues added
     // subtract this number from 9^2 to find how many cells need to be updated
     private int addNecessaryCluesToMakeSolvable() {

          int clueCount = 0;
          placeNum(4, 0, 0);
          clueCount++;
          placeNum(5, 0, 3);
          clueCount++;
          placeNum(7, 0, 6);
          clueCount++;


          placeNum(1, 1, 2);
          clueCount++;
          placeNum(2, 1, 5);
          clueCount++;
          placeNum(8, 1, 7);
          clueCount++;

          placeNum(7, 2, 5);
          clueCount++;
          placeNum(9, 2, 6);
          clueCount++;

          placeNum(3, 3, 1);
          clueCount++;
          placeNum(6, 3, 2);
          clueCount++;
          placeNum(4, 3, 4);
          clueCount++;
          placeNum(2, 3, 8);
          clueCount++;

          placeNum(2, 4, 3);
          clueCount++;

          placeNum(8, 5, 1);
          clueCount++;
          placeNum(3, 5, 4);
          clueCount++;
          placeNum(6, 5, 8);

          placeNum(9, 6, 3);
          clueCount++;
          placeNum(8, 6, 5);
          clueCount++;
          placeNum(5, 6, 6);
          clueCount++;

          placeNum(1, 7, 0);
          clueCount++;
          placeNum(5, 7, 5);
          clueCount++;
          placeNum(8, 7, 6);
          clueCount++;

          placeNum(3, 8, 0);
          clueCount++;
          placeNum(6, 8, 3);
          clueCount++;
          placeNum(1, 8, 7);
          clueCount++;

          return clueCount;
     }

     // places a number at the selected row and column
     private static void placeNum(int number, int rowPos, int colPos) {
          gameBoard[rowPos][colPos] = number;
     }

     // prints game board without any numbers-- not even the default clues the game starts with
     private void printBlankGameBoard(int[][] gameBoard) {
          for (int i = 0; i < 9; i++) {
               if (i % 3 == 0 && i != 0) {
                    System.out.println();
                    for (int j = 0; j < 9; j++) {
                         if (j % 3 == 0) {
                              System.out.print(" ");
                         }
                         if (gameBoard[i][j] == 0) {
                              System.out.print(". ");
                         }
                    }
               } else {
                    for (int j = 0; j < 9; j++) {
                         if (j % 3 == 0) {
                              System.out.print(" ");
                         }
                         if (gameBoard[i][j] == 0) {
                              System.out.print(". ");
                         }
                    }
               }
               System.out.println();
          }
     }

     // print the progress of the game after each valid number is added
     private void printGameBoard() {
          for (int i = 0; i < 9; i++) {
               if (i % 3 == 0 && i != 0) {
                    System.out.println();
                    for (int j = 0; j < 9; j++) {
                         if (j % 3 == 0) {
                              System.out.print(" ");
                         }
                         System.out.print(gameBoard[i][j]);
                    }
               } else {
                    for (int j = 0; j < 9; j++) {
                         if (j % 3 == 0) {
                              System.out.print(" ");
                         }
                         System.out.print(gameBoard[i][j]);
                    }
               }
               System.out.println();
          }
     }

     // create 9x9 game board
     private int[][] buildBoard() {
          return new int[SUDOKU_BOARD_SIZE][SUDOKU_BOARD_SIZE];
     }

     // check to see if the specified number already exists somewhere in the requested row
     // if it does, then it cannot be added-- so return false
     // if it does not, then it can be added-- so return true
     public boolean validateRowForNumber(int number, int rowIndex) {
          for (int i = 0; i < SUDOKU_BOARD_SIZE; i++) {
               if (gameBoard[rowIndex][i] == number) {
                    return false;
               }
          }
          return true;
     }

     // check to see if the specified number already exists somewhere in the requested column
     // if it does, then it cannot be added-- so return false
     // if it does not, then it can be added-- so return true
     public boolean validateColumnForNumber(int number, int colIndex) {
          for (int i = 0; i < SUDOKU_BOARD_SIZE; i++) {
               if (gameBoard[i][colIndex] == number) {
                    return false;
               }
          }
          return true;
     }

     // check to see if the specified number already exists somewhere in the requested 3x3 square in which that cell exists
     // if it does, then it cannot be added-- so return false
     // if it does not, then it can be added-- so return true
     public boolean validateSquareForNumber(int number, int row, int col) {
          int squareRowStart = row / 3 * 3;
          int squareColStart = col / 3 * 3;

          int squareRowEnd = squareRowStart + 3;
          int squareColEnd = squareColStart + 3;

          for (int i = squareRowStart; i < squareRowEnd; i++) {
               for (int j = squareColStart; j < squareColEnd; j++) {
                    if (gameBoard[i][j] == number) {
                         return false;
                    }
               }
          }

          return true;
     }

}



/*
Turn every cell into a Set and add every legal value to that Set--
then see which values are unique in the Sets for particular rows/columns/squares?

 i.e., if of all the sets for a row, only 1 allows for a 7, place the 7 in that space

 */