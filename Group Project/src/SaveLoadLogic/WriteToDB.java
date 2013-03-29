package SaveLoadLogic;

import java.sql.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WriteToDB {

	private static Connection connection = null;
	private static ResultSet resultSet = null;
	private static Statement statement = null;
	String values;
	

	public void WriteDB(DefaultTableModel tableModel, JTable table) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager
					.getConnection("jdbc:sqlite:DonationDb.sqlite");
			statement = connection.createStatement();
			for (int i = 0; i < tableModel.getRowCount(); ++i) {
					String donor = tableModel.getValueAt(i, 0).toString();
					String charname = tableModel.getValueAt(i, 1).toString();
					String charType = tableModel.getValueAt(i, 2).toString();
					String amt = tableModel.getValueAt(i, 3).toString();
					
					values = "VALUES('"+donor
					+"','"+charname
					+"','"+ charType
					+"','"+ amt
					+"') ";
					
					String completeStmt =("INSERT INTO 'main'.'donationtable' ('DonorName', 'CharityName', 'CharityType', 'Amount')"
							+ values);
					statement.executeUpdate(completeStmt);
					
				}
						
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}

