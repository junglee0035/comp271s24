/**
 * A basic linked list that offers stack-like and queue-like behavior.
 */
public class SimpleLinkedList implements Stack271<String>, Queue271<String> {

    /** The first node of the linked list */
    private Node head;

    /** The last node of the linked list */
    private Node tail;

    /**
     * A node in the linked list.
     */
    private class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Inserts an element at the front of the underlying data structure. All
     * existing elements are first moved one position closer to the end of 
     * underlying data structure. Then the new element is placed at the 
     * front position. If the underlying data structure is full, the method
     * returns false to indicate nothing was inserted; otherwise true. When
     * inserting something successfully, usage++
     * 
     * @param e the element to be inserted
     * @return true if the element is successfully inserted, false otherwise
     */
    @Override
    public boolean push(String e) {
        Node newNode = new Node(e);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        return true;
    }

    /**
     * Removes and returns what's in the front of the underlying data structure,
     * including nulls. Null means that structure is empty. Otherwise the first
     * element is returned and the usage is adjusted accordingly. Successful 
     * removals cause remaining elements to move one position closer to the front.
     * 
     * @return the first element in the data structure or null if empty
     */
    @Override
    public String pull() {
        if (head == null) {
            return null;
        }
        String data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

    /**
     * Adds a string at the next available position in the underlying data structure
     * and updates the usage of that data structure. If usage exceed capacity, the
     * method returns a false value. If addition of the element is successful, the
     * method returns a true.
     * 
     * @param e String to add
     * @return true is room for string; false otherwise
     */
    @Override
    public boolean add(String e) {
        Node newNode = new Node(e);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        return true;
    }

    /**
     * Removes the first element from the underlying data structure and returns it.
     * If an element is removed, then the usage of the underlying data structure is
     * reduced by one and every element in the queue moves one position closer to
     * the front. If the usage is already 0, the method returns a null value
     * and the usage remains zero.
     * 
     * @return String in the front of the underlying data structure.
     */
    @Override
    public String remove() {
        if (head == null) {
            return null;
        }
        String data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

    public static void main(String[] args) {
        SimpleLinkedList demoQueue = new SimpleLinkedList();
        SimpleLinkedList demoStack = new SimpleLinkedList();
        
        demoQueue.add("A");
        demoQueue.add("B");
        demoQueue.add("C");
    
        boolean queueWorks = demoQueue.remove().equals("A") && demoQueue.remove().equals("B") && demoQueue.remove().equals("C") && demoQueue.remove() == null;
    
        demoStack.push("A");
        demoStack.push("B");
        demoStack.push("C");
    
        boolean stackWorks = demoStack.pull().equals("C") && demoStack.pull().equals("B") && demoStack.pull().equals("A") && demoStack.pull() == null;
        
        System.out.println(queueWorks);
        System.out.println(stackWorks);
    
    }//main method for testing provided by Prof. Leo
}

/**
 * Interface for stack-like behavior.
 */
public interface Stack271<E> {
    boolean push(E e);
    E pull();
}

/**
 * Interface for queue-like behavior.
 */
public interface Queue271<E> {
    boolean add(E e);
    E remove();
}
