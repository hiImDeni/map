package Controller;

import Model.myException;
import Model.programState;
import Model.iStack;
import Model.Statement.IStatement;
import Repository.iRepo;

public class Controller {
    iRepo repo;

    public Controller(iRepo r) { repo = r; }

    public void addProgram(programState program) { repo.addProgram(program); }

    public programState getProgramAtIndex(int index) throws myException { return repo.getProgramAtIndex(index); }

    public programState oneStep(programState state) throws myException {
        iStack<IStatement> st = state.getExecutionStack();
        if (st.empty())
            throw new myException("Execution stack is empty!");
        IStatement currentStatement = st.pop();
        return currentStatement.execute(state);
    }

    /*public void allSteps() throws MyException {
        ProgramState program = repo.getCurrentProgram();
        while (! program.getExecutionStack().empty()) {
            oneStep(program);
            System.out.println(program.toString());
        }
    }*/

    public void allSteps(programState program) throws Exception {
        //ProgramState program = repo.getCurrentProgram();
        while (! program.getExecutionStack().empty()) {
            oneStep(program);
            //System.out.println(program);
            repo.logProgramState(program);
        }
    }
}
