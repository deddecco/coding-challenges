package personal.y22.m09;


import java.util.HashSet;

public class WordIgnore {

     // this can stay as an []
     public static HashSet<String> wordList = new HashSet<>();


     // change this to a data structure with more efficient lookup than O(n)
     // O(logn)?
     // O(1)?
     // public static HashSet<String> ignoreThese = new HashSet<>();
     public static String[] ignoreThese = {"the", "and", "to", "you", "in", "he", "will", "of", "a", "for"};


     public static void main(String[] args) {
          //printNotIgnored();

          executeNTimes(1);
     }

     private static void printNotIgnored() {
          // look through the word list
          for (String word : wordList) {
               // if the current entry isn't also in the ignored list
               if (!(ignoreIndex(word) == -1)) {
                    // print it out with an extra \t on the back end
                    System.out.println(word + "\t");
               }
          }
     }

     public static int ignoreIndex(String word) {
          /*return ignoreThese.contains(word);*/
          for (int i = 0; i < wordList.size(); i++) {
               if (word.equals(ignoreThese[i])) {
                    return i;
               }
          }
          return -1;
     }

     public static void executeNTimes(int n) {
          long timeNow = System.nanoTime();
          buildUniverseSet();
          //buildIgnoreSet();
          for (int i = 0; i < n; i++) {
               printNotIgnored();
          }
          long timeAfter = System.nanoTime();

          long totalTime = (long) ((timeAfter - timeNow) / 10e6);

          System.out.println("That took " + totalTime + " milliseconds");

     }

     private static void buildUniverseSet() {

          wordList.add("the");
          wordList.add("and");
          wordList.add("of");
          wordList.add("to");
          wordList.add("you");
          wordList.add("in");
          wordList.add("he");
          wordList.add("will");
          wordList.add("a");
          wordList.add("for");
          wordList.add("that");
          wordList.add("they");
          wordList.add("his");
          wordList.add("be");
          wordList.add("is");
          wordList.add("not");
          wordList.add("lord");
          wordList.add("with");
          wordList.add("your");
          wordList.add("have");
          wordList.add("from");
          wordList.add("who");
     }
}
