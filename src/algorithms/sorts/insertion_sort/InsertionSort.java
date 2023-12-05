package algorithms.sorts.insertion_sort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка вставками
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O(n^2)
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * 1) Последовательность разбивается на две части. Отсортированную и не
 *    отсортированную. В качестве отсортированной части обычно выбирается
 *    левая часть последовательности.
 *
 * 2) Выбирается первый элемент не отсортированной последовательности и
 *    для него находится позиция в отсортированной части последовательности.
 *    Правило нахождения позиции для элемента со значением X:
 *      an ≤ X ≤ an + 1
 *
 * 3) Значение устанавливается на найденную позицию со сдвигом элементов
 *    стоящим справа от найденной позиции.
 *
 * 4) Алгоритм продолжается до исчерпания не отсортированной части
 *    последовательности.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/hLetyfSVdJQ">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = { 5, 0, -2, 7, 3 };

        for (int i = 1; i < array.length; i++) {
            int pasteElement = array[i];
            int j;
            for (j = i; j > 0; j--) {
                if (array[j - 1] <= pasteElement) {
                    break;
                }
                array[j] = array[j - 1];
            }
            array[j] = pasteElement;
        }
        System.out.println(Arrays.toString(array));
    }
}
