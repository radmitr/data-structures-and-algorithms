package algoritms.sorts.merge_sort.recursive;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка слиянием. Рекурсивный алгоритм
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Создается дополнительная последовательность размер которой равен сортируемой
 * последовательности. Перейти в 2.
 *
 * 2) Последовательность разбивается на две части и для каждой из частей рекурсивно запускается
 * функция сортировки сначала для левой подпоследовательности, потом для правой. После чего
 * проводят слияние отсортированных подпоследовательностей. Условием выхода из рекурсии
 * является размер подпоследовательности равный нулю.
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Сложность по времени в наихудшем случае O(n·ln(n))
 * Требует дополнительно памяти в размере n
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/pjI5tV6OasI">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class MergeSort1 {

	public static void main(String[] args) {
		int[] array = new int[] { 5, 0, -2, 7, 3 };
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void sort(int[] array) {
		int[] support = Arrays.copyOf(array, array.length);
		int startIndex = 0;
		int stopIndex = support.length - 1;
		sort(array, support, startIndex, stopIndex);
	}

	public static void sort(int[] array, int[] support, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return;
		}
		int middle = startIndex + (endIndex - startIndex) / 2;
		sort(array, support, startIndex, middle);
		sort(array, support, middle + 1, endIndex);
		merge(array, support, startIndex, middle, middle + 1, endIndex);
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
				r++;
			} else if (r > re) {
				array[i] = supportArray[l];
				l++;
			} else if (supportArray[l] < supportArray[r]) {
				array[i] = supportArray[l];
				l++;
			} else {
				array[i] = supportArray[r];
				r++;
			}
		}
	}
}
