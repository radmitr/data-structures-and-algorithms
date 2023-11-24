public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array1 = new int[] { 3, 6, 1 };

		System.out.println(generateHash(array1, 1 << 16));

	}

	public static int arrayToNumber(int[] array) {
		int result = array[0];
		for (int i = 0; i < array.length - 1; i++) {
			result = (result << 5) - result + array[i + 1];
		}
		return result;
	}

	public static int generateHash(int[] array, int m) {
		int number = Math.abs(arrayToNumber(array));
		double a = 0.6180339887;
		return (int) (m * ((number * a) % 1));

	}

}
