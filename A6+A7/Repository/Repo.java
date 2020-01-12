package Repository;

import Model.*;
import Model.Containers.iList;
import Model.Containers.myException;
import Model.Containers.myList;


import java.io.*;
import java.util.List;

public class Repo implements iRepo {
    iList<programState> states;
    String filePath;
    //int index;

    public Repo(programState program, String log) { states = new myList<programState>(); states.add(program); filePath = log; }

    @Override
    public programState getProgramAtIndex(int index) throws myException {
        return states.get(index);
    }

    @Override
    public iList<programState> getProgramsList() { return states; }

    @Override
    public void setProgramsList(List<programState> programs) { states.setContent(programs); }

    @Override
    public void logProgramState(programState program) throws myException{
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            logFile.println(program);
            logFile.close();
        }catch (IOException ex){
            throw new myException(ex.getMessage());
        }
    }

    @Override
    public void addProgram(programState program) { states.add(program); }

    @Override
    public String toString() {
        String result = "";
        for (programState program: states.getContent())
            result += program.getIndex() + ". " + program.getExecutionStack().toString();
        return result;
    }

}
