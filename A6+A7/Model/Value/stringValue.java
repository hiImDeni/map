package Model.Value;

import Model.Type.stringType;
import Model.Type.type;

public class stringValue implements value {
    String val;
    public stringValue(String s) { val = s; }
    public String getValue() { return val; }

    @Override
    public type getType() { return new stringType(); }

    @Override
    public String toString() { return val; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof stringValue)
            return true;
        return false;
    }
}
