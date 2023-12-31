package algorithms.basis.operations.division;

/**
 * ------------------------------------------------------------------------------------------------
 * Деление
 * ------------------------------------------------------------------------------------------------
 * Деление — действие, обратное умножению.
 * a / b = c
 * a — делимое
 * b — делитель
 * c — частное
 * Нахождение частного сводится к нахождению числа которое при умножении на
 * делитель дает делимое.
 * ------------------------------------------------------------------------------------------------
 * Свойства деления
 *
 *   Не коммутативно: a : b ≠ b : a
 *   Не ассоциативно: ( a : b ) : c ≠ a : ( b : c )
 *   Дистрибутивность: ( a + b ) : x = ( a : x ) + ( b : x )
 *   Нейтральный элемент справа: x : 1 = x
 *
 * Существует единственный обратный элемент, получаемый делением единицы на число,
 * что даёт число, обратное исходному.
 *   Обратный элемент: 1 : x = x^−1 , x ≠ 0
 *
 *   Нулевой элемент слева: 0 : x = 0
 *   Деление на ноль 0 (нулевой элемент) не определено. Деление на ноль: x : 0 = ∞
 *   Деление на противоположный элемент даёт минус единицу:  x : ( − x ) = − 1
 * ------------------------------------------------------------------------------------------------
 * Деление целых чисел. Вычисление остатка.
 *
 * Для целых чисел деление определяться следующим образом.
 * a = b * r + q
 * a — делимое (целое)
 * b — делитель (целое)
 * r — неполное частное (целое)
 * q — остаток от деления. 0 ≤ q < |b| (натуральное).
 * Важно! Остаток всегда должен быть положительным.
 * ------------------------------------------------------------------------------------------------
 * Реализация на Java
 *
 * Для примитивных типов используется оператор / (деление) и % (вычисление
 * остатка). Для ссылочных типов используются соответствующие вызовы методов.
 * ------------------------------------------------------------------------------------------------
 * Особенности реализации
 *
 * Для примитивных типов и BigInteger деление и вычисление остатка, не всегда
 * согласованно с математическим определением.
 * ------------------------------------------------------------------------------------------------
 * Реализация дополнительных методов (≥ 1.8)
 *
 * Существуют методы для вычисления неполного частного Math.floorDiv(a, b) и
 * остатка от деления Math.floorMod(a, b).
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/sT-3BCNvUrw">Ссылка на видео</>
 * ------------------------------------------------------------------------------------------------
 */
public class DivisionPrimitiveTypeDouble {

    public static void main(String[] args) {
        double a = 5.2;
        double b = 2.1;
        double c = a / b;
        System.out.println(c);
    }
}
