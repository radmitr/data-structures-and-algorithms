package datastructures.maps;

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
 * Двойное хеширование
 *
 * Как уже было сказано алгоритм открытой адресации чувствителен к выбранному виду хеш-функции.
 * Одним из лучших способов решение этой проблемы является использование двойного
 * хеширования. При двойном хешировании используется хеш-функция вида:
 *
 *   h(key, i) = (h1(key) + i⋅h2(key)) mod p
 *
 *   i - номер попытки
 *   h1(key), h2(key) - вспомогательные хеш-функции выбор которых зависит от выбора p
 *
 * К хеш-функции h2 выдвигается ряд требований:
 *   1) Она не должна возвращать 0.
 *   2) Она должна возвращать значения для циклического прохода по таблице.
 *   3) Желательно быстро вычисляться.
 *   4) Любое полученное значение должно быть взаимно простым с p.
 *
 * Одним из способов определить вспомогательные хеш функции является следующее:
 *
 *   h1(key) = n⋅(key) mod p
 *   h2(key) = 1 + n⋅(key) mod (p − 1)
 *
 *   n(key) — числовое представление ключа
 *   p — простое число
 * ------------------------------------------------------------------------------------------------
 * «Ленивое» удаление
 *
 * При интенсивной работе с ассоциативным массивом можно уменьшить нагрузку на
 * подсистему выделения и освобождения памяти (и как следствие поднять производительность)
 * путем использования «ленивого» удаления. Под «ленивым» удалением понимают фиктивное
 * удаление элемента, заключающееся просто в пометке его как удаленного. Для этого к паре
 * ключ-значение добавляют элемент, выступающий в роли маркера: существует пара или удалена.
 * Фактически удаления пары не происходит, поэтому при вставке на ее место можно просто
 * заметить ее ключ и значение на добавляемые. Таким образом не нужно многократно выделять и
 * освобождать память занимаемую парами.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/KPRQuf39ZzQ">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
/*
    1. Сразу выделяется память под все пары ключ-значение.
    2. Применяется ленивое удаление пар.
*/
public class DoubleHashingHashTable1 {

    public static final int[] PRIME_NUMBERS = {
            17, 37, 73, 149, 307, 613, 1259, 2503, 5003, 10267, 20173, 50423, 100847,
            200899, 500153, 1000249, 2000539, 5000917, 10555031, 20777353, 50111921,
            100333553, 200999741, 500333161, 1000777303, 2147483647
    };
    private int primeIndex;
    private int capacity;
    private int size;
    Pair[] pairTable;

    public DoubleHashingHashTable1() {
        capacity = PRIME_NUMBERS[primeIndex];
        pairTable = new Pair[capacity];
        for (int i = 0; i < pairTable.length; i++) {
            pairTable[i] = new Pair();
        }
    }

    private class Pair {
        String key;
        Object value;
        boolean isPresent;

        public Pair() {
            isPresent = false;
        }

        public Pair(String key, Object value) {
            this.key = key;
            this.value = value;
            this.isPresent = false;
        }
    }

    public void put(String key, Object value) {
        int p = capacity;
        int keyNumber = key.hashCode();
        int addIndex, i = 0;
        while (true) {
            addIndex = Math.abs((keyNumber % p + i * (1 + keyNumber % (p - 1))) % p);
            if (!pairTable[addIndex].isPresent) {
                pairTable[addIndex].key = key;
                pairTable[addIndex].value = value;
                pairTable[addIndex].isPresent = true;
                size++;
//                System.out.println(key + ":" + value + ", (index=" + addIndex + ")"); // трассировка
                break;
            } else {
                if (pairTable[addIndex].key.equals(key)) {
                    pairTable[addIndex].value = value;
                    return;
                }
            }
            i++;
        }
        if (size > capacity / 2) {
            upResize();
        }
    }

    public Object get(String key) {
        int p = capacity;
        int keyNumber = key.hashCode();
        int getIndex, i = 0;
        while (true) {
            getIndex = Math.abs((keyNumber % p + i * (1 + keyNumber % (p - 1))) % p);
            if (!pairTable[getIndex].isPresent) {
                return null;
            } else {
                if (pairTable[getIndex].key.equals(key)) {
                    return pairTable[getIndex].value;
                }
            }
            i++;
        }
    }

    public boolean remove(String key) {
        int p = capacity;
        int keyNumber = key.hashCode();
        int removeIndex, i = 0;
        while (true) {
            removeIndex = Math.abs((keyNumber % p + i * (1 + keyNumber % (p - 1))) % p);
            if (!pairTable[removeIndex].isPresent) {
                return false;
            } else {
                if (pairTable[removeIndex].key.equals(key)) {
                    pairTable[removeIndex].isPresent = false;
                    size--;
                    Pair tempRemovedPair = pairTable[removeIndex];
                    while (true) {
                        i++;
                        int nextRemoveIndex = Math.abs((keyNumber % p + i * (1 + keyNumber % (p - 1))) % p);
//                        System.out.println("nextRemoveIndex=" + nextRemoveIndex); // трассировка
                        if (!pairTable[nextRemoveIndex].isPresent) {
                            pairTable[removeIndex] = tempRemovedPair;
                            return true;
                        }
                        pairTable[removeIndex] = pairTable[nextRemoveIndex];
                        removeIndex = nextRemoveIndex;
                    }
                }
            }
            i++;
        }
    }

    public void upResize() {
        primeIndex++;
        if (primeIndex >= PRIME_NUMBERS.length) {
            throw new RuntimeException("Cannot increase the capacity");
        }
        Pair[] tempPairTable = pairTable;
        capacity = PRIME_NUMBERS[primeIndex];
        pairTable = new Pair[capacity];
        for (int i = 0; i < pairTable.length; i++) {
            pairTable[i] = new Pair();
        }
        size = 0;
        for (Pair pair : tempPairTable) {
            if (pair.isPresent) {
                put(pair.key, pair.value);
            }
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (size > 0) {
            for (Pair pair : pairTable) {
                if (pair.isPresent) {
                    sb.append(pair.key).append(":").append(pair.value).append(", ");
                }
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.append("}").toString();
    }

    //=============================================================================================
    public static void main(String[] args) {
        DoubleHashingHashTable1 hashTable = new DoubleHashingHashTable1();
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



        // 6 - again remove()
        hashTable.remove("two");
        hashTable.remove("three");
        hashTable.remove("zero");
        System.out.println(hashTable);

        // 7 - again getSize()
        System.out.println(hashTable.getSize());
    }
}
