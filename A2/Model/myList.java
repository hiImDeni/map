package Model;

import java.util.ArrayList;

public class myList<T> implements iList<T> {
    ArrayList<T> l;

    public myList() { l = new ArrayList<T>(); }

    @Override
    public int size() { return l.size(); }

    @Override
    public boolean isEmpty() { return l.isEmpty(); }

    @Override
    public boolean contains(Object o) { return l.contains(o); }

    @Override
    public T get(int index) throws MyException{
        try{
            return l.get(index);
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }

    @Override
    public T set(int index, T element) throws MyException{
        try{
            return l.set(index, element);
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }

    @Override
    public boolean add(T elem) { return l.add(elem); }

    @Override
    public void add(int index, T element) throws MyException{
        try{
            l.add(index, element);
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }

    @Override
    public boolean remove(T elem) { return l.remove(elem); }

    @Override
    public T remove(int index) throws MyException{
        try{
            return l.remove(index);
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (T e: l)
            res += e.toString();
        return res;
    }
}
