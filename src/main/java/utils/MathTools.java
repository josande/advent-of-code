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

    public static int getGcd(int n1, int n2) {
        if (n1==0 || n2==0) return 1;
        n1 = Math.abs(n1);
        n2 = Math.abs(n2);
        while(n1 != n2) {
            if(n1 > n2)
                n1 -= n2;
            else
                n2 -= n1;
        }
        return n1;
    }

}
