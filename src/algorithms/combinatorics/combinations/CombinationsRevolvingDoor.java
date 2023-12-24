package algorithms.combinatorics.combinations;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Генерация сочетаний с помощью алгоритма вращающейся двери
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Алгоритм был разработан W. H. Payne в 1979 году. Суть алгоритма в том, что каждое
 * сочетание получается путем замены одного (и только одного) элемента сочетания на
 * элемент из еще не использованных. Называется так потому, как заменяемый элемент
 * как бы выходит через вращающуюся дверь и в этот же момент через нее добавляется
 * элемент который еще не использовался.
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O((n!/(k!⋅(n - k)!))⋅k)
 * Затраты памяти O(k + 1)
 * k — элементов, выбранных из множества из n-элементов
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * Генерируем все сочетания из n целых чисел [0, 1, 2 ... n-1] по k.
 *
 * 1) Создаем последовательность размером k + 1 элемента (в дальнейшем c). Первые k — элементов
 *    устанавливаем равные индексу элемента. Элемент k + 1 устанавливаем равным n - 1. Вводим
 *    дополнительную переменную j. Перейти к 2.
 *
 * 2) Возвращаем первые k элементов последовательность как очередное сочетание. Перейти к 3.
 *
 * 3) Возможно два варианта:
 *      ● k — нечетное. В случае если c0 + 1 < c1, установить c0 = c0 + 1, перейти к 2.
 *        В противном случае установить j = 1 и перейти к 4.
 *      ● k — четное. В случае если c0 > 0, установить c0 = c0 - 1, перейти к 2.
 *        В противном случае установить j = 1 и перейти к 5.
 *
 * 4) В случае cj > j, установить cj = cj-1, cj-1 = j - 1 и перейти к 2.
 *    В противном случае установить j = j + 1 и перейти к 5.
 *
 * 5) Если cj + 1 <= cj+1 установить cj-1 = cj, cj = cj + 1 и перейти к 2.
 *    В противном случае установить j = j + 1, если j < k перейти к 4. В противном случае
 *    закончить алгоритм.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/cmgvQhTlHiA">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class CombinationsRevolvingDoor {

    private static final int STEP_1_START = 1;
    private static final int STEP_2_PRINT = 2;
    private static final int STEP_3_CHECK_FIRST_ELEMENT = 3;
    private static final int STEP_4_DECREASE_ELEMENT = 4;
    private static final int STEP_5_INCREASE_ELEMENT = 5;
    private static final int STEP_6_EXIT = -1;

    public static void main(String[] args) {
//        // 1 - из 5 по 1 (некорректно работает: вывод не верный + ArrayIndexOutOfBoundsException)
//        printAllCombinations(5, 1);
//        System.out.println();

        // 2 - из 5 по 2
        printAllCombinations(5, 2);
        System.out.println();

        // 3 - из 5 по 3
        printAllCombinations(5, 3);
        System.out.println();

        // 4 - из 5 по 4 (некорректно работает: уходит в бесконечный цикл)
//        printAllCombinations(5, 4);
//        System.out.println();

        // 5 - из 5 по 5 (некорректно работает: вывод не верный)
//        printAllCombinations(5, 5);
    }

    public static void printAllCombinations(int n, int k) {
        int step = STEP_1_START;
        int[] c = new int[k + 1];
        for (int i = 0; i < k; i++) {
            c[i] = i;
        }
        c[k] = n - 1;
        int j = 1;
        step = STEP_2_PRINT;
        while (step != STEP_6_EXIT) {
            switch (step) {
                case 2 -> {
                    printCurrentCombination(c, k);
                    step = STEP_3_CHECK_FIRST_ELEMENT;
                }
                case 3 -> {
                    int[] r3 = checkFirstElement(c, k);
                    step = r3[0];
                    j = r3[1];
                }
                case 4 -> {
                    int[] r4 = decreaseElement(c, j);
                    step = r4[0];
                    j = r4[1];
                }
                case 5 -> {
                    int[] r5 = increaseElement(c, j, k);
                    step = r5[0];
                    j = r5[1];
                }
            }
        }
    }

    /**
     * Step 2
     */
    public static void printCurrentCombination(int[] c, int k) {
        System.out.println(Arrays.toString(Arrays.copyOf(c, k)));
    }

    /**
     * Step 3
     */
    public static int[] checkFirstElement(int[] c, int k) {
        int step;
        int j = 1;
        if (k % 2 != 0) {
            if (c[0] + 1 < c[1]) {
                c[0]++;
                step = STEP_2_PRINT;
            } else {
                step = STEP_4_DECREASE_ELEMENT;
            }
        } else {
            if (c[0] > 0) {
                c[0]--;
                step = STEP_2_PRINT;
            } else {
                step = STEP_5_INCREASE_ELEMENT;
            }
        }
        return new int[] { step, j };
    }

    /**
     * Step 4
     */
    public static int[] decreaseElement(int[] c, int j) {
        int step;
        if (c[j] > j) {
            c[j] = c[j - 1];
            c[j - 1] = j - 1;
            step = STEP_2_PRINT;
        } else {
            j++;
            step = STEP_5_INCREASE_ELEMENT;
        }
        return new int[] { step, j };
    }

    /**
     * Step 5
     */
    public static int[] increaseElement(int[] c, int j, int k) {
        int step;
        if (c[j] + 1 <= c[j + 1]) {
            c[j - 1] = c[j];
            c[j]++;
            step = STEP_2_PRINT;
        } else {
            j++;
            if (j < k) {
                step = STEP_4_DECREASE_ELEMENT;
            } else {
                step = STEP_6_EXIT;
            }
        }
        return new int[] { step, j };
    }
}