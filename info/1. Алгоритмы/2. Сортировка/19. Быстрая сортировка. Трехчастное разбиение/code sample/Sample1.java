package sample1;

import java.util.Arrays;

public class Sample1 {
    public static void main(String[] args) {

        int[] array = new int[] { 0, 5, -2, 7, 3 };
        quickSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int[] part = breakPartition(array, lo, hi);
        quickSort(array, lo, part[0] - 1);
        quickSort(array, part[1] + 1, hi);
    }

    public static int[] breakPartition(int[] array, int lo, int hi) {
        int i = lo + 1;
        int lt = lo;
        int gt = hi;
        int supportElement = array[lo];
        for (; i <= gt;) {
            if (array[i] < supportElement) {
                swap(array, i, lt);
                i += 1;
                lt += 1;
            } else if (array[i] > supportElement) {
                swap(array, i, gt);
                gt -= 1;
            } else {
                i += 1;
            }
        }
        return new int[] { lt, gt };
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
