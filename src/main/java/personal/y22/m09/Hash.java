package personal.y22.m09;

import java.io.*;
import java.util.Comparator;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.*;
import static java.util.Collections.reverse;
import static java.util.Comparator.comparingInt;
import static personal.y22.m09.LinkedList.Element;

public class Hash {
     private static LinkedList[] hashes = new LinkedList[40000];

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

     public static void put(Element element) {
          int index;

          index = calculateHash(element);
          if (hashes[index] == null) {
               hashes[index] = new LinkedList();
               hashes[index].put(element);
          } else {
               Element found = hashes[index].getByKey(element.key);
               if (found == null) {
                    hashes[index].put(element);
               } else {
                    element.val += found.val;
                    hashes[index].update(element);
               }
          }
     }


     // remove Element from hash
     private static void deleteFromHash(Element element) {
          hashes[calculateHash(element)].delete(element.key);
     }

     // print Hash
     // don't print out empty lists
     // print out ranges of empty list indices
     // or print out values in populated lists in index order
     public static void printHash() {
          int firstEmpty = -1;
          int lastEmpty = -1;

          for (int i = 0; i < hashes.length; i++) {
               if (hashes[i] == null || hashes[i].isEmpty()) {
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
                    hashes[i].printList();
               }
          }
     }


     public static String readFile(String filePath) throws IOException {
          BufferedReader reader = new BufferedReader(new FileReader(filePath));
          String currLine;
          StringBuilder total = new StringBuilder();
          while ((currLine = reader.readLine()) != null) {
               total.append(currLine).append("\n");
          }

          return total.toString();
     }

     public static void main(String[] args) throws IOException {

          String filePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\wholeBo.txt";
          String contents = readFile(filePath);


          contents = contents.toLowerCase();
          String[] words = contents.split("\\s+");

          Element newElement;

          for (String word : words) {
               newElement = new Element(word, 1);
               put(newElement);
          }
          //  printHash();

          String[][] wordsAndFreqs = new String[14746][2];
          int posCounter = 0;

          generateOutputTable(wordsAndFreqs, posCounter);
          generateOutputFile(wordsAndFreqs);

     }

     private static void generateOutputFile(String[][] wordsAndFreqs) throws IOException {
          System.out.println(deepToString(wordsAndFreqs));

          String outputFilePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\wholebible.txt";
          BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

          String header = "word\tfrequency";

          writer.write(header);
          for (String[] wordRecord : wordsAndFreqs) {
               writer.write("\n" + wordRecord[0] + "\t" + wordRecord[1]);
          }
          writer.close();
     }

     private static void generateOutputTable(String[][] wordsAndFreqs, int posCounter) {
          Element current;
          for (LinkedList list : hashes) {
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

          sort(wordsAndFreqs, recordComparator);
          reverse(asList(wordsAndFreqs));
     }
}