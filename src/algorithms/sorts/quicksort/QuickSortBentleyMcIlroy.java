package algorithms.sorts.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * ------------------------------------------------------------------------------------------------
 * Быстрая сортировка. Трехчастное разбиение.
 * ------------------------------------------------------------------------------------------------
 * Трехчастное разбиение
 *
 * На практике в большинстве случаев в сортируемой последовательности содержатся элементы
 * с одинаковыми ключами сортировки. Но, при разбиении последовательности на две части,
 * элементы, равные опорному элементу (по сути в дальнейшей сортировке не нуждающиеся), все
 * равно попадают в левую или правую часть и подвергаются сортировке. Этой проблемы можно
 * избежать если разбивать последовательность на три части (элементы меньше опорного, элементы
 * больше опорного, элементы равны опорному) и, для дальнейшей сортировки, вызывать только
 * подпоследовательности, с элементами меньше опорного и элементами больше опорного.
 * ================================================================================================
 * Трехчастное разбиение, предложенное Бентли и Макилроем
 *
 * Интересный метод трехчастного разбиения (three-way partitioning) был предложен в
 * 1993 году Бентли и Макилроем (Bentley and McIlroy). Он представляет собой следующую
 * модификацию стандартной схемы разбиения: помещаем ключи из левой части
 * подпоследовательности равные центральному элементу, в левый конец, а ключи из
 * правой части подпоследовательности, равные центральному элементу — в правый конец.
 *
 * После того как i и j пересекутся, то вернуть элементы с краев в центр.
 * ------------------------------------------------------------------------------------------------
 * Разбиение Бентли и Макилроя
 *
 * 1) В качестве опорного элемента выбирается первый элемент последовательности (supportElement).
 *    Объявляются переменные для хранения индексов (в дальнейшем i=lo+1, j=hi, p=lo, g=hi+1).
 *    Переходим к пункту 2.
 *
 * 2) Начиная со второго элемента, ищем элемент такой что a[i] ≥ supportElement, начиная с конца
 *    последовательности ищем такой элемент a[j] ≤ supportElement. Если i ≥ j. Переходим к 5.
 *    В противном случае переходим к 3.
 *
 * 3) Выполняем обмен a[i], a[j]. Если после обмена:
 *      ● a[i]=supportElement. Увеличиваем p на единицу. Выполняем обмен a[i], a[p]
 *      ● a[j]=supportElement. Уменьшаем g на единицу. Выполняем обмен a[j], a[g]
 *    Переходим к 4.
 *
 * 4) Увеличиваем i на единицу, уменьшаем j на единицу. Переходим к 2.
 *
 * 5) Производим обмен от lo до p индекса с элементом на индексе j, при каждом обмене j уменьшаем
 *    на единицу. Производим обмен от hi до g индекса (в обратном порядке) с элементом на индексе i
 *    при каждом обмене увеличиваем i. Вернуть {j,i}
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/Bp35SUE6MDU">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class QuickSortBentleyMcIlroy {

    public static void main(String[] args) {
        // 1 - array
        int[] array = { 0, -1, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        quickSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println();

        // 2 - array2
        int[] array2 = { 0, -1, -2, 0, 7, 3 };
        System.out.println(Arrays.toString(array2));

        quickSort(array2);
        System.out.println(Arrays.toString(array2));
        System.out.println();

        // 3 - array3
        int[] array3 = { 0, 5, -2, 7, 0, 3, 3, 0, -5, -6, 1, 2, 3, 0, -1, -1, -1 };
        System.out.println(Arrays.toString(array3));

        quickSort(array3);
        System.out.println(Arrays.toString(array3));
        System.out.println();

        // 4 - array4
        Random random = new Random();
        int arraySize = 1_000;
        int bound = 10;
        int[] array4 = new int[arraySize];

        for (int i = 0; i < array4.length; i++) {
            array4[i] = random.nextInt(bound + 1);
        }
        System.out.println(Arrays.toString(array4));

        quickSort(array4);
        System.out.println(Arrays.toString(array4));
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int[] part = partition(array, lo, hi);
        quickSort(array, lo, part[0]);
        quickSort(array, part[1], hi);
    }

    public static int[] partition(int[] array, int lo, int hi) {
        int i = lo + 1;
        int p = lo;
        int j = hi;
        int g = hi + 1;
        int supportElement = array[lo];
        while (true) {
            while (i < hi && array[i] < supportElement) {
                i++;
            }
            while (array[j] > supportElement) {
                j--;
            }
            if (i >= j) {
                if (i == j && array[i] == supportElement) {
                    swap(array, i, ++p);
                }
                break;
            }
            swap(array, i, j);
            if (array[i] == supportElement) {
                swap(array, i, ++p);
            }
            if (array[j] == supportElement) {
                swap(array, j, --g);
            }
            i++;
            j--;
        }
        for (int k = lo; k <= p; k++) {
            swap(array, k, j--);
        }
        for (int k = hi; k >= g; k--) {
            swap(array, k, i++);
        }
        return new int[] { j, i };
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
