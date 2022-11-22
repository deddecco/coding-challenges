package personal.y22.m11;

import java.util.*;

public class ArrayShuffler {
     static int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

     public static void main(String[] args) {

          shuffle(2);
     }

     private static void shuffle(int i) {
          int[] toShuffle = new int[i];
          System.arraycopy(nums, 0, toShuffle, 0, i);

          System.out.println(Arrays.toString(toShuffle));

          List<Integer> shuffleThisList = new ArrayList<>();
          for (int k : toShuffle) {
               shuffleThisList.add(k);
          }

          Factorial fact = new Factorial();
          Set<List<Integer>> permutations = new HashSet<>();
          while(!(permutations.size() == fact.factorial(i))){
               Collections.shuffle(shuffleThisList);
          }

     }
}
