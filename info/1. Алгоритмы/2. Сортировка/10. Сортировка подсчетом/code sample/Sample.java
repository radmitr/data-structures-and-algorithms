package sample1;

import java.util.Arrays;

public class Sample {

    public static void main(String[] args) {

        int[] sort = new int[] { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(sort));
        countingSort(sort);
        System.out.println(Arrays.toString(sort));

    }

    public static void countingSort(int[] sort) {
        int[] minMax = findMinMax(sort);
        int minValue = minMax[0];
        int maxValue = minMax[1];
        int[] support = new int[maxValue - minValue + 1];
        for (int element : sort) {
            support[element - minValue] += 1;
        }
        int index = 0;
        for (int i = 0; i < support.length; i++) {
            for (int j = 0; j < support[i]; j++) {
                sort[index] = i + minValue;
                index += 1;
            }

        }
    }

    public static int[] findMinMax(int[] array) {
        int min = array[0];
        int max = array[0];
        for (int element : array) {
            if (min > element) {
                min = element;
            }
            if (max < element) {
                max = element;
            }
        }
        return new int[] { min, max };
    }
}
