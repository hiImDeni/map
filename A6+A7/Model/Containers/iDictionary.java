package Model.Containers;

import java.util.Collection;
import java.util.Map;

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
    void setContent(Map<k,v> map);
    Map<k,v> getContent();
}
