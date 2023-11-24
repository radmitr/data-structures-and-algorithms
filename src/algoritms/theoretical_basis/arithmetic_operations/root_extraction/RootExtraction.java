package algoritms.theoretical_basis.arithmetic_operations.root_extraction;

/**
 * ------------------------------------------------------------------------------------------------
 * Извлечение корня
 * ------------------------------------------------------------------------------------------------
 * Корень n-й степени из числа a определяется как такое число b, что b^n = a. Здесь n —
 * натуральное число, называемое показателем корня (или степенью корня).
 * b = nrt(a)
 *
 * Свойства корня:
 *   nrt(a^n) = { n−четное, a; n−нечетное , |a| }
 *   nrt(a ⋅ b) = nrt(a) ⋅ nrt(b)
 *   nrt(a^m) = a^(m / n)
 *   nrt(a) = a^(1 / n)
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/sT-3BCNvUrw">Ссылка на видео</>
 * ------------------------------------------------------------------------------------------------
 */
public class RootExtraction {

    public static void main(String[] args) {
        double x = 3.5;
        double a = Math.sqrt(x);
        double b = Math.cbrt(x);
        System.out.println(a);
        System.out.println(b);
    }
}
