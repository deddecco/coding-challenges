package personal.y22.m08;
public class RunningSum {
    public static int arrayRunningSum(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }
}