package Model.Value;

import Model.Type.boolType;
import Model.Type.type;

public class boolValue implements value {
    boolean val;
    public boolValue(boolean v) { val = v; }

    public boolean getValue() { return val; }

    @Override
    public String toString(){
        return "" + val;
    }

    @Override
    public type getType() { return new boolType(); }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof boolValue)
            return true;
        return false;
    }
}
