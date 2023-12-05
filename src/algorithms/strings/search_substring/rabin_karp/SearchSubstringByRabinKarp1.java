package algorithms.strings.search_substring.rabin_karp;

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
 * Сведение об алгоритме
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
public class SearchSubstringByRabinKarp1 {

    public static final int BASE = 31;
    public static final int Q = 2147483647; // 2^(31 - 1)

    public static void main(String[] args) {
        String text = "Awesome apple";

        String subText = "some";
        String subText2 = "ple";
        String subText3 = "xxx";
        System.out.println(searchText(text, subText));
        System.out.println(searchText(text, subText2));
        System.out.println(searchText(text, subText3));
    }

    public static int gornerScheme(String text) {
        int result = text.charAt(0);
        for (int i = 0; i < text.length() - 1; i++) {
            result = result * BASE + text.charAt(i + 1);
        }
        return result;
    }

    public static int hash(String text) {
        return gornerScheme(text) % Q;
    }

    public static int searchText(String text, String subText) {
        int subHash = hash(subText);
        int m = subText.length();
        int currentHash = hash(text.substring(0, m));
        int rm = (int) Math.pow(BASE, m - 1); // rm = BASE^(m - 1)

        int i = 0;
        while (true) {
            if (subHash == currentHash) {
                if (subText.equals(text.substring(i, i + m))) {
                    return i;
                }
            }
            if (i + m >= text.length()) {
                break;
            }
//            currentHash = ((currentHash - text.charAt(i) * (int)Math.pow(BASE, m - 1)) * BASE + text.charAt(m + i)) % Q;
            currentHash = ((currentHash - text.charAt(i) * rm) * BASE + text.charAt(m + i)) % Q;
            i++;
        }
        return -1;
    }
}
