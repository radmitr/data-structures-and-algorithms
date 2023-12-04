import java.util.Arrays;

public class NaraianaAlgorithm {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        permutationGenerator(3);
    }

    public static int findMaxIndex(int[] permutation) {
        for (int i = permutation.length - 2; i >= 0; i--) {
            if (permutation[i] < permutation[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    public static int findIndexBiggerElement(int[] permutation, int element) {
        for (int i = permutation.length - 1; i >= 0; i--) {
            if (permutation[i] > element) {
                return i;
            }
        }
        return -1;
    }

    public static void swap(int[] permutation, int i, int j) {
        int temp = permutation[i];
        permutation[i] = permutation[j];
        permutation[j] = temp;
    }

    public static void reverse(int[] permutation, int index) {
        int shift = index + 1;
        for (int i = 0; i < (permutation.length - shift) / 2; i++) {
            int temp = permutation[shift + i];
            permutation[shift + i] = permutation[permutation.length - i - 1];
            permutation[permutation.length - i - 1] = temp;
        }
    }

    public static void permutationGenerator(int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < permutation.length; i++) {
            permutation[i] = i + 1;
        }
        System.out.println(Arrays.toString(permutation));
        int index = findMaxIndex(permutation);
        for (; index != -1;) {
            int element = permutation[index];
            int swapIndex = findIndexBiggerElement(permutation, element);
            swap(permutation, index, swapIndex);
            reverse(permutation, index);
            System.out.println(Arrays.toString(permutation));
            index = findMaxIndex(permutation);
        }
    }

}
