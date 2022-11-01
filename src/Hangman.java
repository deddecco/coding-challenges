package personal.y22.m09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Hangman {
     public static int numLetters;
     public static int numWrongGuesses = 0;
     public static int guessesAllowed = 7;
     private static String word;
     public static int[] indicesOfCorrectGuesses;
     public static final String filePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\wordlist.txt";


     // after each wrong guess, draw more of the man
     private static void addToMan(int numWrongGuesses) {
          String drawingOfMan = "";
          if (numWrongGuesses >= 1) {
               drawingOfMan = " O";
          }
          if (numWrongGuesses == 2) {
               drawingOfMan += "\n |";
          } else if (numWrongGuesses == 3) {
               drawingOfMan += "\n\\|";
          } else if (numWrongGuesses == 4) {
               drawingOfMan += "\n\\|/";
          } else if (numWrongGuesses == 5) {
               drawingOfMan += "\n\\|/";
               drawingOfMan += "\n |";
          } else if (numWrongGuesses == 6) {
               drawingOfMan += "\n\\|/";
               drawingOfMan += " \n |";
               drawingOfMan += "\n /";
          } else if (numWrongGuesses == 7) {
               drawingOfMan += "\n\\|/";
               drawingOfMan += " \n |";
               drawingOfMan += "\n/ \\";
          }

          System.out.println(drawingOfMan);
     }

     // if a correct letter appears more than once in the game word,
     // give credit for all the occurrences of the letter in one turn
     private static void findAllInstancesOfGuessInWord(char guess) {

          int index = word.indexOf(guess);
          if (index == -1) {
               numWrongGuesses++;
          }
          while (index != -1) {
               indicesOfCorrectGuesses[index] = 1;
               index = word.indexOf(guess, index + 1);
          }


          // System.out.println(Arrays.toString(indicesOfCorrectGuesses));
     }

     // prompt the user to enter a guess letter
     // if the user enters more than one letter,
     // the guess letter is by definition the first
     // letter of whatever was entered
     private static char getGuess() {
          Scanner input = new Scanner(System.in);
          System.out.println("Please enter a guess");
          String guessThrowaway = input.next();
          return guessThrowaway.charAt(0);
     }

     // read the list of available words
     // load them all into an array
     // pick an index in bounds in that array
     // return the word at that index;
     private static String getGameWord() throws IOException {
          String[] words = readGameFile(filePath);
          int randomIndex;
          Random random = new Random();
          randomIndex = random.nextInt(words.length);
          getGameWord(words[randomIndex]);
          return words[randomIndex];
     }

     private static void getGameWord(String selectedWord) {
          word = selectedWord;
          indicesOfCorrectGuesses = new int[word.length()];
     }

     // create an array where the length is equal
     // to the number of words = lines in the file
     // and read in each line to a new index of an array
     public static String[] readGameFile(String gameFilePath) throws IOException {
          String[] words = new String[2241];
          BufferedReader br = new BufferedReader(new FileReader(gameFilePath));
          String currLine;

          int i = 0;
          while ((currLine = br.readLine()) != null) {
               words[i] = currLine;
               i++;
          }

          return words;
     }


     // print a partial word
     // every correct letter gets printed
     // every position where a correct guess has
     // not been made gets substituted with a -
     // if only A has been guessed and the word is ALPHA,
     // print "A---A"
     // if the word is "banana" and both B and A have been guessed,
     // print "BA-A-A"
     // etc.
     private static void printProgressSoFar() {
          StringBuilder progress = new StringBuilder();

          for (int i = 0; i < word.length(); i++) {
               if (indicesOfCorrectGuesses[i] == 1) {
                    progress.append(word.charAt(i));
               } else {
                    progress.append("-");
               }
          }
          System.out.println("Progress so far: " + progress);
     }


     public static void main(String[] args) throws IOException {

          String word = getGameWord();
          System.out.println("-".repeat(word.length()));
          char currentGuess;
          while (numWrongGuesses < 7) {
               currentGuess = getGuess();
               findAllInstancesOfGuessInWord(currentGuess);
               printProgressSoFar();
               addToMan(numWrongGuesses);
          }
          detectEnd();
          System.out.println("The word was " + word);

     }

     // count and return the size of the set of letters
     // generated by the game word = the minimum number of guesses needed
     // to win the game
     public static int numberOfUniqueLetters() {
          return createSet().size();
     }


     // put all the characters of the gameword into a set
     public static Set<Character> createSet() {
          String gameWord = "abcdefghijklmnopqrstuvwxyz";
          Set<Character> characterSet = new HashSet<>();
          for (int i = 0; i < gameWord.length(); i++) {
               characterSet.add(gameWord.charAt(i));
          }

          return characterSet;
     }

     // determine if the game is over, and if the player won or lost
     public static void detectEnd() {
          boolean isWin = true;

          if (numWrongGuesses == guessesAllowed) {
               isWin = false;
          } else {
               for (int indicesOfCorrectGuess : indicesOfCorrectGuesses) {
                    if (indicesOfCorrectGuess != 1) {
                         isWin = false;
                         break;
                    }
               }
          }
          if (isWin) {
               System.out.println("Congratulations, you won!");
          } else {
               System.out.println("Better luck next time!");
          }
     }
}