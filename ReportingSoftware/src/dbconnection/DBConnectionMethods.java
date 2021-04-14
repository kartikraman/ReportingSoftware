package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.NameAndId;
import application.Patient;
import application.TestFormat;
import application.TestResult;

public class DBConnectionMethods {
	
	
	public static void addPatient(String patientId,String patientName,String years,String months,String days,String gender,String doctor,String sampleBy,String sampleTime,String panelId,String panelCode,String email) {
		Connection con=DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="INSERT INTO Patient_Credentials(PatientID, PatientName, Years, Months, Days, Gender, RefferedBy, SampleBy, SampleTime, PanelID, PanelCode, Email, Dt) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,date('now'))";
			ps=con.prepareStatement(sql);
			ps.setString(1, patientId);
			ps.setString(2, patientName);
			ps.setString(3,years);
			ps.setString(4,months);
			ps.setString(5,days);
			ps.setString(6,gender);
			ps.setString(7,doctor);
			ps.setString(8,sampleBy);
			ps.setString(9,sampleTime);
			ps.setString(10,panelId);
			ps.setString(11,panelCode);
			ps.setString(12,email);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		currentUserClearTable(patientId);
	}
	
	
	
	
	
	
	public static void currentUserClearTable(String patientId) {
		// TODO Auto-generated method stub
		Connection con=DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="DELETE FROM Cur_User";
			ps=con.prepareStatement(sql);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		addCurrentUser(patientId);
	}
	
	






