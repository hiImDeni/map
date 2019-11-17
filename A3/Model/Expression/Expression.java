package Model.Expression;

import Model.iDictionary;
import Model.myException;
import Model.Value.value;

public interface Expression {
    value eval(iDictionary<String, value> table) throws myException;
}
