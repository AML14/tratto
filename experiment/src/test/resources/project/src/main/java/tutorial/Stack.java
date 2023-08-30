package tutorial;

import java.util.EmptyStackException;

/**
 * An example Stack implementation.
 *
 * @param <T> the type of object in the stack
 */
public class Stack<T> {
    /** The maximum number of objects that can be added to the stack. */
    private int capacity = 10;
    /** The index of the most recent object added to the stack. */
    private int pointer  = 0;
    /** An array of all objects in the stack. */
    private T[] objects = (T[]) new Object[capacity];

    /**
     * Adds an object to the stack.
     *
     * @param o the object to be added to the stack
     * @throws RuntimeException if the stack is already at maximum capacity
     */
    public void push(T o) {
        if (pointer >= capacity)
            throw new RuntimeException("Stack exceeded capacity!");
        objects[pointer++] = o;
    }

    /**
     * Gets and removes the top item from the stack.
     *
     * @return the top item in the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() {
        if (pointer <= 0)
            throw new EmptyStackException();
        return objects[--pointer];
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return pointer <= 0;
    }
}
