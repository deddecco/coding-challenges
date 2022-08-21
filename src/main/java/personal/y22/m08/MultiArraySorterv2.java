package personal.y22.m08;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Arrays.*;
import static java.util.Comparator.comparing;

public class MultiArraySorterv2 {
    static void sortByRow(String[][] arrayOfRecords) {
        // arrayOfRecords contains records
        // record = array of 4 strings in same order every time (sequence is always second)
        // rearrange the order of the records so the sequences are sorted
        sort(arrayOfRecords, comparing(o -> o[1]));
    }

    public static void main(String[] args) {
        String[][] testArray = {
                {"Hello", "4"},
                {"testing", "2"},
                {"apple", "1"}
        };
        sortByRow(testArray);
        System.out.println((deepToString(testArray)));
    }
}
