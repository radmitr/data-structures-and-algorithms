package algoritms.strings.search_substring.rabin_karp;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Алгоритм Рабина — Карпа
 * ------------------------------------------------------------------------------------------------
 * Алгоритм Рабина-Карпа алгоритм который в своей работе использует хеширование. Данный
 * алгоритм отлично подходит для одновременного поиска множества подстрок (одинаковой длины) в
 * строке. В тоже время этот алгоритм довольно редко используется для поиска одиночного образца. Он
 * был разработан в 1987 году Михаэлем Рабином и Ричардом Карпом.
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * 1) Вычисляется хеш-код искомой подстроки (предположим длина подстроки m).
 * 2) Начиная с первого символа выделяем в базовой строке часть длиной m.
 *   ● Хеш части строки и подстроки совпадает. Проверяем на равенство искомою подстроку и
 *     часть строки. В случае равенства возвращаем положительный результат.
 *   ● Не совпадают. Сдвигаем выделяемую часть на один символ вправо. Если часть строки
 *     достигла границы базовой строки, возвращаем отрицательный результат. Поиск не успешен.
 *     Если не достигла переходим к первому подпункту.
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Сложность по времени в наихудшем случае O(n·m)
 * n — длина строки
 * m — длина подстроки
 *
 * Стоит отметить, что худший случай реализуется довольно редко. В среднем и
 * лучшем случае сложность составляет O(n)
 * ------------------------------------------------------------------------------------------------
 * Используемый вид хеш-функции
 *
 * Для использования этого алгоритма используются хеш-функции определенного класса. Они
 * относятся к скользящим (кольцевым) хеш-функциям.
 * Скользящая (кольцевая) хеш-функция — хеш-функция вычисляемая на основе части диапазона
 * входных данных. При этом при сдвиге диапазона хеш вычисляется на основе ранее ранее
 * вычисленного хеша. Вычисление в этом случае выполняется намного быстрее.
 * Для этого алгоритма была предложена полиномиальная хеш-функция.
 *
 * h(s[1..m]) = (sum(s[i] * x^(m - i))) mod q
 *
 * s[i] - целочисленное представление символа
 * x - положительное целое число
 * q - положительное целое число
 *
 * Для выбора q и x есть ряд рекомендаций. Число q — должно быть простым. Для ускорения
 * вычисления рекомендуют использовать числа Мерсенна (простые). Так для 32 битных хешей
 * рекомендуется выбрать q = 2^31 - 1 = 2147483647, для 64 битных 2^61 - 1 = 2305843009213693951.
 * В качестве x можно выбрать любое число в диапазоне 0..q - 1.
 * ------------------------------------------------------------------------------------------------
 * Вычисление нового хеша на основе предыдущего
 *
 * 1) Из старого хеша вычесть значение s[0] * x^(m − 1)
 * 2) Полученный результат умножить на x
 * 3) Прибавить значение s[m + 1]
 * 4) Вычислить остаток от деления на q
 * ------------------------------------------------------------------------------------------------
 * Поиск множества подстрок
 *
 * Этот алгоритм очень удобен для поиска множества подстрок (одинаковой длины). Для каждой
 * подстроки вычисляется хеш и на каждой итерации сравнивают этот хеш с хешом полученным на
 * основе части основной строки. В таком случае этот алгоритм становится наиболее оптимальным для
 * задач такого плана.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/-iNb4_9iFNs">Ссылка на видео</>
 * ------------------------------------------------------------------------------------------------
 */
public class RabinKarp2 {

    public static final int BASE = 31;
    public static final int Q = 2147483647; // 2^(31 - 1)

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                searchFirstSubstring("Awesome apple", "some")));
        System.out.println(Arrays.toString(
                searchFirstSubstring("Awesome apple", "some", "weso")));
        System.out.println(Arrays.toString(
                searchFirstSubstring("Awesome apple", "some", "wsso")));
        System.out.println(Arrays.toString(
                searchFirstSubstring("Awesome apple", "pple", "some", "weso")));
        System.out.println(Arrays.toString(
                searchFirstSubstring("Awesome apple", "apple", "apple", "apple")));
        System.out.println(Arrays.toString(
                searchFirstSubstring("Awesome apple", "xxxx", "yyyy", "appl")));
    }

    public static int gornerScheme(char[] sym, int start, int end) {
        int result = (int) (sym[start]);
        for (int i = start; i < end - 1; i++) {
            result = result * BASE + (int) sym[i + 1];
        }
        return result;
    }

    public static int hash(char[] sym, int start, int end) {
        return gornerScheme(sym, start, end) % Q;
    }

    /**
     * Возвращает индекс первого вхождения в строку baseText одной из строк массива subStrings
     * и индекс элемента этого массива
     */
    public static int[] searchFirstSubstring(String baseText, String... subStrings) {
        if (!checkEqualsLength(subStrings)) {
            throw new IllegalArgumentException("substrings must be the same length");
        }
        int[] result = new int[] { -1, -1 };
        int[] hashArray = new int[subStrings.length];
        for (int i = 0; i < hashArray.length; i++) {
            hashArray[i] = hash(subStrings[i].toCharArray(), 0, subStrings[i].length());
        }
        char[] sym = baseText.toCharArray();
        int start = 0;
        int m = subStrings[0].length();
        int end = start + m;
        int partTextHash = hash(sym, start, end);
        int rm = basePow(m - 1); // rm = BASE^(m - 1)

        for (;;) {
            int[] someHash = findSomeHash(partTextHash, hashArray);
            if (someHash.length > 0) {
                String text = new String(sym, start, m);
                for (int i = 0; i < someHash.length; i++) {
                    if (text.equals(subStrings[someHash[i]])) {
                        result[0] = start;
                        result[1] = someHash[i];
                        return result;
                    }
                }
            }
            start++;
            end++;
            if (end > sym.length) {
                break;
            }
            // 'start' - inclusive, 'end' - exclusive
            partTextHash = ((partTextHash - rm * (int) sym[start - 1]) * BASE + sym[end - 1]) % Q;
        }
        return result;
    }

    public static int basePow(int n) {
        if (n == 0) {
            return 1;
        }
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= BASE;
        }
        return result;
    }

    public static int[] findSomeHash(int hash, int[] subHashs) {
        int n = 0;
        for (int i = 0; i < subHashs.length; i++) {
            if (subHashs[i] == hash) {
                n += 1;
            }
        }
        int[] result = new int[n];
        int insertIndex = 0;
        for (int i = 0; i < subHashs.length; i++) {
            if (subHashs[i] == hash) {
                result[insertIndex++] = i;
            }
        }
        return result;
    }

    public static boolean checkEqualsLength(String[] texts) {
        int l = texts[0].length();
        for (int i = 1; i < texts.length; i++) {
            if (texts[i].length() != l) {
                return false;
            }
        }
        return true;
    }
}
