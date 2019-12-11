package Model.Statement;

import Model.myException;
import Model.programState;

public interface IStatement {
    programState execute(programState program) throws myException;
}
