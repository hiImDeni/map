package Model.Containers;

import java.util.Stack;

public interface iStack<T> {
    void push(T elem);
    T pop() throws myException;
    Boolean empty();
    int search(T elem);
    T peek() throws myException;
    Stack<T> getContent();
}
