import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import AddressBookLogic.Person;
import AddressBookLogic.Validation;
/* Danny Roberts PRG 421
 	Address book program.*/
public class AddressBook {

	private JButton btnCreateEntry;
	private JFrame frmAddressBook;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtPhoneNumber;
	private JTextField txtAge;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	ArrayList<Person> list = new ArrayList<Person>();
	ArrayList<String> componentsList = new ArrayList<String>();
	Object[] object;
	Person p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressBook window = new AddressBook();
					window.frmAddressBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddressBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddressBook = new JFrame();
		frmAddressBook.setTitle("Address Book");
		frmAddressBook.setBounds(100, 100, 469, 414);
		frmAddressBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddressBook.getContentPane().setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 11, 70, 14);
		frmAddressBook.getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(233, 11, 70, 14);
		frmAddressBook.getContentPane().add(lblLastName);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(88, 8, 116, 20);
		frmAddressBook.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(313, 8, 116, 20);
		frmAddressBook.getContentPane().add(txtLastName);
		txtLastName.setColumns(10);

		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(10, 58, 70, 14);
		frmAddressBook.getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(88, 55, 116, 20);
		frmAddressBook.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(233, 58, 70, 14);
		frmAddressBook.getContentPane().add(lblPhoneNumber);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(313, 55, 116, 20);
		frmAddressBook.getContentPane().add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(10, 104, 46, 14);
		frmAddressBook.getContentPane().add(lblAge);

		txtAge = new JTextField();
		txtAge.setBounds(88, 101, 116, 20);
		frmAddressBook.getContentPane().add(txtAge);
		txtAge.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 433, 164);
		frmAddressBook.getContentPane().add(scrollPane);

		table = new JTable(model);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		model.addColumn("First Name");
		model.addColumn("Last Name");
		model.addColumn("Age");
		model.addColumn("E-mail");
		model.addColumn("Phone Number");
		
		/////// Save File Button ///////
		JButton btnSaveEntries = new JButton("Save Entries");
		btnSaveEntries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveData();
				model.setRowCount(0);
			}
		});
		btnSaveEntries.setBounds(10, 342, 109, 23);
		frmAddressBook.getContentPane().add(btnSaveEntries);

		/////// Open File Button ///////
		JButton btnOpen = new JButton("Open Entries");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getData();

			}
		});
		btnOpen.setBounds(129, 342, 103, 23);
		frmAddressBook.getContentPane().add(btnOpen);

		/////// Exit Button ///////
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(350, 342, 93, 23);
		frmAddressBook.getContentPane().add(btnExit);
		
		/////// Create Entry Button ///////
		btnCreateEntry = new JButton("Create Entry");
		btnCreateEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Validation check = new Validation();
				if (check.isValidEmailAddress(txtEmail.getText())) {
					if (check.isValidPhoneNumber(txtPhoneNumber.getText())) {
						if (check.isValidAge(txtAge.getText())) {
							int age = Integer.parseInt(txtAge.getText());
							if (age > 1 && age < 120) {
								memory();
								model.addRow(object);
								clear();
							} else {JOptionPane.showMessageDialog(null,"Age must be less than 120");}
						} else {JOptionPane.showMessageDialog(null,"Age must be a number");}
					} else {
						JOptionPane.showMessageDialog(null,"Not a valid phone number");}
				} else {JOptionPane.showMessageDialog(null, "Not a valid email");}
			}
		});
		btnCreateEntry.setBounds(283, 100, 116, 23);
		frmAddressBook.getContentPane().add(btnCreateEntry);
		
		JButton btnClearTable = new JButton("Clear Table");
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
			}
		});
		btnClearTable.setBounds(247, 342, 93, 23);
		frmAddressBook.getContentPane().add(btnClearTable);

	}

	// /////////////// METHODS //////////////////////////

	// Method to add entries into array list
	public void memory() {
		int age;
		p = new Person(txtFirstName.getText(), txtLastName.getText(),
				age = Integer.parseInt(txtAge.getText()), txtEmail.getText(),
				txtPhoneNumber.getText()) {
		};
		list.add(p);
		object = new Object[] { p.getFirstName(), p.getLastName(), p.getAge(),
				p.getEmail(), p.getPhoneNumber() };
	}

	// Method for creating and saving to a file from the table
	public void saveData() {
		String file = ("src/contents.txt");
		if (table != null && file != null) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(new BufferedWriter(new FileWriter(
						file, true)));
				for (int i = 0; i < model.getRowCount(); ++i) {
					for (int j = 0; j < model.getColumnCount(); ++j) {
						writer.print(model.getValueAt(i, j).toString() + " ");
					}

					writer.println("");
				}
			} catch (IOException ioe) {
			} finally {
				if (writer != null)
					writer.close();
			}
		}
	}

	// Method for getting entries from file and adding them to table.
	public void getData() {
		BufferedReader br = null;
		ArrayList contactList = new ArrayList();
		try {
			br = new BufferedReader(new FileReader("src/contents.txt"));
			String line = br.readLine();
			model.setRowCount(0);
			while (line != null) {

				String[] rowfields = line.split("[ ]+");
				contactList.add(rowfields);
				line = br.readLine();
				model.addRow(rowfields);
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"File not found. You may have deleted it.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to empty text fields.
	public void clear() {
		txtFirstName.setText("");
		txtLastName.setText("");
		txtAge.setText("");
		txtEmail.setText("");
		txtPhoneNumber.setText("");
		txtFirstName.requestFocus();
	}

}
