package cs414.a5.mduelskeilh.gui;

import cs414.a5.mduelskeilh.main.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;
import java.rmi.*;

public class AndroidGUI extends JPanel {

    private Image backgroundImage;
    
    public AndroidGUI(URI fileName) throws IOException {
        backgroundImage = ImageIO.read(new File(fileName));
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(this.backgroundImage, 0, 0, this);
    }
    
    public static void main(String args[]) throws Exception {
        JFrame f = new JFrame();
        init(f);
        f.setVisible(true);
    }
    
    public static void init(JFrame f) throws Exception {
        f.setTitle("Android Kiosk");
        final SystemAccess system = (SystemAccess) Naming.lookup("//localhost/server");

	int x = 2000;
	int y = 1700;
	int w = 0;
	int h = 0;

	int x1 = 20;
	int y1 = 170;
	int w1 = 0;
	int h1 = 0;
        
        // final JTextField txtMenu;
        // final JTextField txtYourOrder;
        // final JTextField textField;
        // final JTextField txtYourPaymentInformation;
        // final DefaultListModel model;
        // final DefaultListModel model2;
        
        // final JList menu;
        // final JList order;
        // final JTextField textField_1;
        // final JTextField CustID;
        // final JTextField CustIdTitle;
        // final JTextField address;
        // final JTextField txtAddress;

	int framWidth = 409;
	int framHight = 804;
        
        f.setBounds(100, 100, framWidth, framHight);
	f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.getContentPane().setLayout(null);
        
        final DefaultListModel model = new DefaultListModel();
        final JList menu = new JList(model);
        menu.setBounds(10+x1, 100+y1, 160, 258);
        f.getContentPane().add(menu);
        
        final DefaultListModel model2 = new DefaultListModel();
        final JList order = new JList(model2);
        order.setBounds(334+x1-150, 100+y1, 168, 258);
        f.getContentPane().add(order);
        
        final JTextField txtMenu = new JTextField();
        txtMenu.setEditable(false);
        txtMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMenu.setText(" Menu");
        txtMenu.setBounds(10+x1, 69+y1, 45, 20);
        f.getContentPane().add(txtMenu);
        txtMenu.setColumns(10);
        
        final JTextField txtYourOrder = new JTextField();
        txtYourOrder.setEditable(false);
        txtYourOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtYourOrder.setText("  Your Order");
        txtYourOrder.setBounds(334+x1-150, 69+y1, 86, 20);
        f.getContentPane().add(txtYourOrder);
        txtYourOrder.setColumns(10);
        
        final JTextField textField = new JTextField();
        textField.setBounds(10+x1, 431+y1+20, 162, 20);
        f.getContentPane().add(textField);
        textField.setColumns(10);
        
        final JTextField txtYourPaymentInformation = new JTextField();
        txtYourPaymentInformation.setFont(new Font("Helvetica", Font.PLAIN, 13));
        txtYourPaymentInformation.setText("       Payment Amount");
        txtYourPaymentInformation.setBounds(11+x1, 403+y1+20-25, 160, 20);
        f.getContentPane().add(txtYourPaymentInformation);
        txtYourPaymentInformation.setColumns(10);
        
        final JTextField textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setBounds(11+x1, 404+y1+20, 160, 20);
        f.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        
        final JTextField CustID = new JTextField();
        CustID.setBounds(10+x1, 37+y1, 60, 20);
        f.getContentPane().add(CustID);
        CustID.setColumns(10);
        
        final JTextField CustIdTitle = new JTextField();
        CustIdTitle.setText("  Input ID");
        CustIdTitle.setEditable(false);
        CustIdTitle.setBounds(10+x1, 11+y1, 60, 20);
        f.getContentPane().add(CustIdTitle);
        CustIdTitle.setColumns(10);
        
        final JTextField address = new JTextField();
        address.setBounds(334+x1-150, 404+y1+20, 244-74, 20);
        f.getContentPane().add(address);
        address.setColumns(10);
        
        
        final JTextField txtAddress = new JTextField();
        txtAddress.setEditable(false);
        txtAddress.setText("      Address");
        txtAddress.setBounds(334+x1-150, 383+y1+15, 86, 20);
        f.getContentPane().add(txtAddress);
        txtAddress.setColumns(10);
        
        
        
        
        
        buildMenu(system, model);
        
        //START A TRANSACTION
        final JButton btnNewButton_1 = new JButton("Start a Transaction");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //----------------------------------------------------------------------------------------
                    // TODO get customer ID and insert it into beginSale();
                    String id = CustID.getText();
                    system.beginSale(id);
                    //----------------------------------------------------------------------------------------
                } catch (Exception q) {
                }
                //TODO: make work with new server
                //this is where the new commands go
            }
        });
        btnNewButton_1.setBounds(284 + x1 - 200, 11 + y1, 390 - 115, 46);
        btnNewButton_1.setBackground(Color.lightGray);
        btnNewButton_1.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
        btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 13));
        f.getContentPane().add(btnNewButton_1);
        
        //ADD TO ORDER
        final JButton btnAddToOrder = new JButton("Add to Order");
        btnAddToOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    system.addItemToSale((String) menu.getSelectedValue());
                } catch (Exception q) {}
                buildOrder(system, model2, textField_1);
                //TODO: make work with new server
                //this is where the new commands go
            }
        });
        btnAddToOrder.setBounds(10 + x1, 369 + y1 - 5, 160, 28);
        btnAddToOrder.setBackground(Color.lightGray);
        btnAddToOrder.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
        btnAddToOrder.setFont(new Font("Arial", Font.BOLD, 13));
        f.getContentPane().add(btnAddToOrder);
        
        //REMOVE FROM ORDER
        final JButton btnRemoveFromOrder = new JButton("Remove from Order");
        btnRemoveFromOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = (String) order.getSelectedValue();
                    boolean b = system.removeItemFromSale((String) order.getSelectedValue());
                    System.out.println("[REMOVE FROM ORDER] " + s + " : " + b);
                    system.removeItemFromSale((String) order.getSelectedValue());
                    buildOrder(system, model2, textField_1);
                } catch (Exception q) {}
                //TODO: make work with new server
                //this is where the new commands go
            }
        });
        btnRemoveFromOrder.setBounds(515 + x1 - 332, 370 + y1 - 5, 170, 28);
        btnRemoveFromOrder.setBackground(Color.lightGray);
        btnRemoveFromOrder.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
        btnRemoveFromOrder.setFont(new Font("Arial", Font.BOLD, 13));
        f.getContentPane().add(btnRemoveFromOrder);
        
        final JCheckBox box = new JCheckBox("To Go?");
        
        box.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                /*
                    try {
                    if (address.getText().length() > 1) {
                        system.setSaleIsDelvery(true);
                        system.setSaleAddress(address.getText());
                    }
                
                    } catch (Exception q) {
                    }
                */
            }
        });
        box.setBounds(588+x1-300-5, 403+y1-8+3, 70, 20);
        f.getContentPane().add(box);
        
        //CONFIRM ORDER AND PAYMENT
        JButton btnNewButton = new JButton("Confirm and Pay");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (box.isSelected()) {
                        system.setSaleIsDelvery(true);
                        system.setSaleAddress(address.getText());
                    } else {
                        system.setSaleIsDelvery(false);
                    }
                    
                    
                    if (system.endSale(CustID.getText(), Double.parseDouble(textField.getText()))) {
                        model2.removeAllElements();
                        CustID.setText("");
                        address.setText("");
                        textField.setText("");
                        //TODO: make work with new server
                        //this is where the new commands go
                        // ALSO MAKE THIS CHECK THE DELIVERY AND CALL IT IF IT IS!
                        
                    }
                } catch (Exception q) {
                }
                
            }
        });
        btnNewButton.setBounds(334 + x1 - 150, 430 + y1 + 20, 340 - 170, 23);
        btnNewButton.setBackground(Color.lightGray);
        btnNewButton.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 13));
        f.getContentPane().add(btnNewButton);
        
        URI img = ClassLoader.getSystemClassLoader().getResource("sample.png").toURI();
        f.getContentPane().add(new AndroidGUI(img));
    }
    
    public static void buildOrder(SystemAccess system, DefaultListModel model2, JTextField textField_1) {
        if (model2.getSize() > 0) {
            model2.removeAllElements();
        }
        try {
            ArrayList<String> names = system.getSaleItemNames();
            for (String s : names) {
                model2.addElement(s);
            }
            textField_1.setText("Sale Cost : " + system.getSaleTotal());
        } catch (Exception q) {}
    }
    
    public static void buildMenu(SystemAccess system, DefaultListModel model) {
        try {
            ArrayList<String> m = system.getMenuNames();
            ArrayList<String> i = system.getMenuItems(m.get(0));
            String Special = system.getMenuSpecial(m.get(0));
            if (Special != null) {
                model.addElement("Today's Special is : " + Special);
            }
            for (String t : i) {
                model.addElement(t);
            }
        } catch (Exception q) {}
    }
}
