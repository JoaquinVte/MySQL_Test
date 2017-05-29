package mysql_test;

import java.awt.EventQueue;

public class MySQL_Test {
	public static void main(String[] args) {

		MySQL db = new MySQL();
		try {
			db.MySQLConnection("jdbc_user", "1111", "jdbc_schema");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		Window frame = new Window(db);
		frame.setVisible(true);
	}
}
