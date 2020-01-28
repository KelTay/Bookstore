/**
 * Project: A00954556_assignment2
 * File: MainFrame.java
 * Date: Nov 13, 2019
 * Time: 8:41:09 AM
 */
package a00954556_kelvin_tay.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Kelvin Tay, A00954556
 *
 */
public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmDrop = new JMenuItem("Drop");
		mnFile.add(mntmDrop);

		JMenuItem mntmQuite = new JMenuItem("Quit");
		mnFile.add(mntmQuite);

		JMenu mnBooks = new JMenu("Books");
		menuBar.add(mnBooks);

		JMenuItem mntmCount = new JMenuItem("Count");
		mnBooks.add(mntmCount);

		JCheckBoxMenuItem chckbxmntmByAuthor = new JCheckBoxMenuItem("By Author");
		mnBooks.add(chckbxmntmByAuthor);

		JCheckBoxMenuItem chckbxmntmDescending = new JCheckBoxMenuItem("Descending");
		mnBooks.add(chckbxmntmDescending);

		JMenuItem mntmList = new JMenuItem("List");
		mnBooks.add(mntmList);

		JMenu mnCustomers = new JMenu("Customers");
		menuBar.add(mnCustomers);

		JMenuItem mntmCount_1 = new JMenuItem("Count");
		mnCustomers.add(mntmCount_1);

		JCheckBoxMenuItem chckbxmntmByJoinedDate = new JCheckBoxMenuItem("By Join Date");
		mnCustomers.add(chckbxmntmByJoinedDate);

		JMenuItem mntmList_1 = new JMenuItem("List");
		mnCustomers.add(mntmList_1);

		JMenu mnPurchases = new JMenu("Purchases");
		menuBar.add(mnPurchases);

		JMenuItem mntmTotal = new JMenuItem("Total");
		mnPurchases.add(mntmTotal);

		JCheckBoxMenuItem chckbxmntmByLastName = new JCheckBoxMenuItem("By Last Name");
		mnPurchases.add(chckbxmntmByLastName);

		JCheckBoxMenuItem chckbxmntmByTitle = new JCheckBoxMenuItem("By Title");
		mnPurchases.add(chckbxmntmByTitle);

		JCheckBoxMenuItem chckbxmntmDescending_1 = new JCheckBoxMenuItem("Descending");
		mnPurchases.add(chckbxmntmDescending_1);

		JMenuItem mntmFilterByCustomer = new JMenuItem("Filter by Customer ID");
		mnPurchases.add(mntmFilterByCustomer);

		JMenuItem mntmList_2 = new JMenuItem("List");
		mnPurchases.add(mntmList_2);

		JMenu mnAbout = new JMenu("Help");
		menuBar.add(mnAbout);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnAbout.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
