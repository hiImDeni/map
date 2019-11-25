package Model.Type;

import Model.Value.intValue;
import Model.Value.value;

public class intType implements type {
    @Override
    public boolean equals(Object obj) {
        if (obj  instanceof  intType)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "int";
    }

    @Override
    public value defaultValue() { return new intValue(0); }
}
