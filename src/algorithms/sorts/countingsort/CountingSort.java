package algorithms.sorts.countingsort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка подсчетом
 * ------------------------------------------------------------------------------------------------
 * Описание сути алгоритма
 *
 * Сортировка подсчетом используется для сортировки массивов целых чисел, значения которых
 * лежат в относительно узком диапазоне. Например если существует массив целых чисел размером
 * 1000000, значения которых лежат в диапазоне [0..1000], то этот алгоритм покажет очень хорошее
 * быстродействие. Отдельно стоит отметить такую особенность этого алгоритма сортировки, как
 * отсутствие операции сравнения ключей.
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O(n)
 * Требует дополнительно памяти в размере диапазона чисел
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Определяем минимальное и максимальное значение в сортируемой последовательности
 *    (в дальнейшем sort) (обозначим их как min и max соответственно). Объявляем вспомогательную
 *    последовательность (в дальнейшем support), длинна которой вычисляется как max-min+1.
 *    Заполняем ее нулями.
 *
 * 2) Выполняем проход по sort, добавляем единицу к значению support[element-min],
 *    где element — это текущий элемент в sort.
 *
 * 3) Выполняем проход по индексам (дальше i) последовательности support, добавляя в sort
 *    значения i+min в количестве support[i].
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/oGJuM4XKFfs">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] sort = { 5, 0, -2, 7, 3, -2 };
        System.out.println(Arrays.toString(sort));

        countingSort(sort);
        System.out.println(Arrays.toString(sort));
    }

    public static void countingSort(int[] sort) {
        int[] minMax = findMinMax(sort);
        int minValue = minMax[0];
        int maxValue = minMax[1];
        int[] support = new int[maxValue - minValue + 1];
        for (int element : sort) {
            support[element - minValue]++;
        }
        int index = 0;
        for (int i = 0; i < support.length; i++) {
            for (int j = 0; j < support[i]; j++) {
                sort[index] = i + minValue;
                index++;
            }
        }
    }

    public static int[] findMinMax(int[] array) {
        int min = array[0];
        int max = array[0];
        for (int element : array) {
            if (min > element) {
                min = element;
            }
            if (max < element) {
                max = element;
            }
        }
        return new int[] { min, max };
    }
}
