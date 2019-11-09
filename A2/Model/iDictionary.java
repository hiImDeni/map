package Model;

import java.util.Enumeration;

public interface iDictionary<k, v> {
    v put(k key, v val) throws MyException;
    v remove(Object key) throws MyException;
    v get(Object key) throws MyException;
    //Enumeration<v> elements();
    //Enumeration<k> keys();
    boolean exists(k key);
    int size();
    boolean isEmpty();
}
