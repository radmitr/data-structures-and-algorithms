package sample;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[] { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(array));
        combSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void combSort(int[] array) {
        int step = (int) (array.length / 1.247);
        int swapCount = 1;
        for (;;) {
            swapCount = 0;
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
            step = (int) (step / 1.247);
            if (step < 1) {
                step = 1;
            }
        }
    }

}
