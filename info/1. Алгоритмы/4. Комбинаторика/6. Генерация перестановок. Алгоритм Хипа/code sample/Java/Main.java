package sample;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[] { 1, 2, 3 };
        int k = array.length;
        permutation(array, k);
    }

    public static void permutation(int[] array, int k) {
        if (k == 1) {
            System.out.println(Arrays.toString(array));
            return;
        }
        for (int i = 0; i < k; i++) {
            permutation(array, k - 1);
            if (k % 2 == 0) {
                int temp = array[i];
                array[i] = array[k - 1];
                array[k - 1] = temp;
            } else {
                int temp = array[0];
                array[0] = array[k - 1];
                array[k - 1] = temp;
            }
        }
    }
}
