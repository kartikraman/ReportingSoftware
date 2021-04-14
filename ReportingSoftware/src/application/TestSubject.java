package application;

import javafx.beans.property.SimpleStringProperty;

public class TestSubject {
	SimpleStringProperty Subject;
	
	
	TestSubject(String Subject){
		this.Subject=new SimpleStringProperty(Subject);
	}
	
	public String getSubject() {
		return Subject.get();
	}

	public void setSubject(String subject) {
		this.Subject.set(subject);
	}
		
}
