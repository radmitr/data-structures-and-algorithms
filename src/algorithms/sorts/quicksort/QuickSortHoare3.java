package algorithms.sorts.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * ------------------------------------------------------------------------------------------------
 * Быстрая сортировка. Оптимизация.
 * ------------------------------------------------------------------------------------------------
 * Модификация алгоритма быстрой сортировки
 *
 * Для увеличения оптимальности алгоритма можно применить следующую модификацию.
 * Рекурсивное разбиения последовательности до размера 1 заменить переходом в режим
 * сортировки вставками, в случае если размер подпоследовательности меньше или равен
 * определенному значению. Оптимальным называется значения 16, 32, 64. При это сама реализация
 * остается довольно простой.
 *
 * Наиболее оптимальным значением размера подпоследовательности, при которой нужно выполнять
 * переход к сортировке вставкой, является 32.
 * ------------------------------------------------------------------------------------------------
 * Проблема выбора опорного элемента
 *
 * Производительность алгоритма быстрой сортировки значительно зависит от выбора опорного
 * элемента. Так если, например, последовательность уже отсортирована, то выбор первого
 * элемента приведет к квадратичной сложности. Так как любая последовательность содержит
 * отсортированные подпоследовательности, то вопрос о выборе опорного элемента довольно важен.
 *
 * Одним из самых простых способов выбора опорного элемента является выбор медианы из
 * трех элементов подпоследовательности (первого, последнего, и элемента из середины
 * подпоследовательности).
 *
 * Медиана в математической статистике — число, характеризующее выборку (например, набор
 * чисел). Если все элементы выборки различны, то медиана — это такое число, что половина из
 * элементов выборки больше него, а другая половина меньше.
 * Для выборки из 3-х элементов это среднее значение.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/_oH2twTOs0k">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class QuickSortHoare3 {

    public static void main(String[] args) {
        // 1 - array
        int[] array = { 0, 5, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        quickSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        Random random = new Random();
        int arraySize = 1_000;
        int bound = 500;
        int[] array2 = new int[arraySize];

        for (int i = 0; i < array2.length; i++) {
            array2[i] = random.nextInt(bound + 1);
        }
        System.out.println(Arrays.toString(array2));

        quickSort(array2);
        System.out.println(Arrays.toString(array2));
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int lo, int hi) {
        if (hi - lo <= 32) {
            insertionSort(array, lo, hi);
            return;
        }
        int med = median(array, lo, lo + (hi - lo) / 2, hi);
        swap(array, lo, med);
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

    public static void insertionSort(int[] array, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            int pasteElement = array[i];
            int j;
            for (j = i; j > begin; j--) {
                if (array[j - 1] <= pasteElement) {
                    break;
                }
                array[j] = array[j - 1];
            }
            array[j] = pasteElement;
        }
    }

    public static int median(int[] array, int lo, int mid, int hi) {
        if (array[lo] <= array[mid]) {
            if (array[mid] <= array[hi]) {
                return mid;
            }
        } else {
            if (array[lo] <= array[hi]) {
                return lo;
            }
        }
        return hi;
    }
}
