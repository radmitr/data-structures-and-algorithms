
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		int[] array = new int[] { 5, 0, -2, 7, 3 };

		coctailSort(array);

		System.out.println(Arrays.toString(array));

	}

	public static void coctailSort(int[] sequince) {
		int left = 0;
		int right = sequince.length - 1;
		int control = right;
		for (; left < right;) {
			for (int i = left; i < right; i++) {
				if (sequince[i] > sequince[i + 1]) {
					swap(sequince, i, i + 1);
					control = i;
				}
			}
			right = control;
			for (int i = right; i > left; i--) {
				if (sequince[i] < sequince[i - 1]) {
					swap(sequince, i, i - 1);
					control = i;
				}
			}
			left = control;
		}
	}

	public static void swap(int[] sequince, int i, int j) {
		int temp = sequince[i];
		sequince[i] = sequince[j];
		sequince[j] = temp;
	}

}
