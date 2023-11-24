package algoritms.theoretical_basis.arithmetic_operations.addition_subtraction;

/**
 * ------------------------------------------------------------------------------------------------
 * Сложение и Вычитание
 * ------------------------------------------------------------------------------------------------
 * Сложение
 *
 * Сложение — одна из основных бинарных математических операций (арифметических
 * действий) двух аргументов (слагаемых), результатом которой является новое число (сумма),
 * получаемое увеличением значения первого аргумента на значение второго аргумента.
 * a + b = c
 * ------------------------------------------------------------------------------------------------
 * У сложения есть несколько важных свойств:
 *   Коммутативность: a + b = b + a
 *   Ассоциативность: ( a + b ) + c = a + ( b + c )
 *   Наличие нуля: a + 0 = a
 * ------------------------------------------------------------------------------------------------
 * Вычитание
 *
 * Вычитание - одна из вспомогательных бинарных математических операций двух аргументов
 * (уменьшаемого и вычитаемого), результатом которой является новое число (разность), получаемое
 * уменьшением значения первого аргумента на значение второго аргумента.
 * Разность — число результат сложения которого с вычитаемым дает уменьшаемое.
 *
 * У вычитания есть несколько важных свойств:
 *   Антикоммутативность: a − b = − ( b − a )
 *   Неассоциативность: ( a − b ) − c ≠ a − ( b − c )
 *   Вычитание 0 (нулевого элемента) даёт число равное исходному: x − 0 = x
 * ------------------------------------------------------------------------------------------------
 * Реализация на Java
 *
 * Для примитивных типов используются операторы + и - . Для ссылочных типов
 * используются соответствующие вызовы методов.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/sT-3BCNvUrw">Ссылка на видео</>
 * ------------------------------------------------------------------------------------------------
 */
public class AdditionPrimitiveType {

    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        int c = a + b;
        double a1 = 2.5;
        double b1 = 3.5;
        double c1 = b1 - a1;
        System.out.println(c);
        System.out.println(c1);
    }
}
