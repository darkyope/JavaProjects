package SaveLoadLogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReadData {

	// Method for getting entries from file and adding them to table.
		public void getFile(DefaultTableModel tableModel) {
			BufferedReader br = null;
			ArrayList contactList = new ArrayList();
			try {
				br = new BufferedReader(new FileReader("src/contents.txt"));
				String line = br.readLine();
				tableModel.setRowCount(0);
				while (line != null) {

					String[] rowfields = line.split("[  ]+ ");
					contactList.add(rowfields);
					line = br.readLine();
					tableModel.addRow(rowfields);
				}

			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,
						"File not found. You may have deleted it.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
