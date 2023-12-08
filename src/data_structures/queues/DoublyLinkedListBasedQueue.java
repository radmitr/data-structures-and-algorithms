package data_structures.queues;

/**
 * ------------------------------------------------------------------------------------------------
 * Очередь на основе двусвязного списка
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
 * Реализация очереди на основе двусвязного списка
 *
 * Пожалуй одной из самых простых реализаций очереди является использование двусвязного
 * списка. У него операции добавления элемента в начало списка и удаления элемента с конца списка
 * обладают константной сложностью, что делает его использование довольно оптимальным. К
 * недостаткам стоит отнести повышенный расход памяти на хранение ссылок на элементы списка.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в конец очереди
 *
 * Создать новый узел который хранит добавляемое значение и next = null и prev = null
 *
 * 1) Установить значение next добавляемого узла равное head.next.
 * 2) Установить prev добавляемого узла равное head.
 * 3) Установить head.next.prev на добавляемый узел.
 * 4) Установить head.next на добавляемый узел.
 * ------------------------------------------------------------------------------------------------
 * Получение значения с удалением с головы очереди
 *
 * 1) Устанавливаем значение tail.prev равным значению prev удаляемого узла.
 * 2) Устанавливаем значение next предыдущего узла равным значению tail.
 * 3) Устанавливаем значение next и prev удаляемого узла равными null.
 * 4) Сохраняем значение удаляемого узла.
 * 5) Освобождаем память, занимаемую удаляемым узлом.
 * 6) Возвращаем сохраненное значение.
 * ------------------------------------------------------------------------------------------------
 * Получение значения без удаления с головы очереди
 *
 * 1) Перейти к узлу по ссылке tail.prev.
 * 2) Вернуть значение этого узла.
 * ------------------------------------------------------------------------------------------------
 * Получение размера очереди
 *
 * Для получения размера очереди стоит объявить переменную с начальным значением 0. Начиная
 * с начала очереди, выполнять переход по ссылке к следующему узлу. На каждом переходе
 * увеличивать значение этой переменной на 1. Закончить на tail.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/gnYEO6YIdOQ">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class DoublyLinkedListBasedQueue {

    private final Node head;
    private final Node tail;
    private long length = 0;

    public DoublyLinkedListBasedQueue() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    private class Node {
        Object data;
        Node next;
        Node prev;

        public Node() {
        }

        public Node(Object data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node [data=" + data + "]";
        }
    }

    public void enqueue(Object value) {
        Node addNode = new Node(value, head.next, head);
        head.next.prev = addNode;
        head.next = addNode;
        length++;
    }

    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node removeNode = tail.prev;
        tail.prev = removeNode.prev;
        removeNode.prev.next = tail;
        Object value = removeNode.data;
        removeNode.next = null;
        removeNode.prev = null;
        length--;
        return value;
    }

    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        Node node = tail.prev;
        return node.data;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    public long getLength() {
        return length;
    }

    public long getLengthWithPass() {
        long length = 0;
        Node currentNode = head.next;
        while (currentNode != tail) {
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }

    public void clear() {
        while (length > 0) {
            dequeue();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node currentNode = head.next;
        while (currentNode != tail) {
            sb.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    //=============================================================================================
    public static void main(String[] args) {
        DoublyLinkedListBasedQueue queue = new DoublyLinkedListBasedQueue();

        // 1 - enqueue()
        queue.enqueue("Hello");
        queue.enqueue("World");
        queue.enqueue("Java");
        System.out.println(queue);

        // 2 - getLength() and getLengthWithPass()
        System.out.println(queue.getLengthWithPass());

        // 3 - dequeue() and getLength()
        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.getLength());

        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.getLength());

        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.getLength());

        // 4 - clear() and peak()
        queue.enqueue("q1");
        queue.enqueue("q2");
        queue.enqueue("q3");
        System.out.println(queue);

        System.out.println(queue.peek());

        queue.clear();
        System.out.println(queue);
    }
}
