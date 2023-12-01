package algoritms.sorts.merge_sort.recursive;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка слиянием. Рекурсивный алгоритм
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Сложность по времени в наихудшем случае O(n·ln(n))
 * Требует дополнительно памяти в размере n
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Создается дополнительная последовательность размер которой равен сортируемой
 *    последовательности. Перейти в 2.
 *
 * 2) Последовательность разбивается на две части и для каждой из частей рекурсивно запускается
 *    функция сортировки сначала для левой подпоследовательности, потом для правой. После чего
 *    проводят слияние отсортированных подпоследовательностей. Условием выхода из рекурсии
 *    является размер подпоследовательности равный нулю.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/pjI5tV6OasI">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class MergeSort1 {

    public static void main(String[] args) {
        // 1 - array
        int[] array = { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        mergeSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        int[] array2 = { 5, 0, -2, 7, 3, -40, 30, -24, 15, 7, 8, 9, 8, 7, 1 };
        System.out.println(Arrays.toString(array2));

        mergeSort(array2);
        System.out.println(Arrays.toString(array2));
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
        int middle = startIndex + (endIndex - startIndex) / 2;
        mergeSort(array, support, startIndex, middle);
        mergeSort(array, support, middle + 1, endIndex);
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
