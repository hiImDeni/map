package Model;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class myDictionary<k, v> implements iDictionary<k, v> {
    HashMap<k, v> dict;

    public myDictionary() { dict = new HashMap<k, v>(); }

    @Override
    public v put(k key, v value) throws MyException{
        try {
            return dict.put(key, value);
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }

    @Override
    public v remove(Object key) throws MyException{
        try {
            return dict.remove(key);
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }
    @Override
    public int size() { return dict.size(); }

    @Override
    public boolean isEmpty() { return dict.isEmpty(); }

    @Override
    public v get(Object key) throws MyException {
        try{
            return dict.get(key);
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }

    @Override
    public boolean exists(k key){ //TODO
        return dict.containsKey(key);
    }

    @Override
    public String toString(){
        String res = "";
        for(HashMap.Entry<k, v> e: dict.entrySet())
            res += "<" + e.getKey() + ", " + e.getValue() + "> ";
        return res;
    }
}
