package personal.y22.m11;

import java.util.ArrayList;
import java.util.List;

public class DiagonalCopy {

     public static void main(String[] args) {

          String[] letters = new String[7];
          letters[0] = "1";
          letters[1] = "2";
          letters[2] = "3";
          letters[3] = "4";
          letters[4] = "5";
          letters[5] = "6";
          letters[6] = "7";


        /*  for (int i = 0; i < letters.length + 1; i++) {
               String[] copy = copyLeaveBlank(letters, i);
               String[] adjusted = insertNew(copy, "z");
               System.out.println(Arrays.toString(adjusted));
          }*/

          DiagonalCopy dc = new DiagonalCopy();
          //System.out.println(Arrays.toString(dc.alpha(letters, 7, "8")));
     }


     // copy n items into an array of size n+1, n different times
     // in the first copy, copy[0] should be blank
     // in the second copy, copy[1] should be blank
     // etc.
     public List<String> copyLeaveBlank(List<String> letters, int n) {

          List<String> lettersCopy = new ArrayList<>();
          for (int i = 0; i < n; i++) {
               lettersCopy.add(i, letters.get(i));
          }
          lettersCopy.add(n, null);
/*
          lettersCopy.addAll(asList(letters).subList(n, letters.size() - 1));
*/

          for (int i = n + 1; i < letters.size(); i++) {
               lettersCopy.add(i, letters.get(i));
          }

          return lettersCopy;
     }


     //fixme
     // given an array with 1 strategically-placed blank,
     // place the second parameter into the blank space in that array
     // and return the array that now has no more blanks
     public List<String> insertNew(List<String> adjustedLetters, String newLetter, int index) {
          adjustedLetters.add(index, newLetter);
          return adjustedLetters;
     }

     //fixme rewrite?
     /*public List<String> alpha(List<String> adjusted, int n, String insertion) {
          //String[] copy = copyLeaveBlank(adjusted, n);
          return insertNew(copy, insertion);
     }*/

     public List<String> alpha(List<String> adjusted, int insertPos, String insertThis) {
          adjusted.add(insertPos, insertThis);
          return adjusted;
     }
}
