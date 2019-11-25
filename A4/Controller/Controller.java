package Controller;

import Model.*;
import Model.Value.refValue;
import Model.Value.value;
import Model.Statement.IStatement;
import Repository.iRepo;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    iRepo repo;

    public Controller(iRepo r) { repo = r; }

    public void addProgram(programState program) { repo.addProgram(program); }

    public programState getProgramAtIndex(int index) throws myException { return repo.getProgramAtIndex(index); }

    private Map<Integer, value> garbageCollector(programState program){
        iDictionary<String, value> sym = program.getSymbolsTable();
        iHeap<value> heap = program.getHeapTable();
        Map<Integer, value> referenced = new HashMap<Integer, value>();
        List<Integer> addresses = new ArrayList<Integer>();
        addresses = getAddressFromSymTable(sym.values());

        for (Map.Entry<Integer, value> e: heap.getContent().entrySet()){
            if (addresses.contains(e.getKey()))
                referenced.put(e.getKey(), e.getValue());
            else{
                int elemAddress = e.getKey();
                for (Map.Entry<Integer, value> searchAddress: heap.getContent().entrySet()){
                    value val = searchAddress.getValue();
                    if (val instanceof refValue){
                        refValue ref = (refValue)val;
                        if (ref.getAddress() == elemAddress)
                            referenced.put(elemAddress, e.getValue());
                    }
                }
            }
        }

        return referenced;
    }

    private List<Integer> getAddressFromSymTable(Collection<value> symTable){
        return symTable.stream().filter(v->v instanceof refValue).map(v-> { refValue v1 = (refValue)v; return v1.getAddress(); }).collect(Collectors.toList());
    }

    Map<Integer, value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,value> heap){
        return heap.entrySet().stream().filter(e-> symTableAddr.contains(e.getKey()) || heap.containsKey(e.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

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
            System.out.println(program);
            //program.getHeapTable().setContent(unsafeGarbageCollector(getAddressFromSymTable(program.getSymbolsTable().getContent().values()), program.getHeapTable().getContent()));
            program.getHeapTable().setContent(garbageCollector(program));
            repo.logProgramState(program);
        }
        //repo.logProgramState(program);
    }
}
