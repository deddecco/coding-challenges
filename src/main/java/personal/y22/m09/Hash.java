package personal.y22.m09;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.Collections.reverse;
import static java.util.Comparator.comparingInt;
import static personal.y22.m09.LinkedList.Element;

public class Hash {
     private final LinkedList[] hashes = new LinkedList[40000];
     private static final List<String> ignoreList = new java.util.LinkedList<>();

     // each element contains two strings: a key and a value
     // we hash based on the key
     // starting a sum at 0,
     // sum the ASCII codes of each character in the string that is not a space
     // (space has an ASCII code of 32, so we ignore any character whose ASCII code is 32)
     // then take the sum % 40009 -- the smallest prime >= the size of the array (smallest at least 4000000)
     private static int calculateHash(Element element) {
          int charSum = 0;

          for (int i = 0; i < element.key.length(); i++) {
               if (element.key.charAt(i) != 32) {
                    charSum += element.key.charAt(i);
               }
          }
          charSum %= 40009;

          return charSum;
     }

     // add Element to hash
     // if the linked list at the index = result of the hash function
     // does not exist, create it
     public void put(Element element) {
          int index = calculateHash(element);

          if (this.hashes[index] == null) {
               this.hashes[index] = new LinkedList();
               this.hashes[index].put(element);
          } else {
               Element found = this.hashes[index].getByKey(element.key);
               if (found == null) {
                    this.hashes[index].put(element);
               } else {
                    element.val += found.val;
                    this.hashes[index].update(element);
               }
          }
     }


     // remove Element from hash if element with given key exists
     private void deleteFromHash(Element element) {
          this.hashes[calculateHash(element)].delete(element.key);
     }

     // print Hash
     // don't print out empty lists
     // print out ranges of empty list indices
     // or print out values in populated lists in index order
     public void printHash() {
          int firstEmpty = -1;
          int lastEmpty = -1;

          for (int i = 0; i < this.hashes.length; i++) {
               if (this.hashes[i] == null || this.hashes[i].isEmpty()) {
                    if (firstEmpty == -1) {
                         firstEmpty = i;
                    }
                    lastEmpty = i;
               } else {
                    if (firstEmpty != -1) {
                         System.out.printf("%d to %d: empty%n", firstEmpty, lastEmpty);
                         System.out.println();
                         firstEmpty = -1;
                         lastEmpty = -1;
                    }
                    System.out.print(i + ": ");
                    this.hashes[i].printList();
               }
          }
     }


     // read the input file with a BufferedReader
     // append each line (and a newline character) to a StringBuilder
     public static String readFile(String filePath) throws IOException {
          BufferedReader reader = new BufferedReader(new FileReader(filePath));
          String currLine;
          StringBuilder total = new StringBuilder();
          while ((currLine = reader.readLine()) != null) {
               total.append(currLine).append("\n");
          }

          return total.toString();
     }


     // we don't care about the frequencies of these very common/very short words
     public static void buildIgnoreList() throws IOException {
          String ignorePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\ignorelist.txt";
          BufferedReader reader = new BufferedReader(new FileReader(ignorePath));

          String word;
          while ((word = reader.readLine()) != null) {
               ignoreList.add(word);
          }

          reader.close();
     }

     // write the output table into a file
     private void generateOutputFile(String[][] wordsAndFreqs) throws IOException {
          String outputFilePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\BibleFrequencyAnalysis.txt";
          BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

          String header = "word\tfrequency";

          writer.write(header);
          for (String[] wordRecord : wordsAndFreqs) {
               writer.write("\n" + wordRecord[0] + "\t" + wordRecord[1]);
          }
          writer.close();
     }

     // takes the Hash of Linked Lists and converts it into an array
     // the first column contains the word
     // and the second contains the frequency
     public void generateOutputTable(String[][] wordsAndFreqs, int posCounter) {
          Element current;
          for (LinkedList list : this.hashes) {
               if (list != null && list.head != null) {
                    current = list.head;
                    while (current != null) {
                         wordsAndFreqs[posCounter][0] = current.key;
                         wordsAndFreqs[posCounter][1] = String.valueOf(current.val);
                         posCounter++;
                         current = current.next;
                    }
               }
          }

          Comparator<String[]> recordComparator = comparingInt(o -> parseInt(o[1]));
          Arrays.sort(wordsAndFreqs, recordComparator);
          reverse(asList(wordsAndFreqs));

          System.out.println(Arrays.deepToString(wordsAndFreqs));
     }


     public static void main(String[] args) throws IOException {
          buildIgnoreList();
          Set<String> uniqueWords = new HashSet<>();

          Hash bibleHash = new Hash();

          String filePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\wholeBible.txt";
          String contents = readFile(filePath);

          contents = contents.toLowerCase();
          String[] words = contents.split("\\s+");

          Element newElement;

          for (String word : words) {
               if (!ignoreList.contains(word)) {
                    newElement = new Element(word, 1);
                    bibleHash.put(newElement);
                    uniqueWords.add(word);
               }
          }

          String[][] wordsAndFreqs = new String[uniqueWords.size()][2];
          int posCounter = 0;

          bibleHash.generateOutputTable(wordsAndFreqs, posCounter);
          bibleHash.generateOutputFile(wordsAndFreqs);


          // todo: ignoreList from file
          // fixme: length of array
          // fixme: fewer static methods
          //~~~~~
          // todo: create getKeys()
          // fixme: generateOutputTable() using getKeys()
          // todo: process one line at a time
     }

}