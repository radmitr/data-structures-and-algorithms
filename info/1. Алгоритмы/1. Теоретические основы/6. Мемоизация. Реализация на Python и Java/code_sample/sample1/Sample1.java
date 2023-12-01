package sample1;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Sample1 {

    public static Map<Integer, BigInteger> mem = new HashMap<>();

    public static void main(String[] args) {

        BigInteger fac = factorial(5);
        System.out.println(fac);
        fac = factorial(5);
    }

    public static BigInteger factorial(int number) {
        BigInteger fact = mem.get(number);
        if (fact != null) {
            return fact;
        } else {
            fact = BigInteger.ONE;
            for (int i = 1; i <= number; i++) {
                fact = fact.multiply(BigInteger.valueOf(i));
            }
            mem.put(number, fact);
            return fact;
        }
    }
}
