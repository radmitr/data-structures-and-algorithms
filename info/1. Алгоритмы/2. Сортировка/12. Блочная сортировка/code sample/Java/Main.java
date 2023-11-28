import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array = new int[] { 12, 2, 4, 7, 5, 10, 8, 9, 11, 9 };

		bucketSort(array, 5);
		System.out.println(Arrays.toString(array));

	}

	public static void insertionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int pasteElement = array[i];
			int j;
			for (j = i; j > 0; j--) {
				if (array[j - 1] <= pasteElement) {
					break;
				}
				array[j] = array[j - 1];
			}
			array[j] = pasteElement;
		}
	}

	public static int[] findMinMax(int[] array) {
		if (array.length == 0) {
			return new int[] { 0, 0 };
		}
		int min = array[0];
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
			if (array[i] > max) {
				max = array[i];
			}
		}
		return new int[] { min, max };
	}

	public static int[] calculateBlockSize(int[] array, int n) {
		int[] blockSize = new int[n];
		int[] minMax = findMinMax(array);
		for (int i = 0; i < array.length; i++) {
			int blockNumber = (int) (1L * n * (array[i] - minMax[0]) / (minMax[1] - minMax[0] + 1));
			blockSize[blockNumber] += 1;
		}
		return blockSize;
	}

	public static void bucketSort(int[] array, int n) {
		int[] minMax = findMinMax(array);
		if (minMax[0] == minMax[1]) {
			return;
		}
		int[][] buckets = new int[n][];
		int[] addIndex = new int[n];
		int[] blockSize = calculateBlockSize(array, n);
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new int[blockSize[i]];
		}
		for (int i = 0; i < array.length; i++) {
			int blockNumber = (int) (1L * n * (array[i] - minMax[0]) / (minMax[1] - minMax[0] + 1));
			buckets[blockNumber][addIndex[blockNumber]++] = array[i];
		}
		for (int[] bucket : buckets) {
			if (bucket.length <= 32) {
				insertionSort(bucket);
			} else {
				bucketSort(bucket, n);
			}
		}
		int index = 0;
		for (int[] bucket : buckets) {
			for (int element : bucket) {
				array[index++] = element;
			}
		}
	}
}
