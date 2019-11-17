package Model;

public interface iList<T> {
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    T get(int index) throws myException;
    T set(int index,  T element) throws myException;
    boolean add(T elem);
    void add(int index, T element) throws myException;
    boolean remove(T elem);
    T remove(int index) throws myException;
}
