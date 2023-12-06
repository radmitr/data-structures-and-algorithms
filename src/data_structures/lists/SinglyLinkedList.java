package data_structures.lists;

/**
 * ------------------------------------------------------------------------------------------------
 * Односвязный список
 * ------------------------------------------------------------------------------------------------
 * Связанный список
 *
 * Связанный список - базовая динамическая структура данных (одна из возможных реализаций
 * списка), состоящая из узлов, каждый из которых содержит как собственно данные, так и одну
 * или две ссылки (указатели) на следующий и/или предыдущий узел списка.
 *
 * Линейный односвязный список (односвязный список) — разновидность связанного списка. Узел
 * содержит данные и одну ссылку (указатель) на следующий элемент списка. Довольно часто
 * используется также альтернативное определение. Односвязный список — рекурсивная линейная
 * структура данных, которая либо пуста, либо ссылается на узел (который хранит данные и ссылку на
 * следующий узел).
 * ------------------------------------------------------------------------------------------------
 * Преимущества и недостатки связанных списков
 *
 * Как уже было сказано выше связанные списки — это динамическая структура данных. По
 * сравнению с массивами она обладает рядом как преимуществ, так и недостатков.
 *
 * Преимущества:
 *   ● Возможность добавления и удаления элементов (изменение размера списка)
 *   ● Размер ограничен доступной оперативной памятью
 *
 * Недостатки:
 *   ● Сложность получения элемента по индексу
 *   ● Дополнительный расход памяти на хранение указателей
 *   ● Нелокальное хранение данных списка (снижает вероятность кеширования)
 *   ● Сложность в выполнении параллельной обработки
 * ------------------------------------------------------------------------------------------------
 * Узел списка
 *
 * Узел списка представляет собой составную структуру. Обычно реализуется с помощью класса
 * или структуры (в процедурных языках). Содержит значения двух типов. Одно для хранения данных
 * (числа, строки и т.д.), второе это ссылка на следующий узел (реализуется как указать или ссылка,
 * тип которых совпадает с типом узел). Из-за того, что на момент компиляции количество узлов
 * списка не известно, то память для их хранения должна выделяться динамически (т.е. использовать
 * heap).
 * ------------------------------------------------------------------------------------------------
 * Односвязный список
 *
 * В простейшем случае односвязный список состоит из структуры хранящей одну ссылку на узел.
 * Такая ссылка называется головой списка (head). Существует два подхода относительно головы
 * списка. В первом случае эта ссылка не инициализирована (равна null, None и т.д.), во втором
 * подходе она указывает на фиктивный узел (создается при инициализации списка). При добавлении
 * значений в список, голова списка указывает на следующий узел в списке (который указывает на
 * следующий узел и т.д.). Такая структура дополняется рядом методов (функций) для добавления,
 * удаления, получения узлов.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в начало списка
 *
 * При добавлении нового значения в список, нужно создать новый узел, который хранит добавляемое
 * значение и next = null.
 *
 * Установить значение next добавляемого узла равное head.
 * Значение head установить равным ссылке на добавляемый узел.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в конец списка. Список пуст
 *
 * При добавлении нового значения в список, нужно создать новый узел, который хранит добавляемое
 * значение и next = null.
 *
 * Значение head установить равным ссылке на добавляемый узел.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в конец списка. Список не пуст
 *
 * При добавлении нового значения в список, нужно создать новый узел, который хранит
 * добавляемое значение и next = null.
 *
 * Начиная с начала списка, проверяем next узла. Если next != null, то переходим к следующему узлу.
 * В случае next == null (текущий узел является последним), то устанавливаем ее на добавляемый
 * узел.
 * ------------------------------------------------------------------------------------------------
 * Добавление значения в произвольное место списка
 *
 * Создаем новый узел, который хранит добавляемое значение и next = null.
 *
 * Начиная с головы списка, проходим до элемента, после которого нужно вставить добавляемый
 * узел. Устанавливаем значение next добавляемого узла, значение равное next текущего. Ссылку next
 * текущего устанавливаем на добавляемый элемент.
 * ------------------------------------------------------------------------------------------------
 * Удаление значения из начала списка
 *
 * Установить значение head, равное значению next, удаляемого элемента. Указать, значение next
 * удаляемого узла равным null. Освободить память занимаемую удаляемым узлом.
 * ------------------------------------------------------------------------------------------------
 * Удаление элемента из конца списка
 *
 * Используем дополнительную ссылку (previous). Начиная с головы списка, выполняем проход по
 * узлам, причем в ссылке previous сохраняем ссылку на предыдущий узел. Как только next текущего
 * узла станет равна null, то переходим к узлу по ссылке previous (это предпоследний элемент),
 * устанавливаем значение next для него равным null. Освобождаем память, занимаемую удаляемым
 * узлом.
 * ------------------------------------------------------------------------------------------------
 * Удаление элемента из произвольного места списка
 *
 * Используем дополнительную ссылку (previous). Начиная с головы списка, выполняем проход по
 * узлам, причем в ссылке previous сохраняем ссылку на предыдущий узел. Как только текущей узел
 * станет равен удаляемому, то переходим к узлу по ссылке previous, устанавливаем значение next для
 * него равным значению next удаляемого узла. Устанавливаем значение next = null для удаляемого.
 * Освобождаем память, занимаемую удаляемым узлом.
 * ------------------------------------------------------------------------------------------------
 * Получение размера списка
 *
 * Для получения размера списка стоит объявить переменную с начальным значением 0. Начиная с
 * начала списка выполнять переход по ссылке к следующему узлу. На каждом переходе увеличивать
 * значение этой переменной на 1. Закончить на узле для которого next == null.
 * ------------------------------------------------------------------------------------------------
 * Работа с индексами
 *
 * Для работы с индексами можно использовать подход аналогичный вычислению длинны. Стоит
 * объявить переменную, начальное значение которой равно начальному индексу (произвольный
 * выбор). Начиная с головы списка, выполняем проход по next (от узла к узлу), то тех пор,
 * пока значение этой переменной не станет равно искомому индексу.
 * ------------------------------------------------------------------------------------------------
 * Использование фиктивного начального узла
 *
 * Для упрощения реализации односвязного списка может быть использован фиктивный первый
 * узел. Голова списка всегда указывает на фиктивный узел. Данные этого узла игнорируются.
 * Применение фиктивного узла позволяет уменьшить количество проверок при реализации. К
 * недостаткам стоит отнести необходимость выделения памяти для хранения фиктивного узла.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://www.youtube.com/watch?v=DkXhIieZK_k">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
class SinglyLinkedList {

    private Node head;

    public SinglyLinkedList() {
        head = new Node();
    }

    private class Node {
        int date;
        Node next;

        public Node() {
        }

        public Node(int date, Node next) {
            this.date = date;
            this.next = next;
        }
    }

    public void addFirst(int value) {
        Node newNode = new Node(value, head.next);
        head.next = newNode;
    }

    public void addLast(int value) {
        Node currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node(value, null);
    }

    public void deleteFirst() {
        head.next = (head.next == null) ? null : head.next.next;
    }

    public void deleteLast() {
        if (head.next == null) {
            return;
        }
        Node curretNode = head.next;
        Node previousNode = head;
        while (curretNode.next != null) {
            previousNode = curretNode;
            curretNode = curretNode.next;
        }
        previousNode.next = null;
    }

    public void insertByIndex(int value, long index) {
        long nodeNumber = 0;
        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next) {
            if (nodeNumber == index) {
                currentNode.next = new Node(value, currentNode.next);
                return;
            }
            nodeNumber += 1L;
        }
        throw new IndexOutOfBoundsException();
    }

    public void deleteByIndex(long index) {
        long nodeNumber = 0;
        Node curretNode = head.next;
        Node previousNode = head;
        while (curretNode != null) {
            if (nodeNumber == index) {
                previousNode.next = curretNode.next;
                curretNode.next = null;
                return;
            }
            previousNode = curretNode;
            nodeNumber += 1;
            curretNode = curretNode.next;
        }
        throw new IndexOutOfBoundsException();
    }

    public int getByIndex(long index) {
        long nodeNumber = 0;
        Node curretNode = head.next;
        while (curretNode != null) {
            if (nodeNumber == index) {
                return curretNode.date;
            }
            nodeNumber += 1L;
            curretNode = curretNode.next;
        }
        throw new IndexOutOfBoundsException();
    }

    public void setByIndex(int value, long index) {
        long nodeNumber = 0;
        Node curretNode = head.next;
        while (curretNode != null) {
            if (nodeNumber == index) {
                curretNode.date = value;
                return;
            }
            nodeNumber += 1L;
            curretNode = curretNode.next;
        }
        throw new IndexOutOfBoundsException();
    }

    public long getLength() {
        long size = 0;
        for (Node currentNode = head.next; currentNode != null; currentNode = currentNode.next) {
            size += 1L;
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node currentNode = head.next; currentNode != null; currentNode = currentNode.next) {
            sb.append(currentNode.date).append(" -> ");
        }
        return sb.toString();
    }

    //=============================================================================================
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        // 1 - addFirst() and addLast()
        list.addFirst(5);
        list.addFirst(2);
        list.addLast(7);
        list.addLast(4);
        System.out.println(list);

        // 2 - deleteByIndex()
        list.deleteByIndex(2); // delete 7
//        list.deleteByIndex(5); // java.lang.IndexOutOfBoundsException
        System.out.println(list);

        // 3 - getByIndex()
        System.out.println(list.getByIndex(0));
        System.out.println(list.getByIndex(1));
        System.out.println(list.getByIndex(2));
//        System.out.println(list.getByIndex(3)); // java.lang.IndexOutOfBoundsException

        // 4 - insertByIndex()
        list.insertByIndex(-3, 0);
        System.out.println(list);

        // 5 - setByIndex()
        list.setByIndex(-1, 2);
        System.out.println(list);
    }
}
