package sample1;

import java.util.Arrays;

public class SampleLomuto2 {

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
        int j = lo;
        int supportElement = array[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (array[i] < supportElement) {
                j+=1;
                swap(array, i, j);
            }
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
