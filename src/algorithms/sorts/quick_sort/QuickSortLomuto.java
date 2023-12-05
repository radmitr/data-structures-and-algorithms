package algorithms.sorts.quick_sort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Быстрая сортировка. Разбиение Ломуто.
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O(n·ln(n))
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) В качестве опорного элемента выбирается первый элемент последовательности
 *    (supportElement). Объявляется две переменных для хранения индексов (в дальнейшем i и j).
 *    Значение j равно первому индексу в подпоследовательности. Переходим к пункту 1.
 *
 * 2) Начиная от начала последовательности проводиться поиск элемента для которого
 *    sequence[i] < supportElement. Если такой элемент найден, то увеличиваем значение j на одну
 *    единицу, производим обмен элементов которые стоят на i и j индексе.
 *
 * 3) Проводим обмен первого элемента последовательности и элемента на индексе j. Возвращаем
 *    значение индекса j и заканчиваем текущее разбиение.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/G6v8ocbDSoo">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class QuickSortLomuto {

    public static void main(String[] args) {
        // 1 - array
        int[] array = { 0, 5, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        quickSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        int[] array2 = { 1, -1, 2, -3, 4, 2 };
        System.out.println(Arrays.toString(array2));

        quickSort(array2);
        System.out.println(Arrays.toString(array2));
        System.out.println();

        // 3 - array3
        int[] array3 = { 0, 5, -2, 7, 3, 10, -9, 25, 4, 4, 5, 6, 7, 6, 5, 4, 23, 15, -1};
        System.out.println(Arrays.toString(array3));

        quickSort(array3);
        System.out.println(Arrays.toString(array3));
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
        int j = lo;
        int supportElement = array[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (array[i] < supportElement) {
                j++;
                swap(array, i, j);
            }
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
