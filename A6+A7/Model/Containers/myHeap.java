package Model.Containers;

import Model.Containers.iHeap;
import Model.Containers.myException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class myHeap<v> implements iHeap<v> {
    Map<Integer,v> heap;
    int nextFree; //TODO: AtomicInteger
    public myHeap() { heap = new HashMap<Integer,v>(); nextFree = 0; }

    /*void setNext() {

    }*/

    @Override
    public v put(v val) {
        nextFree++;
        //setNext();
        return heap.put(nextFree, val);
    }

    @Override
    public v remove(int key) throws myException {
        //if (exists(key))
          //  nextFree = key;
        return heap.remove(key);
    }

    @Override
    public v get(int key) {
        return heap.get(key);
    }

    @Override
    public boolean exists(int key) {
        return heap.containsKey(key);
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public Collection<v> values() {
        return heap.values();
    }

    @Override
    public int getNextFree() { return nextFree; }

    @Override
    public v update(int address, v val) { return heap.put(address, val); }

    @Override
    public String toString(){
        String res = "";
        for(HashMap.Entry<Integer, v> e: heap.entrySet())
            res += "<" + e.getKey() + ", " + e.getValue() + ">\n";
        return res;
    }

    @Override
    public Map<Integer, v> getContent() { return heap; }

    @Override
    public void setContent(Map<Integer, v> map) { heap = map; }
}
