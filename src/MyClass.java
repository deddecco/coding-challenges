package personal.y22.m09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MyClass {
     public static void main(String[] args) throws IOException {
          frequenciesFile();
     }

     private static void frequenciesFile() throws IOException {
          String[][] lineSplits = new String[39602][];

          int i = 0;
          String filename = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\wholeBible.txt";
          BufferedReader br = new BufferedReader(new FileReader(filename));

          String currLine;

          while ((currLine = br.readLine()) != null) {
               String[] lineSplit = currLine.split("\s");
               lineSplits[i] = lineSplit;
               i++;
          }

          HashMap<String, Integer> FrequencyRelation = new HashMap<>();
          int frequency = 1;
          for (String[] line : lineSplits) {
               for (String word : line) {
                    if (!FrequencyRelation.containsKey(word)) {
                         FrequencyRelation.put(word, frequency);
                    } else {
                         FrequencyRelation.put(word, FrequencyRelation.get(word) + 1);
                    }
               }
          }

          System.out.println(FrequencyRelation);
     }
}