package Model;

import java.util.Collection;

public interface iDictionary<k, v> {
    v put(k key, v val);
    v remove(Object key) throws myException;
    v get(Object key);
    //Enumeration<v> elements();
    //Enumeration<k> keys();
    boolean exists(k key);
    int size();
    boolean isEmpty();
    Collection<v> values();
}
