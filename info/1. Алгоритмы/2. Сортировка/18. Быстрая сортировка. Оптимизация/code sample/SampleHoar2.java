package sample1;

import java.util.Arrays;

public class SampleHoar2 {
    public static void main(String[] args) {
        int[] array = new int[] { 0, 5, -2, 7, 3 };
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int lo, int hi) {
        if (hi - lo <= 32) {
            insertionSort(array, lo, hi);
            return;
        }
        int h = breakPartition(array, lo, hi);
        quickSort(array, lo, h - 1);
        quickSort(array, h + 1, hi);
    }

    public static int breakPartition(int[] array, int lo, int hi) {
        int i = lo + 1;
        int supportElement = array[lo];
        int j = hi;
        for (;;) {
            for (; i < hi && array[i] < supportElement;) {
                i += 1;
            }
            for (; array[j] > supportElement;) {
                j -= 1;
            }
            if (i >= j) {
                break;
            }
            swap(array, i++, j--);
        }
        swap(array, lo, j);
        return j;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void insertionSort(int[] array, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            int pasteElement = array[i];
            int j;
            for (j = i; j > begin; j--) {
                if (array[j - 1] <= pasteElement) {
                    break;
                }
                array[j] = array[j - 1];
            }
            array[j] = pasteElement;
        }
    }
}
