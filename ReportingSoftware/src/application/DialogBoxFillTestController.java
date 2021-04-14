package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import dbconnection.DBConnectionMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogBoxFillTestController implements Initializable {
	
	ArrayList<JFXTextField> arTestValueTF=new ArrayList<JFXTextField>();
	
	ArrayList<String> selectedTests;

    @FXML
    private AnchorPane dialogBoxAnchorPane;
    
    @FXML
    private VBox testVbox;

    @FXML
    private VBox valuesVbox;

    @FXML
    void nextOption(ActionEvent event) {
    	ArrayList<String> arValues=new ArrayList<String>();
    	for(int i=0;i<arTestValueTF.size();i++) {
    		arValues.add(arTestValueTF.get(i).getText());
    	}
    	DBConnectionMethods.addTestResults(selectedTests, arValues, DBConnectionMethods.getCurrentUser());
    	Stage s=(Stage)valuesVbox.getScene().getWindow();
    	s.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		selectedTests=DBConnectionMethods.getSelectedTests();
		for(int i=0;i<selectedTests.size();i++) {
			createNewFields(selectedTests.get(i));
		}
		
		
	}

	private void createNewFields(String label) {
		// TODO Auto-generated method stub
		Label scName=new Label(label);
    	scName.setAlignment(Pos.BOTTOM_CENTER);
    	scName.setPrefSize(80, 30);
    	testVbox.getChildren().add(scName);
    	
    	JFXTextField valueNameTF=new JFXTextField();
    	valueNameTF.setPrefSize(125,25);
    	arTestValueTF.add(valueNameTF);
    	valuesVbox.getChildren().add(valueNameTF);
    	
	}

}