package personal.y22.m08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static personal.y22.m08.MatrixTools.*;

class MatrixToolsTest {

    @Test
    void ensureDotProductIs83() {
        int[] firstArray = {1, 3, 4, 5};
        int[] secondArray = {3, 5, 10, 5};
        int dot = calculateDotProduct(firstArray, secondArray);
        assertEquals(dot, 83);
    }
}
