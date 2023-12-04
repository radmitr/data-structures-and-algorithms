package algorithms.searches.interpolation_search;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * ------------------------------------------------------------------------------------------------
 * Интерполяционный поиск
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Алгоритм интерполяционного поиска.
 * Сложность по времени в наихудшем случае O(n)
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * В основе алгоритма лежит интерполяция зависимости элемента последовательности от
 * значения его индекса. Полученная интерполяционная зависимость используется для возможности
 * предсказания местоположения элемента. Как и в случае с бинарным поиском область поиска
 * уменьшается с каждым шагом. Бинарный поиск уменьшает область поиска всегда в два раза. В
 * случае хорошо подобранной интерполяционной зависимости область поиска сужается быстрее, в
 * то же время неудачно подобранная интерполирующая зависимость может привести к падению
 * эффективности до линейной.
 *
 * В общем случае создание интерполяционной зависимости хотя и возможно, но
 * затруднительно. Гораздо более простой и эффективный способ заключается в использовании
 * поиска в отсортированной последовательности.
 * ------------------------------------------------------------------------------------------------
 * Интерполяция
 *
 * Интерполяция, интерполирование — в вычислительной математике способ нахождения
 * промежуточных значений величины по имеющемуся дискретному набору известных значений.
 *
 * Имеющиеся данные вида (xi, yi) — точки данных, базовые точки. Где i = 1...N.
 * xi — узлы интерполяции
 * ∆x = xi - xi-1 — шаг интерполяционной сетки
 * Задача интерполяции состоит в нахождении такой функции F, что F(xi) = yi
 * F — интерполирующая функция
 * ------------------------------------------------------------------------------------------------
 * Линейная интерполяция
 *
 * Пожалуй наиболее простым методом интерполяции по двум точкам (в общем случае
 * интерполяцию можно проводить по N≥2 точкам) является линейная интерполяция. В таком случае
 * в качестве интерполирующей функции используется уравнение прямой по двум точкам. Довольно
 * часто алгоритм интерполяционного поиска использует именно линейную интерполяцию.
 *
 * Уравнение прямой по двум точкам (2-х мерная система координат):
 *   (x − x1)/(x1 − x2) = (y − y1)/(y1 − y2)
 *
 * Отсюда можно получить:
 *   (x − x1)/(x1 − x2) = (y − y1)/(y1 − y2) => (x − x1)⋅(y1 − y2) = (y − y1)⋅(x1 − x2)
 *
 * Требуемые прямые и обратные зависимости:
 *   (x − x1)⋅(y1 − y2) = (y − y1)⋅(x1 − x2) => y(x) = (x − x1)⋅(y1 − y2)/(x1 − x2) + y1
 *   (x − x1)⋅(y1 − y2) = (y − y1)⋅(x1 − x2) => x(x) = (y − y1)⋅(x1 − x2)/(y1 − y2) + x1
 * ------------------------------------------------------------------------------------------------
 * Использование линейной интерполяции в алгоритме
 *
 * Для работы будем использовать отсортированную последовательность. В качестве базовых
 * точек используем пары (i, K[i]), где i — индекс в последовательности, K[i] — значение элемента
 * по этому индексу. Используем линейную интерполяцию по двум точкам. Предсказание индекса на
 * котором стоит искомый элемент (обозначим его К) будем проводить по обратной зависимости.
 * Выберем две точки для построения интерполяционной прямой. Обозначим их как l и r
 * соответственно. В таком случае индекс искомого элемента вычисляется по формуле:
 *   i(K) = (K − K[l])⋅(l − r)/(K[l] − K[r]) + l
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * 1) Сортируется последовательность, в которой будет проводиться поиск. Если последовательность
 *    уже отсортирована, то этот шаг можно пропустить.
 *
 * 2) В качестве начальных точек (левая и правая) выбираем первый и последний элемент
 *    последовательности.
 *
 * 3) Определяется значение индекса по формуле линейной интерполяции. Получаем элемент по
 *    этому индексу. Полученный элемент сравнивается с искомым элементом. Различают случаи:
 *      a) Элемент равен искомому. Заканчиваем алгоритм. Поиск успешен.
 *      b) Элемент больше искомого. Сдвигаем правую точку. Новое значение (найденный индекс - 1).
 *      c) Элемент меньше искомого. Сдвигаем левую точку. Новое значение (найденный индекс + 1).
 *
 * 4) Повторяем пункт 3 до тех пор, пока не будет найден искомый элемент или не станет пустым
 *    интервал для поиска.
 * ------------------------------------------------------------------------------------------------
 * Область применения интерполяционного поиска
 *
 * В случае равномерно распределенных данных в последовательности эффективность алгоритма
 * интерполяционного поиска превышает эффективность бинарного. И только в случае неудачно
 * подобранной интерполяционной функции или неравномерного распределения данных (например
 * данные распределены экспоненциально, а интерполяционная функция выбрана линейной) его
 * эффективность может снизиться до линейной.
 *
 * Как показывает практика чем больше размер последовательности, тем равномернее в ней
 * распределены данные. На больших последовательностях интерполяционный поиск эффективнее
 * бинарного. Это и определяет область его применения.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/5fdY8OMI3WM">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class InterpolationSearch {

    public static void main(String[] args) {
        int[] sequence = { -2, 0, 3, 5, 7, 9, 11, 15, 18 };
        System.out.println(Arrays.toString(sequence));

        // 1 - element found
        int element = 5;
        System.out.println(interpolationSearch(sequence, element)  + " -> " + element);

        // 2 - element2 not found
        int element2 = 8;
        System.out.println(interpolationSearch(sequence, element2)  + " -> " + element2);

        // 3 - element3 in bigSequence
        int[] bigSequence = IntStream.rangeClosed(1, 100_000_000).toArray();
        int element3 = 90_000_000;
        System.out.println(interpolationSearch(bigSequence, element3)  + " -> " + element3);
//        System.out.println(Arrays.toString(bigSequence));
    }

    public static int interpolationSearch(int[] sequence, int element) {
        int l = 0;
        int r = sequence.length - 1;
        while (sequence[l] < element && element < sequence[r]) {
//            if (sequence[l] == sequence[r]) {
//                break;
//            }
            int index = (int) ((element - sequence[l]) * 1L * (l - r) / (sequence[l] - sequence[r]) + l);
            if (sequence[index] > element) {
                r = index - 1;
            } else if (sequence[index] < element) {
                l = index + 1;
            } else {
                return index;
            }
        }
        if (sequence[l] == element) {
            return l;
        }
        if (sequence[r] == element) {
            return r;
        }
        return -1;
    }
}
