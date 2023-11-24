package algoritms.searches.fibonacci_search;

public class Main {

	public static void main(String[] args) {

		int[] sequence = { -2, 0, 3, 5, 7, 9, 11, 15, 18, 21 };
		int element = 7;

		FibonacciSearch fs = new FibonacciSearch();

		int index = fs.search(sequence, element);

		System.out.println(index);
	}
}
