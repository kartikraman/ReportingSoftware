package application;

public class TestResult {
	String id="";
	String testName;
	String testValue;
	public TestResult(String id,String testName,String testValue){
		this.id=id;
		this.testName=testName;
		this.testValue=testValue;
	}
	
	public TestResult(String testName,String testValue) {
		this.testName=testName;
		this.testValue=testValue;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestValue() {
		return testValue;
	}
	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}
}
