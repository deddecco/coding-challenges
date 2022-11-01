package personal.y22.m09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SortComparatorTest {
    public static void main(String[] args) {
        String[][] test;
        test = new String[][]{{"1", "4", "2", "4"}, {"3", "7", "9", "8"}, {"2", "4", "3", "6"}, {"7", "4", "6", "5"}, {"5", "4", "2", "7"}};
        String columnToSortBy = getColumnToSortBy();
        Comparator<String[]> comparator = Comparator.comparing(o -> o[Integer.parseInt(columnToSortBy)]);
        Arrays.sort(test, comparator);
        System.out.println(Arrays.deepToString(test));
    }

    private static String getColumnToSortBy() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a column: ");
        return input.nextLine();
    }
}
