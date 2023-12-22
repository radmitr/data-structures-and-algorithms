package algorithms.combinatorics.permutations;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Генерация перестановок. Алгоритм Хипа.
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени O(n!)
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * Для первого вызова функции используем последовательность (из элементов которой хотим
 * получить перестановки) и значение k равное ее длине.
 *
 * 1) Проверить значение k. В случае если k == 1. Вернуть последовательность как текущую
 *    перестановку. Закончить вызов функции.
 *
 * 2) Используя цикл от индекса первого элемента последовательности до k с шагом в 1, выполнить
 *    действия:
 *      ● Рекурсивно вызвать функцию, передав последовательность и значение k - 1
 *      ● Если k четное, выполнить обмен i и k элемента местами, в противном случае выполнить
 *        обмен первого элемента последовательности и k.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/-HAJoD_6PQA">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class PermutationsHip {

    public static void main(String[] args) {
        // 1 - array1
        int[] array1 = { 1, 2, 3 };
        printAllPermutations(array1);
        System.out.println();

        // 2
        int[] array2 = { 1, 2, 3, 4 };
        printAllPermutations(array2);
    }

    public static void printAllPermutations(int[] array) {
        int[] copiedArray = Arrays.copyOf(array, array.length);
        int k = copiedArray.length;
        permutationsRecursive(copiedArray, k);
    }

    public static void permutationsRecursive(int[] array, int k) {
        if (k == 1) {
            System.out.println(Arrays.toString(array));
            return;
        }
        for (int i = 0; i < k; i++) {
            permutationsRecursive(array, k - 1);
            if (k % 2 == 0) {
                swap(array, i, k - 1);
            } else {
                swap(array, 0, k - 1);
            }
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