	public static void addCurrentUser(String patientId) {
		// TODO Auto-generated method stub
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="INSERT INTO Cur_User(PatientID) VALUES(?)";
			ps=c.prepareStatement(sql);
			ps.setString(1, patientId);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	public static String getCurrentUser() {
		String s="";
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="SELECT * FROM Cur_User";
			ps=c.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				s=rs.getString("PatientID");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}






	public static void addCategory(String category) {
		Connection con=DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="INSERT INTO Test_Category(Category) VALUES(?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, category);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
				System.out.println("Connections closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	public static ArrayList<String> getAllCategory() {
		ArrayList<String> ar=new ArrayList<String>();
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="Select * from Test_Category";
			ps=c.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				String name=rs.getString("Category");
				ar.add(name);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			
			try {
				rs.close();
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return ar;
	}
	
	
	
	
	
	
	public static void addNewTest2Db(String name,String lb,String ub,String unit,String test_subject,int cost) {
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="INSERT INTO Tests(test_name,lower_bound,upper_bound,unit,test_subject,cost) VALUES(?,?,?,?,?,?)";
			ps=c.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, lb);
			ps.setString(3, ub);
			ps.setString(4, unit);
			ps.setString(5, test_subject);
			ps.setInt(6, cost);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	public static void addTestSubject_Category(String cat,String subject) {
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="INSERT INTO Categories_TestSubject(Categories,TestSubjects) VAlUES(?,?)";
			ps=c.prepareStatement(sql);
			ps.setString(1, cat);
			ps.setString(2, subject);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	
	
	
	
	public static ArrayList<String> getAllTestSubjects(String category){
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<String> ar=new ArrayList<String>();
		try {
			String sql="SELECT TestSubjects FROM Categories_TestSubject WHERE Categories=?";
			ps=c.prepareStatement(sql);
			ps.setString(1, category);
			rs=ps.executeQuery();
			while(rs.next()) {
				String testSubject=rs.getString("TestSubjects");
				ar.add(testSubject);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return ar;
	}






	public static ArrayList<String> getAllTest(String subject) {
		// TODO Auto-generated method stub
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<String> ar=new ArrayList<String>();
		try {
			String sql="SELECT test_name FROM Tests WHERE test_subject=?";
			ps=c.prepareStatement(sql);
			ps.setString(1, subject);
			rs=ps.executeQuery();
			while(rs.next()) {
				String test=rs.getString("test_name");
				ar.add(test);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return ar;
	}
	
	
	
	
	public static void addSelectedTests(ArrayList<String> ar,String pid) {
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		try{
			String sql="DELETE FROM Select_Tests";
			ps=c.prepareStatement(sql);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		for(int i=0;i<ar.size();i++) {
			addSelectedTest(ar.get(i),pid);
		}
	}
	
	
	public static void addSelectedTest(String test,String pid) {
		int n=getSerialNumberSelectTests();
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="INSERT INTO Select_Tests(Serial,Test,ID) VALUES(?,?,?)";
			ps=c.prepareStatement(sql);
			ps.setInt(1,n);
			ps.setString(2, test);
			ps.setString(3, pid);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//I am using serial number to be the primary key in a database first I will check if the database has any value.
		//if it does I will take the last serial number and then I and add 1 to it and make it to be the next serial number.
		
		public static int getSerialNumberSelectTests() {
			Connection c=DbConnection.connect();
			PreparedStatement ps=null;
			ResultSet rs=null;
			int i=0;
			try {
				String sql="Select * from Select_Tests";
				ps=c.prepareStatement(sql);
				rs=ps.executeQuery();
				if(rs.next()==false) {
					i=0;
				}
				else {
					do {
						i=rs.getInt("Serial");
					}while(rs.next());
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
					ps.close();
					c.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			i=i+1;
			return i;
		}
		
		
		
	
	
	
	public static ArrayList<String> getSelectedTests(){
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<String> ar=new ArrayList<String>();
		try {
			String sql="SELECT * FROM Select_Tests";
			ps=c.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				ar.add(rs.getString("Test"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ar;
	}
	
	
	
	public static void addTestResults(ArrayList<String> tests,ArrayList<String> results,String id) {
		for(int i=0;i<tests.size();i++) {
			addTestResult(tests.get(i),results.get(i),id);
		}
	}






	private static void addTestResult(String test, String value, String id) {
		// TODO Auto-generated method stub
		int n=getTestResultSerial();
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="INSERT INTO Test_Results(Serial,ID,TestName,Value) VALUES(?,?,?,?)";
			ps=c.prepareStatement(sql);
			ps.setInt(1, n);
			ps.setString(2, id);
			ps.setString(3, test);
			ps.setString(4, value);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static int getTestResultSerial() {
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			String sql="Select * from Test_Results";
			ps=c.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()==false) {
				i=0;
			}
			else {
				do {
					i=rs.getInt("Serial");
				}while(rs.next());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		i=i+1;
		return i;
	}
	
	
	
	public static ArrayList<TestResult> getTestResults(String id){
		Connection con=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<TestResult> ar=new ArrayList<TestResult>();
		try {
			String sql="SELECT * FROM Test_Results WHERE ID = ?";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				String a=rs.getString("ID");
				String b=rs.getString("TestName");
				String c=rs.getString("Value");
				ar.add(new TestResult(a,b,c));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ar;
	}






	public static ArrayList<NameAndId> searchById(String id) {
		
		// TODO Auto-generated method stub
		ArrayList<NameAndId> ar=new ArrayList<NameAndId>();
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="SELECT * FROM Patient_Credentials WHERE PatientID=?";
			ps=c.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				String name=rs.getString("PatientName");
				ar.add(new NameAndId(name,id));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ar;
	}


	public static ArrayList<NameAndId> searchByDoc(String docName) {
		// TODO Auto-generated method stub
		Connection c=DbConnection.connect();
		ArrayList<NameAndId> ar= new ArrayList<NameAndId>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="SELECT * FROM Patient_Credentials WHERE RefferedBy=?";
			ps=c.prepareStatement(sql);
			ps.setString(1, docName);
			rs=ps.executeQuery();
			while(rs.next()) {
				String s=rs.getString("PatientID");
				String q=rs.getString("PatientName");
				ar.add(new NameAndId(q,s));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ar;
	}






	public static ArrayList<NameAndId> searchByDoc(String docName, String frmDate, String toDate) {
		// TODO Auto-generated method stub
		Connection c=DbConnection.connect();
		ArrayList<NameAndId> ar=new ArrayList<NameAndId>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		if(frmDate==toDate) {
			try {
				String sql="SELECT * FROM Patient_Credentials WHERE RefferedBy=? AND Dt=?";
				ps=c.prepareStatement(sql);
				ps.setString(1,docName);
				ps.setString(2, frmDate);
				rs=ps.executeQuery();
				while(rs.next()) {
					String x=rs.getString("PatientID");
					String y=rs.getString("PatientName");
					ar.add(new NameAndId(y,x));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
					ps.close();
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else {
			try {
				String sql="SELECT * FROM Patient_Credentials WHERE RefferedBy=?  BETWEEN ? AND ?";
				ps=c.prepareStatement(sql);
				ps.setString(1,docName);
				ps.setString(2, frmDate);
				ps.setString(3, toDate);
				rs=ps.executeQuery();
				while(rs.next()) {
					String x=rs.getString("PatientID");
					String y=rs.getString("PatientName");
					ar.add(new NameAndId(y,x));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
					ps.close();
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
		}
		return ar;
	}






	public static ArrayList<NameAndId> searchByDate(String frmDate, String toDate) {
		// TODO Auto-generated method stub
		Connection c=DbConnection.connect();
		ArrayList<NameAndId> ar=new ArrayList<NameAndId>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		if(frmDate==toDate) {
			try {
				String sql="SELECT * FROM Patient_Credentials WHERE Dt=?";
				ps=c.prepareStatement(sql);
				ps.setString(1,frmDate);
				ps.setString(2, toDate);
				rs=ps.executeQuery();
				while(rs.next()) {
					String x=rs.getString("PatientID");
					String y=rs.getString("PatientName");
					ar.add(new NameAndId(y,x));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
					ps.close();
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else {
			try {
				String sql="SELECT * FROM Patient_Credentials WHERE Dt BETWEEN ? AND ?";
				ps=c.prepareStatement(sql);
				ps.setString(1, frmDate);
				ps.setString(2, toDate);
				rs=ps.executeQuery();
				while(rs.next()) {
					String x=rs.getString("PatientID");
					String y=rs.getString("PatientName");
					ar.add(new NameAndId(y,x));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
					ps.close();
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
		}
		return ar;
	}
	
	
	
	public static int getDocTestSerial() {
		int n=0;
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			String sql="SELECT * FROM Doc_Test";
			ps=c.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()==false) {
				n=0;
			}
			else {
				do {
					n=rs.getInt("Serial");
				}while(rs.next());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		n=n+1;
		
		return n;
	}
	
	public static void addDocTest(String doc,String test) {
		int n=getDocTestSerial();
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="INSERT INTO Doc_Test(Serial,Doc,Test) VALUES(?,?,?)";
			ps=c.prepareStatement(sql);
			ps.setInt(1, n);
			ps.setString(2, doc);
			ps.setString(3, test);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static String getDate() {
		String cur=getCurrentUser();
		Connection c=DbConnection.connect();
		String s="";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="SELECT Dt FROM Patient_Credentials WHERE PatientId=?";
			ps=c.prepareStatement(sql);
			ps.setString(1, cur);
			rs=ps.executeQuery();
			while(rs.next()) {
				s=rs.getString("Dt");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				rs.close();
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}
	
	
	public static String getTestSubject(String test) {
		String result="";
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="SELECT test_subject FROM Tests WHERE test_name=?";
			ps=c.prepareStatement(sql);
			ps.setString(1, test);
			rs=ps.executeQuery();
			while(rs.next()) {
				result=rs.getString("test_subject");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	public static TestFormat getTestFormat(String testName){
		Connection c=DbConnection.connect();
		TestFormat t=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String sql="SELECT * FROM Tests WHERE test_name=?";
			ps=c.prepareStatement(sql);
			ps.setString(1,testName);
			rs=ps.executeQuery();
			while(rs.next()) {
				String lb=rs.getString("lower_bound");
				String ub=rs.getString("upper_bound");
				String unit=rs.getString("unit");
				t=new TestFormat(testName,lb,ub,unit);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public static Patient getPatient(String id) {
		Patient a=null;
		Connection c=DbConnection.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="SELECT * FROM Patient_Credentials WHERE PatientId=?";
			ps=c.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				a=new Patient(id,rs.getString("PatientName"),rs.getString("Years"),rs.getString("Months"),rs.getString("Days"),rs.getString("Gender"),rs.getString("RefferedBy"),rs.getString("SampleBy"),rs.getString("SampleTime"),rs.getString("PanelID"),rs.getString("PanelCode"),rs.getString("Email"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				ps.close();
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return a;
	}
	
}
