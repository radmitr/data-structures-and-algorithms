package sample;

import java.util.Arrays;

public class Sample1 {

	public static void main(String[] args) {

		int[] firstArray = new int[] { 1, 3, 6, 9 };
		int[] secondArray = new int[] { 2, 4, 4, 7 };

		int[] result = merge(firstArray, secondArray);

		System.out.println(Arrays.toString(result));

	}

	public static int[] merge(int[] firstArray, int[] secondArray) {
		int[] result = new int[firstArray.length + secondArray.length];
		int l = 0;
		int r = 0;
		for (int i = 0; i < result.length; i++) {
			if (l >= firstArray.length) {
				result[i] = secondArray[r];
				r += 1;
			} else if (r >= secondArray.length) {
				result[i] = firstArray[l];
				l += 1;
			} else if (firstArray[l] < secondArray[r]) {
				result[i] = firstArray[l];
				l += 1;
			} else {
				result[i] = secondArray[r];
				r += 1;
			}
		}
		return result;
	}

}
