package algorithms.searches;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Тернарный поиск
 * ------------------------------------------------------------------------------------------------
 * Сведения об алгоритме
 *
 * Сложность по времени в наихудшем случае O(ln(n))
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Поиск выполняется по отсортированной последовательности. Объявить ряд вспомогательных
 *    переменных. В дальнейшем будем использовать такие имена: l — индекс первого элемента
 *    последовательности, r — индекс последнего элемента последовательности, m1 — индекс
 *    вычисляемый как: m1 = l + (r - l)/3, m2 — индекс вычисляемый как: m2 = r - (r - l)/3.
 *    Перейти к пункту 2.
 *
 * 2) Выполнить ряд проверок:
 *    1) Если значение на индексе m1 равно искомому, то закончить алгоритм вернуть m1.
 *    2) Если значение на индексе m2 равно искомому, то закончить алгоритм вернуть m2.
 *    3) Если l > r, закончить алгоритм, вернуть отрицательный результат поиска.
 *
 *    Если ни одно из условий не выполнено, перейти к пункту 3.
 *
 * 3) Выполнить ряд проверок:
 *    1) Если значение стоящее на индексе m1 меньше искомого, а значение стоящее на индексе m2
 *       больше. Установить l = m1 + 1, r = m2 - 1.
 *    2) Если значение стоящее на индексе m1 больше искомого. Установить r = m1 - 1.
 *    3) Если значение стоящее на индексе m2 меньше искомого. Установить l = m2 + 1.
 *
 *    Установить m1 = l + (r - l)/3, m2 = r - (r - l)/3, перейти к пункту 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/6_eZIBvPX9g">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class TernarySearch {

    public static void main(String[] args) {
        int[] array = { -2, 0, 3, 5, 7, 9, 11, 15, 18 };
        System.out.println(Arrays.toString(array));

        // 1 - element found
        int element = 5;
        System.out.println(ternarySearch(array, element)  + " -> " + element);

        // 2 - element2 not found
        int element2 = 8;
        System.out.println(ternarySearch(array, element2)  + " -> " + element2);
    }

    public static int ternarySearch(int[] array, int element) {
        int r = array.length - 1;
        int l = 0;
        while (l <= r) {
            int h = (r - l) / 3;
            int m1 = l + h;
            int m2 = r - h;

            if (array[m1] == element) {
                return m1;
            }
            if (array[m2] == element) {
                return m2;
            }
            if (array[m1] < element && element < array[m2]) {
                l = m1 + 1;
                r = m2 - 1;
            } else if (element < array[m1]) {
                r = m1 - 1;
            } else {
                l = m2 + 1;
            }
        }
        return -1;
    }
}
