package personal.y22.m08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoSumTest {
    @Test
    void ensureSumIs3() {
        int sum = TwoSum.sumOfTwo(1, 2);
        assertEquals(sum, 3);
    }

    @Test
    void ensureSumIs50() {
        int sum = TwoSum.sumOfTwo(27, 23);
        assertEquals(sum, 50);
    }

    @Test
    void ensureSumIs550() {
        int sum = TwoSum.sumOfTwo(327, 223);
        assertEquals(sum, 550);
    }

    // getting close to 1/2 the max value of an integer
    @Test
    void ensureSumIs1B() {
        int sum = TwoSum.sumOfTwo(500000000, 500000000);
        assertEquals(sum, 1000000000);
    }

    // should sum to exactly Integer.MAX_VALUE **WITHOUT** OVERFLOWING
    @Test
    void ensureSumIsMax() {
        int sum = TwoSum.sumOfTwo(2147483640, 7);
        assertEquals(sum, Integer.MAX_VALUE);
    }

    // should be 1 more than MAX_VALUE, triggering an overflow to MIN_VALUE
    @Test
    void ensureSumIsMin() {
        int sum = TwoSum.sumOfTwo(2147483640, 8);
        assertEquals(sum, Integer.MIN_VALUE);
    }

    // should be 1 less than MIN_VALUE, triggering an underflow to MAX_VALUE
    @Test
    void ensureSumIsMaxAgain() {
        int sum = TwoSum.sumOfTwo(-2147483648, -1);
        assertEquals(sum, Integer.MAX_VALUE);
    }
}

