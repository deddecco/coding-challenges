package personal.y22.m08;

public class TestSub {
    public static void main(String[] args) {
        String test = "AAACTGATATAA";
        String testMinusLast = test.substring(0, test.length() - 1);
        testMinusLast = testMinusLast.substring(0, testMinusLast.length() - 1);
        System.out.println(testMinusLast);
    }
}
