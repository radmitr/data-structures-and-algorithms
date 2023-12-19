package algorithms.sorts.quicksort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Быстрая сортировка. Разбиение Хоара.
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O(n·ln(n))
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Выбрать в последовательности элемент, называемым опорным. Этот элемент выбирается
 *    произвольным образом (автор алгоритма предлагает выбирать первый элемент
 *    последовательности).
 *
 * 2) В последовательности сравнить все остальные элементы с опорным и переставить их в
 *    последовательности так, чтобы разбить ее на две непрерывных области, следующих друг за
 *    другом: «элементы меньшие опорного» и «большие».  Рекурсивно вызвать пункт 1 сначала для
 *    подпоследовательности, где хранятся элементы меньше опорного, после для
 *    подпоследовательности с элементами больше опорного. Условие прекращение рекурсии — это
 *    размер просматриваемой подпоследовательности, равный 1.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/6CkfbqzN0N4">Ссылка на видео</>
 * ------------------------------------------------------------------------------------------------
 */
public class QuickSortHoare {

    public static void main(String[] args) {
        // 1 - array
        int[] array = { 0, 5, -2, 7, 3, -2 };
        System.out.println(Arrays.toString(array));

        quickSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        int[] array2 = { 0, 5, -2, 7, 3, -2, 10, -15, 7, 7, 8, 9, 2, 15, 10, 1 };
        System.out.println(Arrays.toString(array2));

        quickSort(array2);
        System.out.println(Arrays.toString(array2));
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int p = partition(array, lo, hi);
        quickSort(array, lo, p - 1);
        quickSort(array, p + 1, hi);
    }

    public static int partition(int[] array, int lo, int hi) {
        int i = lo + 1;
        int supportElement = array[lo];
        int j = hi;
        while (true) {
            while (i < hi && array[i] < supportElement) {
                i++;
            }
            while (array[j] > supportElement) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(array, i++, j--);
        }
        swap(array, lo, j);
        return j;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
