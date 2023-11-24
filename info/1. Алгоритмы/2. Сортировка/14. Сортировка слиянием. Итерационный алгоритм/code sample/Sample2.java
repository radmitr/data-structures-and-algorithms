import java.util.Arrays;
import java.util.Comparator;

public class Sample2 {

	public static void main(String[] args) {

		String[] array = new String[] { "Python", "Ada", "Java", "C", "Fortran" };
		Comparator<String> comp = (a, b) -> a.length() - b.length();
		mergeSort(array, comp);
		System.out.println(Arrays.toString(array));

	}

	public static <T> void mergeSort(T[] array, Comparator<T> comp) {
		T[] support = Arrays.copyOf(array, array.length);
		int n = array.length;
		for (int size = 1; size < n; size *= 2) {
			for (int j = 0; j < n - size; j += 2 * size) {
				merge(array, support, comp, j, j + size - 1, j + size, Math.min(j + 2 * size - 1, n - 1));
			}
		}
	}

	public static <T> void merge(T[] array, T[] support, Comparator<T> comp, int ls, int le, int rs, int re) {
		for (int i = ls; i <= re; i++) {
			support[i] = array[i];
		}
		int l = ls;
		int r = rs;
		for (int i = ls; i <= re; i++) {
			if (l > le) {
				array[i] = support[r];
				r += 1;
			} else if (r > re) {
				array[i] = support[l];
				l += 1;
			} else if (comp.compare(support[l], support[r]) < 0) {
				array[i] = support[l];
				l += 1;
			} else {
				array[i] = support[r];
				r += 1;
			}
		}
	}

}
