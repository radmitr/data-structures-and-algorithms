package sample1;

import java.util.Arrays;
import java.util.Iterator;

public class Sample2 {
	public static void main(String[] args) {

		int[] array = new int[] { 0, -1, -2, 0, 7, 3 };
		quickSort(array);
		System.out.println(Arrays.toString(array));

	}

	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	public static void quickSort(int[] array, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int[] part = breakPartition(array, lo, hi);
		quickSort(array, lo, part[0]);
		quickSort(array, part[1], hi);
	}

	public static int[] breakPartition(int[] array, int lo, int hi) {
		int i = lo + 1;
		int p = lo;
		int j = hi;
		int g = hi + 1;
		int supportElement = array[lo];
		for (;;) {
			for (; i < hi && array[i] < supportElement;) {
				i++;
			}
			for (; array[j] > supportElement;) {
				j--;
			}
			if (i >= j) {
				if (i == j && array[i] == supportElement) {
					swap(array, i, ++p);
				}
				break;
			}
			swap(array, i, j);
			if (array[i] == supportElement) {
				swap(array, i, ++p);
			}
			if (array[j] == supportElement) {
				swap(array, j, --g);
			}
			i++;
			j--;
		}
		for (int k = lo; k <= p; k++) {
			swap(array, k, j--);
		}
		for (int k = hi; k >= g; k--) {
			swap(array, k, i++);
		}
		return new int[] { j, i };
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
