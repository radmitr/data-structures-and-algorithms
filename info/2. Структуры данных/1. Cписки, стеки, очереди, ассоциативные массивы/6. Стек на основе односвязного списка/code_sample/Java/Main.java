
public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Stack stack = new Stack();
        stack.push("Word");
        stack.push("Java");
        stack.push("Hello");

        System.out.println(stack);

        String temp = stack.pop();
        System.out.println(stack);

    }

}

class Stack {
    private class Node {
        String date;
        Node next;

        public Node(String date, Node next) {
            this.date = date;
            this.next = next;
        }

        public Node() {
        }
    }

    private Node head;

    public Stack() {
        super();
    }

    public void push(String value) {
        Node newNode = new Node(value, head);
        head = newNode;
    }

    public String pop() {
        if (head != null) {
            String result = head.date;
            head = head.next;
            return result;
        }
        return null;
    }

    public String peek() {
        if (head != null) {
            String result = head.date;
            return result;
        }
        return null;
    }

    public long size() {
        long size = 0;
        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next) {
            size += 1L;
        }
        return size;
    }

    @Override
    public String toString() {
        String result = "";
        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next) {
            result += currentNode.date + " -> ";
        }
        return result;
    }
}
