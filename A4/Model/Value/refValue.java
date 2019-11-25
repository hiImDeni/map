package Model.Value;

import Model.Type.refType;
import Model.Type.type;

public class refValue implements value {
    int address;
    type locationType;
    public refValue(type loc, int addr) { address = addr; locationType = loc; }

    public int getAddress() { return address; }

    @Override
    public type getType() { return new refType(locationType); }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof refValue)
            return true;
        return false;
    }

    @Override
    public String toString() { return "(" + locationType + ", " + address + ")"; }
}
