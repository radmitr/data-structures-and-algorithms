

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Queue queue = new Queue();
        queue.enqueue(3);
        queue.enqueue(5);
        System.out.println(queue.dequeue());
        queue.enqueue(0);
        queue.enqueue(9);
        System.out.println(queue.dequeue());
        System.out.println(queue);
        queue.enqueue(7);
        System.out.println(queue);
        queue.enqueue(1);
        System.out.println(queue);
        queue.enqueue(11);
        System.out.println(queue);
        queue.trimToSize();
        System.out.println(queue);

    }

}
