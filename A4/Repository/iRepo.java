package Repository;

import Model.myException;
import Model.programState;

public interface iRepo {
    programState getCurrentProgram() throws myException; //?
    programState getProgramAtIndex(int index) throws myException;
    void addProgram(programState program);
    void logProgramState(programState program) throws Exception;

    String tos() throws myException;
}
