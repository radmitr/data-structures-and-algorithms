package sample1;

import java.math.BigInteger;

public class Sample1 {

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " -> " + fibonacciSequince(i));
        }
    }

    public static BigInteger fibonacciSequince(int n) {
        BigInteger result = BigInteger.ZERO;
        BigInteger next = BigInteger.ONE;
        int currentIndex = 0;
        for (; currentIndex < n;) {
            next = result.add(next);
            result = next.subtract(result);
            currentIndex++;
        }
        return result;
    }

}
