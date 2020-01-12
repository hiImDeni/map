package Model.Type;

import Model.Value.refValue;
import Model.Value.value;

public class refType implements type{
    type inner;
    public refType(type in) { inner = in; }

    public type getInner() { return inner; }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof refType) {
            refType compare = (refType)obj;
            return inner.equals(compare.getInner());
        }
        return false;
    }

    @Override
    public String toString() { return "ref(" + inner + ")"; }

    @Override
    public value defaultValue() { return new refValue(inner, 0); }
}
