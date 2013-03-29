package SaveLoadLogic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WriteData {

	public void saveFile(DefaultTableModel tableModel, JTable table) {
		String file = ("src/contents.txt");
		if (table != null && file != null) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(new BufferedWriter(new FileWriter(
						file, true)));
				for (int i = 0; i < tableModel.getRowCount(); ++i) {
					for (int j = 0; j < tableModel.getColumnCount(); ++j) {
						writer.print(tableModel.getValueAt(i, j).toString() + "  ");
					}

					writer.println(", ");
				}
			} catch (IOException ioe) {
			} finally {
				if (writer != null)
					writer.close();
			}
		}
	}
	
}

