package personal.y22.m09;

import java.util.Arrays;

public class DeleteMe {
    public static void main(String[] args) {
        String test = "abc\ndef";
        String[] elems = test.split("ab\\s\\tc");

        System.out.printf("Hello, %s!", Arrays.toString(elems));
    }
}
