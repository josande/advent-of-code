package utils;

public class MathTools {
    public static long getSmallestPrimeFactor(long number) {
        for(int i = 2; i<=Math.sqrt(number); i++) {
            if (number % i == 0) return i;
        }
        return number;
    }
    public static boolean isPrime(long number) {
        for(int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

}
