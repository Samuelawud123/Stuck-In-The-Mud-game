import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Represents a circular linked list where the last element is connected back to the first element.
 * This implementation provides basic operations such as add, remove, and get on the circular linked list.
 *
 * @param <E> the type of elements held in this list
 */
public class CircularLinkedList<E> implements CircularLinkedListInterface<E> {
    private Node<E> tail;
    private int size;
    /**
     * A static inner class representing a node in the linked list.
     * Each node holds data and a reference to the next node in the list.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        /**
         * Constructs a new node with specified data and next node reference.
         *
         * @param data the data stored in the node
         * @param next the next node in the linked list
         */
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
    /**
     * Constructs an empty CircularLinkedList.
     */
    public CircularLinkedList() {
        tail = null;
        size = 0;
    }
    /**
     * Retrieves a count of elements being maintained by the list.
     *
     * @return the size of the list (count of elements)
     */
    @Override
    public int getSize() {
        return size;
    }
    /**
     * Retrieves the data at the specified position in the list
     *
     * @param position 0-based index for the list; must be in the range 0 to size - 1
     * @return the data in the specified position in the list
     */
    @Override
    public E get(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        Node<E> current = tail;
        for (int i = 0; i <= size - position - 1; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Adds a new node to the end of the list; by nature, this element's next will point to the first element
     *
     * @param value the element to add to the list
     */
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);

        if (tail == null) {
            newNode.next = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        tail = newNode;

        size++;
    }
    /**
     * Removes the specified item from the list, if it exists there.
     *
     * @param value the element to remove from the list
     * @return true, if the element was found and removed; false, if not found or list is empty
     */
    @Override
    public boolean remove(E value) {
        if (tail == null) return false;

        Node<E> current = tail;
        Node<E> previous = null;
        boolean found = false;

        for (int i = 0; i < size; i++) {
            if (current.next.data.equals(value)) {
                found = true;
                break;
            }
            previous = current;
            current = current.next;
        }

        if (!found) return false;

        if (previous == null) {
            tail.next = current.next.next;
            if (size == 1) tail = null;
        } else {
            previous.next = current.next.next;
            if (current == tail) tail = previous;
        }

        size--;
        return true;
    }
    /**
     * Removes the node at the specified position in the list
     * @param position position in the list; must be in range 0 to size - 1
     */
    @Override
    public void remove(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }

        Node<E> current = tail;
        Node<E> previous = null;

        for (int i = 0; i < size - position; i++) {
            previous = current;
            current = current.next;
        }

        if (position == size - 1) {
            tail = previous;
        }

        previous.next = current.next;

        size--;
    }
    /**
     * Retrieves an iterator over the list's elements.  Do not do other list operations like add or remove
     * from within an iterator loop; the results are not guaranteed to function as you might expect
     *
     * @return a strongly typed iterator over elements in the list
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularListIterator();
    }

    private class CircularListIterator implements Iterator<E> {
        private Node<E> nextNode;
        private int remaining = size;

        CircularListIterator() {
            if (tail == null) {
                nextNode = null;
            } else {
                nextNode = tail.next;
            }
        }

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public E next() {
            if (remaining == 0) {
                throw new NoSuchElementException();
            }
            E item = nextNode.data;
            if (nextNode.next == tail.next) {
                nextNode = tail.next;
            } else {
                nextNode = nextNode.next;
            }

            remaining--;
            return item;
        }
    }

}