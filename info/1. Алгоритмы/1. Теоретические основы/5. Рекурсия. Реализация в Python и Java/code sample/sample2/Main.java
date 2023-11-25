package sample2;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {

		int number = 50000;

		System.out.println(factorial(number));

	}

	public static BigInteger factorial(int number) {
		if (number <= 1) {
			return new BigInteger("1");
		}
		return BigInteger.valueOf(number).multiply(factorial(number - 1));
	}

}
