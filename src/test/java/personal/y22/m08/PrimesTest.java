package personal.y22.m08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrimesTest {
    @Test
    void ensure2isPrime() {
        boolean twoIsPrime = Primes.isPrime(2);
        assertTrue(twoIsPrime);
    }

    @Test
    void ensure3isPrime() {
        boolean threeIsPrime = Primes.isPrime(3);
        assertTrue(threeIsPrime);
    }

    @Test
    void ensure4isNOTPrime() {
        boolean fourIsNotPrime = Primes.isPrime(4);
        assertFalse(fourIsNotPrime);
    }

    @Test
    void ensure5isPrime() {
        boolean fiveIsPrime = Primes.isPrime(5);
        assertTrue(fiveIsPrime);
    }

    @Test
    void ensure6isNOTPrime() {
        boolean sixIsNotPrime = Primes.isPrime(6);
        assertFalse(sixIsNotPrime);
    }

    @Test
    void ensure7isPrime() {
        boolean sevenIsPrime = Primes.isPrime(7);
        assertTrue(sevenIsPrime);
    }

    @Test
    void ensure8isNOTPrime() {
        boolean eightIsNotPrime = Primes.isPrime(8);
        assertFalse(eightIsNotPrime);
    }

    @Test
    void ensure9isNOTPrime() {
        boolean nineIsNotPrime = Primes.isPrime(9);
        assertFalse(nineIsNotPrime);
    }

    @Test
    void ensure10isNOTPrime() {
        boolean tenIsNotPrime = Primes.isPrime(10);
        assertFalse(tenIsNotPrime);
    }

    @Test
    void ensure11isPrime() {
        boolean elevenIsPrime = Primes.isPrime(11);
        assertTrue(elevenIsPrime);
    }

    @Test
    void ensure12isNOTPrime() {
        boolean twelveIsNotPrime = Primes.isPrime(12);
        assertFalse(twelveIsNotPrime);
    }
}
