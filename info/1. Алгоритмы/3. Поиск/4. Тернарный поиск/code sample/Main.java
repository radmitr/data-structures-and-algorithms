package com.gmail.tsa;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] arr = new int[] { -2, 0, 3, 5, 7, 9, 11, 15, 18 };
        System.out.println(Arrays.toString(arr));
        System.out.println(ternarySearch(arr, 5));

    }

    public static int ternarySearch(int[] array, int element) {
        int r = array.length - 1;
        int l = 0;
        for (; l <= r;) {
            int h = (r - l) / 3;
            int m1 = l + h;
            int m2 = r - h;

            if (array[m1] == element) {
                return m1;
            }
            if (array[m2] == element) {
                return m2;
            }
            if (array[m1] < element && element < array[m2]) {
                l = m1 + 1;
                r = m2 - 1;
            } else if (element < array[m1]) {
                r = m1 - 1;
            } else {
                l = m2 + 1;
            }
        }
        return -1;
    }

}
