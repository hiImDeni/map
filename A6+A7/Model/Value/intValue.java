package Model.Value;

import Model.Type.intType;
import Model.Type.type;

public class intValue implements value {
    int val;
    public intValue(int v) { val = v; }

    public int getValue() { return val; }

    @Override
    public String toString() {
        return "" + val;
    }

    @Override
    public type getType() { return new intType(); }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof intValue)
            return true;
        return false;
    }
}
