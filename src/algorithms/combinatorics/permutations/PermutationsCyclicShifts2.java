package algorithms.combinatorics.permutations;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Генерация перестановок с помощью циклических сдвигов
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O(n!)
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Создать последовательность размером n. Заполнить ее значениями, равными индексам
 *    элементов. Создать вспомогательную переменную (в примере k). Установить ее значение
 *    равное индексу последнего элемента. Перейти к пункту 2.
 *
 * 2) Вывести на экран последовательность. Установить значение k равное индексу последнего
 *    элемента. Перейти к пункту 3.
 *
 * 3) Выполнить циклический сдвиг влево элементов последовательности от первого до k-го. Если k-й
 *    элемент последовательности не равен k, то перейти к пункту 2. В противном случае перейти к
 *    пункту 4.
 *
 * 4) Указать новое значение k = k-1. Если k равен индексу первого элемента последовательности, то
 *    закончить алгоритм. В противном случае перейти к пункту 3.
 * ------------------------------------------------------------------------------------------------
 * Циклический сдвиг влево
 *
 * Под циклическим сдвигом влево подразумевается смещение всех значений
 * последовательности на одну позицию влево. При этом первый элемент последовательности
 * становится последним элементом. Если выполнить сдвиг влево определенное количество раз
 * (размер последовательности), то получим опять начальную последовательность. Поэтому сдвиг и
 * называют циклическим.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/oK8A7eIWXF8">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class PermutationsCyclicShifts2 {

    // Числа от 1 до n
    public static void main(String[] args) {
        // 1 - array
        int[] array = { 1, 2, 3 };
        printAllPermutations(array);
        System.out.println();

        // 2 - array2
        int[] array2 = { 1, 2, 3, 4 };
        printAllPermutations(array2);
    }

    public static void printAllPermutations(int[] array) {
        int k = array.length - 1;
        int n = k;
        System.out.println(Arrays.toString(array));
        while (k > 0) {
            leftShift(array, k);
            // Т.к. числа от 1, то => k + 1
            if (array[k] != k + 1) {
                System.out.println(Arrays.toString(array));
                k = n;
            } else {
                k = k - 1;
            }
        }
    }

    public static void leftShift(int[] array, int k) {
        int temp = array[0];
        for (int i = 0; i < k; i++) {
            array[i] = array[i + 1];
        }
        array[k] = temp;
    }
}
