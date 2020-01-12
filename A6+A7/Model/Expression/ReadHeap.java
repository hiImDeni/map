package Model.Expression;

import Model.Type.refType;
import Model.Type.type;
import Model.Value.refValue;
import Model.Value.value;
import Model.Containers.iDictionary;
import Model.Containers.iHeap;
import Model.Containers.myException;

public class ReadHeap implements Expression {
    Expression expression;
    public ReadHeap(Expression exp) { expression = exp; }

    public type typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        type t = expression.typecheck(typeEnvironment);
        if (t instanceof refType){
            refType reftype = (refType) t;
            return reftype.getInner();
        }
        else throw new myException("Expression must be a reference type!");
    }

    @Override
    public value eval(iDictionary<String, value> table, iHeap<value> heap) throws myException { //TODO
        value val = expression.eval(table, heap);
        if (val.getType() instanceof refType){
            refValue reference = (refValue)val;
            int address = reference.getAddress();
            value valueAtAddress = heap.get(address);
            if (valueAtAddress == null)
                throw new myException("Address " + address + " does not exist!");
            return valueAtAddress;
        }
        else
            throw new myException("Expression must be a refValue");
    }

    @Override
    public String toString() { return "ReadHeap(" + expression + ");"; }
}
