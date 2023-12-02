
public class BinarySearch {
    public static void main(String[] args) {

        int[] array = new int[] { -2, 0, 3, 5, 7, 9, 11, 15, 18 };
        System.out.println(binarySearch(array, 5));
        System.out.println(binarySearch(array, 8));

    }

    public static int binarySearch(int[] array, int requiredElement) {
        int l = 0;
        int r = array.length - 1;
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
}
