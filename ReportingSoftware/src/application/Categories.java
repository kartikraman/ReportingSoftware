package application;

import javafx.beans.property.SimpleStringProperty;

public class Categories {
	SimpleStringProperty category;
	
	
	Categories(String cat){
		this.category=new SimpleStringProperty(cat);
	}
	
	public String getCategory() {
//		return category;
		return category.get();
	}
	public void setCategory(String category) {
//		this.category = category;
		this.category.set(category);
	}
	
}
