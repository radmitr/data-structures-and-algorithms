package algorithms.sorts.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * ------------------------------------------------------------------------------------------------
 * Быстрая сортировка. Трехчастное разбиение.
 * ------------------------------------------------------------------------------------------------
 * Трехчастное разбиение
 *
 * На практике в большинстве случаев в сортируемой последовательности содержатся элементы
 * с одинаковыми ключами сортировки. Но, при разбиении последовательности на две части,
 * элементы, равные опорному элементу (по сути в дальнейшей сортировке не нуждающиеся), все
 * равно попадают в левую или правую часть и подвергаются сортировке. Этой проблемы можно
 * избежать если разбивать последовательность на три части (элементы меньше опорного, элементы
 * больше опорного, элементы равны опорному) и, для дальнейшей сортировки, вызывать только
 * подпоследовательности, с элементами меньше опорного и элементами больше опорного.
 * ================================================================================================
 * Трехчастное разбиение Дейкстры
 *
 * Один из простых для реализаций алгоритм троичного разбиения был
 * предложен Дейкстрой. Он предложил использовать три индекса lt, i, gt.
 * После обработки, последовательности a[lo..lt-1] меньше опорного
 * элемента, a[lt..gt-1] равны, a[gt..hi] больше.
 * ------------------------------------------------------------------------------------------------
 * Разбиение Дейкстры
 *
 * 1) В качестве опорного элемента выбирается первый элемент последовательности (supportElement).
 *    Объявляется переменные для хранения индексов (в дальнейшем i=lo+1, lt=lo, gt=hi).
 *
 * 2) Выполняем проход по последовательности. Если элемент последовательности a[i]:
 *      ● Меньше опорного. Обмен a[lt], a[i] увеличиваем lt и i на единицу
 *      ● Больше опорного. Обмен a[gt], a[i] уменьшаем gt на единицу
 *      ● Равен опорному. Увеличиваем i на единицу
 *    Если i > gt, переходим к пункту 3.
 *
 * 3) Вернуть lt и gt
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/Bp35SUE6MDU">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class QuickSortDijkstra {

    public static void main(String[] args) {
        // 1 - array
        int[] array = { 0, 5, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        quickSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        int[] array2 = { 0, 5, -2, 7, 0, 3 };
        System.out.println(Arrays.toString(array2));

        quickSort(array2);
        System.out.println(Arrays.toString(array2));
        System.out.println();

        // 3 - array3
        int[] array3 = { 0, 5, -2, 7, 0, 3, 3, 0, -5, -6, 1, 2, 3, 0, -1, -1, -1 };
        System.out.println(Arrays.toString(array3));

        quickSort(array3);
        System.out.println(Arrays.toString(array3));
        System.out.println();

        // 4 - array4
        Random random = new Random();
        int arraySize = 1_000;
        int bound = 10;
        int[] array4 = new int[arraySize];

        for (int i = 0; i < array4.length; i++) {
            array4[i] = random.nextInt(bound + 1);
        }
        System.out.println(Arrays.toString(array4));

        quickSort(array4);
        System.out.println(Arrays.toString(array4));
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int[] part = partition(array, lo, hi);
        quickSort(array, lo, part[0] - 1);
        quickSort(array, part[1] + 1, hi);
    }

    public static int[] partition(int[] array, int lo, int hi) {
        int i = lo + 1;
        int lt = lo;
        int gt = hi;
        int supportElement = array[lo];
        while (i <= gt) {
            if (array[i] < supportElement) {
                swap(array, i, lt);
                i++;
                lt++;
            } else if (array[i] > supportElement) {
                swap(array, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        return new int[] { lt, gt };
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
