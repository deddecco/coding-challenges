/*
package personal.y22.m11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.arraycopy;

public class CopyDiagonalAdjust {

     public static void main(String[] args) {
          String[] oneToFour = {"1", "2", "3", "4", "5", "6"};

          List<List<String>> oneToFourBlankSpaces = copyAddBlank(oneToFour);

          System.out.println(oneToFourBlankSpaces);
     }

     public static List<List<String>> copyAddBlank(String[] toBeCopied) {


          int numberOfCopies = 0;
          Factorial factorial = new Factorial();
          List<List<String>> copiesBlanks = new ArrayList<>();


          for (int i = 0; i < copiesBlanks.size(); i++) {
               String[] copyThis = new String[toBeCopied.length + 1];
               arraycopy(toBeCopied, 0, copyThis, 0, toBeCopied.length);
*/
/*
               copyThis[copyThis.length - 1] = 0;
*//*

               copiesBlanks.get(i) = List.of(copyThis);
               numberOfCopies++;
          }

          return copiesBlanks;
     }
}
*/
