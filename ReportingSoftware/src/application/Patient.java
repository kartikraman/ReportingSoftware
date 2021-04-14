package application;

import dbconnection.DBConnectionMethods;

public class Patient {

	String id;
	String name;
	String years;
	String months;
	String days;
	String gender;
	String doc;
	String sampleBy;
	String sampleTime;
	String panelId;
	String panelCode;
	String email;
	public Patient(String id, String name, String years, String months, String days, String gender, String doc,
			String sampleBy, String sampleTime, String panelId, String panelCode, String email) {
		this.id = id;
		this.name = name;
		this.years = years;
		this.months = months;
		this.days = days;
		this.gender = gender;
		this.doc = doc;
		this.sampleBy = sampleBy;
		this.sampleTime = sampleTime;
		this.panelId = panelId;
		this.panelCode = panelCode;
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getSampleBy() {
		return sampleBy;
	}
	public void setSampleBy(String sampleBy) {
		this.sampleBy = sampleBy;
	}
	public String getSampleTime() {
		return sampleTime;
	}
	public void setSampleTime(String sampleTime) {
		this.sampleTime = sampleTime;
	}
	public String getPanelId() {
		return panelId;
	}
	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}
	public String getPanelCode() {
		return panelCode;
	}
	public void setPanelCode(String panelCode) {
		this.panelCode = panelCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		// TODO Auto-generated method stub
		return DBConnectionMethods.getDate();
	}
	public String getAge() {
		// TODO Auto-generated method stub
		String age="";
		if(years=="") {
			if(months=="") {
				age=days;
			}
			else {
				age=months;
			}
		}
		else {
			age=years;
		}
		
		
		return age;
	}
	
	
	
}
