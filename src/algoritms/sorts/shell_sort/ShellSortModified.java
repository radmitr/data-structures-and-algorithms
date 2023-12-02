package algoritms.sorts.shell_sort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка Шелла
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Алгоритм сортировки Шелла.
 * Сложность по времени в наихудшем случае O(n^2)
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Некоторые сведения о алгоритме
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
 * 2) Начиная от первого элемента выполняется сравнение элементов стоящих друг от
 *    друга на расстоянии выбранного шага. Для значения элемента (в дальнейшем X)
 *    выбирается место в последовательности таких элементов, что
 *    ai ≤ X ≤ ai + h
 *    h - используемый шаг
 *    ai, ai + h - значение элемента на i индексе, и на i+h индексе соответственно.
 *
 * 3) После окончания прохода с текущим шагом, шаг уменьшают. Если текущий шаг равен 1
 *    алгоритм заканчивают, если нет его уменьшают согласно выбранному закону его
 *    изменения и возвращаются к пункту 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/X-Pef9LHGos">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class ShellSortModified {

    public static void main(String[] args) {
        // 1 - initialArray
        int[] initialArray = { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(initialArray));

        int[] array = new int[initialArray.length];

        System.arraycopy(initialArray, 0, array, 0, initialArray.length);
        shellSort(array, new ShellStep(array));
        System.out.println(Arrays.toString(array));

        System.arraycopy(initialArray, 0, array, 0, initialArray.length);
        shellSort(array, new HibbardStep(array));
        System.out.println(Arrays.toString(array));

        System.arraycopy(initialArray, 0, array, 0, initialArray.length);
        shellSort(array, new SedgewickStep(array));
        System.out.println(Arrays.toString(array));

        System.arraycopy(initialArray, 0, array, 0, initialArray.length);
        shellSort(array, new KnuthStep(array));
        System.out.println(Arrays.toString(array));

        // 2 - initialArray2
        int[] initialArray2 = { 32, 95, 16, 82, 24, 66, 35, 19, 75, 54, 40, 43 };
        System.out.println(Arrays.toString(initialArray2));

        int[] array2 = new int[initialArray2.length];

        System.arraycopy(initialArray2, 0, array2, 0, initialArray2.length);
        shellSort(array2, new ShellStep(array2));
        System.out.println(Arrays.toString(array2));

        System.arraycopy(initialArray2, 0, array2, 0, initialArray2.length);
        shellSort(array2, new HibbardStep(array2));
        System.out.println(Arrays.toString(array2));

        System.arraycopy(initialArray2, 0, array2, 0, initialArray2.length);
        shellSort(array2, new SedgewickStep(array2));
        System.out.println(Arrays.toString(array2));

        System.arraycopy(initialArray2, 0, array2, 0, initialArray2.length);
        shellSort(array2, new KnuthStep(array2));
        System.out.println(Arrays.toString(array2));
    }

    public static void shellSort(int[] array, StepGenerator stepGen) {
        int step = stepGen.nextStep();
        while (step > 0) {
            for (int i = step; i < array.length; i++) {
                for (int j = i; j >= step && array[j] < array[j - step]; j -= step) {
                    int temp = array[j];
                    array[j] = array[j - step];
                    array[j - step] = temp;
                }
            }
            step = stepGen.nextStep();
        }
    }
}

interface StepGenerator {
    int nextStep();
}

class ShellStep implements StepGenerator {
    private int step;

    public ShellStep(int[] array) {
        step = array.length / 2;
    }

    @Override
    public int nextStep() {
        step = step / 2;
        return step;
    }
}

class HibbardStep implements StepGenerator {
    private int i;

    public HibbardStep(int[] array) {
        while ((int) (Math.pow(2, i) - 1) < array.length) {
            i++;
        }
    }

    @Override
    public int nextStep() {
        i--;
        return (int) (Math.pow(2, i) - 1);
    }
}

class SedgewickStep implements StepGenerator {
    private int i;

    public SedgewickStep(int[] array) {
        long number = (long) (9 * (Math.pow(2, i) - Math.pow(2, i / 2)) + 1);
        while (number < array.length) {
            i++;
            if (i % 2 == 0) {
                number = (long) (9 * (Math.pow(2, i) - Math.pow(2, i / 2)) + 1);
            } else {
                number = (long) (8 * Math.pow(2, i) - 6 * Math.pow(2, (i + 1) / 2) + 1);
            }
        }
    }

    @Override
    public int nextStep() {
        i--;
        if (i <= -1) {
            return 0;
        }
        if (i % 2 == 0) {
            return (int) (9 * (Math.pow(2, i) - Math.pow(2, i / 2)) + 1);
        } else {
            return (int) (8 * Math.pow(2, i) - 6 * Math.pow(2, (i + 1) / 2) + 1);
        }
    }
}

    // Variant 1
class KnuthStep implements StepGenerator {
    private int i;

    public KnuthStep(int[] array) {
        while ((Math.pow(3, i) - 1) / 2 < array.length / 3) {
            i++;
        }
    }

    @Override
    public int nextStep() {
        int step = (int) ((Math.pow(3, i) - 1) / 2);
        i--;
        return step;
    }
}

    // Variant 2
//class KnuthStep implements StepGenerator {
//    private int i;
//
//    public KnuthStep(int[] array) {
//        while ((Math.pow(3, i) - 1) / 2 <= array.length / 3) {
//            i++;
//        }
//    }
//
//    @Override
//    public int nextStep() {
//        i--;
//        int step = (int) ((Math.pow(3, i) - 1) / 2);
//        return step;
//    }
//}

    // Variant 3
//class KnuthStep implements StepGenerator {
//    private int i;
//
//    public KnuthStep(int[] array) {
//        while ((Math.pow(3, i) - 1) / 2 < (double) array.length / 3) {
//            i++;
//        }
//    }
//
//    @Override
//    public int nextStep() {
//        i--;
//        int step = (int) ((Math.pow(3, i) - 1) / 2);
//        return step;
//    }
//}
