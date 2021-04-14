package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import dbconnection.DBConnectionMethods;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SelectTestDialogBox implements Initializable {
	
	@FXML
	private TableView<Test> selectedTests;
	
	ObservableList<Test> selectedTestList=FXCollections.observableArrayList();

    @FXML
    private TableView<Categories> catTable;

    @FXML
    private TableColumn<Categories, String> catCol;

    @FXML
    private TableView<TestSubject> subjectTable;
    
    @FXML
    private TableColumn<TestSubject, String> testSubjectCol;

    @FXML
    private TableView<Test> tableTest;
    
    @FXML
    private TableColumn<Test, String> testCol;

    @FXML
    private JFXButton nextBttn;
    
    @FXML
    private TableColumn<Test, String> selectedCol;

    @FXML
    void enterTestFunction(ActionEvent event) {
    	String patientID=DBConnectionMethods.getCurrentUser();
    	ArrayList<String> selectedTestStringList=new ArrayList<String>();
    	for(int i=0;i<selectedTestList.size();i++) {
    		selectedTestStringList.add(selectedTestList.get(i).getTest());
    	}
    	DBConnectionMethods.addSelectedTests(selectedTestStringList, patientID);
    	Stage s=(Stage) nextBttn.getScene().getWindow();
    	s.close();
    	try {
    		FXMLLoader f=new FXMLLoader(getClass().getResource("DiallogBoxFillTest.fxml"));
			Pane p=f.load();
			Dialog d=new Dialog();
			d.getDialogPane().setContent(p);
			d.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		catCol.setCellValueFactory(new PropertyValueFactory<Categories,String>("category"));
		catTable.setItems(getCat());
		catTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        fillTestSubjectTable(DBConnectionMethods.getAllTestSubjects(newSelection.getCategory()));
		    }
		});

	}
	
	
	
	private void fillTestSubjectTable(ArrayList<String> allTestSubjects) {
		// TODO Auto-generated method stub
		testSubjectCol.setCellValueFactory(new PropertyValueFactory<TestSubject,String>("Subject"));
		subjectTable.setItems(getTestSub(allTestSubjects));	
		subjectTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        fillTestTable(DBConnectionMethods.getAllTest(newSelection.getSubject()));
		    }
		});
		
	}
	
	private void fillTestTable(ArrayList<String> allTest) {
		// TODO Auto-generated method stub
		testCol.setCellValueFactory(new PropertyValueFactory<Test,String>("test"));
		tableTest.setItems(getTest(allTest));
		tableTest.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
			if(newSelection !=null) {
				fillSelectedTestTable(newSelection);
			}
		});
		
		
	}

	private void fillSelectedTestTable(Test newSelection) {
		// TODO Auto-generated method stub
		selectedCol.setCellValueFactory(new PropertyValueFactory<Test,String>("test"));
		if(!selectedTestList.contains(newSelection)) {
			selectedTestList.add(newSelection);	
		}
		
		selectedTests.setItems(selectedTestList);
	}

	private ObservableList<Test> getTest(ArrayList<String> allTest){
		final ObservableList<Test> data=FXCollections.observableArrayList();
		for(int i=0;i<allTest.size();i++) {
			data.add(new Test(allTest.get(i)));
		}
		return data;
	}

	private ObservableList<TestSubject> getTestSub(ArrayList<String> allTestSubjects) {
		final ObservableList<TestSubject> data = FXCollections.observableArrayList();
		ArrayList<String> ar=allTestSubjects;
		for(int i=0;i<ar.size();i++) {
			data.add(new TestSubject(ar.get(i)));
		}
		return data;
	}

	private ObservableList<Categories> getCat(){
		final ObservableList<Categories> data = FXCollections.observableArrayList();
		ArrayList<String> ar=DBConnectionMethods.getAllCategory();
		for(int i=0;i<ar.size();i++) {
			data.add(new Categories(ar.get(i)));
		}
		return data;
	}

}
