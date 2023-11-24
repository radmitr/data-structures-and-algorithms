import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		int[] array = new int[] { 5, 0, -2, 7, 3 };
	
		for (int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[minIndex] > array[j]) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				int temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}
		}

		System.out.println(Arrays.toString(array));

	}

}
