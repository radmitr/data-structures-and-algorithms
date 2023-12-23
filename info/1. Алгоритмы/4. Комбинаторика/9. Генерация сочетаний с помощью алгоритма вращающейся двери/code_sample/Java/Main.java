package sample;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        printAllCombination(3, 5);

    }

    public static void printAllCombination(int k, int n) {
        int[] c = new int[k + 1];
        for (int i = 0; i < k; i++) {
            c[i] = i;
        }
        c[k] = n - 1;
        int step = 2;
        int j = 1;
        for (; step != -1;) {
            switch (step) {
            case 2:
                printCurrentCombination(c, k);
                step = 3;
                break;
            case 3:
                int[] r3 = checkFirstElement(c, k);
                step = r3[0];
                j = r3[1];
                break;
            case 4:
                int[] r4 = decreasElement(c, j);
                step = r4[0];
                j = r4[1];
                break;
            case 5:
                int[] r5 = enlargementElement(c, j, k);
                step = r5[0];
                j = r5[1];
                break;
            }
        }
    }

    public static void printCurrentCombination(int[] c, int k) {
        System.out.println(Arrays.toString(Arrays.copyOf(c, k)));
    }

    public static int[] checkFirstElement(int[] c, int k) {
        int step;
        int j = 1;
        if (k % 2 != 0) {
            if (c[0] + 1 < c[1]) {
                c[0] += 1;
                step = 2;
            } else {
                step = 4;
            }
        } else {
            if (c[0] > 0) {
                c[0] -= 1;
                step = 2;
            } else {
                step = 5;
            }
        }
        return new int[] { step, j };
    }

    public static int[] decreasElement(int[] c, int j) {
        int step;
        if (c[j] > j) {
            c[j] = c[j - 1];
            c[j - 1] = j - 1;
            step = 2;
        } else {
            j += 1;
            step = 5;
        }
        return new int[] { step, j };
    }

    public static int[] enlargementElement(int[] c, int j, int k) {
        int step;
        if (c[j] + 1 <= c[j + 1]) {
            c[j - 1] = c[j];
            c[j] += 1;
            step = 2;
        } else {
            j += 1;
            if (j < k) {
                step = 4;
            } else {
                step = -1;
            }
        }
        return new int[] { step, j };
    }

}