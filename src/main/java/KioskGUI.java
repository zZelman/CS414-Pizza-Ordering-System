import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class KioskGUI {

    private JFrame frmKiosk;
    private JTextField txtMenu;
    private JTextField txtYourOrder;
    private JTextField textField;
    private JTextField txtYourPaymentInformation;
    private PizzaSystem system;
    DefaultListModel model;
    DefaultListModel model2;
    
    JList menu;
    JList order;
    
    /**
        Launch the application.
    */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PizzaSystem ps = new PizzaSystem();
                    KioskGUI window = new KioskGUI(ps);
                    window.frmKiosk.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
        Create the application.
    */
    public KioskGUI(PizzaSystem system) {
        this.system = system;
        initialize();
    }
    
    public void buildOrder() {
        if (model2.getSize() > 0) {
            model2.removeAllElements();
        }
        ArrayList<String> names = system.getSaleItemNames();
        for (String s : names) {
            model2.addElement(s);
        }
    }
    
    public void buildMenu() {
        ArrayList<String> m = system.getMenuNames();
        ArrayList<String> i = system.getMenuItems(m.get(0));
        for (String t : i) {
            model.addElement(t);
        }
    }
    
    /**
        Initialize the contents of the frame.
    */
    private void initialize() {
        frmKiosk = new JFrame();
        frmKiosk.setTitle("Kiosk");
        frmKiosk.setBounds(100, 100, 700, 500);
        frmKiosk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmKiosk.getContentPane().setLayout(null);
        
        model = new DefaultListModel();
        menu = new JList(model);
        menu.setBounds(10, 100, 277, 258);
        frmKiosk.getContentPane().add(menu);
        
        model2 = new DefaultListModel();
        order = new JList(model2);
        order.setBounds(334, 100, 340, 258);
        frmKiosk.getContentPane().add(order);
        
        txtMenu = new JTextField();
        txtMenu.setEditable(false);
        txtMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMenu.setText("Menu");
        txtMenu.setBounds(10, 69, 42, 20);
        frmKiosk.getContentPane().add(txtMenu);
        txtMenu.setColumns(10);
        
        txtYourOrder = new JTextField();
        txtYourOrder.setEditable(false);
        txtYourOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtYourOrder.setText("Your Order");
        txtYourOrder.setBounds(334, 69, 86, 20);
        frmKiosk.getContentPane().add(txtYourOrder);
        txtYourOrder.setColumns(10);
        
        textField = new JTextField();
        textField.setBounds(10, 431, 315, 20);
        frmKiosk.getContentPane().add(textField);
        textField.setColumns(10);
        
        txtYourPaymentInformation = new JTextField();
        txtYourPaymentInformation.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtYourPaymentInformation.setText("Payment Amount");
        txtYourPaymentInformation.setBounds(10, 403, 108, 20);
        frmKiosk.getContentPane().add(txtYourPaymentInformation);
        txtYourPaymentInformation.setColumns(10);
        
        buildMenu();
        
        //START A TRANSACTION
        JButton btnNewButton_1 = new JButton("Start a Transaction");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                system.beginSale();
            }
        });
        btnNewButton_1.setBounds(10, 11, 664, 46);
        frmKiosk.getContentPane().add(btnNewButton_1);
        
        //ADD TO ORDER
        JButton btnAddToOrder = new JButton("Add to Order");
        btnAddToOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                system.addItemToSale((String) menu.getSelectedValue());
                buildOrder();
            }
        });
        btnAddToOrder.setBounds(10, 369, 122, 23);
        frmKiosk.getContentPane().add(btnAddToOrder);
        
        //REMOVE FROM ORDER
        JButton btnRemoveFromOrder = new JButton("Remove from Order");
        btnRemoveFromOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                system.removeItemFromSale((String) menu.getSelectedValue());
                buildOrder();
            }
        });
        btnRemoveFromOrder.setBounds(334, 369, 159, 23);
        frmKiosk.getContentPane().add(btnRemoveFromOrder);
        
        //CONFIRM ORDER AND PAYMENT
        JButton btnNewButton = new JButton("Confirm Order and Payment");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (system.endSale(Double.parseDouble(textField.getText()))) {
                    model2.removeAllElements();
                }
                
            }
        });
        btnNewButton.setBounds(334, 430, 340, 23);
        frmKiosk.getContentPane().add(btnNewButton);
        
    }
}
