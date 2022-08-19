package personal.y22.m08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunningSumTest {

    // ascending arrays
    @Test
    void ensureSumIs3() {
        int runningSum = RunningSum.arrayRunningSum(new int[]{1, 2});
        assertEquals(runningSum, 3);
    }

    @Test
    void ensureSumIs6() {
        int runningSum = RunningSum.arrayRunningSum(new int[]{1, 2, 3});
        assertEquals(runningSum, 6);
    }

    @Test
    void ensureSumIs10() {
        int runningSum = RunningSum.arrayRunningSum(new int[]{1, 2, 3, 4});
        assertEquals(runningSum, 10);
    }

    @Test
    void ensureSumIs15() {
        int runningSum = RunningSum.arrayRunningSum(new int[]{1, 2, 3, 4, 5});
        assertEquals(runningSum, 15);
    }

    // descending arrays
    @Test
    void ensureSumIs21() {
        int runningSum = RunningSum.arrayRunningSum(new int[]{6, 5, 4, 3, 2, 1});
        assertEquals(runningSum, 21);
    }

    // oscillating order arrays
    @Test
    void ensureSumIs28() {
        int runningSum = RunningSum.arrayRunningSum(new int[]{1, 7, 2, 6, 3, 5, 4});
        assertEquals(runningSum, 28);
    }

    // empty array
    @Test
    void ensureSumIs0() {
        int runningSum = RunningSum.arrayRunningSum(new int[]{});
        assertEquals(runningSum, 0);
    }

    // negative numbers in array
    @Test
    void ensureSumIsNeg15() {
        int runningSum = RunningSum.arrayRunningSum(new int[]{-5, -4, -3, -2, -1});
        assertEquals(runningSum, -15);
    }
}
