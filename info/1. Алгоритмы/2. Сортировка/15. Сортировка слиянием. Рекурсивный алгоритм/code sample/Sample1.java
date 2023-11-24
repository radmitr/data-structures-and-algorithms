import java.util.Arrays;

public class Sample1 {

	public static void main(String[] args) {
		int[] array = new int[] { 5, 0, -2, 7, 3 };
		mergeSort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void mergeSort(int[] array) {
		int[] support = Arrays.copyOf(array, array.length);
		int startIndex = 0;
		int stopIndex = support.length - 1;
		mergeSort(array, support, startIndex, stopIndex);
	}

	public static void mergeSort(int[] array, int[] support, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return;
		}
		int h = startIndex + (endIndex - startIndex) / 2;
		mergeSort(array, support, startIndex, h);
		mergeSort(array, support, h + 1, endIndex);
		merge(array, support, startIndex, h, h + 1, endIndex);
	}

	public static void merge(int[] array, int[] supportArray, int ls, int le, int rs, int re) {
		for (int i = ls; i <= re; i++) {
			supportArray[i] = array[i];
		}
		int l = ls;
		int r = rs;
		for (int i = ls; i <= re; i++) {
			if (l > le) {
				array[i] = supportArray[r];
				r += 1;
			} else if (r > re) {
				array[i] = supportArray[l];
				l += 1;
			} else if (supportArray[l] < supportArray[r]) {
				array[i] = supportArray[l];
				l += 1;
			} else {
				array[i] = supportArray[r];
				r += 1;
			}
		}
	}

}
