package application;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DialogBoxEmptyFieldController {
	
	 @FXML
	 private JFXButton exitButton;

    @FXML
    void exitButton(ActionEvent event) {
    	Stage s=(Stage) exitButton.getScene().getWindow();
    	s.close();	
    }

}
