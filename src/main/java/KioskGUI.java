package cs414a4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class KioskGUI {

	private JFrame frmKiosk;
	private JTextField txtMenu;
	private JTextField txtYourOrder;
	private JTextField textField;
	private JTextField txtYourPaymentInformation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KioskGUI window = new KioskGUI();
					window.frmKiosk.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KioskGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKiosk = new JFrame();
		frmKiosk.setTitle("Kiosk");
		frmKiosk.setBounds(100, 100, 700, 500);
		frmKiosk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKiosk.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 44, 277, 301);
		frmKiosk.getContentPane().add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(334, 44, 340, 301);
		frmKiosk.getContentPane().add(list_1);
		
		txtMenu = new JTextField();
		txtMenu.setEditable(false);
		txtMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMenu.setText("Menu");
		txtMenu.setBounds(10, 11, 42, 20);
		frmKiosk.getContentPane().add(txtMenu);
		txtMenu.setColumns(10);
		
		txtYourOrder = new JTextField();
		txtYourOrder.setEditable(false);
		txtYourOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtYourOrder.setText("Your Order");
		txtYourOrder.setBounds(334, 11, 86, 20);
		frmKiosk.getContentPane().add(txtYourOrder);
		txtYourOrder.setColumns(10);
		
		JButton btnAddToOrder = new JButton("Add to Order");
		btnAddToOrder.setBounds(10, 356, 122, 23);
		frmKiosk.getContentPane().add(btnAddToOrder);
		
		JButton btnRemoveFromOrder = new JButton("Remove from Order");
		btnRemoveFromOrder.setBounds(334, 356, 159, 23);
		frmKiosk.getContentPane().add(btnRemoveFromOrder);
		
		textField = new JTextField();
		textField.setBounds(10, 431, 315, 20);
		frmKiosk.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirm Order and Payment");
		btnNewButton.setBounds(334, 430, 340, 23);
		frmKiosk.getContentPane().add(btnNewButton);
		
		txtYourPaymentInformation = new JTextField();
		txtYourPaymentInformation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtYourPaymentInformation.setText("Your Payment Information");
		txtYourPaymentInformation.setBounds(10, 403, 159, 20);
		frmKiosk.getContentPane().add(txtYourPaymentInformation);
		txtYourPaymentInformation.setColumns(10);
	}
}
