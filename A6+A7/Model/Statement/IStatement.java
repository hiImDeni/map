package Model.Statement;

import Model.Type.type;
import Model.Containers.iDictionary;
import Model.Containers.myException;
import Model.programState;

public interface IStatement {
    programState execute(programState program) throws myException;
    iDictionary<String, type> typecheck(iDictionary<String, type> typeEnvironment) throws myException;
}
