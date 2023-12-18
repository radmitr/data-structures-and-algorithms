package datastructures.maps;

import datastructures.lists.DoublyLinkedList;

/**
 * ------------------------------------------------------------------------------------------------
 * Ассоциативный массив на основе хеш-таблиц
 * ------------------------------------------------------------------------------------------------
 * Ассоциативный массив
 *
 * Ассоциативный массив — абстрактный тип данных, позволяющий хранить пары вида «(ключ, значение)».
 * Предполагается, что ассоциативный массив не может хранить две пары с одинаковыми ключами.
 *
 * Поддерживаемые операции:
 *   ● Добавление пары
 *   ● Удаление пары
 *   ● Поиск пары и поиск значения по ключу
 *   ● Получение размера ассоциативного массива
 * ------------------------------------------------------------------------------------------------
 * Хеш-таблица
 *
 * В качестве хеш-таблицы выступает массив (или список), в котором элементы хранятся парами
 * ключ-значение. Индекс для хранения пары определяется хеш-кодом ключа. При этом хеш-функция
 * выбирается такая, что бы генерировать хеш-код в диапазоне индексов этого массива.
 * ------------------------------------------------------------------------------------------------
 * Разрешение коллизий
 *
 * Диапазон всех ключей гораздо больше диапазона индексов в хеш таблице. Таким образом
 * возможны коллизии. Это означает, что для разных ключей генерируется один и тот же хеш-код и как
 * следствие они должны быть размещены по одному и тому же индексу.
 *
 * Способы разрешения коллизий:
 *   1) Метод списков
 *   2) Открытая адресация
 * ================================================================================================
 * Метод списков
 *
 * Одним из самых простых способов разрешения коллизий является метод списков. В таком
 * случае элементами хеш-таблицы выступают списки, содержащие пары ключ-значение. При
 * коллизии пара ключ-значение добавляется в соответствующий список.
 * ------------------------------------------------------------------------------------------------
 * Некоторые термины
 *
 * Hash Table (хеш-таблица) — массив для хранения пар ключ-значение.
 * Capacity (емкость) — количество элементов в массиве.
 * Load Factor(коэффициент загрузки) — вещественное значение (по умолчанию 0.75) используемое в
 *                                     алгоритме работы хеш-таблицы.
 * Threshold (порог) — пороговое значение количества добавленных элементов после достижения
 *                     которого происходит увеличение хеш-таблицы. Обычно равен
 *                     capacity * load factor.
 * Size (количество элементов) — количество элементов во всех списках хеш-таблицы.
 * ------------------------------------------------------------------------------------------------
 * Добавление пары в хеш-таблицу
 *
 * 1) Вычисляется хеш-код на основе ключа. Должна использоваться хеш-функция, генерирующая хеш в
 *    диапазоне от [0..capacity-1].
 * 2) Полученный хеш используется как индекс в массиве. Начинают итерации по списку, расположенном
 *    на этом индексе.
 *    1) Если при итерации оказалось, что элемент с таким ключом уже есть в списке, тогда
 *       обновляют найденный элемент. Обновление заключается в замене значения на добавляемое.
 *    2) Если в списке нет элемента с таким ключом, тогда формируется новый узел списка и
 *       добавляется в его конец.
 * ------------------------------------------------------------------------------------------------
 * Добавление пары в хеш-таблицу (ключа нет в карте)
 *
 * Предположим: добавляется пара элементов (ключ - «one», значение — 1). Текущий размер хеш-таблицы
 * примем равный 4. Процесс добавления выглядит следующим образом:
 *   1) Вычисляем хеш-код ключа.
 *   2) Переходим к списку по индексу, равному полученному хеш-коду. Проходим по списку. В списке
 *      нет элементов с таким ключом.
 *   3) Создаем новый элемент хранящий пару ключ-значение.
 *   4) Добавляем полученный элемент в конец списка.
 * ------------------------------------------------------------------------------------------------
 * Добавление пары в хеш-таблицу (ключа есть в карте)
 *
 * Предположим: добавляется пара элементов (ключ - «one», значение — -1). Текущий размер
 * хеш-таблицы примем равный 4. Процесс добавления выглядит следующим образом:
 *   1) Вычисляем хеш-код ключа.
 *   2) Переходим к списку по индексу, равному полученному хеш-коду. Проходим по списку. В списке
 *      есть элемент с таким ключом.
 *   3) Переходим к найденному элементу.
 *   4) Заменяем значение на добавляемое.
 * ------------------------------------------------------------------------------------------------
 * Поиск значения по ключу
 *
 * Выполняем поиск ключа - «one». Поиск выглядит так:
 *   1) Вычисляем хеш-код ключа.
 *   2) Переходим к списку по индексу, равному полученному хеш-коду. Проходим по списку. В списке
 *      есть элемент с таким ключом.
 *   3) Переходим к найденному элементу.
 *   4) Возвращаем значение записанное в этом элементе.
 * ------------------------------------------------------------------------------------------------
 * Удаление значения по ключу
 *
 * Удаляем пару по ключу - «one». Удаление выглядит так:
 *   1) Вычисляем хеш-код ключа.
 *   2) Переходим к списку по индексу, равному полученному хеш-коду. Проходим по списку. В списке
 *      есть элемент с таким ключом.
 *   3) Переходим к найденному элементу.
 *   4) Удаляем найденный элемент.
 * ------------------------------------------------------------------------------------------------
 * Увеличение размера хеш-таблицы
 *
 * Как только количество элементов превышает пороговое значение, вызывается алгоритм
 * увеличения размера хеш-таблицы. Его суть сводиться к созданию новой хеш-таблицы большего
 * размера. После чего добавляем все элементы из старой хеш-таблицы в новый. После этого удаляем
 * старую хеш-таблицу.
 * ------------------------------------------------------------------------------------------------
 * Получение размера ассоциативного массива
 *
 * Возможны два варианта получения количества элементов:
 *   1) Объявление дополнительной переменной с начальным значением равной 0. При добавлении пары
 *      ключ значение, увеличиваем значение на единицу. При удалении уменьшаем. В таком случае в
 *      любой момент времени значение этой переменной равно количеству элементов.
 *   2) Пройти по всем элементам хеш-таблицы. Просуммировать количество элементов во всех списках.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/KPRQuf39ZzQ">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class ListMethodHashTable {

    public static final int INITIAL_CAPACITY = 16;
    public static final double INITIAL_LOAD_FACTOR = 0.75;

    private int capacity;
    private double loadFactor;
    private int size;
    private DoublyLinkedList[] hashTable;

    public ListMethodHashTable() {
        this(INITIAL_LOAD_FACTOR);
    }

    public ListMethodHashTable(double loadFactor) {
        capacity = INITIAL_CAPACITY;
        this.loadFactor = loadFactor;
        hashTable = new DoublyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            hashTable[i] = new DoublyLinkedList();
        }
    }

    private class Pair {
        String key;
        Object value;

        public Pair(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(String key, int value) {
        int n = Math.abs(key.hashCode() % capacity);
        boolean isNewNode = true;
        for (int i = 0; i < hashTable[n].getLength(); i++) {
            Pair pair = (Pair) hashTable[n].getByIndex(i);
            if (pair.key.equals(key)) {
                pair.value = value;
                isNewNode = false;
//                System.out.println(key + ":" + value + ", (index=" + n + ")"); // трассировка
                break;
            }
        }
        if (isNewNode) {
            hashTable[n].addLast(new Pair(key, value));
//            System.out.println(key + ":" + value + ", (index=" + n + ")"); // трассировка
            size++;
        }
        if (size > capacity * loadFactor) {
            /*
               capacity == 16
               capacity * loadFactor = 16 * 0.75 = 12
               size > 12
            */
            upResize();
            /*
               capacity = 2 * capacity = 2 * 16 = 32
               capacity == 32
               capacity * loadFactor = 32 * 0.75 = 24
               size < 24
            */
        }
    }

    public Object get(String key) {
        int n = Math.abs(key.hashCode() % capacity);
        for (int i = 0; i < hashTable[n].getLength(); i++) {
            Pair pair = (Pair) hashTable[n].getByIndex(i);
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }
        return null;
    }

    public boolean remove(String key) {
        int n = Math.abs(key.hashCode() % capacity);
        for (int i = 0; i < hashTable[n].getLength(); i++) {
            Pair pair = (Pair) hashTable[n].getByIndex(i);
            if (pair.key.equals(key)) {
                hashTable[n].removeByIndex(i);
                size--;
                return true;
            }
        }
        return false;
    }

    public void upResize() {
        capacity *= 2;
        DoublyLinkedList[] newHashTable = new DoublyLinkedList[capacity];
        for (int i = 0; i < newHashTable.length; i++) {
            newHashTable[i] = new DoublyLinkedList();
        }
        for (int i = 0; i < hashTable.length; i++) {
            for (int j = 0; j < hashTable[i].getLength(); j++) {
                Pair pair = (Pair) hashTable[i].getByIndex(j);
                int n = Math.abs(pair.key.hashCode() % capacity);
                newHashTable[i].addLast(pair);
            }
        }
        hashTable = newHashTable;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (size > 0) {
            for (int i = 0; i < hashTable.length; i++) {
                for (int j = 0; j < hashTable[i].getLength(); j++) {
                    Pair pair = (Pair) hashTable[i].getByIndex(j);
                    sb.append(pair.key).append(":").append(pair.value).append(", ");
                }
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.append("}").toString();
    }

    //=============================================================================================
    public static void main(String[] args) {
        ListMethodHashTable hashTable = new ListMethodHashTable();
        System.out.println(hashTable);

        // 1 - put()
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        hashTable.put("four", 4);
        hashTable.put("five", 5);
        hashTable.put("nine", 9);
        System.out.println(hashTable);

        // 2 - remove()
        hashTable.remove("one");
        System.out.println(hashTable);

        // 3 - get()
        System.out.println(hashTable.get("one")); // >> null
        System.out.println(hashTable.get("two")); // >> 2

        // 4 - upResize()
        hashTable.put("one", 1);
        hashTable.put("six", 6);
        hashTable.put("seven", 7);
        hashTable.put("eight", 8);
        hashTable.put("ten", 10);
        hashTable.put("eleven", 11);
        hashTable.put("twelve", 12);
        hashTable.put("zero", 0);
        System.out.println(hashTable);

        // 5 - getSize()
        System.out.println(hashTable.getSize());
    }
}