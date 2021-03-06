package Repository;

import Model.Containers.iList;
import Model.Containers.myException;
import Model.programState;

import java.util.List;

public interface iRepo {
    programState getProgramAtIndex(int index) throws myException;
    void addProgram(programState program);
    void logProgramState(programState program) throws myException;
    iList<programState> getProgramsList();
    void setProgramsList(List<programState> prgs);
}
