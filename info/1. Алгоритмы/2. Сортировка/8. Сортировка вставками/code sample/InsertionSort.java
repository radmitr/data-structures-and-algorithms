
import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

        int[] array = new int[] { 5, 0, -2, 7, 3 };

        for (int i = 0; i < array.length; i++) {
            int pasteElement = array[i];
            int j;
            for (j = i; j > 0; j--) {
                if (array[j - 1] <= pasteElement) {
                    break;
                }
                array[j] = array[j - 1];
            }
            array[j] = pasteElement;
        }

        System.out.println(Arrays.toString(array));
    }

}
