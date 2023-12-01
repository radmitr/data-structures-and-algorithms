
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        
        int[] array = new int[] { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(array));
        gnomeSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void gnomeSort(int[] array) {
        int index = 1;
        int i = 0;
        for (; i < array.length - 1;) {
            if (array[i] <= array[i + 1]) {
                i = index;
                index += 1;
            } else {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                i = i - 1;
                if (i < 0) {
                    i = index;
                    index += 1;
                }
            }
        }
    }

}
