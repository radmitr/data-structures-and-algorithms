
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] array = new String[] { "1", "2", "3", "4" };

        printAllPermutation(array);

    }

    public static <T> void printAllPermutation(T[] array) {
        T[] aSequince = Arrays.copyOf(array, array.length);
        int[] bTable = new int[aSequince.length];
        int[] cTable = new int[aSequince.length + 1];
        int k = 1;
        int j = 1;
        for (int i = 0; i < bTable.length; i++) {
            bTable[i] = i;
        }
        for (;;) {
            System.out.println(Arrays.toString(aSequince));
            k = 1;
            for (; cTable[k] == k;) {
                cTable[k++] = 0;
            }
            if (k == aSequince.length) {
                break;
            }
            cTable[k] += 1;
            swap(aSequince, 0, bTable[k]);
            j = 1;
            k = k - 1;
            for (; j < k;) {
                swap(bTable, k--, j++);
            }
        }
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
