package sample4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sample4 {
    static Map<Integer, BigInteger> mem = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(representationOfZeckendorf(BigInteger.valueOf(100)));
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

    public static List<BigInteger> representationOfZeckendorf(BigInteger number) {
        List<BigInteger> roz = new ArrayList<>();
        int k = 0;
        for (; fibonacciSequince(k).compareTo(number) <= 0;) {
            k = k + 1;
        }
        for (; number.compareTo(BigInteger.ZERO) > 0;) {
            if (fibonacciSequince(k).compareTo(number) <= 0) {
                BigInteger n = fibonacciSequince(k);
                roz.add(n);
                number = number.subtract(n);
            }
            k = k - 1;
        }
        return roz;
    }
}
