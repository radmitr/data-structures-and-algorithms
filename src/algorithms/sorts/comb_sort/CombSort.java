package algorithms.sorts.comb_sort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка расческой
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Алгоритм сортировки расческой.
 * Сложность по времени в наихудшем случае O(n^2)
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * 1) Выбирается начальное значение шага сортировки. Рекомендуемым является шаг
 *    равный целой части деления размера последовательности на 1.247. Также добавляем
 *    переменную для хранения числа обменов за один проход.
 *
 * 2) Устанавливаем переменную для хранения числа обменов равной нулю. Начиная с
 *    первого элемента последовательности, сравниваем текущий элемент с элементом,
 *    индекс которого равен индексу текущего элемента плюс шаг. Если текущий элемент
 *    больше элемента с которым сравниваем, производим их обмен и увеличиваем
 *    переменную для хранения числа обменов на единицу.
 *
 * 3) В случае если на предыдущем шаге количество обменов равно 0 и шаг равен 1, то
 *    заканчиваем алгоритм. В противном случае устанавливаем значение шага равным
 *    целой части от деления предыдущего шага на 1.247 (в случае если получается
 *    нулевой шаг, то устанавливаем его в 1) и возвращаемся к пункту 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/qaICQwA0aNM">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class CombSort {

    // Оптимальное значение фактора уменьшения равно
    // 1/(1-e-φ) ≈ 1.247, где е – основание натурального логарифма, а φ – золотое сечение.
    public static final double FACTOR = 1.247;

    public static void main(String[] args) {
        // 1 - sort int[]
        int[] array = { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        combSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println("");

        // 2 - sort double[]
        double[] array2 = { 7.91, 5.16, -5.21, 5.88, -11.98 };
        System.out.println(Arrays.toString(array2));

        combSort(array2);
        System.out.println(Arrays.toString(array2));
    }

    public static void combSort(int[] array) {
        int step = (int) (array.length / FACTOR);
        while (true) {
            int swapCount = 0;
            for (int i = 0; i + step < array.length; i++) {
                if (array[i] > array[i + step]) {
                    int temp = array[i];
                    array[i] = array[i + step];
                    array[i + step] = temp;
                    swapCount++;
                }
            }
            if (step == 1 && swapCount == 0) {
                break;
            }
            if (step > 1) {
                step = (int) (step / FACTOR);
            }
            if (step < 1) {
                step = 1;
            }
        }
    }

    public static void combSort(double[] array) {
        int step = (int) (array.length / FACTOR);
        while (true) {
            int swapCount = 0;
            for (int i = 0; i + step < array.length; i++) {
                if (array[i] > array[i + step]) {
                    double temp = array[i];
                    array[i] = array[i + step];
                    array[i + step] = temp;
                    swapCount++;
                }
            }
            if (step == 1 && swapCount == 0) {
                break;
            }
            if (step > 1) {
                step = (int) (step / FACTOR);
            }
            if (step < 1) {
                step = 1;
            }
        }
    }
}
