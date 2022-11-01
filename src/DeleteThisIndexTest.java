package personal.y22.m09;

public class DeleteThisIndexTest {
    private static String[] nums = {"1", "2", "3", "4", "5", "6", "7", "7", "8", "9", "10"};

    private static int numRecords = nums.length;

    public static void main(String[] args) {
         deleteThisIndex(6);
    }

    private static void deleteThisIndex(int indexToDelete) {
        String[] newNums = new String[numRecords - 1];
        int i = 0;
        while (i < indexToDelete) {
            newNums[i] = nums[i];
            i++;
        }
        i = indexToDelete + 1;
        while (i < numRecords) {
            newNums[i - 1] = nums[i];
            i++;
        }
        numRecords--;
    }
}
