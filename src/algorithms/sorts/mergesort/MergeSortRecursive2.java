package algorithms.sorts.mergesort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка слиянием. Рекурсивный алгоритм.
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O(n·ln(n))
 * Требует дополнительно памяти в размере n
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Создается дополнительная последовательность, размер которой равен сортируемой
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
public class MergeSortRecursive2 {

    public static void main(String[] args) {
        String[] array = { "Python", "Ada", "Java", "C", "Fortran" };
        Comparator<String> comp = (a, b) -> a.length() - b.length();
        mergeSort(array, comp);
        System.out.println(Arrays.toString(array));
    }

    public static <T> void mergeSort(T[] array, Comparator<T> comp) {
        T[] support = Arrays.copyOf(array, array.length);
        int startIndex = 0;
        int endIndex = support.length - 1;
        mergeSort(array, support, comp, startIndex, endIndex);
    }

    public static <T> void mergeSort(T[] array, T[] support, Comparator<T> comp, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int middle = startIndex + (endIndex - startIndex) / 2;
        mergeSort(array, support, comp, startIndex, middle);
        mergeSort(array, support, comp, middle + 1, endIndex);
        merge(array, support, comp, startIndex, middle, middle + 1, endIndex);
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
                r++;
            } else if (r > re) {
                array[i] = support[l];
                l++;
            } else if (comp.compare(support[l], support[r]) < 0) {
                array[i] = support[l];
                l++;
            } else {
                array[i] = support[r];
                r++;
            }
        }
    }
}
