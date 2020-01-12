package sample;

import Model.*;
import Model.Containers.iList;
import Model.Containers.myList;
import Model.Statement.*;
import Model.Value.stringValue;
import Model.Value.value;
import Repository.iRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    //List<programState> programs;//?
    iRepo currentRepo;
    programState currentProgram;
    @FXML
    private Button exitButton, backButton;
    @FXML
    private TextField nrOfProgramsField;
    @FXML
    private ListView<programState> programsListView;
    @FXML
    private ListView<String> outputListView;
    @FXML
    private TableView<heapElement> heapTableView;
    @FXML
    private TableColumn<heapElement, Integer> addressColumn;
    @FXML
    private TableColumn<heapElement, String> valueColumn;
    @FXML
    private ListView<stringValue> fileListView;
    @FXML
    private TableView<variable> symbolsTableView;
    @FXML
    private TableColumn<variable, String> nameColumn;
    @FXML
    private TableColumn<variable, String> variableValueColumn;
    @FXML
    private ListView<IStatement> executionListView;


    private ObservableList<programState> getProgramsList() {
        //List<programState> programsList = repository.getProgramsList().getContent();
        ObservableList<programState> observablePrograms = FXCollections.observableArrayList(currentRepo.getProgramsList().getContent());
        return observablePrograms;
    }

    private ObservableList<heapElement> getHeapTable() throws Exception{
        heapTableView.getItems().clear();
        ObservableList<heapElement> heapElements = FXCollections.observableArrayList();
        Map<Integer, value> heap = currentProgram.getHeapTable().getContent();
        Iterator heapIterator = heap.entrySet().iterator();
        while(heapIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) heapIterator.next();
            heapElements.add(new heapElement((Integer) pair.getKey(), pair.getValue().toString()));
        }

        return heapElements;
    }

    private ObservableList<String> getOutput() throws Exception{
        List<String> outputList = new ArrayList<String>();
        outputList.addAll(currentProgram.getOutput().getContent());
        ObservableList<String> output = FXCollections.observableArrayList(outputList);
        //System.out.println(output);
        return output;
    }

    private ObservableList<stringValue> getFileTable() throws Exception{ //TODO ?
        Map<stringValue, BufferedReader> fileMap = new HashMap<>();
        List<stringValue> fileList = new ArrayList<>();
        fileMap = currentProgram.getFileTable().getContent();
        for (Map.Entry<stringValue, BufferedReader> file: fileMap.entrySet())
            fileList.add(file.getKey());

        return FXCollections.observableArrayList(fileList);
    }

    private ObservableList<variable> getSymbolsTable() throws Exception {
        /*List<Map.Entry<String, value>> symbolsList = new ArrayList<>();
        ObservableList<variable> symbolsObservable;
        if (! currentRepo.getProgramsList().isEmpty()) {
            //for (Map.Entry<String, value> symbol: currentRepo.getProgramsList().get(0).getSymbolsTable().getContent().entrySet())
             //   symbolsList.add(new Map.Entry<String, value>(symbol.getKey(), symbol.getValue()));
            symbolsObservable = FXCollections.observableArrayList(currentRepo.getProgramsList().get(0).getSymbolsTable().getContent().entrySet());
            return symbolsObservable;
        }
        //System.out.println(symbolsObservable);
        return null;*/

        ObservableList<variable> variables = FXCollections.observableArrayList();
        Map<String, value> symbols = currentProgram.getSymbolsTable().getContent();
        Iterator symbolsIterator = symbols.entrySet().iterator();
        while (symbolsIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) symbolsIterator.next();
            variables.add(new variable((String) pair.getKey(), pair.getValue().toString()));
        }
        return variables;
    }

    private ObservableList<IStatement> getExecutionStack() throws Exception {
        List<IStatement> executionList = new ArrayList<>();
        if (! currentRepo.getProgramsList().isEmpty())
            executionList.addAll(currentProgram.getExecutionStack().getContent());
        return FXCollections.observableArrayList(executionList);
    }

    private void populateProgramsListView() {
        programsListView.setItems(getProgramsList());
        programsListView.getSelectionModel().selectIndices(0);
        currentProgram = programsListView.getSelectionModel().getSelectedItem();

        //currentRepo = programsListView.getSelectionModel().getSelectedItem();
        nrOfProgramsField.setText(String.valueOf(currentRepo.getProgramsList().size()));

        // focus
        //programsListView.getFocusModel().focus(2);

        /*programsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<iRepo>() { //?
            @Override
            public void changed(ObservableValue<? extends iRepo> observableValue, iRepo oldrepo, iRepo newRepo) {
                System.out.println("old: " + oldrepo.toString() + "new: " + newRepo.toString());
            }
        });*/
    }

    private void populateHeapTableView() throws Exception{
        heapTableView.setItems(getHeapTable());
    }

    private void populateOutputListView() throws Exception {

        //outputListView = new ListView<>();
        outputListView.setItems(getOutput());
        //outputListView.setItems(getOutput());
    }

    private void populateFileListView() throws Exception {
        //TODO
        fileListView.setItems(getFileTable());
    }

    private void populateSymbolsTableView() throws Exception {
        symbolsTableView.setItems(getSymbolsTable());
    }

    private void populateExecutionListView() throws Exception {
        executionListView.setItems(getExecutionStack());
    }

    @FXML
    public void initialize() throws Exception{
        addressColumn = new TableColumn<>("Address");
        valueColumn = new TableColumn<>("Value");
        addressColumn.setCellValueFactory(new PropertyValueFactory<heapElement, Integer>("address")); //?
        addressColumn.setPrefWidth(126);
        valueColumn.setCellValueFactory(new PropertyValueFactory<heapElement, String>("value"));
        valueColumn.setPrefWidth(126);
        heapTableView.getColumns().addAll(addressColumn, valueColumn);

        //outputListView = new ListView<>();

        nameColumn = new TableColumn<variable, String>("Name");
        variableValueColumn = new TableColumn<variable, String>("Value");

        /*nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, value>, String> name)-> new SimpleStringProperty(name.getValue().getKey())); //paranthesis?
        variableValueColumn.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, value>, value> val) -> {
            //String valueString = val.getValue().getValue().toString();
            //SimpleStringProperty property = (SimpleStringProperty) valueString;
            //SimpleStringProperty plssduuud = new SimpleStringProperty(valueString);
            ObservableValue<value> observableValue = new ReadOnlyObjectWrapper<>(val.getValue().getValue());

            return observableValue;
        });*/
        nameColumn.setCellValueFactory(new PropertyValueFactory<variable, String>("name"));
        nameColumn.setPrefWidth(126);
        variableValueColumn.setCellValueFactory(new PropertyValueFactory<variable, String>("value"));
        variableValueColumn.setPrefWidth(126);
        symbolsTableView.getColumns().addAll(nameColumn, variableValueColumn);

        currentRepo = MainController.getCurrentRepo();

        //initializeRepo();
        populateProgramsListView();
        populateHeapTableView();
        populateOutputListView();
        populateFileListView();
        populateSymbolsTableView();
        populateExecutionListView();
    }

    public void selectProgram(MouseEvent mouseEvent) throws Exception{
        if (currentProgram != programsListView.getSelectionModel().getSelectedItem()){
            currentProgram = programsListView.getSelectionModel().getSelectedItem();
            nrOfProgramsField.setText(String.valueOf(currentRepo.getProgramsList().size()));
            populateHeapTableView();
            populateOutputListView();
            populateFileListView();
            populateSymbolsTableView();
            populateExecutionListView();
        }
    }

    private List<programState> removeCompleted(List<programState> inProgramList){
        return inProgramList.stream().filter(program->program.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(MouseEvent mouseEvent) throws Exception {
        //RUN concurrently one step for each of the existing PrgStates
        //prepare the list of callables

        List<programState> programsList = removeCompleted(currentRepo.getProgramsList().getContent());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Callable<programState>> callList = programsList.stream().map(((programState program) ->
                (Callable<programState>)(() -> { return program.oneStep(); }))).collect(Collectors.toList());

        if (programsList.isEmpty()){
            executor.shutdownNow();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Program finished its execution", ButtonType.OK);
            alert.show();
            return;
        }

        //start the execution of the callables
        //it returns the list of newly created programStates -> threads
        List<programState> newProgramsList =
                executor.invokeAll(callList).stream().map(future -> {
                    try{
                        return future.get();
                    }catch (InterruptedException | ExecutionException ex) {
                        Alert error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                        //error.setTitle("Error");
                        error.show();
                    }
                    return null;
                }).filter(program -> program != null).collect(Collectors.toList());

        //add the new threads to the programList
        newProgramsList.forEach(newThread -> programsList.add(newThread)); //newThread is a program

        nrOfProgramsField.setText(Integer.toString(currentRepo.getProgramsList().size()));
        try {
            populateProgramsListView();
            populateHeapTableView();
            populateOutputListView();
            populateFileListView();
            populateSymbolsTableView();
            populateExecutionListView();
        } catch (Exception ex) {
            Alert error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            //error.setTitle("Error");
            error.show();
        }

        programsList.forEach(program -> {
            try {
                currentRepo.logProgramState(program);
            } catch (Exception ex) {
                Alert error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                //error.setTitle("Error");
                error.show();
            }
        });

        iList<programState> programs = new myList<programState>();
        programs.setContent(programsList);
        currentRepo.setProgramsList(programsList);
    }

    public void exitApp(MouseEvent mouseEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void backToMain(MouseEvent mouseEvent) {
        Stage mainStage = (Stage) backButton.getScene().getWindow();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            mainStage.setTitle("Toy Language");
            Scene mainScene = new Scene(root);
            mainStage.setScene(mainScene);
            mainStage.show();
        } catch (Exception ex){
            Alert error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            //error.setTitle("Error");
            error.show();
        }
    }
}
