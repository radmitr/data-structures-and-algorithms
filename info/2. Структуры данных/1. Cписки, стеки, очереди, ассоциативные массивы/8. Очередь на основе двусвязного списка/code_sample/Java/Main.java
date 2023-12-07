

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Queue queue = new Queue();

        queue.enqueue("Hello");
        queue.enqueue("World");

        System.out.println(queue);
        System.out.println(queue.getLength());

        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.getLength());

        queue.clear();
        System.out.println(queue);

    }

}
