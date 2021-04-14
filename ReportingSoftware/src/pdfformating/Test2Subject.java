package pdfformating;

import application.TestResult;

public class Test2Subject {

	TestResult t;
	String subject;
	
	
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public TestResult getT() {
		return t;
	}
	public void setT(TestResult t) {
		this.t = t;
	}
	public Test2Subject(TestResult t, String subject) {
		super();
		this.t = t;
		this.subject = subject;
	}
	
	
	
}
