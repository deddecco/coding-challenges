package personal.y22.m08;
import java.util.Arrays;
import java.util.Comparator;

public class MultiArraySorter {
    static void sortByRow(String[][] arrayOfRecords) {
        // arrayOfRecords contains records
        // record = array of 4 strings in same order every time (sequence is always second)
        // rearrange the order of the records so the sequences are sorted
        Comparator<? super String[]> cr = (o1, o2) -> o1[1].compareTo(o2[1]);
        Arrays.sort(arrayOfRecords, cr);
    }

    public static void main(String[] args) {
        String[][] testArray = {
                {"Hello", "4"},
                {"testing", "2"},
                {"apple", "1"}
        };
        sortByRow(testArray);
        System.out.println((Arrays.deepToString(testArray)));
    }
}