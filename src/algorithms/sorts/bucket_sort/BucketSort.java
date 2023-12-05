package algorithms.sorts.bucket_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * ------------------------------------------------------------------------------------------------
 * Блочная сортировка
 * ------------------------------------------------------------------------------------------------
 * Описание сути алгоритма
 *
 * Блочная сортировка (карманная, корзинная сортировка) — алгоритм сортировки не
 * использующий сравнение элементов между собой. Предназначена для сортировки данных, ключи
 * которых можно представить в виде целых или вещественных чисел (алгоритм разработан для
 * вещественных чисел [0,1), но можно адаптировать и для целых чисел). В основе лежит рекурсивное
 * разбиение ключей на несколько блоков (поддиапазонов), как только размер блока станет меньше или
 * равен наперед заданному числу (одним из оптимальных размеров является 32), то он сортируется
 * любым оптимальным алгоритмом (например сортировка вставкой). После чего отсортированные
 * блоки объединяются в отсортированную последовательность.
 * ------------------------------------------------------------------------------------------------
 * Принцип разбиения на блоки
 *
 * Основной задачей данного алгоритма является разбиение всех ключей на блоки. Диапазоны
 * этих блоков должны быть не пересекающимися и должны быть расположены в возрастающем
 * порядке. Для этого стоит найти минимальное и максимальное значение ключей, это позволит
 * определить общий диапазон. После этого выбрать количество карманов, и разбить общий диапазон
 * на нужное количество блоков. После это стоит определить функцию, которая отнесет текущий
 * элемент к тому или иному блоку.
 *
 * Для целых чисел:
 *   F(x) = n⋅(x − min) / (max − min + 1)
 *
 * Для вещественных чисел:
 *   F(x) = floor(n⋅(x − min) / (max − min + 1))
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме блочной сортировки
 *
 * Сложность по времени в наихудшем случае O(n + n^2/k), k — количество блоков
 * Дополнительно используемая память O(n)
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Определяем значение минимального и максимального значения ключей сортировки.
 *    Разбиваем полученный диапазон на нужное количество блоков(в дальнейшем n). Для этого
 *    нужно создать массив списков размером n.
 *
 * 2) Заполняем блоки данными из базовой последовательности. Для определения индекса блока
 *    используется функция соответствия.
 *
 * 3) Выполняем проход по полученным блокам. Если размер блока равен или меньше 32, провести
 *    его сортировку используя сортировку вставкой (или любую иную оптимальную). Если размер
 *    больше то рекурсивно перейти к пункту 1.
 *
 * 4) Выполнить сборку отсортированных блоков в отсортированную последовательность.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/sSVM1E3OWOI">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class BucketSort {

    public static void main(String[] args) {
        // 1 - array
        int[] array = { 12, 2, 4, 7, 5, 10, 8, 9, 11, 9 };
        System.out.println(Arrays.toString(array));

        bucketSort(array, 5);
        System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        int[] array2 = { 12, 2, 4, 7, 5, 10, 8, 9, 11, 9, 100, -2, -50, 55, 23, 89, 65, 30, 77, 4,
                        -7, -23, 58, 27, 10, 15, 33, 35, 33, 61, 78, 79, 80, 83, 82, 38, 79, 42, 99,
                        -30, 94, 69, 70, 72, -17, 56, 30, 60, 45, 56, 58, 68, 73, 11, 3, -3, 50, -6,
                        -2, 93};
        System.out.println(Arrays.toString(array2));

        bucketSort(array2, 5);
        System.out.println(Arrays.toString(array2));
        System.out.println();

        // 3 - array3
        int arraySize = 10_000;
        int bound = 10_000;
        int numberOfBuckets = 1_000;
        int[] array3 = new int[arraySize];
        Random random = new Random();

        for (int i = 0; i < array3.length; i++) {
            array3[i] = random.nextInt(bound);
        }
        System.out.println(Arrays.toString(array3));

        bucketSort(array3, numberOfBuckets);
        System.out.println(Arrays.toString(array3));
        System.out.println();
    }

    public static void insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
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
    }

    public static int[] findMinMax(int[] array) {
        if (array.length == 0) {
            return new int[] { 0, 0 };
        }
        int min = array[0];
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }
        return new int[] { min, max };
    }

    public static int[] calculateBlockSize(int[] array, int n) {
        int[] blockSize = new int[n];
        int[] minMax = findMinMax(array);
        for (int i = 0; i < array.length; i++) {
            int blockNumber = (int) (1L * n * (array[i] - minMax[0]) / (minMax[1] - minMax[0] + 1));
            blockSize[blockNumber]++;
        }
        return blockSize;
    }

    public static void bucketSort(int[] array, int n) {
        int[] minMax = findMinMax(array);
        if (minMax[0] == minMax[1]) {
            return;
        }
        int[][] buckets = new int[n][];
        int[] addIndex = new int[n];
        int[] blockSize = calculateBlockSize(array, n);
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new int[blockSize[i]];
        }
        for (int i = 0; i < array.length; i++) {
            int blockNumber = (int) (1L * n * (array[i] - minMax[0]) / (minMax[1] - minMax[0] + 1));
            buckets[blockNumber][addIndex[blockNumber]++] = array[i];
        }
        for (int[] bucket : buckets) {
            if (bucket.length <= 32) {
                insertionSort(bucket);
            } else {
                bucketSort(bucket, n);
            }
        }
        int index = 0;
        for (int[] bucket : buckets) {
            for (int element : bucket) {
                array[index++] = element;
            }
        }
    }
}
