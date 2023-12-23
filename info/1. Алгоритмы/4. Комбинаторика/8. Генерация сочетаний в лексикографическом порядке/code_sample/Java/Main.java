

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        printCombination(3, 5);
    }

    public static void printCombination(int k, int n) {
        int[] comb = new int[k];
        for (int i = 0; i < comb.length; i++) {
            comb[i] = i + 1;
        }
        for (;;) {
            System.out.println(Arrays.toString(comb));
            int m = -1;
            for (int i = k - 1; i >= 0; i--) {
                if (comb[i] <= n - k + i) {
                    comb[i] += 1;
                    m = i;
                    break;
                }
            }
            if (m == -1) {
                break;
            }
            for (int i = m + 1; i < k; i++) {
                comb[i] = comb[i - 1] + 1;
            }
        }
    }

}
