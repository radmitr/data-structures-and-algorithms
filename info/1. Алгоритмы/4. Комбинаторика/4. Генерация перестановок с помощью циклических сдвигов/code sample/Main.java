
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] arr = new int[] { 0, 1, 2};

        printAllPermutation(arr);

    }

    public static void printAllPermutation(int[] array) {
        int k = array.length - 1;
        int n = k;
        System.out.println(Arrays.toString(array));
        for (; k > 0;) {
            leftShift(array, k);
            if (array[k] != k) {
                System.out.println(Arrays.toString(array));
                k = n;
            } else {
                k = k - 1;
            }

        }
    }

    public static void leftShift(int[] array, int k) {
        int temp = array[0];
        for (int i = 0; i < k; i++) {
            array[i] = array[i + 1];
        }
        array[k] = temp;
    }

}
