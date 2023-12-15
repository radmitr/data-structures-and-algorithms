package datastructures.queues;

/**
 * ------------------------------------------------------------------------------------------------
 * Очередь на основе массива
 * ------------------------------------------------------------------------------------------------
 * Очередь
 *
 * Очередь — это абстрактный тип данных, представляющий собой список элементов,
 * организованных по принципу FIFO (англ. first in — first out, «первым пришёл — первым вышел»).
 * Очередь - динамическая структура данных.
 *
 * Поддерживаемые операции:
 *   ● Добавление элемента в конец очереди (enqueue)
 *   ● Удаление элемента из головы очереди (dequeue)
 *   ● Получение головного элемента без удаления (peek)
 *   ● Получение размера очереди
 * ================================================================================================
 * Реализация очереди на основе массива
 *
 * Для реализации очереди можно использовать одномерные массивы. Такой подход позволит
 * значительно снизить затраты памяти на хранение по сравнению с очередью на основе двусвязного
 * списка (не нужно хранить множество ссылок на соседние элементы). Идея и реализация также
 * довольно простые. С помощью двух переменных хранят индексы указывающие на начало и конец
 * очереди. Важной особенностью подобных индексов является их цикличность. Это означает что дойдя
 * до конца последовательности они переходят в ее начало.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в конец очереди (tail + 1 ≠ head)
 *
 * 1) Устанавливаем элемент на индекс tail.
 * 2) Увеличиваем значение tail на единицу.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в конец очереди (tail + 1 = head)
 *
 * 1) Создаем новый массив, размер которого равен 4/3 + 1 от текущего.
 *    Начиная с head, копируем элементы до tail в новый массив (начиная с начала).
 *    При этом head установить в начало массива, а tail на первый свободный элемент.
 * 2) Ставим элемент на индекс tail. Увеличим значение tail на единицу.
 * 3) Освобождаем память, занимаемую старым массивом.
 * ------------------------------------------------------------------------------------------------
 * Получение значения с удалением с головы очереди
 *
 * 1) Если значение head = tail, очередь пуста. Заканчиваем.
 * 2) Возвращаем значение на индексе head. Увеличиваем head на единицу.
 * ------------------------------------------------------------------------------------------------
 * Получение значения без удаления с головы очереди
 *
 * 1) Если значение head = tail, очередь пуста. Заканчиваем.
 * 2) Возвращаем значение на индексе head.
 * ------------------------------------------------------------------------------------------------
 * Уменьшение размера очереди
 *
 * В большинстве случаев очередь на основе массива только увеличивает свой размер.
 * Автоматического уменьшения не предусматривают. Для уменьшения размера используют функцию,
 * вызов которой осуществляется по желанию разработчика.
 *
 * 1) Создается новый массив, размер которого равен количеству элементов + 1. Начиная с head,
 *    копируем элементы до tail в новый массив (начиная с начала). При этом head установить в
 *    начало массива, а tail на первый свободный элемент.
 * 2) Освобождаем память, занимаемую старым массивом.
 * ------------------------------------------------------------------------------------------------
 * Получение размера очереди
 *
 * Для получения размера очереди стоит объявить переменную с начальным значением 0. Начиная
 * с начала очереди, добавляем к значению head и этой переменной единицу до тех пор, пока head не
 * станет равно tail. Вернуть значение переменной.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/Ptk3KCAU5Vc">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class ArrayBasedQueue {
    
    private final int DEFAULT_SIZE = 16;
    private Object[] data;
    private int head;
    private int tail;
    private int size;

    public ArrayBasedQueue() {
        data = new Object[DEFAULT_SIZE];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void enqueue(Object value) {
        if ((tail + 1) % data.length == head) {
            upResize();
        }
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void upResize() {
        if (data.length >= Integer.MAX_VALUE - 10) {
            throw new IllegalArgumentException("Cannot increase the size");
        }
        Object[] newArray = new Object[Math.min(Integer.MAX_VALUE - 10, (int) (data.length * 4L / 3L + 1L))];
        int addIndex = 0;
        while (true) {
            if (head % data.length == tail) {
                break;
            }
            newArray[addIndex] = data[head % data.length];
            addIndex++;
            head = (head + 1) % data.length;
        }
        data = newArray;
        head = 0;
        tail = addIndex;
    }

    public Object dequeue() {
        if (head == tail) {
            return null;
        }
        Object returnValue = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return returnValue;
    }

    public Object peek() {
        if (head == tail) {
            return null;
        }
        return data[head];
    }

    public void trimToSize() {
        Object[] newArray = new Object[size + 1];
        int addIndex = 0;
        while (true) {
            if (head % data.length == tail) {
                break;
            }
            newArray[addIndex] = data[head % data.length];
            addIndex++;
            head = (head + 1) % data.length;
        }
        data = newArray;
        head = 0;
        tail = addIndex;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (size > 0) {
            int currentIndex = (tail - 1) >= 0 ? tail - 1 : data.length - 1;
            for (int i = size; i > 0; i--) {
                sb.append(data[currentIndex]).append(", ");
                currentIndex = (currentIndex - 1) >= 0 ? currentIndex - 1 : data.length - 1;
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    //=============================================================================================
    public static void main(String[] args) {
        ArrayBasedQueue queue = new ArrayBasedQueue();
        System.out.println(queue);
        System.out.println(queue.peek());

        queue.enqueue(3);
        System.out.println(queue);

        queue.enqueue(5);
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);

        queue.enqueue(0);
        System.out.println(queue);

        queue.enqueue(9);
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);

        queue.enqueue(7);
        System.out.println(queue);

        queue.enqueue(1);
        System.out.println(queue);

        queue.enqueue(11);
        System.out.println(queue);
        System.out.println(queue.peek());

        queue.trimToSize();
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);
    }
}
