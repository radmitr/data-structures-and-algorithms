
public class InterpolationSearch {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] sequince = new int[] { -2, 0, 3, 5, 7, 9, 11, 15, 18 };
        int element = 5;
        System.out.println(interpolationSearch(sequince, element));

    }

    public static int interpolationSearch(int[] sequince, int element) {
        int l = 0;
        int r = sequince.length - 1;
        for (; sequince[l] < element && element < sequince[r];) {
            if (sequince[l] == sequince[r]) {
                break;
            }
            int index = (int) ((element - sequince[l]) * 1L * (l - r) / (sequince[l] - sequince[r]) + l);
            if (sequince[index] > element) {
                r = index - 1;
            } else if (sequince[index] < element) {
                l = index + 1;
            } else {
                return index;
            }
        }
        if (sequince[l] == element) {
            return l;
        }
        if (sequince[r] == element) {
            return r;
        }
        return -1;
    }

}
