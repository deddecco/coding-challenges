package personal.y22.m09;

public class TabSetter {
     public static String[] headings;
     public static String[][] rowOfText;

     public static void setRowOfText() {
          rowOfText = new String[5][3];

          rowOfText[0] = new String[]{"abcde", "this is some text", "12345"};
          rowOfText[1] = new String[]{"here is even more text", "38987398", "aggjhwghdghjwgiu"};
          rowOfText[2] = new String[]{"apple blue apron", "2y6827678", "this text could be anything"};
          rowOfText[3] = new String[]{"xyz", "2897987928", "red blue green yellow"};
          rowOfText[4] = new String[]{"blue muffins", "red pizza", "orange soda"};
     }

     public static void setHeadings() {
          headings = new String[3];

          headings[0] = "red";
          headings[1] = "orange";
          headings[2] = "yellow";
     }


     public static void printHeadings() {
          for (int i = 0; i < headings.length - 1; i++) {
               System.out.print(headings[i] + " ".repeat(findLongestInCol(i) - headings[i].length() + 1));
          }
          System.out.println(headings[headings.length - 1]);
     }

     public static void printRowOfText() {
          for (int i = 0; i < rowOfText.length; i++) {
               for (int j = 0; j < rowOfText[i].length - 1; j++) {
                    System.out.print(rowOfText[i][j] + " ".repeat(findLongestInCol(j) - rowOfText[i][j].length() + 1));
               }
               System.out.println(rowOfText[i][rowOfText[i].length - 1]);
          }
     }

     public static void main(String[] args) {

          setRowOfText();
          setHeadings();


          printHeadings();
          //printAdjustedHeadings();
          printRowOfText();
/*
        printAdjustedData();
*/

        /*List<String> text = new ArrayList<>();

        text.add(String.format("A string: [%s]", "abc"));
        text.add(String.format("A string with minimum width: [%20s]", "abc"));
        text.add(String.format("A string with maximum width: [%.20s]", "abcdefghijklmnopqrstuvwxyz"));
        text.add(String.format("A string with maximum width: [%.20s]", "abc"));
        text.add(String.format("A string with min AND max width: [%10.10s]", "abc"));
        text.add(String.format("A string with min AND max width: [%10.10s]", "abcdefghijklmnopqrstuvwxyz"));
        text.add(String.format("An integer: [%d]", 123));
        text.add(String.format("An integer: [%010d]", 123));
        text.add(String.format("A floating point number (5.2): [%5.2f]", 1234.123456));
        text.add(String.format("A floating point number (5.4): [%5.4f]", 1234.123456));
        text.add(String.format("A floating point number (2.3): [%2.3f]", 1234.123456));

        System.out.println("text = " + String.join("\n", text));
        System.out.println((String.format("A string with maximum width: [%.30s]", Arrays.toString(headings))));
        System.out.printf("A sequence of 20 chars: [%.10s]", Arrays.toString(headings));
        //System.out.printf("%2$[findlongestHeading()]%s", Arrays.toString(headings));
        //System.out.printf("[%20s]", Arrays.deepToString(rowOfText) + "\n");*/

/*
        String[] test = new String[1];

        test[0] = "this is a much longer string that I want to trim";
        System.out.printf("[%.50s]", test[0]);
*/

     }

     public static void printAdjustedHeadings() {
          for (int pos = 0; pos < headings.length; pos++) {
               headings[pos] += " ".repeat(findLongestLength() - headings[pos].length() - pos);
          }
          printHeadings();
     }

     public static void printAdjustedData() {
          for (int row = 0; row < rowOfText.length; row++) {
               for (int col = 0; col < rowOfText[row].length; col++) {
                    rowOfText[row][col] += " ".repeat(findLongestInCol(col) - rowOfText[row][col].length());
                    System.out.println(rowOfText[row][col] + "t");
               }
               System.out.println();
          }
     }

     public static int findLongestLength() {
          int longestLength = rowOfText[0][0].length();
          for (String[] strings : rowOfText) {
               for (int j = 0; j < rowOfText[0].length; j++) {
                    longestLength = Math.max(longestLength, strings[j].length());
               }
          }
          return longestLength;
     }

     public static int findLongestInCol(int col) {
          int longest = 0;
          for (int row = 0; row < rowOfText.length; row++) {
/*            System.out.println("length of[" + row + "][" + col + "] = " + rowOfText[row][col].length());
            System.out.println(rowOfText[row][col] + "*")*/
               longest = Math.max(longest, rowOfText[row][col].length());
          }
          return longest;
     }

     public static int findLongestHeading() {
          int longestLength = headings[0].length();
          for (String heading : headings) {
               longestLength = Math.max(longestLength, heading.length());
          }
          return longestLength;
     }
}