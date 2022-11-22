package personal.y22.m11;

import java.util.ArrayList;
import java.util.List;

public class NumberPermutationGenerator {

     // recursive method that prints out all the permutations of the numbers [1, n]
     public List<List<String>> permute(int bound) {

          // return this
          List<List<String>> PermOfN = new ArrayList<>();
          // base case
          List<String> PermOf1 = new ArrayList<>();

          List<List<String>> permNMinus1;
          if (bound == 1) {
               PermOf1.add("1");
               PermOfN.add(PermOf1);
          } else {
               permNMinus1 = permute(bound - 1);
               for (List<String> strings : permNMinus1) {
                    PermOfN.addAll(makeSeqCopies(strings, bound, String.valueOf(bound)));
               }
          }
          return PermOfN;
     }


     // confirm that the size of the list of the permutations of [1, n] is in fact n!
     public int checkPermuteListSize(List<List<String>> permutations) {
          return permutations.size();
     }


     // given a list and the number of copies of that list to me made,
     // add the given element in the ith position of the ith copy
     /// and return a list of those copies
     public List<List<String>> makeSeqCopies(List<String> sequence, int n, String addThisElem) {

          ArrayList<List<String>> copies = new ArrayList<>();
          for (int i = 0; i < n; i++) {
               copies.add(new ArrayList<>(sequence));
          }

          addNewElem(addThisElem, copies);

          return copies;
     }


     // add the element to each of the copies in the list of copies in such a way that
     // the ith copy of the list has the element inserted at the ith position
     private void addNewElem(String addThisElem, List<List<String>> copies) {
          int index = 0;
          for (List<String> list : copies) {
               list.add(index, addThisElem);
               index++;
          }
     }

     public static void main(String[] args) {

          NumberPermutationGenerator p = new NumberPermutationGenerator();
          int bound = 5;
          List<List<String>> permute = p.permute(bound);
          System.out.println(p.checkPermuteListSize(permute));
          System.out.println(permute);
     }
}
