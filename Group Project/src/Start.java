import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Logic.CharityLogic;
import Logic.CharityType;
import Logic.ICharity;
import SaveLoadLogic.ReadDB;
import SaveLoadLogic.ReadData;
import SaveLoadLogic.WriteData;
import SaveLoadLogic.WriteToDB;


public class Start {

	private CharityLogic logic;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private JFrame frmDonationloc;
	private JTextField txtDonor;
	private JTextField txtAmount;
	private JTextField txtCharityName;
	private JTable table;
	private JComboBox<?> cmbCharityType;
	private Object[] charityArray;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frmDonationloc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logic = new CharityLogic();

		frmDonationloc = new JFrame();
		frmDonationloc.setResizable(false);
		frmDonationloc.setTitle("Donation Locker - Team A");
		frmDonationloc.setBounds(100, 100, 550, 310);
		frmDonationloc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDonationloc.getContentPane().setLayout(null);

		// ////////////Labels//////////////
		JLabel lblDoner = new JLabel("Donor Name:");
		lblDoner.setBounds(10, 17, 99, 14);
		frmDonationloc.getContentPane().add(lblDoner);

		JLabel lblAmountPledged = new JLabel("Amount Pledged:");
		lblAmountPledged.setBounds(204, 20, 99, 14);
		frmDonationloc.getContentPane().add(lblAmountPledged);

		JLabel lblCharityName = new JLabel("Charity Name:");
		lblCharityName.setBounds(10, 48, 99, 14);
		frmDonationloc.getContentPane().add(lblCharityName);

		// ////////////Text Fields//////////////
		txtDonor = new JTextField();
		txtDonor.setBounds(99, 17, 86, 20);
		frmDonationloc.getContentPane().add(txtDonor);
		txtDonor.setColumns(10);

		txtAmount = new JTextField();
		txtAmount.setBounds(315, 14, 86, 20);
		frmDonationloc.getContentPane().add(txtAmount);
		txtAmount.setColumns(10);

		txtCharityName = new JTextField();
		txtCharityName.setBounds(99, 48, 86, 20);
		frmDonationloc.getContentPane().add(txtCharityName);
		txtCharityName.setColumns(10);

		// ////////////Table//////////////
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 524, 158);
		frmDonationloc.getContentPane().add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		tableModel.addColumn("Donor Name");
		tableModel.addColumn("Charity Name");
		tableModel.addColumn("Charity Type");
		tableModel.addColumn("Amount Pledged");
		table.setFillsViewportHeight(true);

		// Button for adding data into the table.
		JButton btnAddData = new JButton("Add Data");
		btnAddData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Util.isDecimal(txtAmount.getText())) {
					tableData();
					Clear();
				} else {
					JOptionPane.showMessageDialog(null,
							"Please enter an amount to pledge.");
					txtAmount.requestFocus();
				}
			}
		});
		btnAddData.setBounds(435, 13, 99, 23);
		frmDonationloc.getContentPane().add(btnAddData);

		// Button for clearing the textfields
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnClearForm.setBounds(435, 44, 99, 23);
		frmDonationloc.getContentPane().add(btnClearForm);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 79, 524, 2);
		frmDonationloc.getContentPane().add(separator);

		JLabel lblCharityType = new JLabel("Charity Type:");
		lblCharityType.setBounds(204, 48, 99, 14);
		frmDonationloc.getContentPane().add(lblCharityType);

		// ////////////Charity type combo Box//////////////
		cmbCharityType = new JComboBox<Object>(CharityType.values());
		cmbCharityType.setBounds(290, 45, 111, 20);
		frmDonationloc.getContentPane().add(cmbCharityType);

		// ////////////Menu Bar//////////////
		JMenuBar menuBar = new JMenuBar();
		frmDonationloc.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		// Clear the Table
		JMenuItem mntmClearTable = new JMenuItem("Clear Table");
		mntmClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableModel.setRowCount(0);				
			}
		});
		mnFile.add(mntmClearTable);

		// Exit the Program
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnSave = new JMenu("Save");
		menuBar.add(mnSave);

		// Save data to a database.
		JMenuItem mntmSaveToDatabase = new JMenuItem("Save to Database");
		mntmSaveToDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WriteToDB db = new WriteToDB();
				db.WriteDB(tableModel, table);
			}
		});
		mnSave.add(mntmSaveToDatabase);
		

		// Save the data to a file.
		JMenuItem mntmSaveToFile = new JMenuItem("Save to File");
		mntmSaveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WriteData write = new WriteData();
				write.saveFile(tableModel, table);
			}
		});
		mnSave.add(mntmSaveToFile);

		JMenu mnLoad = new JMenu("Load");
		menuBar.add(mnLoad);

		// Load data from a database.
		JMenuItem mntmLoadFromDatabase = new JMenuItem("Load from Database");
		mntmLoadFromDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableModel.setRowCount(0);
				ReadDB.readFromDb(tableModel);

			}
		});
		mnLoad.add(mntmLoadFromDatabase);

		// Load data from a file.
		JMenuItem mntmLoadFromFile = new JMenuItem("Load from File");
		mntmLoadFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReadData read = new ReadData();
				read.getFile(tableModel);
			}
		});
		mnLoad.add(mntmLoadFromFile);
	}

	// Method to clear text fields and set focus.
	public void Clear() {

		txtDonor.setText("");
		txtAmount.setText("");
		txtCharityName.setText("");
		cmbCharityType.setSelectedItem(null);
		txtDonor.requestFocus();

	}

	// Method for displaying data to a table.
	public void tableData() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		String donorName = txtDonor.getText();
		String charityName = txtCharityName.getText();
		CharityType type = (CharityType) cmbCharityType.getSelectedItem();
		double amt = Double.parseDouble(txtAmount.getText());

		logic.addDonation(type, donorName, charityName, amt);
		tableModel.setRowCount(0);

		for (ICharity donation : logic.GetCharities()) {
			
			
			charityArray = (new Object[] { donation.getDonorName(),
					donation.getCharityName(),
					donation.getClass().getSimpleName(),
					formatter.format(donation.getAmount()) });
						
			tableModel.addRow(charityArray);
						
			
			
			
						
		}
		
		logic.removeDonation(donorName);
	}

	
	
	

}
