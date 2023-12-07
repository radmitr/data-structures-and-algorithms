
public class Main {

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addFirst(3);
        list.addLast(7);
        list.addLast(11);
        list.addLast(13);
        list.addLast(17);
        list.addLast(19);
        System.out.println(list);

        list.setByIndex(2, -2);
        System.out.println(list);

        list.clear();

        System.out.println(list);
    }

}
