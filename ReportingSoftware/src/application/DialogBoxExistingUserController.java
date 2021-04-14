package application;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DialogBoxExistingUserController {

    @FXML
    private JFXButton exitButton;

    @FXML
    void closeDialogBox(ActionEvent event) {
    	
    	Stage s=(Stage) exitButton.getScene().getWindow();
    	s.close();		
    }

}
