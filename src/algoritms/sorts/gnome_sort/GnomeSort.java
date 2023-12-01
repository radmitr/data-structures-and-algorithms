package algoritms.sorts.gnome_sort;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Гномья сортировка
 * ------------------------------------------------------------------------------------------------
 * При чем тут гномы???
 *
 * Гномья сортировка основана на технике, используемой обычным голландским
 * садовым гномом (нидерл. tuinkabouter). Это метод, которым садовый гном сортирует ).
 * Это метод, которым садовый гном сортирует линию цветочных горшков.
 * По существу он смотрит на текущий и предыдущий садовые горшки: если они в правильном порядке,
 * он шагает на один горшок вперёд, иначе он меняет их местами и шагает на один горшок назад.
 * Граничные условия: если нет предыдущего горшка, он шагает вперёд; если нет следующего горшка,
 * он закончил.
 *                                                                                     Дик Грун
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Алгоритм гномьей сортировки.
 * Сложность по времени в наихудшем случае O(n^2)
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * 1) В качестве опорного берем первый элемент последовательности. Вводим
 *    дополнительную переменную, для хранения индекса. Устанавливаем ее равной
 *    индексу первого элемента.
 *
 * 2) Выполняем сравнение опорного и следующего за ним элемента. Если опорный
 *    элемент является последним элементом последовательности заканчиваем
 *    алгоритм. Если опорный элемент меньше или равен следующему элементу, то в
 *    качестве опорного выбирают следующий элемент и повторяют пункт 2.
 *
 * 3) Если опорный элемент больше следующего, то устанавливаем значение
 *    переменной для хранения индекса равным значению индекса следующего
 *    элемента. Производим обмен опорного и следующего элемента. В качестве
 *    опорного выбираем предыдущий. Повторяем до тех пор пока или не дойдем до
 *    начала последовательности или опорный элемент не станет меньше следующего.
 *    После чего в качестве опорного выбираем элемент индекс которого хранится во
 *    вспомогательной переменной. И повторяем пункт 2.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/uSHv3Q1Smv8">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class GnomeSort {

    public static void main(String[] args) {
        int[] array = { 5, 0, -2, 7, 3 };
        System.out.println(Arrays.toString(array));

        gnomeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void gnomeSort(int[] array) {
        int index = 1;
        int i = 0;
        while (i < array.length - 1) {
            if (array[i] <= array[i + 1]) {
                i = index;
                index += 1;
            } else {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                i = i - 1;
                if (i < 0) {
                    i = index;
                    index += 1;
                }
            }
        }
    }
}
