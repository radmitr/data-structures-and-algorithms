package algorithms.searches.exponential_search;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Экспоненциальный поиск
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Сложность по времени в наихудшем случае O(ln(k))
 * k — Позиция искомого элемента в последовательности
 * ------------------------------------------------------------------------------------------------
 * Краткие сведение о авторах и принципе алгоритма
 *
 * Алгоритм был разработан Джоном Бентли и Эндрю Чи-Чи Яо в 1976 году. Этот
 * алгоритм используется для быстрого определение диапазона поиска, с последующим
 * поиском в указанном диапазоне с помощью бинарного поиска.
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Поиск проводим в отсортированной последовательности. Для определения границы
 *    объявляется дополнительная переменная (в дальнейшем border) ее значение устанавливается
 *    равной единице. Переходим к пункту 2.
 *
 * 2) Выполняется проверка: если значение border больше чем длинна последовательности то в
 *    таком случае выполняем бинарный поиск нужного элемента в промежутке от border/2 до
 *    размера последовательности. Заканчиваем поиск. В противном случае переходим к пункту 3.
 *
 * 3) Выполняем проверку: если значение на индексе border больше искомого элемента то
 *    выполняем бинарный поиск нужного элемента в промежутке от border/2 до border.
 *    Заканчиваем поиск. В противном случае переходим к пункту 4.
 *
 * 4) Увеличиваем значение border в два раза. Переходим к пункту 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/NBfWa2GRVtk">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class ExponentialSearch {

    public static void main(String[] args) {
        int[] array = { -2, 0, 3, 5, 7, 9, 11, 15, 18 };
        System.out.println(Arrays.toString(array));

        // 1 - requiredElement found
        int requiredElement = 5;
        System.out.println(requiredElement + " => i=" + exponentialSearch(array, requiredElement));

        // 2 - requiredElement2 not found
        int requiredElement2 = 8;
        System.out.println(requiredElement2 + " => i=" + exponentialSearch(array, requiredElement2));
    }

    public static int binarySearch(int[] array, int requiredElement, int l, int r) {
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

    public static int exponentialSearch(int[] array, int requiredElement) {
        long border = 1;
        while (border < array.length && array[(int) border] < requiredElement) {
            border *= 2;
        }
        int l = (int) (border / 2);
        int r;
        if (border > array.length - 1) {
            r = array.length - 1;
        } else {
            r = (int) border;
        }
        return binarySearch(array, requiredElement, l, r);
    }
}
