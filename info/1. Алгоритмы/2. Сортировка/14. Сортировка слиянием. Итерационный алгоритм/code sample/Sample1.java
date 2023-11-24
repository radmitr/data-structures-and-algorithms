import java.util.Arrays;

public class Sample1 {

	public static void main(String[] args) {

		int[] array = new int[] { 5, 0, -2, 7, 3 };
		mergeSort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void mergeSort(int[] array) {
		int[] supportArray = Arrays.copyOf(array, array.length);
		int n = array.length;
		for (int size = 1; size < n; size *= 2) {
			for (int j = 0; j < n - size; j += 2 * size) {
				merge(array, supportArray, j, j + size - 1, j + size, Math.min(j + 2 * size - 1, n - 1));
			}
		}
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
