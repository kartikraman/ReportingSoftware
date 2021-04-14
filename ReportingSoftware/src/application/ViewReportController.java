package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import dbconnection.DBConnectionMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pdfformating.FormatRoot;

public class ViewReportController implements Initializable{
	
	ArrayList<NameAndId> ar;
	
	String id="";
	
	String docName="";
	
	String frmDate="";
	
	String toDate="";
	
	String idResult;
	ArrayList<TestResult> arTestResult;
	
	public String getIdResult() {
		return idResult;
	}

	public void setIdResult(String idResult) {
		this.idResult = idResult;
	}

	public ArrayList<TestResult> getArTestResult() {
		return arTestResult;
	}

	public void setArTestResult(ArrayList<TestResult> arTestResult) {
		this.arTestResult = arTestResult;
	}

    @FXML
    private JFXTextField patientIDTF;

    @FXML
    private JFXTextField docNameTF;

	@FXML
    private JFXTextField dateFromTF;

    @FXML
    private JFXTextField dateToTF;

    @FXML
    private TableView<NameAndId> queryResultTable;

    @FXML
    private TableView<TestResult> resultDetailTable;
    
    @FXML
    private TableColumn<NameAndId, String> idCol;

    @FXML
    private TableColumn<NameAndId, String> nameCol;
    
    @FXML
    private TableColumn<TestResult, String> testNameCol;

    @FXML
    private TableColumn<TestResult, String> testValCol;
    

    @FXML
    void printAction(ActionEvent event) {
    	FormatRoot fr=new FormatRoot();
    	fr.createReport(DBConnectionMethods.getPatient(getIdResult()), getArTestResult());
    }

    @FXML
    void searchAction(ActionEvent event) {
    	if(!patientIDTF.getText().isEmpty()) {
    		id=patientIDTF.getText();
    	}
    	if(!docNameTF.getText().isEmpty()) {
    		docName=docNameTF.getText();
    	}
    	if(!dateFromTF.getText().isEmpty()) {
    		frmDate=dateFromTF.getText();
    	}
    	if(!dateToTF.getText().isEmpty()) {
    		toDate=dateToTF.getText();
    	}
    	
    	
//    	if(id=="") {
//    		if(docName=="") {
//    			if(frmDate=="" || toDate=="") {
//    				//potential error message
//    			}
//    			else {
//    				//search by date
//    				searchByDate();	
//    			}	
//    		}
//    		else {
//    			searchByDoc();
//    		}
//    	}
//    	else {
//    		searchById();
//    	}
    	
    	if(!patientIDTF.getText().isEmpty()) {
    		searchById();
    	}
    	else if(!docNameTF.getText().isEmpty()){
    		searchByDoc();
    	}
    	else if(!(dateFromTF.getText().isEmpty()) && !dateToTF.getText().isEmpty()) {
    		searchByDate();
    	}
    	else {
    		System.out.println("Error");
    	}
    	
    }
    

	private void searchByDoc() {
		// TODO Auto-generated method stub
		if(frmDate=="" && toDate=="") 
		{
			ar=DBConnectionMethods.searchByDoc(docName);
		}
		else 
		{
			ar=DBConnectionMethods.searchByDoc(docName,frmDate,toDate);
		}
		ObservableList<NameAndId> oal=FXCollections.observableArrayList(ar);
		idCol.setCellValueFactory(new PropertyValueFactory<NameAndId,String>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<NameAndId,String>("name"));
		queryResultTable.setItems(oal);
	}
	
	
	
	private void searchById() {
		// TODO Auto-generated method stub
		ar=DBConnectionMethods.searchById(id);
		ObservableList<NameAndId> oal=FXCollections.observableArrayList();
		for(int i=0;i<ar.size();i++) {
			oal.add(ar.get(i));
		}
		idCol.setCellValueFactory(new PropertyValueFactory<NameAndId,String>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<NameAndId,String>("name"));
		
		queryResultTable.setItems(oal);
		
	}

	private void searchByDate() {
		// TODO Auto-generated method stub
		ar=DBConnectionMethods.searchByDate(frmDate,toDate);
		ObservableList<NameAndId> oal=FXCollections.observableArrayList(ar);
		idCol.setCellValueFactory(new PropertyValueFactory<NameAndId,String>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<NameAndId,String>("name"));
		queryResultTable.setItems(oal);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		testNameCol.setCellValueFactory(new PropertyValueFactory<TestResult,String>("testName"));
		testValCol.setCellValueFactory(new PropertyValueFactory<TestResult,String>("testValue"));
		queryResultTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        resultDetailTable.setItems(setResultTable(newSelection.getId()));
		    }
		});
		
	}

	private ObservableList<TestResult> setResultTable(String id) {
		 //TODO Auto-generated method stub
		ArrayList<TestResult> tr=DBConnectionMethods.getTestResults(id);
		ObservableList<TestResult> rt=FXCollections.observableArrayList(tr);
		setIdResult(id);
		setArTestResult(tr);
		return rt;
	}

}
