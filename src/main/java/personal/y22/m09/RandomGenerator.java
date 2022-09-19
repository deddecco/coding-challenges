package personal.y22.m09;

import java.util.Arrays;
import java.util.Random;

import static java.util.Arrays.*;

public class RandomGenerator {
    public static void main(String[] args) {
        Random random = new Random();
        int[] psalms = new int[10];
        for (int i = 0; i < 10; i++) {
            psalms[i] = random.nextInt(150);
        }
        sort(psalms);
        System.out.println(Arrays.toString(psalms));
    }
}
