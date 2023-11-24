import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

    double[] polyCoff = new double[] { 5, 1, -3, 2, 5 };

		double result = gornerScheme(polyCoff, 2);

		System.out.println(result);

		System.out.println(Arrays.toString(quotientPolynomCalculate(polyCoff, 2)));
	}

	public static double gornerScheme(double[] polyCoff, double c) {
		double result = polyCoff[0];
		for (int i = 0; i < polyCoff.length - 1; i++) {
			result = result * c + polyCoff[i + 1];
		}
		return result;
	}

	public static double[] quotientPolynomCalculate(double[] polyCoff, double c) {
		double[] coff = new double[polyCoff.length - 1];
		coff[0] = polyCoff[0];
		for (int i = 1; i < coff.length; i++) {
			coff[i] = c * coff[i - 1] + polyCoff[i];
		}
		return coff;
	}

}
