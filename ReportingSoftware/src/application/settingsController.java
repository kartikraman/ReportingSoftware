package application;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import dbconnection.DBConnectionMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

public class settingsController {

    @FXML
    private JFXButton addTestBttn;

    @FXML
    private JFXButton configureTestBttn;

    @FXML
    private JFXButton addCategoryBttn;

    @FXML
    void addCategory(ActionEvent event) {
    	
    	try {
    		FXMLLoader f=new FXMLLoader(getClass().getResource("DialogBoxNewCategory.fxml"));
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
    void addTest(ActionEvent event) {
    	try {
    		FXMLLoader f=new FXMLLoader(getClass().getResource("DialogBoxNewTest.fxml"));
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
    void configureTest(ActionEvent event) {

    }
    
}
