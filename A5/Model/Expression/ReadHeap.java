package Model.Expression;

import Model.Type.refType;
import Model.Value.refValue;
import Model.Value.value;
import Model.iDictionary;
import Model.iHeap;
import Model.myException;

public class ReadHeap implements Expression {
    Expression expression;
    public ReadHeap(Expression exp) { expression = exp; }

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
