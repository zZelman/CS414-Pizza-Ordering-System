package cs414a4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ManagerGui {

	private JFrame frmManagerGui;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGui window = new ManagerGui();
					window.frmManagerGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManagerGui = new JFrame();
		frmManagerGui.setTitle("Manager Gui");
		frmManagerGui.setBounds(100, 100, 700, 500);
		frmManagerGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManagerGui.getContentPane().setLayout(null);
		
		JButton btnCreateItem = new JButton("Create Item");
		btnCreateItem.setBounds(10, 60, 148, 23);
		frmManagerGui.getContentPane().add(btnCreateItem);
		
		JTextPane txtpnListOfExisting = new JTextPane();
		txtpnListOfExisting.setEditable(false);
		txtpnListOfExisting.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnListOfExisting.setText("List of Existing Items");
		txtpnListOfExisting.setBounds(340, 25, 128, 24);
		frmManagerGui.getContentPane().add(txtpnListOfExisting);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(10, 140, 312, 286);
		frmManagerGui.getContentPane().add(textPane_1);
		
		JTextPane txtpnMenu = new JTextPane();
		txtpnMenu.setEditable(false);
		txtpnMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnMenu.setText("Menu");
		txtpnMenu.setBounds(10, 113, 37, 20);
		frmManagerGui.getContentPane().add(txtpnMenu);
		
		textField = new JTextField();
		textField.setBounds(10, 25, 312, 24);
		frmManagerGui.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAddItemTo = new JButton("Add to Menu");
		btnAddItemTo.setBounds(168, 60, 154, 23);
		frmManagerGui.getContentPane().add(btnAddItemTo);
		
		JButton btnSetSpecial = new JButton("Set Special");
		btnSetSpecial.setBounds(168, 89, 154, 23);
		frmManagerGui.getContentPane().add(btnSetSpecial);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(340, 59, 322, 367);
		frmManagerGui.getContentPane().add(textArea);
	}
}
