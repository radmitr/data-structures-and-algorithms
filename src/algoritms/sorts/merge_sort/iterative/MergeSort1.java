package algoritms.sorts.merge_sort.iterative;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка слиянием. Итерационный алгоритм
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Создается дополнительная последовательность размер которой равен сортируемой
 * последовательности. Перейти в 2.
 *
 * 2) Устанавливается начальный размер сливаемых последовательностей равный 1. Выполняем
 * попарное слияние соседних подпоследовательностей указанного размера начиная с начала
 * последовательности. В случае если для подпоследовательности нет пары, то слияние
 * производить не нужно (для последовательности нечетной длины). Перейти к 3.
 *
 * 3) Увеличить значение размера в два раза. Если размер больше длинны последовательности
 * закончить алгоритм. В противном случае перейти к 2.
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Сложность по времени в наихудшем случае O(n·ln(n))
 * Требует дополнительно памяти в размере n
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/grydAs8Pi44">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class MergeSort1 {

	public static void main(String[] args) {
        // 1 - array
		int[] array = { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

		sort(array);
		System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        int[] array2 = { 5, 0, -2, 7, 3, -40, 30, -24, 15, 7, 8, 9, 8, 7, 1 };
        System.out.println(Arrays.toString(array2));

        sort(array2);
        System.out.println(Arrays.toString(array2));
	}

	public static void sort(int[] array) {
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
