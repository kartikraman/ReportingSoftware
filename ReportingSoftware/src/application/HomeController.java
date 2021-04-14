package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class HomeController implements Initializable{

    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private Pane home_container;
    
    public void addToMainScreen(String filename) {
    	try {
    		home_container.getChildren().clear();
    		Pane p=FXMLLoader.load(getClass().getResource(filename));
        	p.prefHeightProperty().bind(home_container.heightProperty());
    		p.prefWidthProperty().bind(home_container.widthProperty());
    		home_container.getChildren().addAll(p);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try{
			drawer.prefHeightProperty().bind(anchorPane.heightProperty());
			home_container.prefHeightProperty().bind(anchorPane.heightProperty());
			home_container.prefWidthProperty().bind(anchorPane.widthProperty());
			VBox nav=FXMLLoader.load(getClass().getResource("SideNavigation.fxml"));
			drawer.setSidePane(nav);
			addToMainScreen("CreateReport.fxml");
			drawer.setDisable(true);
			for(Node node:nav.getChildren()) {
				if(node.getAccessibleText()!=null) {
					node.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->{
						switch(node.getAccessibleText()) {
						case "button1":
							addToMainScreen("CreateReport.fxml");
//							drawer.close();
							break;				
						case "button2":
							addToMainScreen("ViewReport.fxml");
//							drawer.close();
							break;
						case "button3":
							addToMainScreen("Accounts.fxml");
//							drawer.close();
							break;					
						case "button4":
							addToMainScreen("Settings.fxml");
//							drawer.close();
							break;
						case "button5":
							addToMainScreen("About.fxml");
//							drawer.close();
							break;
						}
					});
				}
			}
			HamburgerBackArrowBasicTransition burgerTask=new HamburgerBackArrowBasicTransition(hamburger);
			burgerTask.setRate(-1);
			hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) ->{
			burgerTask.setRate(burgerTask.getRate() * -1);
			burgerTask.play();
			
			if(drawer.isOpened()) {
				drawer.close();
				drawer.setDisable(true);
			}
			else {
				drawer.open();
				drawer.setVisible(true);	
				drawer.setDisable(false);
			}
		});
			
			drawer.addEventHandler(MouseEvent.DRAG_DETECTED, (e) ->{
//				burgerTask.setRate(-1);
				burgerTask.setRate(burgerTask.getRate() * -1);
				burgerTask.play();
				if(drawer.isOpened()) {
					drawer.close();
					drawer.setDisable(true);
				}
				else {
					drawer.open();
					drawer.setVisible(true);
					drawer.setDisable(false);
				}
				System.out.println("Drag happend");
			});
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


}
