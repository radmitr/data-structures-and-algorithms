package datastructures.lists;

import java.util.Arrays;

/**
 * ------------------------------------------------------------------------------------------------
 * Список на основе массива
 * ------------------------------------------------------------------------------------------------
 * Список
 * Список — это абстрактный тип данных, представляющий собой упорядоченный набор
 * значений, в котором некоторое значение может встречаться более одного раза. Список
 * динамическая структура данных.
 *
 * Поддерживаемые операции:
 *   ● Добавление элемента в список
 *   ● Удаление элемента из списка
 *   ● Получение элемента по индексу
 *   ● Замена элемента по индексу
 *   ● Получение размера списка
 * ------------------------------------------------------------------------------------------------
 * Список на основе массива
 *
 * В качестве основы списка можно использовать массивы переменной длинны. В таком случае
 * получение и замена элемента массива по индексу реализуется особенно просто. Сложности
 * возникнут только со вставкой и удалением элемента из списка. Особое внимание следует уделить
 * вопросам увеличения и уменьшения размера списка на основе массива.
 * ------------------------------------------------------------------------------------------------
 * Реализации списка на основе массива
 *
 * В качестве основы списка берем массив нужного типа данных. Его размер будем называть
 * capacity (емкость). Также введем дополнительную переменную size (размер), она будет указателем
 * на место для добавления элемента и к тому же используется для получения количества добавленных
 * элементов. При создании списка устанавливается в начало массива.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в конец списка
 *
 * Если size меньше чем capacity, то добавляем элемент на индекс size и увеличиваем size на
 * единицу.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в конец списка
 *
 * Если size равно capacity, то создаем новый массив размером (capacity * 3)/2 + 1. Копируем
 * данные из базового массива в новый. Указываем, что теперь для хранения используется новый
 * массив. Добавляем элемент на индекс size и увеличиваем size на единицу. Освобождаем память
 * занимаемую старым массивом.
 * ------------------------------------------------------------------------------------------------
 * Вставка значения по индексу
 *
 * Проверяем достаточно ли места для вставки. Если нет, запускаем процесс увеличения размера.
 * Сдвигаем правую часть массива (от индекса, на который вставляем элемент, до size) на одну
 * позицию вправо (желательно вызвать быструю функцию копирования массива блоками). Ставим элемент
 * на нужный индекс. Увеличиваем size на единицу.
 * ------------------------------------------------------------------------------------------------
 * Удаление элемента по индексу
 *
 * Сдвигаем правую часть массива (от индекса удаляемого элемента до size) на одну позицию влево
 * (желательно вызвать быструю функцию копирования массива блоками). Уменьшаем значение size на
 * единицу.
 * ------------------------------------------------------------------------------------------------
 * Получение и замена элемента по индексу
 *
 * При получении значения по индексу сначала проверяют корректность индекса. После чего
 * возвращаем значение по индексу.
 *
 * При замене значения по индексу сначала проверяют корректность индекса. После чего
 * заменяем значение по индексу.
 * ------------------------------------------------------------------------------------------------
 * Уменьшение размера списка
 *
 * В большинстве случаев список только увеличивает свою емкость. Автоматического уменьшения
 * емкости не предусматривают. Для уменьшения емкости используют функцию, вызов которой
 * осуществляется по желанию разработчика. В этой функции обычно устанавливают capacity равное
 * size.
 *
 * Создают новый массив размером size. Копируют данные из основного массива в новый.
 * Указываем что новый массив теперь используется вместо основного. Освобождаем память занимаемую
 * старым массивом.
 * ------------------------------------------------------------------------------------------------
 * Получение размера списка
 *
 * Для получения размера списка достаточно вернуть значение size.
 * ------------------------------------------------------------------------------------------------
 *
 * Оценка сложности операций
 * ---------------------------------------------------------------
 * |   Операция             | Сложность операции в худшем случае |
 * ---------------------------------------------------------------
 * | Вставка элемента       |  O(n)                              |
 * | Удаление элемента      |  O(n)                              |
 * | Получение по индексу   |  O(1)                              |
 * | Изменение по индексу   |  O(1)                              |
 * | Получение размера      |  O(1)                              |
 * ---------------------------------------------------------------
 *
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/qeS9g28pkM0">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class ArrayBasedList {

    private static final int DEFAULT_CAPACITY = 10;
    private int[] dataArray;
    private int size;
    private int capacity;

    public ArrayBasedList() {
        dataArray = new int[DEFAULT_CAPACITY];
        capacity = dataArray.length;
        size = 0;
    }

    public void add(int value) {
        if (size >= capacity) {
            boolean resizeResult = upResize();
            if (!resizeResult) {
                throw new RuntimeException("Cannot add an element");
            }
        }
        dataArray[size] = value;
        size++;
    }

    public void addByIndex(int value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size >= capacity) {
            boolean resizeResult = upResize();
            if (!resizeResult) {
                throw new RuntimeException("Cannot add an element");
            }
        }
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
        size++;
    }

    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        size--;
    }

    public int size() {
        return size;
    }

    public boolean upResize() {
        if (capacity >= Integer.MAX_VALUE - 1) {
            return false;
        }
        long newCapacityL = (capacity * 3L) / 2L + 1L;
        int newCapacity = (newCapacityL < Integer.MAX_VALUE - 1) ? (int) newCapacityL : Integer.MAX_VALUE - 1;
        dataArray = Arrays.copyOf(dataArray, newCapacity);
        capacity = newCapacity;
        return true;
    }

    public void trimToSize() {
        dataArray = Arrays.copyOf(dataArray, size);
        capacity = dataArray.length;
    }

    public void clear() {
        dataArray = new int[0];
        capacity = dataArray.length;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                sb.append(dataArray[i]).append(", ");
            } else {
                sb.append(dataArray[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //=============================================================================================
    public static void main(String[] args) {
        ArrayBasedList list = new ArrayBasedList();

        // 1 - add()
        for (int i = 0; i < 12; i++) {
            list.add(i);
        }
        System.out.println(list);

        list.add(-5);
        System.out.println(list);

        // 2 - addByIndex()
        list.addByIndex(-15, 0);
        System.out.println(list);

        // 3 - deleteByIndex()
        list.deleteByIndex(0);
        System.out.println(list);

        list.deleteByIndex(7);
        System.out.println(list);

        // 4 - trimToSize()
        list.trimToSize();
        System.out.println(list);

        // 5 - size()
        System.out.println(list.size());

        // 6 - clear()
        list.clear();
        System.out.println(list);
    }
}
