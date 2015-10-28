package cs414a4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ChefGUI {

	private JFrame frmOrdersToCook;
	private JTextField txtOrders;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChefGUI window = new ChefGUI();
					window.frmOrdersToCook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChefGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOrdersToCook = new JFrame();
		frmOrdersToCook.setTitle("Orders to Cook");
		frmOrdersToCook.setBounds(100, 100, 700, 500);
		frmOrdersToCook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOrdersToCook.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 42, 664, 359);
		frmOrdersToCook.getContentPane().add(textArea);
		
		txtOrders = new JTextField();
		txtOrders.setEditable(false);
		txtOrders.setText("Orders");
		txtOrders.setBounds(10, 11, 49, 20);
		frmOrdersToCook.getContentPane().add(txtOrders);
		txtOrders.setColumns(10);
		
		JButton btnCompleteOrder = new JButton("Complete Order");
		btnCompleteOrder.setBounds(10, 416, 664, 35);
		frmOrdersToCook.getContentPane().add(btnCompleteOrder);
	}

}
