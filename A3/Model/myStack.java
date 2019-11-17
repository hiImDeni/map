package Model;

import java.util.ArrayList;
import java.util.Stack;

public class myStack<T> implements iStack<T> {
    Stack<T> st;

    public myStack() { st = new Stack<T>();}

    @Override
    public void push(T elem) {
        st.push(elem);
    }

    @Override
    public T pop() throws myException {
        try{
            return st.pop();
        }
        catch (Exception ex){
            throw new myException(ex.getMessage());
        }
    }

    @Override
    public Boolean empty() {
        return st.empty();
    }

    @Override
    public int search(T elem) {
        return st.search(elem);
    }

    @Override
    public T peek() throws myException {
        try {
            return st.peek();
        }
        catch (Exception ex){
            throw new myException(ex.getMessage());
        }
    }

    @Override
    public String toString(){
        /*String res = "";
        for (T e: st) {
            res += e.toString() + " \n";
        }*/

        ArrayList<T> auxiliary = new ArrayList<T>();
        for (T e: st) {
            auxiliary.add(0, e);
        }

        String res = "";
        for (T e: auxiliary)
            res += e.toString() + " \n";
        return res;
    }
}
