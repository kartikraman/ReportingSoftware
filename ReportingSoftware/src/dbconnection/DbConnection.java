package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection connect() {
		Connection c=null;
		try {
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:RSdb.db");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e+"");
		}
		return c;
	}
}
