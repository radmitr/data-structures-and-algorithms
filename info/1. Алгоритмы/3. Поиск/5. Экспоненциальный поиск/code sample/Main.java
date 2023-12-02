
public class Main {

    public static void main(String[] args) {
        int[] array = new int[] { -2, 0, 3, 5, 7, 9, 11, 15, 18 };
        int requiredElement = 5;
        System.out.println(exponentialSearch(array, requiredElement));

    }

    public static int binarySearch(int[] array, int requiredElement, int l, int r) {
        for (; l <= r;) {
            int m = l + (r - l) / 2;
            int element = array[m];
            if (requiredElement == element) {
                return m;
            }
            if (element < requiredElement) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    public static int exponentialSearch(int[] array, int requiredElement) {
        long border = 1;
        for (; border < array.length && array[(int) border] < requiredElement;) {
            border *= 2;
        }
        int l = (int) (border / 2);
        int r;
        if (border > array.length - 1) {
            r = array.length - 1;
        } else {
            r = (int) border;
        }
        return binarySearch(array, requiredElement, l, r);
    }

}
