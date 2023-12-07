
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        ArrayBasedStack stack = new ArrayBasedStack();

        stack.push("Hello");
        stack.push("Java");
        stack.push("stack");

        System.out.println(stack);

        for (; stack.peek() != null;) {
            System.out.println(stack.pop());
        }

    }

}

class ArrayBasedStack {
    private Object[] dataArray;
    private int size;
    private int capacity;
    private final int DEFAULT_CAPACITY = 16;
    private final int MAX_STACK_SIZE = Integer.MAX_VALUE - 1;

    public ArrayBasedStack() {
        dataArray = new Object[DEFAULT_CAPACITY];
        capacity = dataArray.length;
        size = 0;
    }

    public void push(Object value) {
        if (size >= capacity) {
            boolean resizeResult = upResize();
            if (!resizeResult) {
                throw new RuntimeException("Cannot add element");
            }
        }
        dataArray[size] = value;
        size += 1;
    }

    public Object pop() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Object element = dataArray[size];
        dataArray[size] = null;
        return element;
    }

    public Object peek() {
        if (size == 0) {
            return null;
        }
        return dataArray[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean upResize() {
        if (capacity >= MAX_STACK_SIZE) {
            return false;
        }
        long newCapacityL = (capacity * 3L) / 2L + 1L;
        int newCapacity = (newCapacityL < MAX_STACK_SIZE) ? (int) newCapacityL : MAX_STACK_SIZE;
        dataArray = Arrays.copyOf(dataArray, newCapacity);
        capacity = newCapacity;
        return true;
    }

    public void trimToSize() {
        dataArray = Arrays.copyOf(dataArray, size);
        capacity = dataArray.length;
    }

    public void clear() {
        dataArray = new Object[DEFAULT_CAPACITY];
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
