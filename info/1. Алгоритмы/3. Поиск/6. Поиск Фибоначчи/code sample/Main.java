
public class Main {
    public static void main(String[] args) {

        int[] sequince = new int[] { -2, 0, 3, 5, 7, 9, 11, 15, 18, 21 };
        int element = 7;

        FibonacciSearch fs = new FibonacciSearch();

        int index = fs.search(sequince, element);

        System.out.println(index);

    }
}
