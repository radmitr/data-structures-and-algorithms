
public class Queue {
    private class Node {
        Object data;
        Node next;
        Node prev;

        public Node(Object data, Node next, Node prev) {
            super();
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Node() {
            super();
        }

        @Override
        public String toString() {
            return "Node [data=" + data + ", next=" + next + ", prev=" + prev + "]";
        }
    }

    private final Node head;
    private final Node tail;
    private long length = 0;

    public Queue() {
        super();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public void enqueue(Object value) {
        Node addNode = new Node(value, head.next, head);
        head.next.prev = addNode;
        head.next = addNode;
        length += 1;
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
        length -= 1;
        return value;
    }

    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        Node removeNode = tail.prev;
        return removeNode.data;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    public long getLength() {
        long length = 0;
        Node currentNode = head.next;
        for (; currentNode != tail;) {
            length += 1;
            currentNode = currentNode.next;
        }
        return length;
    }

    public void clear() {
        for (; length > 0;) {
            dequeue();
        }
    }

    @Override
    public String toString() {
        String result = "[";
        Node currentNode = head.next;
        for (; currentNode != tail;) {
            result += currentNode.data + " ";
            currentNode = currentNode.next;
        }
        return result += "]";
    }
}
