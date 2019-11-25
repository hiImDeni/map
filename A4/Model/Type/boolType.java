package Model.Type;

import Model.Value.boolValue;
import Model.Value.value;

public class boolType implements type {
    @Override
    public boolean equals(Object obj){
        if (obj instanceof boolType)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "bool";
    }

    @Override
    public value defaultValue() { return new boolValue(false); }
}
