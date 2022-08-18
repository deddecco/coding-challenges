package personal.y22.m08;

public class Primes {
    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 2; i < 100; i++) {
            System.out.println("isPrime(" + i + ") = " + isPrime(i));
        }
    }
}
