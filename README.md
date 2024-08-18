# Circular Linked List Implementation in Java

## Project Overview

This project is part of the CSC 143 (Computer Programming 2) course. It provides a custom implementation of a **Circular Linked List** in Java, a specialized data structure where the last element is connected back to the first element, forming a continuous loop. The project demonstrates the use of linked lists, node-based structures, and iterators in Java.

## Features

- **Custom Linked List**: Implementation of a circular linked list where the last node points back to the first, enabling continuous traversal.
- **Node Class**: A static inner class that stores each element (`data`) and a reference to the next node (`next`).
- **Add Elements**: Add elements to the end of the list, maintaining the circular nature of the list.
- **Get Elements**: Retrieve elements from any position in the list.
- **Remove Elements**: Remove elements by value or by position.
- **Iterator Support**: Provides an iterator that allows traversal of the list, respecting its circular nature.
- **Unit Tests**: Includes unit tests to validate the functionality of the CircularLinkedList implementation.

## Classes and Interfaces

### `CircularLinkedList<E>`

This is the main class that implements the circular linked list. It provides methods to add, remove, retrieve elements, and get the size of the list. The list is backed by nodes that hold the data and a reference to the next node.

- **`getSize()`**: Returns the number of elements in the list.
- **`get(int position)`**: Retrieves the element at the specified position in the list.
- **`add(E value)`**: Adds a new element to the end of the list.
- **`remove(E value)`**: Removes the first occurrence of the specified element from the list.
- **`remove(int position)`**: Removes the element at the specified position.
- **`iterator()`**: Returns an iterator for the list.

### `CircularLinkedListInterface<E>`

This interface defines the required methods for the `CircularLinkedList` class.

### `Player`

Represents a participant in a game called "Stuck in the Mud." Each player has a name, a score, and keeps track of the "stuck" status of each die they roll.

- **`getName()`**: Returns the player's name.
- **`getScore()`**: Returns the player's current score.
- **`addScore(int roundScore)`**: Adds points to the player's score.
- **`checkIfStuck(int[] diceRoll)`**: Updates the stuck status of the dice based on the latest roll.
- **`resetStuckDice()`**: Resets the stuck status of all the player's dice.

### `StuckInTheMud`

This class represents the game logic for "Stuck in the Mud," a dice game where players roll dice and accumulate points unless they roll specific "stuck" numbers.

- **`startGame()`**: Starts the game and continues until a player wins by reaching the winning score.
- **`takeTurn(Player player)`**: Handles a single turn for a player, including dice rolls and scoring.
- **`isGameWon()`**: Checks if any player has won the game.

### `CircularLinkedListTest`

This class contains unit tests for the `CircularLinkedList` class. It tests various functionalities, including list creation, element addition, retrieval, removal, and iterator behavior.

## How to Run the Project

1. **Clone the Repository**: Use `git clone <repo-url>` to clone the repository to your local machine.
2. **Compile the Code**: Use a Java compiler (`javac`) to compile the `.java` files.
3. **Run the Tests**: Execute the unit tests to ensure that all functionalities are working as expected.
4. **Run the Game**: Execute the `StuckInTheMud` class to start the game.

## Unit Testing

The project includes a comprehensive suite of unit tests for the `CircularLinkedList` class, ensuring that the implementation works correctly under various conditions.

### Test Cases

- **Test New List Is Empty**: Ensures that a new list is empty and accessing elements throws an exception.
- **Test Add and Get**: Validates the addition and retrieval of elements.
- **Test Remove by Value and Index**: Tests removal of elements by value and index.
- **Test Circular Behavior**: Ensures the list maintains its circular structure.
- **Test Iterator**: Validates the behavior of the list's iterator.
- **Test Size Consistency**: Ensures the `getSize()` method returns accurate results after operations.

## License

This project is intended for educational purposes as part of the CSC 143 course.

