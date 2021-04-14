package application;

public class TestFormat {
	String testName;
	String lb;
	String ub;
	String unit;
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getUb() {
		return ub;
	}
	public void setUb(String ub) {
		this.ub = ub;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public TestFormat(String testName, String lb, String ub, String unit) {
		this.testName = testName;
		this.lb = lb;
		this.ub = ub;
		this.unit = unit;
	}
	
}
