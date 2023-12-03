package sample3;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Sample3 {

    static Map<Integer, BigInteger> mem = new HashMap<>();

    public static void main(String[] args) {

        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " -> " + fibonacciSequince(i));
        }
    }

    public static BigInteger fibonacciSequince(int n) {
        BigInteger result = mem.get(n);
        if (result != null) {
            return result;
        } else if (n == 0) {
            mem.put(0, BigInteger.ZERO);
        } else if (n == 1) {
            mem.put(1, BigInteger.ONE);
        } else {
            mem.put(n, fibonacciSequince(n - 1).add(fibonacciSequince(n - 2)));
        }
        return mem.get(n);
    }

}
