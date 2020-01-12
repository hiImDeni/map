package Controller;

import Model.*;
import Model.Containers.*;
import Model.Value.refValue;
import Model.Value.value;
import Repository.iRepo;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    iRepo repo;
    ExecutorService executor;//the fuk???

    public Controller(iRepo r) { repo = r; }

    public void addProgram(programState program) { repo.addProgram(program); }

    public programState getProgramAtIndex(int index) throws myException { return repo.getProgramAtIndex(index); }

    public List<programState> removeCompleted(List<programState> inProgramList){
        return inProgramList.stream().filter(program->program.isNotCompleted()).collect(Collectors.toList());
    }



    private List<Integer> getAddressFromSymTable(Collection<value> symTable){
        return symTable.stream().filter(v->v instanceof refValue).map(v-> { refValue v1 = (refValue)v; return v1.getAddress(); }).collect(Collectors.toList());
    }

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

    void oneStepForAllPrograms(List<programState> programsList) throws myException, InterruptedException {
        //RUN concurrently one step for each of the existing PrgStates
        //prepare the list of callables
        List<Callable<programState>>callList = programsList.stream().map(((programState program)-> (Callable<programState>)(()->{return program.oneStep();}))).collect(Collectors.toList());

        //start the execution of the callables
        //it return the list of newly created programStates -> threads
        List<programState> newProgramList =
                executor.invokeAll(callList).stream().map(future -> {
                            try {
                                return future.get();
                            } catch (InterruptedException | ExecutionException e) {
                                System.out.println(e.getMessage());
                            }
                    return null;//??
                        }).filter(program -> program!=null).collect(Collectors.toList());

        //add the new threads to the programList
        newProgramList.forEach(newThread -> programsList.add(newThread));

        programsList.forEach(program -> {
            try {
                repo.logProgramState(program);
                System.out.println(program);
            } catch (myException e) {
                System.out.println(e.getMessage());
            }
        });

        iList<programState> programs = new myList<programState>();
        programs.setContent(programsList);
        repo.setProgramsList(programsList);
    }

    public void allSteps(programState program) throws Exception {
        executor = Executors.newFixedThreadPool(3);
        //remove completed programs
        List<programState> programList = removeCompleted(repo.getProgramsList().getContent());

        while (programList.size() > 0){
            program.getHeapTable().setContent(garbageCollector(program));
            oneStepForAllPrograms(programList);

            //remove completed programs
            programList = removeCompleted(repo.getProgramsList().getContent());
        }
        executor.shutdownNow();
        //here repo contains at least one completed program and the programList is not empty
        //oneStepForAllPrograms calls setProgramList to change the repo

        //update the repository state
        repo.setProgramsList(programList);

        //ProgramState program = repo.getCurrentProgram();
        /*while (! program.getExecutionStack().empty()) {
            oneStep(program);
            System.out.println(program);
            program.getHeapTable().setContent(unsafeGarbageCollector(getAddressFromSymTable(program.getSymbolsTable().getContent().values()), program.getHeapTable().getContent()));
            repo.logProgramState(program);
        }*/
        //repo.logProgramState(program);
    }
}
