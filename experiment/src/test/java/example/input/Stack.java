package example.input;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<T> {
    private final List<T> items = new ArrayList<>();
    private final int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
    }

    public void push(T item) {
        if (items.size() >= capacity) {
            throw new RuntimeException("Stack exceeded capacity!");
        }
        items.add(item);
    }

    public T pop() {
        if (items.size() == 0) {
            throw new EmptyStackException();
        }
        return items.remove(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.size() == 0;
    }
}
