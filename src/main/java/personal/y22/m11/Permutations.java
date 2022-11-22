package personal.y22.m11;
import java.util.ArrayList;
import java.util.List;

public class Permutations {

     public static void main(String[] args) {
          String alphabet = "103056";
          // most methods not static to allow for the creation of multiple Permutations objects
          Permutations permutations = new Permutations();
          permutations.printAll(alphabet);
     }

     private void printAll(String alphabet) {
          // create a list of all digits 1-6
          List<Character> listOfAllDigits = createPossibleCharList();
          // create a list of all digits used in the number
          List<Character> alphabetList = createAlphabetList(alphabet);
          // create a list of the digits missing from the number
          List<Character> missingList = createMissingList(listOfAllDigits, alphabetList);

          // print out all the permutations that add back that missing digit
          createPermutationList(alphabetList, missingList);
     }

     private void createPermutationList(List<Character> alphabetList, List<Character> missingList) {
          StringBuilder sb = new StringBuilder();
          for (char c : alphabetList) {
               sb.append(c);
          }

          String string = sb.toString();

          List<StringBuilder> permutations = new ArrayList<>();

          for (int i = 0; i < 6; i++) {
               sb = reset(string);
               /*
                fixme: help expanding this beyond just get(0) to any/every index in the list
                maybe a loop going through each element of missingList, nested in the i-Loop?
                or maybe something recursive since nPr and nCr are recursively defined given n!?
               */
               sb.insert(i, missingList.get(i));
               permutations.add(sb);
          }
          System.out.println(permutations);
     }

     // return StringBuilder object to unmodified state before modifying again
     private StringBuilder reset(String string) {
          return new StringBuilder(string);
     }

     // list of digits not used
     private List<Character> createMissingList(List<Character> listOfAllDigits, List<Character> alphabetList) {
          List<Character> missingChars = new ArrayList<>();
          // create and return a list holding within it the digits from the master list not found in the alphabet list
          for (Character c : listOfAllDigits) {
               if (!alphabetList.contains(c)) {
                    missingChars.add(c);
               }
          }
          return missingChars;
     }

     // list of digits actually used
     private List<Character> createAlphabetList(String alphabet) {
          List<Character> alphaList = new ArrayList<>();
          // create and return a list that contains each non-zero digit used in the number
          for (int i = 0; i < alphabet.length(); i++) {
               if (alphabet.charAt(i) != '0') {
                    alphaList.add(alphabet.charAt(i));
               }
          }
          return alphaList;
     }


     // list of all digits
     private static List<Character> createPossibleCharList() {
          List<Character> listOfAllDigits = new ArrayList<>();
          char[] chars = new char[]{'1', '2', '3', '4', '5', '6'};
          // transform that array of digits into a list and return it
          for (char c : chars) {
               listOfAllDigits.add(c);
          }
          return listOfAllDigits;
     }
}