package algoritms.sorts.cocktail_sort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка перемешиванием
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Сложность по времени в наихудшем случае O(n^2)
 * ------------------------------------------------------------------------------------------------
 * Описание сути алгоритма
 *
 * Сортировка перемешиванием является модификацией алгоритма сортировки пузырьком.
 * Сутью этой модификации является следующие наблюдения.
 * ● Первая перестановка в последовательности начинается с определенного индекса и при
 *   следующем проходе можно начинать с этого индекса, а не с начала последовательности.
 * ● При проходе в одну сторону элемент сдвигается на одну позицию к соответствующему краю
 *   последовательности. И если чередовать проходы с начала последовательности до ее конца с
 *   проходами в обратном направлении то можно обеспечить более быстрое «всплывание»
 *   элемента к нужному краю последовательности.
 *
 * Именно реализация этих изменений при работе алгоритма сортировки пузырьком и получила
 * название сортировки перемешиванием.
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Объявляем две дополнительные переменные для хранения границ с которых нужно начинать
 *    проход по элементам последовательности (left и right соответственно) и переменную для
 *    контроля наличия факта обмена (control). Устанавливаем значение left = 0, right — индексу
 *    последнего элемента последовательности, control = right. Перейти к пункту 2.
 *
 * 2) Начиная от left и до right выполняем проход по элементам последовательности. Если текущий
 *    элемент больше следующего элемента то провести их обмен и установить значение control
 *    равной индексу текущего элемента. После прохода установить значение right равный control.
 *    Перейти к пункту 3.
 *
 * 3) Начиная от right до left выполняем обратный проход по элементам последовательности. Если
 *    текущий элемент меньше предыдущего то выполняем их обмен и устанавливаем значение
 *    control равным индексу текущего элемента. После прохода устанавливаем значение left равным
 *    control. Перейти в пункту 4.
 *
 * 4) Если left < right вернуться к пункту 2. В противном случае закончить алгоритм.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/qB4NZ_2jpgQ">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class CocktailSort {

	public static void main(String[] args) {
		int[] array = { 5, 0, -2, 7, 3 };
		System.out.println(Arrays.toString(array));

		cocktailSort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void cocktailSort(int[] sequince) {
		int left = 0;
		int right = sequince.length - 1;
		int control = right;
		while (left < right) {
			for (int i = left; i < right; i++) {
				if (sequince[i] > sequince[i + 1]) {
					swap(sequince, i, i + 1);
					control = i;
				}
			}
			right = control;
			for (int i = right; i > left; i--) {
				if (sequince[i] < sequince[i - 1]) {
					swap(sequince, i, i - 1);
					control = i;
				}
			}
			left = control;
		}
	}

	public static void swap(int[] sequince, int i, int j) {
		int temp = sequince[i];
		sequince[i] = sequince[j];
		sequince[j] = temp;
	}
}
