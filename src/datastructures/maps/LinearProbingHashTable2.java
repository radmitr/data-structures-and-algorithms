package datastructures.maps;

import java.util.Arrays;
import java.util.Random;

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
 * Метод открытой адресации
 *
 * В хеш-таблице хранятся сами пары ключ-значение. Алгоритм вставки элемента проверяет
 * ячейку массива в некотором порядке до тех пор, пока не будет найдена первая свободная ячейка,
 * в которую и будет записан новый элемент.
 * ------------------------------------------------------------------------------------------------
 * Линейное пробирование
 *
 * Последовательность, в которой просматриваются ячейки хеш-таблицы, называется
 * последовательностью проб. Одна из самых простых (и в то же время эффективная) — это линейное
 * пробирование. Его суть сводиться к просмотру индексов (при коллизии) по порядку с шагом в 1
 * до нахождения первого свободного места.
 *
 * Последовательность проб
 *
 *   h(key ,i) = (h(key ) + i) mod N
 *
 *   N - размер хеш-таблицы
 *   i - номер попытки
 * ------------------------------------------------------------------------------------------------
 * Используемая хеш-функция
 *
 * Хеш-таблица с открытой адресацией (линейное пробирование) чувствительна к виду
 * используемой хеш-функции. И хотя работать она будет с любой, для оптимальной
 * производительности стоит использовать семейство функций k-независимого хеширования.
 * Одним из таких является псевдослучайное полиномиальное хеширование.
 *
 * Идея хеширование следующая. Выбирается простое число p, также используют значение
 * p=2n. Выбираются как минимум k=5 случайных чисел (которые фиксируются) из диапазона [0..p-1].
 * Эти числа выступают коэффициентами полинома. В таком случае хеш-код вычисляется
 * следующим образом.
 *
 *             k−1
 *   h(key) = (∑Ai⋅n⋅key^i) mod p
 *             i=0
 *
 *   n(key ) - числовое представление ключа
 *   Ai - случайное число выступающее как коэффициент полинома
 *
 * ------------------------------------------------------------------------------------------------
 * Пример псевдослучайного полиномиального хеширования
 *
 * Выбираем значение p = 2^4 = 16
 *
 * Генерируем 5 случайных чисел в диапазоне от 0..15:
 * -------------------------------------
 * |  i  |  0  |  1  |  2  |  3  |  4  |
 * |-----------------------------------|
 * |  Ai |  7  |  2  |  13 |  5  |  11 |
 * -------------------------------------
 *
 * Полученная хеш-функция равна:
 *   h(key) = (7 + 2⋅n(key) + 13⋅n(key)^2 + 5⋅n(key)^3 + 11⋅n(key)^4) mod 16
 * ------------------------------------------------------------------------------------------------
 * Добавление пары (ключа нет в хеш-таблице)
 *
 * При добавлении пары, вычисляем индекс на который попадет элемент, если на этом индексе
 * нет элемента, то добавляем пару ключ значение и заканчиваем. Если же этот индекс уже занят, то
 * переходим к следующему элементу и так далее.
 *
 * 1) Вычисляем хеш-код ключа.
 * 2) Переходим к индексу равному полученному хеш-коду. Этот индекс уже занят. Переходим к
 *    следующему индексу.
 * 3) Этот индекс также занят. Переходи к следующему.
 * 4) Этот индекс свободен. Записываем пару ключ-значение на полученный индекс.
 * ------------------------------------------------------------------------------------------------
 * Добавление пары (ключ есть в хеш-таблице)
 *
 * При добавлении пары вычисляем индекс, на который попадет элемент, если на этом индексе
 * есть элемент с равным ключом, заменяем значение на добавляемое и заканчиваем.
 *
 * 1) Вычисляем хеш-код ключа.
 * 2) Переходим к индексу равному полученному хеш-коду. Этот индекс уже занят. Переходим к
 *    следующему индексу.
 * 3) Этот индекс также занят. Хеш-код пары равен добавляемому.
 * 4) Заменяем значение пары, на добавляемое значение.
 * ------------------------------------------------------------------------------------------------
 * Поиск пары по ключу
 *
 * При поиске пары по ключу, вычисляем хеш-код ключа. Переходим по индексу равному
 * полученному хеш-коду. Если на индексе нет пары, то поиск неудачен. Если ключ пары на индексе
 * равен искомому, возвращаем значение, если нет переходим к следующему индексу.
 *
 * 1) Вычисляем хеш-код ключа.
 * 2) Переходим к индексу равному полученному хеш-коду. Там есть пара. Ее ключ не равен искомому.
 *    Переходим к следующему индексу.
 * 3) На этом индексе есть пара. Ее ключ равен искомому.
 * 4) Возвращаем значение найденной пары.
 * ------------------------------------------------------------------------------------------------
 * Удаление пары по ключу
 *
 * Вычисляем хеш-код ключа. Переходим по индексу равному полученному хеш-коду. Если на
 * индексе нет пары, заканчиваем такой пары нет. Методом проб находим пару ключ, которой равен
 * удаляемому. Удаляем пару. После чего выполняем смещение все последующих пар (до следующей
 * пустой позиции) на одну позицию влево.
 *
 * 1) Вычисляем хеш-код ключа.
 * 2) Переходим к индексу равному полученному хеш-коду. Тут есть пара. Ключ не равен удаляемому.
 *    Переходим к следующему элементу. Ключ пары равен удаляемому.
 * 3) Удаляем найденный элемент.
 * 4) Производим сдвиг пар (до следующего отсутствующего элемента) на одну позицию влево.
 * ------------------------------------------------------------------------------------------------
 * Увеличение размера хеш-таблицы
 *
 * В случае использования линейного пробирования увеличение размера стоит выполнять при
 * коэффициенте заполнения 0.5. Увеличивают размер массива в два раза, и просто последовательно
 * добавляют пары (используя тот же алгоритм добавления) из старого массива в новый. После
 * окончания работы освобождают память (при необходимости) занимаемую старым массивом. В
 * качестве хеш-таблицы используем новый.
 * ------------------------------------------------------------------------------------------------
 * Получение размера ассоциативного массива
 *
 * Возможны два варианта получения количества элементов:
 *   1) Объявление дополнительной переменной с начальным значением равной 0. При добавлении пары
 *      ключ значение, увеличиваем значение на единицу. При удалении уменьшаем. В таком случае в
 *      любой момент времени значение этой переменной равно количеству элементов.
 *   2) Пройти по всем элементам хеш-таблицы. Просуммировать количество существующих элементов.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/KPRQuf39ZzQ">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
