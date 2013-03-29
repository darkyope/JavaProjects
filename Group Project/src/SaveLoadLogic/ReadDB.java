package SaveLoadLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;

import javax.swing.table.DefaultTableModel;

public class ReadDB {

	private static Connection connection = null;
	private static ResultSet resultSet = null;
	private static Statement statement = null;
	static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	public static void readFromDb(DefaultTableModel tableModel) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager
					.getConnection("jdbc:sqlite:DonationDb.sqlite");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM 'main'.'donationtable'");
			while (resultSet.next()) {
				Object[] rowfields = { resultSet.getString("DonorName"),
						resultSet.getString("CharityName"),
						resultSet.getString("CharityType"),
						resultSet.getString("Amount") };

				tableModel.addRow(rowfields);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}

//'DonorName', 'CharityName', 'CharityType', 'Amount' 