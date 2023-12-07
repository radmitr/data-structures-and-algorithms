package data_structures.lists;

/**
 * ------------------------------------------------------------------------------------------------
 * Двусвязный список
 * ------------------------------------------------------------------------------------------------
 * Двусвязный список
 *
 * Двусвязный список — разновидность связанного списка. Узел содержит данные и две ссылки
 * (указатели) на предыдущий и следующий элемент списка.
 * ------------------------------------------------------------------------------------------------
 * Преимущества и недостатки двусвязных списков
 *
 * Преимущества:
 *   ● Простота вставки и удаления элемента в начале и конце списка
 *
 * Недостатки:
 *   ● Сложность получения элемента по индексу
 *   ● Удвоенный (по сравнению с односвязным списком) расход памяти на хранение указателей
 * ------------------------------------------------------------------------------------------------
 * Узел списка
 *
 * Узел списка представляет собой составную структуру. Обычно реализуется с помощью класса
 * или структуры (в процедурных языках). Содержит значения двух типов. Одно для хранения данных
 * (числа, строки и т.д.), и две ссылки на следующий узел или предыдущий узел (реализуется как
 * указатель или ссылка, тип которых совпадает с типом узла).
 * ------------------------------------------------------------------------------------------------
 * Двусвязный список
 *
 * В одной из самых распространенных реализаций двусвязного списка, используется два фиктивных
 * элемента head и tail (начало и конец списка соответственно). Остальные узлы вставляются между
 * ними.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в начало списка
 *
 * Создать новый узел, который хранит добавляемое значение и next = null и prev = null.
 *
 * 1) Установить значение next добавляемого узла равное head.next.
 * 2) Установить prev добавляемого узла равное head.
 * 3) Установить head.next.prev на добавляемый узел.
 * 4) Установить head.next на добавляемый узел.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в конец списка
 *
 * Создать новый узел, который хранит добавляемое значение и next = null и prev = null.
 *
 * 1) Установить значение next добавляемого узла равным tail.
 * 2) Установить значение prev равный значению tail.prev.
 * 3) Установить значение tail.prev.next равное добавляемому узлу.
 * 4) Установить значение tail.prev равным добавляемому узлу.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в произвольное место списка
 *
 * Создаем новый узел, который хранит добавляемое значение и next = null, prev = null.
 *
 * 1) Начиная с головы или хвоста списка, проходим до элемента, перед которым нужно вставить
 *    добавляемый узел.
 * 2) Устанавливаем значение next добавляемого узла на текущий элемент.
 * 3) Устанавливаем значение prev добавляемого узла равной значению prev текущего.
 * 4) Устанавливаем next предыдущего узла равным добавляемому.
 * 5) Ссылку  prev текущего равный добавляемому.
 * ------------------------------------------------------------------------------------------------
 * Удаление значения из начала списка
 *
 * 1) Установить значение head.next равное значению next удаляемого.
 * 2) Установить значение prev следующего элемента равным значению prev удаляемого.
 * 3) Указать, значение next и prev удаляемого узла равным null.
 * 4) Освободить память, занимаемую удаляемым узлом.
 * ------------------------------------------------------------------------------------------------
 * Удаление элемента из конца списка
 *
 * 1) Устанавливаем значение tail.prev равным значению prev удаляемого узла.
 * 2) Устанавливаем значение next предыдущего узла равным значению tail.
 * 3) Устанавливаем значение next и prev удаляемого узла равными null.
 * 4) Освобождаем память, занимаемую удаляемым узлом.
 * ------------------------------------------------------------------------------------------------
 * Удаление элемента из произвольного места списка
 *
 * 1) Начиная с головы или хвоста списка, проходим до элемента который нужно удалить.
 * 2) Устанавливаем значение next предыдущего узла равным next удаляемого.
 * 3) Устанавливаем значение prev следующего узла равной значению prev удаляемого.
 * 4) Устанавливаем next и prev удаляемого узла равным null.
 * 5) Освобождаем память, занимаемую удаляемым узлом.
 * ------------------------------------------------------------------------------------------------
 * Получение размера списка
 *
 * Для получения размера списка стоит объявить переменную с начальным значением 0. Начиная с
 * начала списка, выполнять переход по ссылке к следующему узлу. На каждом переходе увеличивать
 * значение этой переменной на 1. Закончить на tail.
 * ------------------------------------------------------------------------------------------------
 * Работа с индексами
 *
 * Для работы с индексами можно использовать подход аналогичный вычислению длины. Стоит
 * объявить переменную, начальное значение которой равно начальному индексу (произвольный
 * выбор). Начиная с головы списка, выполняем проход по next (от узла к узлу) до тех пор,
 * пока значение этой переменной не станет равно искомому индексу.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/FUWSv-ezD_c">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class DoublyLinkedList {

    private final Node head;
    private final Node tail;
    private long length = 0;

    public DoublyLinkedList() {
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

        // comment out for debugging
//        @Override
//        public String toString() {
//            return "Node [data=" + data + ", next=" + next + ", prev=" + prev + "]";
//        }
    }

    public void addFirst(Object value) {
        Node addNode = new Node(value, head.next, head);
        head.next.prev = addNode;
        head.next = addNode;
        length++;
    }

    public void addLast(Object value) {
        Node addNode = new Node(value, tail, tail.prev);
        tail.prev.next = addNode;
        tail.prev = addNode;
        length++;
    }

    public void removeFirst() {
        if (isEmpty()) {
            return;
        }
        Node removeNode = head.next;
        head.next = removeNode.next;
        removeNode.next.prev = head;
        removeNode.next = null;
        removeNode.prev = null;
        length--;
    }

    public void removeLast() {
        if (isEmpty()) {
            return;
        }
        Node removeNode = tail.prev;
        tail.prev = removeNode.prev;
        removeNode.prev.next = tail;
        removeNode.next = null;
        removeNode.prev = null;
        length--;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    private Node getNodeByIndex(long index) {
        Node resultNode = null;
        if (index < length / 2) {
            long nodeIndex = 0;
            resultNode = head.next;
            while (nodeIndex != index) {
                resultNode = resultNode.next;
                nodeIndex++;
            }
        } else {
            long nodeIndex = length - 1;
            resultNode = tail.prev;
            while (nodeIndex != index) {
                resultNode = resultNode.prev;
                nodeIndex--;
            }
        }
        return resultNode;
    }

    public Object getByIndex(long index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        return resultNode.data;
    }

    public void removeByIndex(long index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        resultNode.prev.next = resultNode.next;
        resultNode.next.prev = resultNode.prev;
        resultNode.next = null;
        resultNode.prev = null;
        length--;
    }

    public void insertByIndex(long index, Object value) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        Node addNode = new Node(value, resultNode, resultNode.prev);
        resultNode.prev.next = addNode;
        resultNode.prev = addNode;
        length++;
    }

    public void setByIndex(long index, Object value) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        resultNode.data = value;
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
            removeFirst();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[head <-> ");
        for (Node currentNode = head.next; currentNode != tail; currentNode = currentNode.next) {
            sb.append(currentNode.data).append(" <-> ");
        }
        sb.append("tail]");
        return sb.toString();
    }

    // ============================================================================================
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        // 1 - addFirst() and addLast()
        list.addFirst(3);
        list.addLast(7);
        list.addLast(11);
        list.addLast(13);
        list.addLast(17);
        list.addLast(19);
        list.addFirst(1);
        System.out.println(list);

        // 2 - getLength() and getLengthWithPass()
        System.out.println(list.getLength());
        System.out.println(list.getLengthWithPass());

        // 3 - setByIndex()
        list.setByIndex(2, -2);
        System.out.println(list);

        // 4 - removeFirst()
        list.removeFirst();
        System.out.println(list);

        // 5 - removeLast()
        list.removeLast();
        System.out.println(list);

        // 6 - getByIndex()
        System.out.println(list.getByIndex(2));

        // 7 - removeByIndex()
        list.removeByIndex(2);
        System.out.println(list);

        // 8 - insertByIndex()
        list.insertByIndex(2, 111);
        System.out.println(list);

        // 9 - clear()
        list.clear();
        System.out.println(list);
    }
}
