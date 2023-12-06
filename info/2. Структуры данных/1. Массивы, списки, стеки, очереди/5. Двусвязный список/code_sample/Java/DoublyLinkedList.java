
public class DoublyLinkedList {

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

    public DoublyLinkedList() {
        super();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Object value) {
        Node addNode = new Node(value, head.next, head);
        head.next.prev = addNode;
        head.next = addNode;
        length += 1;
    }

    public void addLast(Object value) {
        Node addNode = new Node(value, tail, tail.prev);
        tail.prev.next = addNode;
        tail.prev = addNode;
        length += 1;
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
        length -= 1;
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
        length -= 1;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    private Node getNodeByIndex(long index) {
        Node resultNode = null;
        if (index < length / 2) {
            long nodeIndex = 0;
            resultNode = head.next;
            for (; nodeIndex != index;) {
                resultNode = resultNode.next;
                nodeIndex += 1;
            }
        } else {
            long nodeIndex = length - 1;
            resultNode = tail.prev;
            for (; nodeIndex != index;) {
                resultNode = resultNode.prev;
                nodeIndex -= 1;
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
        length -= 1;
    }

    public void insertByIndex(long index, Object value) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        Node addNode = new Node(value, resultNode, resultNode.prev);
        resultNode.prev.next = addNode;
        resultNode.prev = addNode;
        length += 1;
    }

    public void setByIndex(long index, Object value) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        resultNode.data = value;
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
            removeFirst();
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
