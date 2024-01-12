package algorithms.sorts.heapsort;

import java.util.Arrays;
import java.util.Random;

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
 * Модификация алгоритма
 *
 * Можно модифицировать этот алгоритм упростив реализацию. Стоит заметить, что применяя
 * просеивание вниз кучи начиная с ее первой половины, можно также привести ее к нужному виду. В
 * таком случае просеивание вверх уже не нужно.
 * 
 * Таким образом алгоритм приобретает следующий вид. Начиная от середины массива в сторону
 * начала, проводим просеивание вниз. Дальнейший ход алгоритма не отличается от описанного ранее.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/0jkXxIUUINk">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class HeapSortModified {

    public static void main(String[] args) {
        int[] array = { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        heapSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        int[] array2 = { 5, 0, -2, 7, 3, 10, 100, 57, 23, 45, 24, 3, 3, 4,  33, 99, 57, 63, 72, 80, -11 };
        System.out.println(Arrays.toString(array2));

        heapSort(array2);
        System.out.println(Arrays.toString(array2));
        System.out.println();

        // 3 - array3
        Random random = new Random();
        int arraySize = 500;
        int bound = 100;
        int[] array3 = new int[arraySize];

        for (int i = 0; i < array3.length; i++) {
            array3[i] = random.nextInt(bound + 1);
        }
        System.out.println(Arrays.toString(array3));
        System.out.println("-----");

        heapSort(array3);
        System.out.println(Arrays.toString(array3));
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

    private static void siftDown(int[] array, int i, int size) {
        while (true) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            int j = i;
            if (leftIndex < size && array[leftIndex] > array[j]) {
                j = leftIndex;
            }
            if (rightIndex < size && array[rightIndex] > array[j]) {
                j = rightIndex;
            }
            if (i != j) {
                swap(array, i, j);
                i = j;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
