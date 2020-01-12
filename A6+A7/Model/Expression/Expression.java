package Model.Expression;

import Model.Type.type;
import Model.Containers.iDictionary;
import Model.Containers.iHeap;
import Model.Containers.myException;
import Model.Value.value;

public interface Expression {
    value eval(iDictionary<String, value> table, iHeap<value> heap) throws myException;
    type typecheck(iDictionary<String, type> myEnvironment) throws myException;
}
