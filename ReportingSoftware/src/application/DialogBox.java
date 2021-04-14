package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dbconnection.DBConnectionMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DialogBox {

    @FXML
    private Button close;
    
    @FXML
    private JFXButton addBttn;
    
    @FXML
    private JFXTextField newCategoryTF;
    
    @FXML
    void addNewCategory(ActionEvent event) {
    	try {
    		String s=newCategoryTF.getText();
//    		System.out.println(s);
    		DBConnectionMethods d=new DBConnectionMethods();
    		d.addCategory(s);
    	}
    	catch(NullPointerException e) {
    		System.out.println("BLah Blah Blah");
    	}
    	
    }

    @FXML
    void closeOnAction(ActionEvent event) {
    	Stage s=(Stage) close.getScene().getWindow();
    	s.close();
    }

}
