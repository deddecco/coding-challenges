package personal.y22.m09;

import java.util.Arrays;

public class CopyClass {
    public static void main(String[] args) {
        String[][] first = {
                {"Code", "Letter"},
                {"Alpha", "A"},
                {"Bravo", "B"},
                {"Charlie", "C"},
                {"Delta", "D"},
                {"Echo", "E"},
                {"Foxtrot", "F"}};
        String[][] second = makeCopy(first, "Code");

        System.out.println("Col containing Code  = " + whereIsCol(first, "Code"));
        System.out.println("Col containing Letter = " + whereIsCol(first, "Letter"));

        System.out.println(Arrays.deepToString(first));
        System.out.println(Arrays.deepToString(second));
    }

    public static int whereIsCol(String[][] array, String colName) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][0].equals(colName)) {
                return i;
            }
        }
        return -1;
    }

    public static String[][] makeCopy(String[][] original, String colToCopy) {
        String[][] copy = new String[original.length][original[0].length];
        String[] copyOfColumn = new String[original[0].length];
        for (int i = 0; i < copyOfColumn.length; i++) {
            copyOfColumn[whereIsCol(original, colToCopy)] = original[i][whereIsCol(original, colToCopy)];
        }
        addToCopy(copyOfColumn, copy);
        return copy;

    }

    public static void addToCopy(String[] record, String[][] destination) {
        int numrecords = 0;
        destination[numrecords] = record;
    }
}
