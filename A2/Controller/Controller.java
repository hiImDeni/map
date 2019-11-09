package Controller;

import Model.MyException;
import Model.ProgramState;
import Model.iStack;
import Model.iStatement;
import Repository.iRepo;

public class Controller {
    iRepo repo;

    public Controller(iRepo r) { repo = r; }

    public void addProgram(ProgramState program) { repo.addProgram(program); }

    public ProgramState getProgramAtIndex(int index) throws MyException { return repo.getProgramAtIndex(index); }

    public ProgramState oneStep(ProgramState state) throws MyException{
        iStack<iStatement> st = state.getExecutionStack();
        if (st.empty())
            throw new MyException("Execution stack is empty!");
        iStatement currentStatement = st.pop();
        return currentStatement.execute(state);
    }

    /*public void allSteps() throws MyException {
        ProgramState program = repo.getCurrentProgram();
        while (! program.getExecutionStack().empty()) {
            oneStep(program);
            System.out.println(program.toString());
        }
    }*/

    public void allSteps(ProgramState program) throws MyException {
        //ProgramState program = repo.getCurrentProgram();
        while (! program.getExecutionStack().empty()) {
            oneStep(program);
            System.out.println(program);
        }
    }

    public String tos() throws MyException { return repo.tos(); }
}
