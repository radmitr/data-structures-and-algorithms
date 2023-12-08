package sample;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        HashTable hashTable = new HashTable();

        hashTable.addPair("one", 1);
        hashTable.addPair("five", 5);
        hashTable.addPair("four", 4);
        hashTable.addPair("two", 2);
        hashTable.addPair("three", 3);
        hashTable.addPair("nine", 9);

        System.out.println(hashTable.get("nine"));

        System.out.println(hashTable);

        hashTable.remove("four");
        System.out.println(hashTable);

    }

}

class HashTable {
    private class Pair {
        public String key;
        public Object value;

        public Pair(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private Pair[] pairArray;
    private int capacity = 16;
    private int[] polyCoeff = new int[5];
    private int size = 0;

    public HashTable() {
        super();
        pairArray = new Pair[capacity];
        calculatePolyCoeff();
    }

    private final void calculatePolyCoeff() {
        Random rn = new Random();
        for (int i = 0; i < polyCoeff.length; i++) {
            polyCoeff[i] = rn.nextInt(capacity);
        }
    }

    private int calculateNewHash(int oldHash) {
        int newHash = polyCoeff[0];
        for (int i = 0; i < polyCoeff.length - 1; i++) {
            newHash = newHash * oldHash + polyCoeff[i + 1];
        }
        return Math.abs(newHash % capacity);
    }

    public void addPair(String key, Object value) {
        int index = calculateNewHash(key.hashCode());
        for (;;) {
            if (pairArray[index] == null) {
                pairArray[index] = new Pair(key, value);
                size += 1;
                break;
            } else if (pairArray[index].key.equals(key)) {
                pairArray[index].value = value;
                break;
            } else {
                index = (index + 1) % capacity;
            }
        }
        if (size > capacity / 2) {
            upResize();
        }
    }

    private void upResize() {
        int newSize = capacity * 2;
        if (newSize < 0) {
            throw new IllegalArgumentException("");
        }
        Pair[] oldPairArray = pairArray;
        pairArray = new Pair[newSize];
        capacity = newSize;
        calculatePolyCoeff();
        for (Pair pair : oldPairArray) {
            if (pair != null) {
                addPair(pair.key, pair.value);
            }
        }
    }

    public Object get(String key) {
        int index = calculateNewHash(key.hashCode());
        for (;;) {
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
        for (;;) {
            if (pairArray[index] == null) {
                return false;
            } else if (pairArray[index].key.equals(key)) {
                pairArray[index] = null;
                for (;;) {
                    pairArray[index] = pairArray[(index + 1) % capacity];
                    if (pairArray[index] == null) {
                        break;
                    }
                    index = (index + 1) % capacity;
                }
                size = size - 1;
                return true;
            } else {
                index = (index + 1) % capacity;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        String result = "{";
        for (Pair pair : pairArray) {
            if (pair != null) {
                result += pair.key + ": " + pair.value + ", ";
            }
        }
        return result.substring(0, result.length() - 2) + "}";
    }

}