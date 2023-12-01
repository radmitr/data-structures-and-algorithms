package sample2;

import java.math.BigInteger;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Function<Integer, BigInteger> fact = Main::factorial;

        fact = Memoization.getMemoizationFunction(fact);

        System.out.println(fact.apply(5));
        System.out.println(fact.apply(5));

    }

    public static BigInteger factorial(int number) {
        BigInteger fact = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
}
