package cwang_P2EC;

/**
 * This program executes the entire unraveling process of the encoded message.
 *
 * @author Jason Wang
 * @version 42.0
 */
public class MessageDecoder {
    /**
     * The Node class represents a list node.
     */
    public static class Node {
        String value;     // The value of a list element
        int index;        // The location of the value
        Node next;        // Link to next node in the list

        /**
         * Constructor.
         *
         * @param letter The element to store in this node.
         * @param number The location of the value.
         * @param n   The reference to the next node.
         */
        Node(String letter, int number, Node n) {
            value = letter;     // Assign the letter to value
            index = number;     // Assign the number to index
            next = n;           // Link to next node in the list
        }

        /**
         * Constructor.
         *
         * @param letter The element to store in this node.
         */
        Node(String letter, int number) {
            value = letter;     // Assign the letter to the value
            index = number;     // Assign the number to index
            next = null;        // Link to null
        }
    }

    private Node first;     // List head
    private Node last;      // Last element in list

    /**
     * Constructor.
     */
    public MessageDecoder() {
        first = null;   // Assign first to null
        last = null;    // Assign last to null
    }

    /**
     * The isEmpty method checks to see if the list is empty.
     *
     * @return True if list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * The add method add letters to the linked list and sort them in numerical
     * order.
     *
     * @param letter The value to add.
     * @param number The location of the value.
     */
    public void add(String letter, int number) {

        // Determine where the number should be placed
        if (isEmpty()) {
            first = new Node(letter, number);
            last = first;
        } else if (number < first.index) {
            first = new Node(letter, number, first);
        } else if (number > last.index) {
            last.next = new Node(letter, number, null);
            last = last.next;
        } else {
            Node pred = first;      // Assign first to pred

            // Traverse if the while loop is false
            while (!(pred.next == null || number <= pred.next.index)) {
                pred = pred.next;
            }
            pred.next = new Node(letter, number, pred.next);
        }
    }

    /**
     * The print method displays the Linked list contents.
     */
    public void print() {
        Node ref = first;       // Assign first to ref

        System.out.print("\nPlain text: ");

        // Print elements until ref is null
        while (ref != null) {
            System.out.print(ref.value);
            ref = ref.next;
        }
    }
}
