package Model.Type;

import Model.Value.stringValue;
import Model.Value.value;

public class stringType implements type {
    @Override
    public value defaultValue() { return new stringValue(""); }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof stringType)
            return true;
        return false;
    }

    @Override
    public String toString() { return "string"; }
}