/*
    1. Добавлено поле startIndex во внутренний класс Pair.
    2. При удалении: сдвигаем пару влево, но не позволяем сдвинуть левее, чем startIndex.
    3. При добавлении: у примыкающих пар, пара с большим startIndex перетекает правее.
    4. При добавлении: не допускаем циклическое перетекание пар с больших индексов на меньшие.
    5. Добавлен коэффициент сжатия индексов. Сжимаем индексы к левому краю, оставляя запас для
       перетекания пар на большие индексы.
*/
class LinearProbingHashTable2 {

    public static final int DEFAULT_CAPACITY = 16;

    /**
     * Коэффициент сжатия индексов.
     *
     * Влияет на работу функции `calculateIndex()`. Диапазон значений от 0 до 1.0:
     *   0 - сжатия нет, функция `calculateIndex()` будет возвращать индексы во всём
     *       диапазоне: [0, capacity-1].
     *   0.05 - сжатие 5%, функция `calculateIndex()` не будет возвращать 5% старших индексов.
     *   1.0 - максимальное сжатие, функция `calculateIndex()` будет возвращать только 0.
     */
    private static final double INDEX_COMPRESSION_RATIO = 0.05;

    private Pair[] pairArray;
    private int capacity = DEFAULT_CAPACITY;
    private int[] polyCoeff = new int[5];
    private int size = 0;

    public LinearProbingHashTable2() {
        pairArray = new Pair[capacity];
        calculatePolyCoeff();
    }

    private class Pair {
        String key;
        Object value;
        int startIndex; // введён для корректного удаления пар

        Pair(String key, Object value, int startIndex) {
            this.key = key;
            this.value = value;
            this.startIndex = startIndex;
        }
    }

    private void calculatePolyCoeff() {
        Random rnd = new Random();
        for (int i = 0; i < polyCoeff.length; i++) {
            polyCoeff[i] = rnd.nextInt(capacity);
        }
    }

    private int calculateNewHash(int oldHash) {
        int newHash = polyCoeff[0];
        for (int i = 0; i < polyCoeff.length - 1; i++) {
            newHash = newHash * oldHash + polyCoeff[i + 1];
        }
        int index = Math.abs(newHash % capacity);
        // сжимаем индексы к левому краю, оставляя запас для перетекания пар на большие индексы
        return (int) Math.abs(index * (1 - INDEX_COMPRESSION_RATIO));
    }

    public void addPair(String key, Object value) {
        int index = calculateNewHash(key.hashCode());
        Pair pair =  new Pair(key, value, index);
        while (true) {
            if (pairArray[index] == null) {
                pairArray[index] = pair;
//                System.out.println(key + ":" + value + ", (index=" + index + ")"); // трассировка
                size++;
                break;
            } else if (pairArray[index].key.equals(key)) {
                pairArray[index].value = value;
//                System.out.println(key + ":" + value + ", (index=" + index + ")"); // трассировка
                break;
            } else {
//                System.out.println(key + ":" + value + ", (index=" + index + ")"); // трассировка
                // у примыкающих пар, пара с большим startIndex перетекает правее
                if (pairArray[index].startIndex > pair.startIndex) {
                    Pair tempPair = pairArray[index];
                    pairArray[index] = pair;
                    pair = tempPair;
                }
                if (index + 1 >= capacity) {
                    // не допускаем циклическое перетекание пар с больших индексов на меньшие
                    upResize();
                    addPair(pair.key, pair.value);
                    return;
                } else {
                    index++;
                }
            }
        }
        if (size > capacity / 2) {
            upResize();
        }
    }

