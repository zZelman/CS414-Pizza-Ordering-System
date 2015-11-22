package main;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;
import java.rmi.*;

public class Playground extends JPanel {

    private Image backgroundImage;
    
    public Playground(URI fileName) throws IOException {
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
        SystemAccess system = (SystemAccess) Naming.lookup("//localhost/server");
        
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
        
        f.setBounds(100, 100, 701, 504);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.getContentPane().setLayout(null);
        
        final DefaultListModel model = new DefaultListModel();
        final JList menu = new JList(model);
        menu.setBounds(10, 100, 277, 258);
        f.getContentPane().add(menu);
        
        final DefaultListModel model2 = new DefaultListModel();
        final JList order = new JList(model2);
        order.setBounds(334, 100, 340, 258);
        f.getContentPane().add(order);
        
        final JTextField txtMenu = new JTextField();
        txtMenu.setEditable(false);
        txtMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMenu.setText("Menu");
        txtMenu.setBounds(10, 69, 42, 20);
        f.getContentPane().add(txtMenu);
        txtMenu.setColumns(10);
        
        final JTextField txtYourOrder = new JTextField();
        txtYourOrder.setEditable(false);
        txtYourOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtYourOrder.setText("Your Order");
        txtYourOrder.setBounds(334, 69, 86, 20);
        f.getContentPane().add(txtYourOrder);
        txtYourOrder.setColumns(10);
        
        final JTextField textField = new JTextField();
        textField.setBounds(10, 431, 315, 20);
        f.getContentPane().add(textField);
        textField.setColumns(10);
        
        final JTextField txtYourPaymentInformation = new JTextField();
        txtYourPaymentInformation.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtYourPaymentInformation.setText("Payment Amount");
        txtYourPaymentInformation.setBounds(10, 403, 108, 20);
        f.getContentPane().add(txtYourPaymentInformation);
        txtYourPaymentInformation.setColumns(10);
        
        final JTextField textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setBounds(143, 404, 182, 20);
        f.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        
        final JTextField CustID = new JTextField();
        CustID.setBounds(10, 37, 264, 20);
        f.getContentPane().add(CustID);
        CustID.setColumns(10);
        
        final JTextField CustIdTitle = new JTextField();
        CustIdTitle.setText("Input Customer ID");
        CustIdTitle.setEditable(false);
        CustIdTitle.setBounds(10, 11, 264, 20);
        f.getContentPane().add(CustIdTitle);
        CustIdTitle.setColumns(10);
        
        final JTextField address = new JTextField();
        address.setBounds(334, 404, 244, 20);
        f.getContentPane().add(address);
        address.setColumns(10);
        
        
        final JTextField txtAddress = new JTextField();
        txtAddress.setEditable(false);
        txtAddress.setText("Address");
        txtAddress.setBounds(334, 383, 86, 20);
        f.getContentPane().add(txtAddress);
        txtAddress.setColumns(10);
        
        
        
        
        
        buildMenu(system, model);
        
        //START A TRANSACTION
        JButton btnNewButton_1 = new JButton("Start a Transaction");
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
        btnNewButton_1.setBounds(284, 11, 390, 46);
        f.getContentPane().add(btnNewButton_1);
        
        //ADD TO ORDER
        JButton btnAddToOrder = new JButton("Add to Order");
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
        btnAddToOrder.setBounds(10, 369, 122, 23);
        f.getContentPane().add(btnAddToOrder);
        
        //REMOVE FROM ORDER
        JButton btnRemoveFromOrder = new JButton("Remove from Order");
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
        btnRemoveFromOrder.setBounds(515, 370, 159, 23);
        f.getContentPane().add(btnRemoveFromOrder);
        
        final JCheckBox box = new JCheckBox("Delivery?");
        
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
        box.setBounds(588, 403, 86, 23);
        f.getContentPane().add(box);
        
        //CONFIRM ORDER AND PAYMENT
        JButton btnNewButton = new JButton("Confirm Order and Payment");
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
        btnNewButton.setBounds(334, 430, 340, 23);
        f.getContentPane().add(btnNewButton);
        
        URI img = ClassLoader.getSystemClassLoader().getResource("./sample.png").toURI();
        f.getContentPane().add(new Playground(img));
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
