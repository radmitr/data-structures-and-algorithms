package algorithms.combinatorics.permutations;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Генерация перестановок. Алгоритм Джонсона - Троттера.
 * ------------------------------------------------------------------------------------------------
 * Перестановки
 *
 * В комбинаторике перестановка — это упорядоченный набор без повторений чисел 1, 2, … n,
 * обычно трактуемый как биекция на множестве { 1, 2, … n }, которая числу i ставит в соответствие
 * i-й элемент из набора. Число n при этом называется длиной перестановки.
 * ------------------------------------------------------------------------------------------------
 * Генерация всех перестановок
 *
 * Ряд задач предполагает генерацию всех перестановок длинной n. Именно для этого
 * используется алгоритм  Джонсона — Троттера.
 *
 * Преимущества данного алгоритма:
 *   ● Относительная легкость не рекурсивной реализации
 *   ● Малый расход памяти
 *
 * Для генерации перестановок элементов любых типов данных, можно рассматривать все равно
 * перестановки целых чисел. В таком случае перестановки целых чисел можно рассматривать в
 * качестве индексов последовательностей. В свою очередь в последовательностях можно хранить
 * любые типы данных.
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Алгоритм Джонсона — Троттера.
 * Сложность по времени в наихудшем случае O(n!)
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * С каждым элементом перестановки связываем направление. Направление — указатель на
 * соседний элемент (может указывать на элемент справа или слева). Элемент перестановки
 * называется мобильным, если его направление указывает на меньший соседний элемент.
 *
 * 1) Создается первая перестановка. Ряд чисел по возрастанию 1, 2, 3, … n. Направление каждого
 *    элемента указывает влево.
 * 2) Ищем наибольший мобильный элемент. Если не находим, то алгоритм закончен.
 * 3) Производим обмен найденного мобильного элемента с элементом, на который указывает
 *    направление найденного мобильного элемента.
 * 4) Меняем направление у всех элементов, которые больше чем найденный на шаге 2 элемент.
 * 5) Переходим к шагу 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/6L5mm_e3Hms">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class JohnsonTrotter {

    public static void main(String[] args) {
        permutationGenerator(4);
    }

    public static int findMaxMobileElement(int[] permutation, int[] direction) {
        int index = -1;
        for (int i = 0; i < permutation.length; i++) {
            int nextIndex = i + direction[i];
            if (nextIndex >= 0 && nextIndex < permutation.length) {
                if (permutation[i] > permutation[nextIndex]) {
                    if (index == -1) {
                        index = i;
                    } else {
                        if (permutation[i] > permutation[index]) {
                            index = i;
                        }
                    }
                }
            }
        }
        return index;
    }

    public static void changeDirection(int[] permutation, int[] direction, int mobileElement) {
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] > mobileElement) {
                direction[i] = direction[i] * (-1);
            }
        }
    }

    public static void swap(int[] permutation, int[] direction, int i, int j) {
        int permutationTemp = permutation[i];
        permutation[i] = permutation[j];
        permutation[j] = permutationTemp;

        int directionTemp = direction[i];
        direction[i] = direction[j];
        direction[j] = directionTemp;
    }

    public static void permutationGenerator(int n) {
        int[] permutation = new int[n];
        int[] direction = new int[n];
        for (int i = 0; i < permutation.length; i++) {
            permutation[i] = i + 1;
            direction[i] = -1;
        }
        System.out.println(Arrays.toString(permutation));
        int mobileElementIndex = findMaxMobileElement(permutation, direction);
        while (mobileElementIndex != -1) {
            int mobileElement = permutation[mobileElementIndex];
            int nextIndex = mobileElementIndex + direction[mobileElementIndex];
            swap(permutation, direction, mobileElementIndex, nextIndex);
            changeDirection(permutation, direction, mobileElement);
            System.out.println(Arrays.toString(permutation));
            mobileElementIndex = findMaxMobileElement(permutation, direction);
        }
    }
}
