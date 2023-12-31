package algorithms.combinatorics.combinations;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Генерация сочетаний в лексикографическом порядке
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O((n!/(k!⋅(n - k)!))⋅(n + 1)/(n - k + 1))
 * Затраты памяти O(k)
 * k — элементов, выбранных из множества из n-элементов
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * 1) Создаем последовательность (в дальнейшем a) размером k элементов. Индексация в
 *    последовательности начинается с нуля. Заполняем последовательность значениями
 *    от 1 до k. Переходим к пункту 2.
 *
 * 2) Используем последовательность как очередное сочетание. Переходим к пункту 3.
 *
 * 3) Начиная с конца последовательности ищем такой элемент, что выполняется условие
 *    a[i] ≤ n - k + i. Если такой элемент найден, то переходим к пункту 4. В противном случае
 *    заканчиваем алгоритм.
 *
 * 4) Увеличиваем найденный элемент на единицу. От найденного элемента и до конца
 *    последовательности устанавливаем значения на единицу больше предыдущего
 *    элемента. Переходим к пункту 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/8-xzy50m-dY">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class CombinationsLexicographic {

    public static void main(String[] args) {
        // 1 - из 5 по 1
        printAllCombinations(5, 1);
        System.out.println();

        // 2 - из 5 по 2
        printAllCombinations(5, 2);
        System.out.println();

        // 3 - из 5 по 3
        printAllCombinations(5, 3);
    }

    public static void printAllCombinations(int n, int k) {
        int[] comb = new int[k];
        for (int i = 0; i < comb.length; i++) {
            comb[i] = i + 1;
        }
        while (true) {
            System.out.println(Arrays.toString(comb));
            int m = -1;
            for (int i = k - 1; i >= 0; i--) {
                if (comb[i] <= n - k + i) {
                    comb[i]++;
                    m = i;
                    break;
                }
            }
            if (m == -1) {
                break;
            }
            for (int i = m + 1; i < k; i++) {
                comb[i] = comb[i - 1] + 1;
            }
        }
    }
}
