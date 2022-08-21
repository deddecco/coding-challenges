package personal.y22.m08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiArraySorterTest {
    @Test
    void ensureBandAgetsSortedToAandB() {
        MultiArraySorter sorter = new MultiArraySorter();

        String[][] sampleInput = {
                {"222", "B"},
                {"333", "A"},
        };

        sorter.sortByRow(sampleInput);

        assertEquals("A", sampleInput[0][1]);
    }

    @Test
    void ensureABCstaysABC() {
        MultiArraySorter sorter = new MultiArraySorter();

        String[][] sampleInput = {
                {"222", "A"},
                {"111", "B"},
                {"333", "C"},
        };

        sorter.sortByRow(sampleInput);

        assertEquals("A", sampleInput[0][1]);
        assertEquals("B", sampleInput[1][1]);
        assertEquals("C", sampleInput[2][1]);
    }

    @Test
    void ensureCBAbecomesABC() {
        MultiArraySorter sorter = new MultiArraySorter();

        String[][] sampleInput = {
                {"222", "C"},
                {"111", "B"},
                {"333", "A"},
        };

        sorter.sortByRow(sampleInput);

        assertEquals("A", sampleInput[0][1]);
        assertEquals("B", sampleInput[1][1]);
        assertEquals("C", sampleInput[2][1]);
    }
}