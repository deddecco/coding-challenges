package personal.y22.m11;

public class SurroundingTextPrinter {
     static String[] boundaries = {"blue", "bananas", "test", "apple", "fried"};
     static String[] enclosures = {"abc", "def", "ghi", "jkl", "mno"};

     public static void main(String[] args) {
     }

     public void printBoundary(String boundary) {
          System.out.println(boundary);
     }

     public int setEnclosureSize(int size) {
          return size;
     }

     public String setEnclosedText(String enclosedText) {
          return enclosedText;
     }

     public String setBoundaryText(String boundaryText) {
          return boundaryText;
     }

     private static void printEnclosureNumberOfTimes(String text, int number) {
          for (int i = 0; i < number; i++) {
               System.out.println(text);
          }
     }
}
