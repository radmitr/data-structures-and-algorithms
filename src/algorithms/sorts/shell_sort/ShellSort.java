package algorithms.sorts.shell_sort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка Шелла
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O(n^2)
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Некоторые сведения об алгоритме
 *
 * Алгоритм сортировки Шелла является усовершенствованным вариантом сортировки
 * вставками. Идея метода Шелла состоит в сравнении элементов, стоящих не только
 * рядом, но и на определённом расстоянии друг от друга. Иными словами — это
 * сортировка вставками с предварительными «грубыми» проходами.
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * 1) Выбирается начальное значение шага сортировки. К выбору шага стоит отнестись
 *    серьезно. От выбора шага зависит средняя сложность сортировки.
 *
 * 2) Начиная от первого элемента, выполняется сравнение элементов, стоящих друг от
 *    друга на расстоянии выбранного шага. Для значения элемента (в дальнейшем X)
 *    выбирается место в последовательности таких элементов, что:
 *
 *      ai ≤ X ≤ ai + h
 *
 *      h - используемый шаг
 *      ai, ai + h - значение элемента на i индексе, и на i+h индексе соответственно.
 *
 * 3) После окончания прохода с текущим шагом, шаг уменьшают. Если текущий шаг равен 1,
 *    алгоритм заканчивают, если нет — его уменьшают, согласно выбранному закону его
 *    изменения, и возвращаются к пункту 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/X-Pef9LHGos">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class ShellSort {

    public static void main(String[] args) {
        // 1 - array
        int[] array = { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        shellSort(array);
        System.out.println(Arrays.toString(array));

        // 2 - array2
        int[] array2 = { 32, 95, 16, 82, 24, 66, 35, 19, 75, 54, 40, 43 };
        System.out.println(Arrays.toString(array2));

        shellSort(array2);
        System.out.println(Arrays.toString(array2));
    }

    public static void shellSort(int[] array) {
        int step = array.length / 2;
        while (step > 0) {
            for (int i = step; i < array.length; i++) {
                for (int j = i; j >= step && array[j] < array[j - step]; j -= step) {
                    int temp = array[j];
                    array[j] = array[j - step];
                    array[j - step] = temp;
                }
            }
            step = step / 2;
        }
    }
}
