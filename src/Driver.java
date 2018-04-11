public class Driver {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addToFront(20);
        list.addToBack(10);
        list.addToBack(12);
        list.addToBack(44);
        list.addToBack(60);

        System.out.println(list);

        list.reverse();

        System.out.println(list);

    }
}
