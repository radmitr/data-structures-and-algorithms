package algoritms.theoretical_basis.recursion;

/**
 * ------------------------------------------------------------------------------------------------
 * Рекурсия
 * ------------------------------------------------------------------------------------------------
 * Рекурсия — описание какого то объекта или процесса внутри этого же объекта или
 * процесса. Т.е. рекурсия это описание объекта которые содержит подобный себе объект
 * внутри, или процесса который в свою очередь состоит из таких же процессов. И хотя
 * определение рекурсии встречается в различных областях человеческой деятельности,
 * в дальнейшем мы будет рассматривать рекурсию в программировании.
 * ------------------------------------------------------------------------------------------------
 * Рекурсия в программировании
 *
 * Рекурсия — возможность функции или процедуры вызывать саму себя.
 *
 * В зависимости от того, как реализуется рекурсивный вызов рассматривают следующие виды
 * рекурсии:
 *   ● Простая — функция или процедура вызывают сами себя.
 *   ● Сложная или косвенная — вызов функции происходит посредством вспомогательных
 *     функций. Например основная функция вызывает функцию А, а уже в свою очередь функция А
 *     вызывает основную функцию.
 *
 * В зависимости от того сколько раз (за один вызов базовой) производиться рекурсивных
 * вызовов рекурсия подразделяется на:
 *   ● Одиночная — в рекурсивной части производиться ровно один рекурсивный вызов.
 *   ● Множественная (параллельная рекурсия) — в рекурсивной части производиться более одного
 *     вызова рекурсивной функции или процедуры.
 *
 * Анонимная рекурсия — рекурсия с использованием неявных реализаций функций или
 * процедур (лямбда функции и подобные им механизмы).
 *
 * Глубина рекурсии — количество вложенных вызовов функции или процедуры.
 * ------------------------------------------------------------------------------------------------
 * Описание базовых составляющих рекурсии
 *
 * Рекурсивная функция или процедура всегда должна содержать условный оператор
 * описывающий условие прекращение рекурсии - терминальная часть. Терминальная часть
 * выполняется если условие прекращения рекурсии вернет true. После терминальной части идет
 * описание рекурсивной части. Рекурсивная часть должна выполняться в случае когда условие
 * прекращение рекурсии вернет false. Правильно реализованная рекурсивная функция или
 * процедура должна давать гарантию завершения за конечное число вызовов.
 * ------------------------------------------------------------------------------------------------
 * Как обычно реализуется рекурсия
 *
 * В большинстве языков программирования реализация рекурсии опирается на механизм стека
 * вызовов. Переменные функции и адрес возврата сохраняются в стек (область оперативной памяти
 * используемой ПО), благодаря чему каждый следующий рекурсивный вызов этой функции
 * пользуется своим набором локальных переменных и за счёт этого работает корректно.
 *
 * С другой стороны каждый рекурсивный вызов требуется некоторое количество оперативной
 * памяти компьютера, и при чрезмерно большой глубине рекурсии может наступить переполнение
 * стека вызовов (классический Stack Overflow).
 *
 * Поэтому обычно не рекомендуют использовать рекурсивные методы с большой глубиной
 * рекурсии. В таком случае если есть возможность, то следует попробовать использовать
 * циклический подход.
 *
 * Хвостовая рекурсия - частный случай рекурсии, при котором любой рекурсивный вызов
 * является последней операцией перед возвратом из функции. Такой вызов может быть
 * оптимизирован компилятором языка в простую итерацию, как следствие переполнение стека не
 * произойдет.
 * ------------------------------------------------------------------------------------------------
 * Когда стоит использовать рекурсию
 *
 * Рекурсивный подход облегчает реализацию в нескольких случаях:
 *   1) Задача разбивается на подзадачи. Но каждая подзадача эквивалентна базовой задаче.
 *   2) Задача изначально сформулирована с помощью рекурсивного описания.
 * ------------------------------------------------------------------------------------------------
 * Поддержка рекурсии
 *
 * Поддерживается:
 *   ● Прямой рекурсивный вызов методов
 *   ● Косвенный рекурсивный вызов методов
 *   ● Параллельная рекурсия
 *
 * Не поддерживается:
 *   ● Оптимизация хвостовой рекурсии
 * ------------------------------------------------------------------------------------------------
 * Реализация рекурсии в Java
 *
 * Рекурсия в Java реализована довольно просто. Для рекурсивного вызова метода
 * достаточно просто вызвать его в теле этого метода (прямой рекурсивный вызов), или в другом
 * методе вызванном из базового (косвенная рекурсия).
 * ------------------------------------------------------------------------------------------------
 * Превышение глубины рекурсивных вызовов
 *
 * В Java ограничение идет не на количество рекурсивных вызовов, а на объем оперативной
 * памяти занимаемой стеком вызовов методов. Поэтому чем больше локальных переменных в
 * методе и чем больше у метода параметров тем быстрее переполняется стек и генерируется
 * исключение java.lang.StackOverflowError.
 * ------------------------------------------------------------------------------------------------
 * Увеличение объема стека в Java
 *
 * За выполнение программ на Java отвечает JVM(Java Virtual Machine) - основная часть
 * исполняющей системы Java. Данная платформа поддерживает возможности тонкой настройки
 * почти всех аспектов выполнения программ, в том числе и управление памятью отводимой под стек.
 * Для этого используются ключи запуска JVM:
 *   ● -Xms size - Устанавливает начальный размер (в байтах) кучи. Это значение должно быть кратным
 *     1024 и превышать 1 МБ.
 *   ● -Xmx size - Задает максимальный размер (в байтах) пула распределения памяти в байтах. Это
 *     значение должно быть кратным 1024 и превышать 2 МБ.
 *   ● -Xss size - Устанавливает размер стека потока (в байтах).
 *
 * Добавьте букву k или K для обозначения KB, m или M для обозначения MB и g или G для
 * обозначения GB.
 * Размер стека по умолчанию зависит от типа ОС. Обычно это 1 МБ.
 *
 * Если использовать такой ключ запуска: java -Xss100M — то будет установлен размер
 * стека в 100 Мбайт, чего с избытком хватит для вычисления данной задачи.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/JYeZtl9owOU">Ссылка на видео</>
 * ------------------------------------------------------------------------------------------------
 */
public class CountingWhiteSpace {

    public static void main(String[] args) {
        String text = "one two three four five";

        System.out.println(countCyclically(text));
        System.out.println(countRecursively(text));
        System.out.println(countByTailRecursion(text));
    }

    public static int countCyclically(String text) {
        if (text.length() == 0) {
            return 0;
        }
        int n = 0;
        for (char symbol : text.toCharArray()) {
            if (symbol == ' ') {
                n++;
            }
        }
        return n;
    }

    public static int countRecursively(String text) {
        if (text.length() == 0) {
            return 0;
        }
        int n = 0;
        if (text.charAt(0) == ' ') {
            n = 1;
        }
        return n + countRecursively(text.substring(1));
    }

    public static int countByTailRecursion(String text) {
        return countByTailRecursion(text, 0);
    }

    private static int countByTailRecursion(String text, int count) {
        if (text.length() == 0) {
            return count;
        }
        int n = 0;
        if (text.charAt(0) == ' ') {
            n = 1;
        }
        return countByTailRecursion(text.substring(1), count + n);
    }
}
