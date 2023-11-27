package algoritms.theoretical_basis.memoization.sample1;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * ------------------------------------------------------------------------------------------------
 * Мемоизация
 * ------------------------------------------------------------------------------------------------
 * Мемоизация — сохранение результатов выполнения функций для предотвращения
 * повторных вычислений. Применяется для увеличения скорости выполнения программ.
 * Основной механизм реализации - перед вызовом функции проверяется, вызывалась ли
 * функция ранее:
 *   ● если не вызывалась, то функция вызывается, и результат её выполнения
 *     сохраняется;
 *   ● если вызывалась, то используется сохранённый результат.
 *
 * Мемоизацию можно отнести к разновидности кеширования данных. При работе с
 * рекурсивными функциями может повышать производительность.
 * ------------------------------------------------------------------------------------------------
 * Общие идее при реализации мемоизации
 *
 * Нужно создать элемент (в дальнейшем хранилище) который способен хранить пары значений с
 * последующим быстрым извлечением. Для этого идеально подойдут ассоциативные массивы
 * (словарь, карта) в качестве ключа использовать значение параметра функции, в качестве значения
 * результат ее работы. В таком случае при вызове функции сначала проверяем нет ли таких
 * параметров в хранилище (проверяем наличие такого ключа) и если они там есть, то возвращаем
 * значение не выполняя при этом тело функции, если же таких параметров в хранилище нет, то
 * выполняем тело функции после чего записываем в хранилище пару ключ которой равен
 * параметрам, а значение вычисленному результату. После чего возвращаем вычисленное значение.
 * ------------------------------------------------------------------------------------------------
 * Замечания по использованию мемоизации
 *
 * Общие рекомендации при применении мемоизации:
 *   ● Для того, чтобы функцию можно было подвергнуть мемоизации, она должна быть чистой:
 *     ● детерминированной (т.е. при одном и том же наборе параметров функции должна
 *       возвращать одинаковое значение)
 *     ● без побочных эффектов (т.е. не должна влиять на состояние системы).
 *   ● Мемоизация — это компромисс между производительностью и потреблением памяти.
 * Мемоизация хороша для функций, имеющих сравнительно небольшой диапазон входных
 * значений, что позволяет достаточно часто, при повторных вызовах функций, задействовать
 * значения, найденные ранее, не тратя на хранение данных слишком много памяти.
 *   ● Функции с мемоизацией хорошо показывают себя там, где выполняются сложные,
 *     ресурсоёмкие вычисления. Здесь данная техника может значительно повысить
 *     производительность решения.
 * ================================================================================================
 * Описание реализации
 *
 * В этом примере была продемонстрирована реализация мемоизации с помощью
 * хранилища в виде статического поля класса (Map<Integer, BigInteger> mem). Каждый статический
 * метод этого класса имеет доступ к этому полю. В методе вычисляющем факториал сначала
 * выполняется проверка на наличие такого ключа в карте, и если такое значение есть то
 * возвращается значение с ним связанное. Если нет, то сначала вычисляется факториал (циклическим
 * или рекурсивным способом) и после вычисления в карту заносится как число, так и вычисленный
 * факториал. Таким образом при следующем вызове, вычисления уже не будут выполнятся и результат
 * будет взят из карты.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/VwmJ-Lj1Qsk">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class FactorialMemoization {

	public static Map<Integer, BigInteger> mem = new HashMap<>();

	public static void main(String[] args) {
		// 1 - factorialCyclical()
		int number1 = 5;
		BigInteger fact = factorialCyclical(number1);
		System.out.printf("%d! = %d\n", number1, fact);
		System.out.println();

		fact = factorialCyclical(5);
		System.out.printf("%d! = %d\n", number1, fact);
		System.out.println("-----------------------------------");

		// 2 - factorialRecursive()
		int number2 = 8;
		fact = factorialRecursive(number2);
		System.out.printf("%d! = %d\n", number2, fact);
		System.out.println();

		int number3 = 7;
		fact = factorialRecursive(number3);
		System.out.printf("%d! = %d\n", number3, fact);
	}

	public static BigInteger factorialCyclical(int number) {
		BigInteger fact = mem.get(number);
		if (fact != null) {
			System.out.printf("Cache: %d=%d\n", number, fact);
			return fact;
		} else {
			fact = BigInteger.ONE;
			for (int i = 1; i <= number; i++) {
				fact = fact.multiply(BigInteger.valueOf(i));
			}
			mem.put(number, fact);
			return fact;
		}
	}

	public static BigInteger factorialRecursive(int number) {
		if (number <= 1) {
			return BigInteger.ONE;
		} else if (mem.containsKey(number)) {
			BigInteger fact = mem.get(number);
			System.out.printf("Cache: {%d=%d}\n", number, fact);
			return fact;
		} else {
			BigInteger fact = BigInteger.valueOf(number).multiply(factorialRecursive(number - 1));
			mem.put(number, fact);
			return fact;
		}
	}
}