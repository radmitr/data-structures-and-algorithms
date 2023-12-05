import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BinaryHeap heap = new BinaryHeap();
        heap.add(6, "Orange");
        heap.add(7, "Apple");
        heap.add(3, "Plum");
        heap.add(4, "Lemon");
        heap.add(5, "Pear");
        heap.add(9, "Cherry");
        heap.add(12, "Banana");
        System.out.println(heap);
        System.out.println();

        for (;;) {
            Object data = heap.extract();
            if (data == null) {
                break;
            }
            System.out.println(data);

        }

    }
}

class BinaryHeap {
    private class Node {
        int key;
        Object data;

        public Node(int key, Object data) {
            this.key = key;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node [key=" + key + ", data=" + data + "]";
        }
    }

    private List<Node> nodes = new ArrayList<>();

    public BinaryHeap() {
        super();
    }

    public void add(int key, Object data) {
        nodes.add(new Node(key, data));
        siftUp(nodes.size() - 1);
    }

    public Object extract() {
        if (nodes.size() == 0) {
            return null;
        }
        if (nodes.size() == 1) {
            return nodes.remove(nodes.size() - 1).data;
        }
        Object result = nodes.get(0).data;
        nodes.set(0, nodes.remove(nodes.size() - 1));
        siftDown(0);
        return result;
    }

    public Object inserAndExtract(int key, Object data) {
        if (nodes.size() == 0) {
            nodes.add(new Node(key, data));
            return null;
        }
        Object result = null;
        result = nodes.get(0);
        nodes.set(0, new Node(key, data));
        siftDown(0);
        return result;
    }

    public void delete(int key) {
        int i = findIndexByKey(key);
        if (i != -1) {
            Node node = nodes.remove(nodes.size() - 1);
            if (nodes.size() == 0) {
                return;
            }
            nodes.set(i, node);
            heapRecovery(i);
        }
    }

    public void changeKey(int oldKey, int newKey) {
        int i = findIndexByKey(oldKey);
        if (i != -1) {
            nodes.get(i).key = newKey;
            heapRecovery(i);
        }
    }

    private void siftUp(int i) {
        for (; i > 0;) {
            int j = (i - 1) / 2;
            if (nodes.get(i).key > nodes.get(j).key) {
                Collections.swap(nodes, i, j);
            } else {
                break;
            }
            i = j;
        }
    }

    private void siftDown(int i) {
        for (;;) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            int j = i;
            if (leftIndex <= nodes.size() - 1 && nodes.get(leftIndex).key > nodes.get(j).key) {
                j = leftIndex;
            }
            if (rightIndex <= nodes.size() - 1 && nodes.get(rightIndex).key > nodes.get(j).key) {
                j = rightIndex;
            }
            if (i != j) {
                Collections.swap(nodes, i, j);
                i = j;
            } else {
                break;
            }
        }
    }

    private void heapRecovery(int i) {
        if (i > 0 && nodes.get(i).key > nodes.get((i - 1) / 2).key) {
            siftUp(i);
            return;
        }
        siftDown(i);
    }

    private int findIndexByKey(int key) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).key == key) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
