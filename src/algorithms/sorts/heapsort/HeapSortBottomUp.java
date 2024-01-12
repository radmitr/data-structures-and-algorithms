package algorithms.sorts.heapsort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Пирамидальная сортировка (сортировка кучей)
 * ------------------------------------------------------------------------------------------------
 * Сложность алгоритма
 *
 * Вычислительная сложность: O(n·ln(n))
 * ------------------------------------------------------------------------------------------------
 * Алгоритм
 *
 * 1) Создать бинарную кучу на основе массива. Для этого можно использовать метод восстановления
 *    свойств кучи для каждого элемента массива. Массив делиться на сортированную и не
 *    сортированную часть. Сортированная часть — правая. В начале алгоритма ее длина равна 0.
 *    Перейти к пункту 2.
 *
 * 2) Провести обмен первого элемента (а он — максимум) и последнего в не сортированной части.
 *    Увеличить отсортированную часть на единицу. Провести просеивание вниз на не сортированной
 *    части, начиная с первого элемента. Перейти к пункту 3.
 *
 * 3) Если длина отсортированной части равна длине массива, то алгоритм закончен, в противном
 *    случае перейти к пункту 2.
 * ================================================================================================
 * Сортировка кучей снизу-вверх
 *
 * Сортировка кучей снизу-вверх (bottom-up heap sort) — модификация сортировки кучей
 * которая уменьшает количество сравнений на этапе просеивания вниз. Эта модификация является
 * выигрышной в случае высоких затрат на сравнение двух элементов массива (длинные строки и т.д.).
 * В случае если сравнение происходит быстро (ключи являются базовыми числовыми типами),
 * то ее использование практически не дает никакого выигрыша.
 *
 * Модифицированное просеивание вниз выглядит следующим образом. Находим максимальный лист
 * кучи (узел на последнем слое у которого нет дочерних) от текущего элемента (чаще всего
 * от корня). После этого двигаемся от этого элемента вверх до нахождения элемента, который
 * больше текущего. Ставим текущий элемент на найденное место, остальные элементы сдвигаем
 * на один элемент выше по куче.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/0jkXxIUUINk">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class HeapSortBottomUp {

    public static void main(String[] args) {
        // 1 - array
        int[] array = { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        heapSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        int[] array2 = { 5, 0, -2, 7, 3, 10, 100, 57, 23, 45, 24, 3, 3, 4, 33, 99, 57, 63, 72, 80, -11 };
        System.out.println(Arrays.toString(array2));

        heapSort(array2);
        System.out.println(Arrays.toString(array2));
        System.out.println();

        // 3 - strArray
        String[] strArray = { "Hi", "Hey", "Hibernate", "Hello", "H" };
        System.out.println(Arrays.toString(strArray));

        heapSort(strArray);
        System.out.println(Arrays.toString(strArray));
        System.out.println();

        // 4 - strArray2
        String[] strArray2 = { "Hello", "Hi", "Hell", "he", "HE", "He", "hE", "Hey", "Hibernate", "789", "123", "456" };
        System.out.println(Arrays.toString(strArray2));

        heapSort(strArray2);
        System.out.println(Arrays.toString(strArray2));
    }

    public static void heapSort(int[] array) {
        // heapify
        int halfSize = array.length / 2;
        for (int i = halfSize; i >= 0; i--) {
            siftDown(array, i, array.length);
        }
        // sort
        int lastIndex = array.length - 1;
        while (lastIndex > 0) {
            swap(array, 0, lastIndex);
            siftDown(array, 0, lastIndex);
            lastIndex--;
        }
    }

    public static void heapSort(String[] array) {
        // heapify
        int halfSize = array.length / 2;
        for (int i = halfSize; i >= 0; i--) {
            siftDown(array, i, array.length);
        }
        // sort
        int lastIndex = array.length - 1;
        while (lastIndex > 0) {
            swap(array, 0, lastIndex);
            siftDown(array, 0, lastIndex);
            lastIndex--;
        }
    }

    private static int searchLargestLeaf(int[] array, int i, int size) {
        int j = i;
        int leftIndex;
        int rightIndex;
        while (true) {
            leftIndex = 2 * j + 1;
            rightIndex = 2 * j + 2;
            if (rightIndex >= size) {
                break;
            }
            if (array[leftIndex] > array[rightIndex]) {
                j = leftIndex;
            } else {
                j = rightIndex;
            }
        }
        // если был break (if (rightIndex >= size) == true)
        if (leftIndex < size) {
            j = leftIndex;
        }
        return j;
    }

    private static int searchLargestLeaf(String[] array, int i, int size) {
        int j = i;
        int leftIndex;
        int rightIndex;
        while (true) {
            leftIndex = 2 * j + 1;
            rightIndex = 2 * j + 2;
            if (rightIndex >= size) {
                break;
            }
            if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
                j = leftIndex;
            } else {
                j = rightIndex;
            }
        }
        // если был break (if (rightIndex >= size) == true)
        if (leftIndex < size) {
            j = leftIndex;
        }
        return j;
    }

    private static void siftDown(int[] array, int i, int size) {
        int j = searchLargestLeaf(array, i, size);
        // Двигаемся от j-го элемента вверх до нахождения элемента, который больше текущего.
        // Мы не зайдём за i-ый элемент, т.к. j-ый дочерний элемент i-го элемента.
        while (array[i] > array[j]) {
            j = (j - 1) / 2;
        }
        // Ставим текущий элемент на найденное место, остальные элементы сдвигаем
        // на один элемент выше по куче.
        while (j > i) {
            swap(array, i, j);
            j = (j - 1) / 2;
        }
    }

    private static void siftDown(String[] array, int i, int size) {
        int j = searchLargestLeaf(array, i, size);
        // Двигаемся от j-го элемента вверх до нахождения элемента, который больше текущего.
        // Мы не зайдём за i-ый элемент, т.к. j-ый дочерний элемент i-го элемента.
        while (array[i].compareTo(array[j]) > 0) {
            j = (j - 1) / 2;
        }
        // Ставим текущий элемент на найденное место, остальные элементы сдвигаем
        // на один элемент выше по куче.
        while (j > i) {
            swap(array, i, j);
            j = (j - 1) / 2;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