    private void upResize() {
        int newCapacity = capacity * 2;
        if (newCapacity < 0) {
            throw new RuntimeException("Cannot increase the capacity");
        }
        Pair[] oldPairArray = pairArray;
        pairArray = new Pair[newCapacity];
        capacity = newCapacity;
        calculatePolyCoeff();
        size = 0;
        for (Pair pair : oldPairArray) {
            if (pair != null) {
                addPair(pair.key, pair.value);
            }
        }
    }

    public Object get(String key) {
        int index = calculateNewHash(key.hashCode());
        while (true) {
            if (pairArray[index] == null) {
                return null;
            } else if (pairArray[index].key.equals(key)) {
                return pairArray[index].value;
            } else {
                index = (index + 1) % capacity;
            }
        }
    }

    public boolean remove(String key) {
        int index = calculateNewHash(key.hashCode());
        while (true) {
            if (pairArray[index] == null) {
                return false;
            } else if (pairArray[index].key.equals(key)) {
                pairArray[index] = null;
                while (true) {
                    int nextIndex = (index + 1) % capacity;
                    // сдвигаем пару влево, но не позволяем сдвинуть левее, чем startIndex
                    if (pairArray[nextIndex] == null || index < pairArray[nextIndex].startIndex) {
                        break;
                    } else {
                        pairArray[index] = pairArray[nextIndex];
                        pairArray[nextIndex] = null;
                    }
                    index = nextIndex;
                }
                size--;
                return true;
            } else {
                index = (index + 1) % capacity;
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
            for (Pair pair : pairArray) {
                if (pair != null) {
                    sb.append(pair.key).append(":").append(pair.value).append(", ");
                }
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.append("}").toString();
    }

    //=============================================================================================
    public static void main(String[] args) {
        LinearProbingHashTable2 hashTable = new LinearProbingHashTable2();
        System.out.println(hashTable);
        System.out.println(Arrays.toString(hashTable.polyCoeff));

        // 1 - add()
        hashTable.addPair("zero", 0);
        hashTable.addPair("one", 1);
        hashTable.addPair("two", 2);
        hashTable.addPair("three", 3);
        hashTable.addPair("four", 4);
        hashTable.addPair("five", 5);
        hashTable.addPair("six", 6);
        hashTable.addPair("seven", 7);
        hashTable.addPair("eight", 8);
        hashTable.addPair("nine", 9);
        hashTable.addPair("ten", 10);
        hashTable.addPair("eleven", 11);
        hashTable.addPair("twelve", 12);
//        hashTable.addPair("thirteen", 13);
//        hashTable.addPair("fourteen", 14);
//        hashTable.addPair("fifteen", 15);
//        hashTable.addPair("sixteen", 16);
//        hashTable.addPair("seventeen", 17);
//        hashTable.addPair("eighteen", 18);
//        hashTable.addPair("nineteen", 19);
//        hashTable.addPair("twenty", 20);
//        hashTable.addPair("twenty one", 21);
//        hashTable.addPair("fifty", 50);
//        hashTable.addPair("one hundred", 100);
//        hashTable.addPair("five hundred", 500);
//        hashTable.addPair("one thousand", 1000);
//        hashTable.addPair("minus one", -1);
//        hashTable.addPair("minus five", -5);
//        hashTable.addPair("minus ten", -10);
//        hashTable.addPair("minus fifty", -50);
//        hashTable.addPair("minus one hundred", -100);
//        hashTable.addPair("plus infinity", Integer.MAX_VALUE);
//        hashTable.addPair("minus infinity", Integer.MIN_VALUE);
//        hashTable.addPair("one billion", 1_000_000_000);
//        hashTable.addPair("a billion billion", 0);
        System.out.println(hashTable);

        // 2 - remove()
        System.out.println("Remove: \"one\"");
        hashTable.remove("one");
        System.out.println(hashTable);

        System.out.println("Remove: \"three\"");
        hashTable.remove("three");
        System.out.println(hashTable);

        // 3 - get()
        System.out.println(hashTable.get("two"));
        System.out.println(hashTable.get("four"));
        System.out.println(hashTable.get("ten"));

        // 4 - getSize()
        System.out.println("size = " + hashTable.getSize());
    }
}
