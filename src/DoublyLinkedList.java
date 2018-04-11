// this is a doubly linked list storing ValueType objects

class DoublyLinkedList<ValueType> {
    // private member variables
    // note: 'head' and 'tail' are sentinels
    private Node head;
    private Node tail;
    private int size;

    // constructor
    public DoublyLinkedList() {
        // create an empty list
        size = 0;

        // we have to create these sentinels, and link them to each other
        head = new Node(null);
        tail = new Node(null);

        // link them to each other
        head.setNext(tail);
        tail.setPrev(head);
    }


    // helper size methods
    private boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    // helper method to insert between two nodes
    private void addBetween(ValueType value, Node pred, Node succ) {
        // first, create a new node for value
        Node newNode = new Node(value);

        // second, link value to its predecessor and successor
        newNode.setPrev(pred);
        newNode.setNext(succ);

        // third, link the predecessor and successor to the new node
        pred.setNext(newNode);
        succ.setPrev(newNode);

        // fourth, we created a new node, so we should increase the size
        //   variable on our list
        size++;
    }

    public void addToFront(ValueType value) {
        addBetween(value, head, head.getNext());
    }

    public void addToBack(ValueType value) {
        addBetween(value, tail.getPrev(), tail);
    }



    public void reverse(){
        Node current = head.getNext();
        Node temp = null;

        // Cycling through each value and updating its connections
        while(current != tail){
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

        // Only working on ends if list isn't empty
        if (temp != null) {
            // Fixing ends
            head.getNext().setNext(tail);
            tail.getPrev().setPrev(head);

            // Switching tail and head
            Node firstNode = tail.getPrev();
            tail.setNext(null);
            tail.setPrev(head.getNext());
            head.setNext(firstNode);
            head.setPrev(null);
        }

    }

                    ///// PART 2 and 3 /////

    // This takes O(n) time, because the amount of lines grows as the array gets larger, but only by a factor of 1 (n).
    // O(n) is the minimum amount of time possible to complete this in, because every item in the list must be touched,
    // and referencing every item at least once is an O(n) cost function.



    private ValueType removeNode(Node node) {
        // 1.) we store its value
        ValueType ret = node.getValue();

        // 2.) update the references before and after the node
        Node prev = node.getPrev();
        Node next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        //node.getPrev().setNext(node.getNext());

        // 3.) decrement the size, because we just removed a node
        size--;

        // 4.) return the old value
        return ret;
    }

    public ValueType removeFront() {
        if (isEmpty())
            throw new IllegalStateException("cannot remove from an empty list");

        return removeNode(head.getNext());
    }

    public ValueType removeBack() {
        if (isEmpty())
            throw new IllegalStateException("cannot remove from an empty list");

        return removeNode(tail.getPrev());
    }

    /**
     * Method added for assignment; removes first node with given value
     *
     * @param e The value of the node to remove
     * @return Whether a node was removed or not
     */
    public boolean remove(ValueType e) {
        if (isEmpty()) {
            return false;
        }

        Node node = tail.getPrev();
        for (int i = 0; i <= size; i++) {
            if (node.getValue().equals(e)) {
                removeNode(node);
                return true;
            }
        }
        return false;
    }


    // to string method
    public String toString() {
        // build a string as we go
        StringBuilder sb = new StringBuilder();

        // first, print out the size
        sb.append("(size=").append(size).append(")");
        // optional: if we want a newline, there is a special char for this
        // sb.append("\n");

        // second, append all values in the list
        for (Node tmp = head.getNext(); tmp != tail; tmp = tmp.getNext()) {
            sb.append(" ").append(tmp.getValue());
        }

        // return full string
        return sb.toString();
    }


    // private inner class for nodes
    private class Node {
        // private variables
        private ValueType value;
        private Node next;
        private Node prev;

        // constructors
        Node(ValueType value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        Node(ValueType value) {
            // this calls the previous constructor with the listed
            // arguments
            this(value, null, null);
        }

        // getters
        ValueType getValue() {
            return value;
        }

        // setters
        public void setValue(ValueType value) {
            this.value = value;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        Node getPrev() {
            return prev;
        }

        void setPrev(Node prev) {
            this.prev = prev;
        }

        public String toString(){
            return value.toString();
        }
    }
}
