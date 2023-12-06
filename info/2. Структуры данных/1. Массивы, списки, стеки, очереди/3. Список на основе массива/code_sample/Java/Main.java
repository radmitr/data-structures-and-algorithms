import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrayBasedList list = new ArrayBasedList();
        for (int i = 0; i < 12; i++) {
            list.add(i);
        }
        System.out.println(list);
        list.trimToSize();
        System.out.println(list);
        list.add(-5);
        System.out.println(list);
    }
}

class ArrayBasedList {
    private int[] dataArray;
    private int size;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;

    public ArrayBasedList() {
        dataArray = new int[DEFAULT_CAPACITY];
        capacity = dataArray.length;
        size = 0;
    }

    public void add(int value) {
        if (size >= capacity) {
            boolean resizeResult = upResize();
            if (!resizeResult) {
                throw new RuntimeException("Cannot add element");
            }
        }
        dataArray[size] = value;
        size += 1;
    }

    public void addByIndex(int value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size >= capacity) {
            boolean resizeResult = upResize();
            if (!resizeResult) {
                throw new RuntimeException("Cannot add element");
            }
        }
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
        size += 1;

    }

    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        size -= 1;
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
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                result += dataArray[i] + ", ";
            } else {
                result += dataArray[i];
            }
        }
        return result += "]";
    }

}
