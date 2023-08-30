package tutorial;

import java.util.EmptyStackException;

public class Stack<T> {
    private int capacity = 10;
    private int pointer  = 0;
    private T[] objects = (T[]) new Object[capacity];

    /**
     * Adds a new object to the stack.
     *
     * @param o an object
     * @throws RuntimeException if o is null
     */
    public void push(T o) {
        if (pointer >= capacity)
            throw new RuntimeException("Stack exceeded capacity!");
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

    public boolean isEmpty() {
	    return pointer <= 0;
    } 
}
