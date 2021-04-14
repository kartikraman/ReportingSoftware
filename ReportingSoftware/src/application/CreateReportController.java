package application;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import dbconnection.DBConnectionMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import pdfformating.FormatRoot;

public class CreateReportController implements Initializable{
	
		String patientName="";
		String years="";
		String months="";
		String days="";
		String patientId;
		String gender="";
		String doctor;
		String sampleBy="";
		String sampleTime="";
		String panelId="";
		String panelCode="";
		String email="";
		
		Patient a;
		Set<String> suggestion;
	
	   @FXML
	    private JFXComboBox<Label> salCB;

	    @FXML
	    private JFXTextField nameTF;

	    @FXML
	    private JFXRadioButton maleRB;

	    @FXML
	    private JFXRadioButton femaleRB;

	    @FXML
	    private JFXTextField yearTF;

	    @FXML
	    private JFXTextField monthsTF;

	    @FXML
	    private JFXTextField daysTF;

	    @FXML
		protected JFXTextField pidTF;

	    @FXML
	    private JFXTextField samplebyTF;

	    @FXML
	    private JFXTextField sampletimeTF;

	    @FXML
	    private JFXTextField panelidTF;

	    @FXML
	    private JFXTextField panelcodeTF;

	    @FXML
	    private JFXTextField emailTF;
	    
	    @FXML
	    private JFXTextField doctorTF;

	    @FXML
	    private JFXButton selectTestBttn;
	    
	    @FXML
	    private JFXButton printPreviewBttn;
	    
	    @FXML
	    private TableView<TestResult> selectedTestValuesTable;
	    
	    @FXML
	    private TableColumn<TestResult, String> testNameCol;

	    @FXML
	    private TableColumn<TestResult, String> testValueCol;

	    @FXML
	    void printPreview(ActionEvent event) {
	    	FormatRoot fr=new FormatRoot();
	    	fr.createReport(a,DBConnectionMethods.getTestResults(DBConnectionMethods.getCurrentUser()));
	    }
	    
	    @FXML
	    void rbselected(ActionEvent event) {
	    	if(maleRB.isSelected()){
	    		gender="male";
	    	}
	    	if(femaleRB.isSelected()){
	    		gender=new String("female");
	    	}
	    }
	    
	    @FXML
	    void selectTest(ActionEvent event) {
	    	if(salCB.getValue()!=null) {
	    		patientName=salCB.getValue().getText()+" "+nameTF.getText();//getting the name
	    	}
	    	
	    	//getting the age
	    	years=yearTF.getText();
	    	months=monthsTF.getText();
	    	days=daysTF.getText();
	    	
	    	String age=years+" "+months+" "+days;
	    	//patient id
	    	patientId=pidTF.getText();
	    	
	    	//gender is processed with the rbselected method
	    	
	    	//doctor
	    	doctor=doctorTF.getText();
	    	
	    	//lab only entry section
	    	sampleBy=samplebyTF.getText();
	    	sampleTime=sampletimeTF.getText();
	    	panelId=panelidTF.getText();
	    	panelCode=panelcodeTF.getText();
	    	email=emailTF.getText();
	    	a=new Patient(patientId,patientName,years,months,days,gender,doctor,sampleBy,sampleTime,panelId,panelCode,email);
	    	
	    	
//	    	System.out.println("Name "+patientName+" years "+years+" months "+months+" days "+days+" sample By "+ sampleBy+" sampleTime "+sampleTime+" panelId "+panelId+" panelCode "+panelCode+" email "+email+" Doctor "+doctor+" Gender "+gender);                                                                       
	    	DBConnectionMethods input=new DBConnectionMethods();
	    	if(patientId.isBlank()||patientName.isBlank()||gender.isBlank()||doctor.isBlank()||age.isBlank()) {
	    		creatEmptyFieldError();
	    	}
	    	else if(DBConnectionMethods.getPatient(patientId)!=null) {
	    		createExistingUserError(patientId);
	    	}
	    	else {
	    		input.addPatient(patientId, patientName, years, months, days, gender, doctor, sampleBy, sampleTime, panelId, panelCode, email);
		    	createSelectTestBox();
	    	}
	    	
	    }


	private void creatEmptyFieldError() {
			// TODO Auto-generated method stub
		try {
    		FXMLLoader errorBox=new FXMLLoader(getClass().getResource("DialogBoxEmptyField.fxml"));
			Pane p=errorBox.load();
			Dialog d=new Dialog();
			d.getDialogPane().setContent(p);
			d.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	private void createExistingUserError(String patientId2) {
			// TODO Auto-generated method stub
		try {
    		FXMLLoader errorBox=new FXMLLoader(getClass().getResource("DialogBoxExistingUser.fxml"));
			Pane p=errorBox.load();
			Dialog d=new Dialog();
			d.getDialogPane().setContent(p);
//			d.initStyle(StageStyle.TRANSPARENT);
			d.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	private void createSelectTestBox() {
			// TODO Auto-generated method stub

		try {
    		FXMLLoader f=new FXMLLoader(getClass().getResource("SelectTest.fxml"));
			Pane p=f.load();
			Dialog d=new Dialog();
			d.getDialogPane().setContent(p);
//			d.initStyle(StageStyle.TRANSPARENT);
			d.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @FXML
    void valueCheckBttn(ActionEvent event) {
    	selectedTestValuesTable.setItems(getTestValueItems());
    }

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//contents for the salCB
		salCB.getItems().add(new Label("Mr."));
		salCB.getItems().add(new Label("Mrs."));
		salCB.getItems().add(new Label("Ms."));
		salCB.getItems().add(new Label("Mast"));
		salCB.getItems().add(new Label("Baby"));
		salCB.getItems().add(new Label("Dr."));
		salCB.getItems().add(new Label("Miss"));
		//Contents for the RadioButton
		ToggleGroup rbTG=new ToggleGroup();
		maleRB.setToggleGroup(rbTG);
		femaleRB.setToggleGroup(rbTG);
		
		testValueCol.setCellValueFactory(new PropertyValueFactory<TestResult,String>("testValue"));
		testNameCol.setCellValueFactory(new PropertyValueFactory<TestResult,String>("testName"));
		
		
		
//		selectedTestValuesTable.setItems(getTestValueItems());
		
	}

	private ObservableList<TestResult> getTestValueItems() {
		// TODO Auto-generated method stub
		ArrayList<TestResult> ar=DBConnectionMethods.getTestResults(DBConnectionMethods.getCurrentUser());
		ObservableList<TestResult> obar=FXCollections.observableArrayList();
		for(int i=0;i<ar.size();i++) {
			obar.add(ar.get(i));
		}
		return obar;
	}
}