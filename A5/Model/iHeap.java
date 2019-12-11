package Model;

import java.util.Collection;
import java.util.Map;

public interface iHeap<v> {
    v put(v val);
    v remove(int key) throws myException;
    v get(int key);
    boolean exists(int key);
    int size();
    boolean isEmpty();
    Collection<v> values();
    int getNextFree();
    v update(int address, v val);
    Map<Integer,v> getContent();
    void setContent(Map<Integer,v> map);
}
