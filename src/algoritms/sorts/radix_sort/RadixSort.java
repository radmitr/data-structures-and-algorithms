package algoritms.sorts.radix_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * ------------------------------------------------------------------------------------------------
 * Поразрядная сортировка
 * ------------------------------------------------------------------------------------------------
 * !!! Вариация поразрядной сортировки только для положительных целых чисел !!!
 * ------------------------------------------------------------------------------------------------
 * Описание сути алгоритма
 *
 * Поразрядная сортировка — алгоритм сортировки не использующий сравнение элементов
 * между собой. Предназначен для сортировки данных ключи которых можно представить в виде
 * последовательности «разрядов» каждому из которых можно сопоставить целое число. Например
 * это могут быть целые числа (разряды записаны явно), строки (каждый символ это разряд).
 * Алгоритм сводится к повторению алгоритма сортировки распределяющим подсчетом для каждого
 * разряда. Важным моментом является способ «выравнивания» ключей сортировки, т.е. как
 * сравнивать ключи которые имеют разное количество разрядов.
 *
 * В зависимости от того как выполнять выравнивание ключей сортировки поразрядная
 * сортировка делится на:
 *   ● LSD (least significant digit) — выравнивание по младшему разряду.
 *   ● MSD (most significant digit) — выравнивание со старшему разряду.
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Сложность по времени в наихудшем случае O(n)
 * Требуется (n + диапазон ключей) дополнительной памяти
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Определяем максимальное количество разрядов в ключах сортировки (в дальнейшем k).
 *    Определяем способ выравнивания ключей сортировки (LSD или MSD).
 *
 * 2) Выполняем последовательно k раз сортировку используя алгоритм распределяющего подсчета,
 *    где в качестве ключа сортировки используется значение соответствующего разряда.
 * ------------------------------------------------------------------------------------------------
 * !!! Вариация поразрядной сортировки только для положительных целых чисел !!!
 * ------------------------------------------------------------------------------------------------
 * <a href="">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class RadixSort {

    public static void main(String[] args) {
        // 1 - numbers
        int[] numbers = { 121, 5, 24, 9, 32 };
        System.out.println(Arrays.toString(numbers));

        int[] result = radixSort(numbers);
        System.out.println(Arrays.toString(result));
        System.out.println();

        // 2 - numbers2
        int[] numbers2 = { 121, 5, 24, 9, 32, 5, 34, 67, 150, 20, 67, 82, 83, 6, 0, 3, 58, 4397, 22, 24, 1020 };
        System.out.println(Arrays.toString(numbers2));

        int[] result2 = radixSort(numbers2);
        System.out.println(Arrays.toString(result2));
        System.out.println();

        // 3 - numbers3
        int arraySize = 10_000;
        int bound = 50_000;
        int[] numbers3 = new int[arraySize];
        Random random = new Random();

        for (int i = 0; i < numbers3.length; i++) {
            numbers3[i] = random.nextInt(bound);
        }
        System.out.println(Arrays.toString(numbers3));

        int[] result3 = radixSort(numbers3);
        System.out.println(Arrays.toString(result3));
    }

    public static int numberOfDigits(int number) {
        int i = 1;
        long n = 10;
        while (number >= n) {
            i++;
            n *= 10;
        }
        return i;
    }

    public static int findMaxNumberOfDigits(int[] numbers) {
        int result = 1;
        for (int i = 0; i < numbers.length; i++) {
            int digits = numberOfDigits(numbers[i]);
            if (digits > result) {
                result = digits;
            }
        }
        return result;
    }

    public static int getDigit(int number, int divider) {
        return number % (divider * 10) / (divider);
    }

    public static int[] findMinMaxKey(int[] numbers, int divider) {
        int minKey = getDigit(numbers[0], divider);
        int maxKey = minKey;
        for (int number : numbers) {
            int digit = getDigit(number, divider);
            if (digit < minKey) {
                minKey = digit;
            }
            if (digit > maxKey) {
                maxKey = digit;
            }
        }
        return new int[] { minKey, maxKey };
    }

    public static int[] distributionCountingSort(int[] numbers, int divider) {
        int[] minMaxKey = findMinMaxKey(numbers, divider);
        int minKey = minMaxKey[0];
        int maxKey = minMaxKey[1];
        int n = maxKey - minKey + 1;
        int[] support = new int[n];
        for (int number : numbers) {
            support[getDigit(number, divider) - minKey]++;
        }
        int size = numbers.length;
        for (int i = support.length - 1; i >= 0; i--) {
            size -= support[i];
            support[i] = size;
        }
        int[] result = new int[numbers.length];
        for (int number : numbers) {
            result[support[getDigit(number, divider) - minKey]] = number;
            support[getDigit(number, divider) - minKey]++;
        }
        return result;
    }

    public static int[] radixSort(int[] numbers) {
        int maxNumberOfDigits = findMaxNumberOfDigits(numbers);
        int divider = 1;
        for (int i = 0; i < maxNumberOfDigits; i++) {
            numbers = distributionCountingSort(numbers, divider);
            divider *= 10;
        }
        return numbers;
    }
}
