package personal.y22.m09;

import java.io.*;
import java.util.*;

import static java.lang.Integer.*;
import static java.util.Comparator.comparingInt;

public class FrequencyAnalysisTool {

     public static HashSet<String> ignoreThese = new HashSet<>();

     private static HashMap<String, Integer> frequenciesFile() throws IOException {
          String[][] lineSplits = new String[37880][];

          int i = 0;
          String filename = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\wholeBible.txt";
          BufferedReader br = new BufferedReader(new FileReader(filename));

          String currLine;

          while ((currLine = br.readLine()) != null) {
               String[] lineSplit = currLine.toLowerCase().split("\s");
               lineSplits[i] = lineSplit;
               i++;
          }

          HashMap<String, Integer> frequencyRelation = new HashMap<>();
          int frequency = 1;
          buildIgnoreSet();
          for (String[] line : lineSplits) {
               for (String word : line) {
                    if (!ignoreThese.contains(word)) {
                         if (!frequencyRelation.containsKey(word)) {
                              frequencyRelation.put(word, frequency);
                         } else {
                              frequencyRelation.put(word, frequencyRelation.get(word) + 1);
                         }
                    }
               }
          }

          System.out.println(frequencyRelation);
          return frequencyRelation;
     }

     public static void mapToArray() throws IOException {

          // create an array
          // rows = number of unique words
          // columns = 2 (one word, one frequency per row)
          String[][] matches = new String[frequenciesFile().keySet().size()][2];


          // all the keys from the Map go into a collection,
          // then all the elements of that collection
          // go into the first column of an array
          Collection<String> keySet = frequenciesFile().keySet();
          String[] targetArray = keySet.toArray(new String[0]);


          // all the values from the Map go into a collection,
          // then all the elements of that collection
          // go into the first column of another array
          Collection<Integer> values = frequenciesFile().values();
          Integer[] ints = values.toArray(new Integer[0]);


          // this will populate the data structure we want
          // keys (words) go in their proper rows, in the first column
          int i = 0;
          for (String key : targetArray) {
               matches[i][0] = key;
               i++;
          }

          // values (integers) go in their proper rows, in the second column
          int j = 0;
          for (Integer integer : ints) {
               matches[j][1] = String.valueOf(integer);
               j++;
          }

          // set up a comparator that looks at the numerical values of the strings in the 2d array
          // (turns those strings back into numbers and then compares them)
          Comparator<String[]> mostCommon = comparingInt(o -> parseInt(o[1]));

          Arrays.sort(matches, mostCommon);
          Collections.reverse(Arrays.asList(matches));

          String resultFilePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\BibleFrequencyResults.txt";
          BufferedWriter writer = new BufferedWriter(new FileWriter(resultFilePath));

          writer.write("Word" + "\t" + "Frequency");

          for (String[] row : matches) {
               writer.write("\n");
               writer.write(row[0] + "\s\t\s" + row[1]);
          }
          writer.close();

     }

     public static void buildIgnoreSet() {
          ignoreThese.add("the");
          ignoreThese.add("and");
          ignoreThese.add("to");
          ignoreThese.add("you");
          ignoreThese.add("in");
          ignoreThese.add("he");
          ignoreThese.add("will");
          ignoreThese.add("of");
          ignoreThese.add("a");
          ignoreThese.add("for");
     }


     public static void main(String[] args) throws IOException {
          mapToArray();
     }
}