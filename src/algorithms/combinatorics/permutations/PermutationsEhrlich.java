package algorithms.combinatorics.permutations;

import java.util.Arrays;
/**
 * ------------------------------------------------------------------------------------------------
 *  Генерация перестановок. Метод обмена Эрлиха.
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O(n)
 * !!!Внимание для одной перестановки!!!
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Создать две вспомогательные последовательности. Длинна первой равна длине базовой
 *    последовательности (в дальнейшем b). Длинна второй на один элемент больше (в дальнейшем c).
 *    Заполнить последовательность b числами от 0 и далее по возрастанию с шагом 1.
 *    Заполнить c нулями.  Объявить переменную k = 1, j = 1. Перейти к пункту 2.
 *
 * 2) Вернуть базовую последовательность как очередную перестановку. Перейти к пункту 3.
 *
 * 3) Присвоить k = 1. Выполняем проход от начала последовательности до тех пор, пока c[k] = k. На
 *    каждом шаге устанавливаем c[k] = 0. По окончанию прохода проверяем, если k = n, закончить
 *    алгоритм. В противном случае c[k] = c[k] + 1 и перейти к пункту 4.
 *
 * 4) Выполнить обмен a[0] <-> a[b[k]]. Перейти к 5.
 *
 * 5) Установить j = 1, k = k - 1. До тех пор, пока j < k, выполнять обмен b[j] <-> b[k],
 *    установить j = j + 1, k = k - 1.  Вернуться к 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/OfMt6QorlUc">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class PermutationsEhrlich {

    public static void main(String[] args) {
        String[] array = { "1", "2", "3", "4" };
        printAllPermutations(array);
    }

    public static <T> void printAllPermutations(T[] array) {
        T[] aSequence = Arrays.copyOf(array, array.length);
        int[] bTable = new int[aSequence.length];
        int[] cTable = new int[aSequence.length + 1];
        int k = 1;
        int j = 1;
        for (int i = 0; i < bTable.length; i++) {
            bTable[i] = i;
        }
        while (true) {
            System.out.println(Arrays.toString(aSequence));
            k = 1;
            while (cTable[k] == k) {
                cTable[k++] = 0;
            }
            if (k == aSequence.length) {
                break;
            }
            cTable[k]++;
            swap(aSequence, 0, bTable[k]);
            j = 1;
            k--;
            while (j < k) {
                swap(bTable, k--, j++);
            }
        }
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
