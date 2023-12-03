package algoritms.combinatorics.permutations;

import java.util.Arrays;

public class JohnsonTrotterAlgorithm {

    public static void main(String[] args) {
        permutationGenerator(4);
    }

    public static int findMaxMobileElement(int[] permutation, int[] direction) {
        int index = -1;
        for (int i = 0; i < permutation.length; i++) {
            int nextIndex = i + direction[i];
            if (nextIndex >= 0 && nextIndex < permutation.length) {
                if (permutation[i] > permutation[nextIndex]) {
                    if (index == -1) {
                        index = i;
                    } else {
                        if (permutation[i] > permutation[index]) {
                            index = i;
                        }
                    }
                }
            }
        }
        return index;
    }

    public static void changeDirection(int[] permutation, int[] direction, int mobileElement) {
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] > mobileElement) {
                direction[i] = direction[i] * (-1);
            }
        }
    }

    public static void swap(int[] permutation, int[] direction, int i, int j) {
        int permutationTemp = permutation[i];
        permutation[i] = permutation[j];
        permutation[j] = permutationTemp;

        int directionTemp = direction[i];
        direction[i] = direction[j];
        direction[j] = directionTemp;
    }

    public static void permutationGenerator(int n) {
        int[] permutation = new int[n];
        int[] direction = new int[n];
        for (int i = 0; i < permutation.length; i++) {
            permutation[i] = i + 1;
            direction[i] = -1;
        }
        System.out.println(Arrays.toString(permutation));
        int mobileElementIndex = findMaxMobileElement(permutation, direction);
        while (mobileElementIndex != -1) {
            int mobileElement = permutation[mobileElementIndex];
            int nextIndex = mobileElementIndex + direction[mobileElementIndex];
            swap(permutation, direction, mobileElementIndex, nextIndex);
            changeDirection(permutation, direction, mobileElement);
            System.out.println(Arrays.toString(permutation));
            mobileElementIndex = findMaxMobileElement(permutation, direction);
        }
    }
}
