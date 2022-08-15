public class DigitCounter {
    public static int countDigits(int[] nums) {
        int[] countOfDigits = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != 0) {
                nums[i] /= 10;
                countOfDigits[i]++;
            }
        }

        int evenNumDigits = 0;
        for (int countDigits : countOfDigits) {
            if (countDigits % 2 == 0) {
                evenNumDigits++;
            }
        }
        return evenNumDigits;
    }
}
