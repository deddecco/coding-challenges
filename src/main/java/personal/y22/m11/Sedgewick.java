package personal.y22.m11;

public class Sedgewick {

     public static void main(String[] args) {

          System.out.println(exR2(1));
     }

     private static String exR2(int i) {
          String s = exR2(i - 3) + exR2(i - 2) + i;
          return i <= 0 ? "" : s;
     }
}
