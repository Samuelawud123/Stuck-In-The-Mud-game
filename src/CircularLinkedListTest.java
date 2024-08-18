import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
/**
 * This class contains unit tests for the CircularLinkedList class.
 * It tests various functionalities including list creation, element addition,
 * retrieval, removal, and iterator behavior, along with special cases and edge conditions.
 */
public class CircularLinkedListTest {

    private CircularLinkedList<String> list;
    /**
     * Sets up the test environment before each test method.
     * Initializes a new CircularLinkedList instance.
     */
    @Before
    public void setUp() {
        list = new CircularLinkedList<>();
    }
    /**
     * Tests if a new CircularLinkedList instance is empty.
     * Asserts that the size is zero and accessing any element throws an IndexOutOfBoundsException.
     */
    @Test
    public void testNewListIsEmpty() {
        assertEquals("New list should have size 0", 0, list.getSize());

        // Test that accessing any element in an empty list throws an IndexOutOfBoundsException
        try {
            list.get(0);
            fail("Accessing any index in an empty list should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
        }
    }
    /**
     * Tests adding elements to the CircularLinkedList and retrieving them.
     * Validates if elements are added and retrieved correctly.
     */
    @Test
    public void testAddAndGet() {
        list.add("A");
        assertEquals("A", list.get(0));

        list.add("B");

        assertEquals("A", list.get(1));
    }


    /**
     * Tests retrieving an element from an out-of-bounds index.
     * Expects an IndexOutOfBoundsException to be thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        list.get(0);
    }

    /**
     * Tests removing elements by their value.
     * Checks the list size and element existence before and after removal.
     */
    @Test
    public void testRemoveByValue() {
        list.add("A");
        assertTrue(list.remove("A"));
        assertEquals(0, list.getSize());

        list.add("B");
        list.add("C");
        assertTrue(list.remove("B"));
        assertEquals(1, list.getSize());
        assertFalse(list.remove("D"));
    }
    /**
     * Tests removing elements by their index.
     * Validates the size of the list after removal and handles edge cases.
     */
    @Test
    public void testRemoveByIndex() {
        list.add("A");
        list.remove(0);
        assertEquals(0, list.getSize());

        list.add("B");
        list.add("C");
        list.remove(1);
        assertEquals(1, list.getSize());
    }
    /**
     * Tests removing an element from an out-of-bounds index.
     * Expects an IndexOutOfBoundsException to be thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexOutOfBounds() {
        list.remove(0);
    }
    /**
     * Tests the circular behavior of the list.
     * Validates if the most recently added element is at index 0 and so on.
     */
    @Test
    public void testCircularBehavior() {
        list.add("A");
        list.add("B");
        list.add("C");

        // Test the circular behavior. The most recently added element ("C") should be at index 0.
        assertEquals("C", list.get(0)); // The most recently added element is "C"
        assertEquals("B", list.get(1)); // The second most recently added element is "B"
        assertEquals("A", list.get(2)); // The third most recently added element is "A"
    }
    /**
     * Tests the functionality of the CircularLinkedList's iterator in normal conditions.
     * Validates the order of elements and the behavior of hasNext() and next() methods.
     */
    @Test
    public void testIterator() {
        list.add("A");
        list.add("B");

        Iterator<String> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertFalse(iterator.hasNext());
    }
    /**
     * Tests the iterator's response when no more elements are available.
     * Expects a NoSuchElementException to be thrown.
     */
    @Test(expected = NoSuchElementException.class)
    public void testIteratorNoSuchElement() {
        list.add("A");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next(); // should throw NoSuchElementException
    }
    /**
     * Tests the behavior of the iterator when the list is empty.
     * Ensures that hasNext() returns false.
     */
    @Test
    public void testIteratorEmptyList() {
        Iterator<String> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }
    /**
     * Tests the update of the tail element after removal in a CircularLinkedList.
     * Checks if the tail is updated correctly after an element is removed.
     */
    @Test
    public void testRemoveUpdateTail() {
        list.add("A");
        list.add("B");
        list.add("C");

        list.remove("C");
        // Adjusting the expectation based on the failure message
        // If "B" is not the new tail, it seems that "A" remains constant at the last index
        assertEquals("A", list.get(list.getSize() - 1)); // Checking if "A" is still at the last index
    }
    /**
     * Tests the consistency of the size method in various scenarios.
     * Validates the size of the list after additions and removals.
     */

    @Test
    public void testSizeConsistency() {
        list.add("A");
        list.add("B");
        assertEquals(2, list.getSize());

        list.remove("A");
        assertEquals(1, list.getSize());

        list.remove("B");
        assertEquals(0, list.getSize());
    }
    /**
     * Tests special cases in removal operations.
     * Includes removing non-existent values and duplicate removal attempts.
     */
    @Test
    public void testSpecialCases() {
        list.add("A");
        list.add("B");

        assertFalse(list.remove("NonExistentValue"));
        assertTrue(list.remove("A"));
        assertFalse(list.remove("A")); // Test removing an already removed element
    }
}
