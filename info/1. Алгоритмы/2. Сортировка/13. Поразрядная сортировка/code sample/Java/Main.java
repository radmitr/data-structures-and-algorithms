import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		int[] numbers = new int[] { 121, 5, 24, 9, 32 };
		int[] result = radixSort(numbers);
		System.out.println(Arrays.toString(result));
	}

	public static int numberOfDigits(int number) {
		int i = 1;
		long n = 10;
		for (; number >= n;) {
			i++;
			n *= 10;
		}
		return i;
	}

	public static int findMaxNumberOfDigits(int[] numbers) {
		int result = 1;
		for (int i = 0; i < numbers.length; i++) {
			int digits = numberOfDigits(numbers[i]);
			if (digits > result) {
				result = digits;
			}
		}
		return result;
	}

	public static int getDigit(int number, int devider) {
		return number % (devider * 10) / (devider);
	}

	public static int[] findMinMaxKey(int[] numbers, int devider) {
		int minKey = getDigit(numbers[0], devider);
		int maxKey = minKey;
		for (int number : numbers) {
			int digit = getDigit(number, devider);
			if (digit < minKey) {
				minKey = digit;
			}
			if (digit > maxKey) {
				maxKey = digit;
			}
		}
		return new int[] { minKey, maxKey };
	}

	public static int[] countSort(int[] numbers, int devider) {
		int[] minMaxKey = findMinMaxKey(numbers, devider);
		int minKey = minMaxKey[0];
		int maxKey = minMaxKey[1];
		int n = maxKey - minKey + 1;
		int[] support = new int[n];
		for (int number : numbers) {
			support[getDigit(number, devider) - minKey] += 1;
		}
		int size = numbers.length;
		for (int i = support.length - 1; i >= 0; i--) {
			size -= support[i];
			support[i] = size;
		}
		int[] result = new int[numbers.length];
		for (int number : numbers) {
			result[support[getDigit(number, devider) - minKey]] = number;
			support[getDigit(number, devider) - minKey] += 1;
		}
		return result;
	}

	public static int[] radixSort(int[] numbers) {
		int maxNumberOfDigits = findMaxNumberOfDigits(numbers);
		int devider = 1;
		for (int i = 0; i < maxNumberOfDigits; i++) {
			numbers = countSort(numbers, devider);
			devider *= 10;
		}
		return numbers;
	}

}
