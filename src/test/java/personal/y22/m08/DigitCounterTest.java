package personal.y22.m08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitCounterTest {

    @Test
    void ensure_zero_when_all_integers_have_odd_digit_counts() {
        int evenCount = DigitCounter.countDigits(new int[]{1, 123, 12345});

        assertEquals(0, evenCount);
    }

    @Test
    void ensure_single_integer_is_counted() {
        int evenCount = DigitCounter.countDigits(new int[]{1, 12, 123});

        assertEquals(1, evenCount);
    }

    @Test
    void ensure_all_integers_are_counted() {
        int evenCount = DigitCounter.countDigits(new int[]{12, 1234, 123456});

        assertEquals(3, evenCount);
    }
}