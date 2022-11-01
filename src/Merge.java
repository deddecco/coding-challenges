package personal.y22.m09;

public class Merge {
    public static void main(String[] args) {
        }

    public static String[] mergeArrays(String[] array1, String[] array2, String[] array3) {
        String[] merged = new String[array1.length + array2.length + array3.length];

        int start1 = 0;
        int finish1 = array1.length - 1;

        int start2 = finish1 + 1;
        int finish2 = start2 + array2.length - 1;

        int start3 = finish2 + 1;
        int finish3 = start3 + array3.length - 1;

        System.arraycopy(array1, 0, merged, start1, finish1 + 1 - start1);
        if (finish2 + 1 - start2 >= 0) {
            System.arraycopy(array2, 0, merged, start2, finish2 + 1 - start2);
        }
        if (finish3 + 1 - start3 >= 0) {
            System.arraycopy(array3, 0, merged, start3, finish3 + 1 - start3);
        }
        return merged;

    }

    public static String[] mergeArrays(String[] array1, String[] array2) {
        String[] merged = new String[array1.length + array2.length];

        int start1 = 0;
        int finish1 = array1.length - 1;

        int start2 = finish1 + 1;
        int finish2 = start2 + array2.length - 1;

        System.arraycopy(array1, start1, merged, start1, finish1 + 1 - start1);
        if (finish2 + 1 - start2 >= 0) {
            System.arraycopy(array2, 0, merged, start2, finish2 + 1 - start2);
        }

        return merged;
    }

}