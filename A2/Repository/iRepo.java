package Repository;

import Model.MyException;
import Model.ProgramState;

public interface iRepo {
    ProgramState getCurrentProgram() throws MyException; //?
    ProgramState getProgramAtIndex(int index) throws MyException;
    void addProgram(ProgramState program);
    String tos() throws MyException;
}
