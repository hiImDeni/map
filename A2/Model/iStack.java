package Model;

public interface iStack<T> {
    void push(T elem);
    T pop() throws MyException;
    Boolean empty();
    int search(T elem);
    T peek() throws MyException;
}
