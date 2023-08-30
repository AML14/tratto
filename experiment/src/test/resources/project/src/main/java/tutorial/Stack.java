package tutorial;

import java.util.EmptyStackException;

public class Stack<T> {
    private int capacity = 10;
    private int pointer  = 0;
    private T[] objects = (T[]) new Object[capacity];

    /**
     * Adds a new object to the stack.
     *
     * @param o an object. Must not be null.
     * @throws IllegalArgumentException if o is null
     */
    public void push(T o) {
        if (pointer >= capacity)
            throw new RuntimeException("Stack exceeded capacity!");
        if (o == null)
            throw new IllegalArgumentException("The given object is null!");
        objects[pointer++] = o;
    }

    /**
     * Gets and removes the most recent object pushed onto the stack.
     *
     * @return the most recent object pushed to the stack
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
     * @return true if there are no items in the stack, false otherwise
     */
    public boolean isEmpty() {
	    return pointer <= 0;
    }
}
