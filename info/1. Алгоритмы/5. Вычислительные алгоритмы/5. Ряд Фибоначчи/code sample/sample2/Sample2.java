package sample2;

import java.math.BigInteger;

public class Sample2 {

    public static void main(String[] args) {
        
        for (int i = 0; i <= 10; i++) {
            System.out.println(i+" -> "+fibonacciSequince(i));    
        }

    }

    public static BigInteger fibonacciSequince(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1) {
            return BigInteger.ONE;
        }
        return fibonacciSequince(n - 1).add(fibonacciSequince(n - 2));
    }

}
