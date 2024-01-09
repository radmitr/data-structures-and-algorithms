import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int[] array = new int[] { 5, 0, -2, 7, 3 };

        heapSort(array);

        System.out.println(Arrays.toString(array));

    }

    public static void heapSort(int[] array) {
        int n = array.length / 2;
        int lastIndex = array.length;
        for (int i = n; i >= 0; i--) {
            siftDOwn(array, i, lastIndex);
        }
        lastIndex -= 1;
        for (; lastIndex > 0;) {
            swap(array, 0, lastIndex);
            siftDOwn(array, 0, lastIndex);
            lastIndex -= 1;

        }

    }

    public static void siftDOwn(int[] array, int i, int lasIndex) {
        for (;;) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            int j = i;
            if (leftIndex < lasIndex && array[leftIndex] > array[j]) {
                j = leftIndex;
            }
            if (rightIndex < lasIndex && array[rightIndex] > array[j]) {
                j = rightIndex;
            }
            if (i != j) {
                swap(array, i, j);
                i = j;
            } else {
                break;
            }
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
