package Model.Containers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class myDictionary<k, v> implements iDictionary<k, v> {
    Map<k, v> dict;

    public myDictionary() { dict = new HashMap<k, v>(); }

    @Override
    public v put(k key, v value) {
            return dict.put(key, value);
    }

    @Override
    public v remove(Object key) throws myException {
        try {
            return dict.remove(key);
        }
        catch (Exception ex){
            throw new myException(ex.getMessage());
        }
    }
    @Override
    public int size() { return dict.size(); }

    @Override
    public boolean isEmpty() { return dict.isEmpty(); }

    @Override
    public v get(Object key) {
        return dict.get(key);
    }

    @Override
    public boolean exists(k key){ //TODO
        return dict.containsKey(key);
    }

    @Override
    public String toString(){
        String res = "";
        for(Map.Entry<k, v> e: dict.entrySet())
            res += "<" + e.getKey() + ", " + e.getValue() + ">\n";
        return res;
    }

    @Override
    public Collection<v> values() { return dict.values(); }


    @Override
    public void setContent(Map<k, v> map) {
        dict = map;
    }

    @Override
    public Map<k, v> getContent(){
        return dict;
    }
}
