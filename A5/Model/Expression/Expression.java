package Model.Expression;

import Model.iDictionary;
import Model.iHeap;
import Model.myException;
import Model.Value.value;

public interface Expression {
    value eval(iDictionary<String, value> table, iHeap<value> heap) throws myException;
}
