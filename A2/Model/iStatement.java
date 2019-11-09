package Model;

public interface iStatement {
    ProgramState execute(ProgramState program) throws MyException;
}
