package algoritms.compute;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Схема Горнера
 * ------------------------------------------------------------------------------------------------
 * Схема Горнера — алгоритм вычисления значения полинома в определенной точке. Алгоритм
 * назван в честь британского математика В. Горнера, который впервые опубликовал этот алгоритм.
 * ------------------------------------------------------------------------------------------------
 * Некоторые применения схемы Горнера
 *
 * 1) Теорема Безу
 *
 * Согласно теореме Безу остаток от деления полинома на двучлен вида (x-c) равен значению
 * полинома в точке c.
 *
 * 2) Быстрое вычисление значений в произвольной системе счисления
 *
 * Число в позиционной системе счисления (с произвольной степенью) представимо в виде
 * полинома. Использование схемы Горнера позволит быстро вычислить значение.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/g5vcdzI6uPg">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class GornerScheme {

    public static void main(String[] args) {

        double[] polyCoeff = new double[] { 5, 1, -3, 2, 5 };

        double result = gornerScheme(polyCoeff, 2);
        System.out.println(result);

        System.out.println(Arrays.toString(calculateQuotientPolynomial(polyCoeff, 2)));
	}

	public static double gornerScheme(double[] polyCoeff, double c) {
		double result = polyCoeff[0];
		for (int i = 0; i < polyCoeff.length - 1; i++) {
			result = result * c + polyCoeff[i + 1];
		}
		return result;
	}

	public static double[] calculateQuotientPolynomial(double[] polyCoeff, double c) {
		double[] coeff = new double[polyCoeff.length - 1];
		coeff[0] = polyCoeff[0];
		for (int i = 1; i < coeff.length; i++) {
			coeff[i] = c * coeff[i - 1] + polyCoeff[i];
		}
		return coeff;
	}
}
