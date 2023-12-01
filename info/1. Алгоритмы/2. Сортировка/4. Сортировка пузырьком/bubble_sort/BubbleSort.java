
import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        int[] array = new int[] { 5, 0, -2, 7, 3 };

        int sortedIndex = array.length;
        int numberOfSwap = 1;
        while (numberOfSwap > 0) {
            numberOfSwap = 0;
            for (int i = 0; i < sortedIndex - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    numberOfSwap += 1;
                }
            }
            sortedIndex -= 1;

        }
        System.out.println(Arrays.toString(array));

    }

}
