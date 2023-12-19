package algorithms.sorts.mergesort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка слиянием. Итерационный алгоритм.
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
 * 2) Устанавливается начальный размер сливаемых последовательностей равный 1. Выполняем
 *    попарное слияние соседних подпоследовательностей указанного размера начиная с начала
 *    последовательности. В случае если для подпоследовательности нет пары, то слияние
 *    производить не нужно (для последовательности нечетной длины). Перейти к 3.
 *
 * 3) Увеличить значение размера в два раза. Если размер больше длинны последовательности
 *    закончить алгоритм. В противном случае перейти к 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/grydAs8Pi44">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class MergeSortCyclic2 {

    public static void main(String[] args) {
        String[] array = { "Python", "Ada", "Java", "C", "Fortran" };
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
