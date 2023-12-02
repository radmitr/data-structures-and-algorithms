package algoritms.searches.binary_search;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Бинарный поиск
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Алгоритм бинарного поиска.
 * Сложность по времени в наихудшем случае O(ln(n))
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * 1) Сортируется последовательность в которой будет проводиться поиск. Если
 *    последовательность уже отсортирована, то этот шаг можно пропустить.
 *
 * 2) Определяется значение элемента в середине последовательности.
 *    Полученный элемент сравнивается с искомым элементом. Различают
 *    случаи:
 *      a) Средний элемент равен искомому. Заканчиваем алгоритм. Поиск
 *         успешен.
 *      b) Средний элемент больше искомого. Рассматриваем левую от среднего
 *         элемента часть последовательности.
 *      c) Средний элемент меньше искомого. Рассматриваем правую от среднего
 *         элемента часть последовательности.
 *
 * 3) Повторяем пункт 2 до тех пор, пока не будет найден искомый элемент или
 *    не станет пустым интервал для поиска.
 * ------------------------------------------------------------------------------------------------
 * В каком случае стоит использовать бинарный поиск
 *
 * Предположим, что существует не отсортированная последовательность размером N элементов.
 * В ней нужно выполнить К поисков (0 < K). При каком значении К стоит
 * выполнить обычный линейный поиск (не сортируя массив)? При каком значении стоит
 * отсортировать массив и использовать бинарный поиск?
 * ----------------------------------------------------------------
 * Вспомогательные данные:
 *
 * Сложность линейного поиска                            O(N)
 * Сложность оптимальной сортировки (например TimSort)   O(N⋅ln(N))
 * Сложность бинарного поиска                            O(ln(N))
 * ----------------------------------------------------------------
 * Линейный поиск                      Сортировка + бинарный поиск
 *      C1⋅K⋅N                              C2⋅N⋅ln(N)+C3⋅K⋅ln (N)
 *
 * Условие перехода:
 * C1⋅K⋅N ⩾ C2⋅N⋅ln(N) + C3⋅K⋅ln(N)
 *
 * K = C2/C1 ⋅ N⋅ln(N)/(N-C3⋅ln(N)/C1)
 * Где:
 * C1, С2, С3 - Константы зависящие от ПК
 *
 * lim C2/C1 ⋅ N⋅ln(N)/(N - C3⋅ln(N)/C1) = C2/C1 ⋅ ln(N)
 * N→∞
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/cqMmVA3ivLY">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = { -2, 0, 3, 5, 7, 9, 11, 15, 18 };
        System.out.println(Arrays.toString(array));

        // 1 - element found
        int element = 5;
        System.out.println(element + " => i=" + binarySearch(array, element));

        // 2 - element2 not found
        int element2 = 8;
        System.out.println(element2 + " => i=" + binarySearch(array, element2));
    }

    public static int binarySearch(int[] array, int requiredElement) {
        int l = 0;
        int r = array.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            int element = array[m];
            if (requiredElement == element) {
                return m;
            }
            if (element < requiredElement) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }
}
