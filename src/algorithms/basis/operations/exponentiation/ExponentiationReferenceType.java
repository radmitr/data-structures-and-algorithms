package algorithms.basis.operations.exponentiation;

import java.math.BigInteger;

/**
 * ------------------------------------------------------------------------------------------------
 * Возведение в степень
 * ------------------------------------------------------------------------------------------------
 * Возведение в степень — арифметическая операция, первоначально определяемая
 * как результат многократного умножения числа на себя.
 * Степень с основанием a и натуральным показателем b обозначается как a^b
 * ------------------------------------------------------------------------------------------------
 * Свойства возведения в степень
 *
 * Не коммутативно: a^b ≠ b^a
 * Не ассоциативно: (a^b)^c ≠ a^(b^c)
 * Существование единицы: (a)^1 = a
 * Возведение в степень 0: (a)^0 = 1
 * Дистрибутивность относительно умножения: (a ⋅ b)^c = a^c ⋅ b^c
 *                                          a^b ⋅ a^c = a^(b+c)
 *                                          (a^b)^c = a^(b ⋅ c)
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/sT-3BCNvUrw">Ссылка на видео</>
 * ------------------------------------------------------------------------------------------------
 */
public class ExponentiationReferenceType {

    public static void main(String[] args) {
        BigInteger a = new BigInteger("3");
        BigInteger b = new BigInteger("2");
        BigInteger c = a.pow(b.intValue());
    }
}
