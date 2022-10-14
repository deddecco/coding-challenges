package personal.y22.m09;

public class HashFunction {

     public static void main(String[] args) {
          String test = "apple";

          int charSum = calculateCharSum(test);

          System.out.println(test + "\t" + charSum);


          test = "this is a test";
          charSum = calculateCharSum(test);
          System.out.println(test + "\t" + charSum);

          test = "a reading from the letter of saint paul to the romans";

          charSum = calculateCharSum(test);
          System.out.println(test + "\t" + charSum);

     }

     private static int calculateCharSum(String test) {
          int charSum = 0;

          for (int i = 0; i < test.length(); i++) {
               if (test.charAt(i) != 32) {
                    System.out.println("charAt(i): " + (char) test.charAt(i) + "\t" + (int) test.charAt(i));
                    charSum += test.charAt(i);
               }
          }

          charSum %= 100;

          return charSum;
     }
}
