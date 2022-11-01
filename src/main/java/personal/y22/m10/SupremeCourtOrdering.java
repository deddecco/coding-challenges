package personal.y22.m10;

import java.util.Arrays;

public class SupremeCourtOrdering {

     public String[] reorder(String[] toBeOrdered) {
          int indexOfFirst = toBeOrdered.length / 2;

          String[] reordered = new String[toBeOrdered.length];
          if (toBeOrdered.length % 2 == 1) {
               for (int l = 0; l < toBeOrdered.length; l += 2) {
                    reordered[l / 2] = toBeOrdered[l];
               }

               int maxOddIdx = toBeOrdered.length - 2;
               System.out.println("maxOddIdx = " + maxOddIdx);


               for (int offsetFromMid = 1; offsetFromMid <= toBeOrdered.length / 2; offsetFromMid++) {
                    reordered[indexOfFirst + offsetFromMid] = toBeOrdered[maxOddIdx - 2 * (offsetFromMid - 1)];
               }
          } else {
               int indexOfLeftMiddlePair = toBeOrdered.length / 2 - 1;
               int indexOfRightMiddlePair = toBeOrdered.length / 2;


               for (int offset = 0; offset < 3; offset++) {
                    reordered[indexOfLeftMiddlePair - offset] = toBeOrdered[2 * offset];
               }


               for (int offset = 0; offset < 3; offset++) {
                    reordered[indexOfRightMiddlePair + offset] = toBeOrdered[2 * offset + 1];
               }


          }

          System.out.println(Arrays.toString(toBeOrdered));
          System.out.println(Arrays.toString(reordered));

          return reordered;
     }

     public String[][] reorder(String[][] toBeOrdered) {
          int indexOfFirst = toBeOrdered.length / 2;

          String[][] reordered = new String[toBeOrdered.length][2];
          if (toBeOrdered.length % 2 == 1) {
               for (int l = 0; l < toBeOrdered.length; l += 2) {
                    reordered[l / 2] = toBeOrdered[l];
               }

               int maxOddIdx = toBeOrdered.length - 2;
               System.out.println("maxOddIdx = " + maxOddIdx);


               for (int offsetFromMid = 1; offsetFromMid <= toBeOrdered.length / 2; offsetFromMid++) {
                    reordered[indexOfFirst + offsetFromMid] = toBeOrdered[maxOddIdx - 2 * (offsetFromMid - 1)];
               }
          } else {
               int indexOfLeftMiddlePair = toBeOrdered.length / 2 - 1;
               int indexOfRightMiddlePair = toBeOrdered.length / 2;


               for (int offset = 0; offset < toBeOrdered.length / 2; offset++) {
                    reordered[indexOfLeftMiddlePair - offset] = toBeOrdered[2 * offset];
               }
               for (int offset = 0; offset < toBeOrdered.length / 2; offset++) {
                    reordered[indexOfRightMiddlePair + offset] = toBeOrdered[2 * offset + 1];
               }


          }
          return reordered;
     }


     public static void main(String[] args) {

          SupremeCourtOrdering sco = new SupremeCourtOrdering();
          sco.reorder(new String[][]{{"a", "1"}, {"b", "2"}, {"c", "3"}, {"d", "4"}, {"e", "5"}, {"f", "6"}, {"g", "6"}});
     }
}
