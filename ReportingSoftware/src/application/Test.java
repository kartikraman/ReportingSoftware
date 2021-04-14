package application;

import javafx.beans.property.SimpleStringProperty;

public class Test {
	SimpleStringProperty test;
	
	Test(String test){
		this.test=new SimpleStringProperty(test);
	}

	public String getTest() {
		return test.get();
	}

	public void setTest(String test) {
		this.test.set(test);
	}
}
