package Model;

import java.util.Stack;

public class myStack<T> implements iStack<T> {
    Stack<T> st;

    public myStack() { st = new Stack<T>();}

    @Override
    public void push(T elem) {
        st.push(elem);
    }

    @Override
    public T pop() throws MyException{
        try{
            return st.pop();
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
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
    public T peek() throws MyException{
        try {
            return st.peek();
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }

    @Override
    public String toString(){
        String res = "";
        for (T e: st) {
            res += e.toString() + " ";
        }
        return res;
    }
}
