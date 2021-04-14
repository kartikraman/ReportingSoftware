package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import dbconnection.DBConnectionMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogBoxNewTestController implements Initializable {
	int n=0;
	ArrayList<TextField> ar=new ArrayList<TextField>();;
	ArrayList<JFXTextField> arTfName=new ArrayList<JFXTextField>();
	ArrayList<JFXTextField> arTfLb=new ArrayList<JFXTextField>();
	ArrayList<JFXTextField> arTfUb=new ArrayList<JFXTextField>();
	ArrayList<JFXTextField> arTfUnit=new ArrayList<JFXTextField>();
	ArrayList<JFXTextField> arTfCost=new ArrayList<JFXTextField>();
	
	String call;
    @FXML
    private JFXComboBox<Label> categoryCB;

    @FXML
    private VBox functionVB;

    @FXML
    private JFXTextField nameTF;

    @FXML
    private JFXButton addTestButton;

    @FXML
    private JFXButton addSCButton;
    
    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;
    
    @FXML
    void addSC2VB(ActionEvent event) {
    	functionVB.getChildren().add(customScHBox());
    }

    @FXML
    void addTest2VB(ActionEvent event) {
    	functionVB.getChildren().add(customHBox());
//    	n++;
//    	System.out.println(n);
    }
    private HBox customScHBox() {
    	HBox hb=new HBox();
    	
    	Label scName=new Label("Sub-Cateogory");
    	scName.setAlignment(Pos.BOTTOM_CENTER);
    	scName.setPrefSize(80, 30);
    	
    	JFXTextField valueNameTF=new JFXTextField();
    	valueNameTF.setPrefSize(125,25);
    	
    	valueNameTF.textProperty().addListener((observable,oldValue,newValue)->{
    		call=newValue;
    	});
    	
    	hb.getChildren().addAll(scName,valueNameTF);
    	return hb;
    }

    private HBox customHBox() {
    	
    	HBox hb=new HBox();
    	
    	
    	
    	Label name=new Label("Name");
    	name.setAlignment(Pos.BOTTOM_CENTER);
    	name.setPrefSize(80, 30);
    	
    	
    	JFXTextField valueNameTF=new JFXTextField();
    	valueNameTF.setPrefSize(125,25);
    	arTfName.add(valueNameTF);
    	
    	
    	Label lb=new Label("LB");
    	lb.setAlignment(Pos.BOTTOM_CENTER);
    	lb.setPrefSize(29, 27);
    	
    	
    	JFXTextField lbTF=new JFXTextField();
    	lbTF.setPrefSize(39, 25);
    	arTfLb.add(lbTF);
    	
    	
    	Label ub=new Label("UB");
    	ub.setAlignment(Pos.BOTTOM_CENTER);
    	ub.setPrefSize(29, 27);
    	
    	
    	JFXTextField ubTF=new JFXTextField();
    	ubTF.setPrefSize(39, 25);
    	arTfUb.add(ubTF);
    	
    	
    	Label unit=new Label("Unit");
    	unit.setAlignment(Pos.BOTTOM_CENTER);
    	unit.setPrefSize(29, 27);
    	
    	
    	JFXTextField unitTF=new JFXTextField();
    	unitTF.setPrefSize(39, 25);
    	arTfUnit.add(unitTF);
    	
    	Label cost=new Label("Cost");
    	cost.setAlignment(Pos.BOTTOM_CENTER);
    	cost.setPrefSize(29, 27);
    	
    	JFXTextField costTF=new JFXTextField();
    	costTF.setPrefSize(39, 25);
    	costTF.setPadding(new Insets(0,10,0,0));
    	arTfCost.add(costTF);
    	
    	hb.getChildren().addAll(name,valueNameTF,lb,lbTF,ub,ubTF,unit,unitTF,cost,costTF);
    	hb.setSpacing(10);
    	
    	return hb;
    }
    
    
    
    @FXML
    void add2Db(ActionEvent event) {
    	
    	String name=nameTF.getText();
    	String category=categoryCB.getValue().getText();
    	DBConnectionMethods db=new DBConnectionMethods();
    	db.addTestSubject_Category(category, name);
    	
    	for(int i=0;i<arTfName.size();i++) {
//    		String s=arTfName.get(i).getText()+" "+arTfUb.get(i).getText()+" "+arTfLb.get(i).getText()+" "+arTfUnit.get(i).getText()+" "+call+" "+categoryCB.getValue().getText();                                                         
//    		System.out.println(s);
    		db.addNewTest2Db(arTfName.get(i).getText(), arTfLb.get(i).getText(), arTfUb.get(i).getText(),arTfUnit.get(i).getText(), name,Integer.valueOf(arTfCost.get(i).getText()));
    	}
    	
    	Stage s=(Stage) cancelButton.getScene().getWindow();
    	s.close();
    }

    @FXML
    void cancel(ActionEvent event) {
    	Stage s=(Stage) cancelButton.getScene().getWindow();
    	s.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		addSCButton.setDisable(true);
		addSCButton.setVisible(false);
		ArrayList<String> x=DBConnectionMethods.getAllCategory();
		for(String i:x) {
			categoryCB.getItems().add(new Label(i));
		}
	}
}
