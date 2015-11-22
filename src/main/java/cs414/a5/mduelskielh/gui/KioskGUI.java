package cs414.a5.mduelskeilh.gui;

import cs414.a5.mduelskeilh.main.*;

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
import javax.swing.JCheckBox;

import java.rmi.Naming;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class KioskGUI {

    public JFrame frmKiosk;
    private JTextField txtMenu;
    private JTextField txtYourOrder;
    private JTextField textField;
    private JTextField txtYourPaymentInformation;
    private SystemAccess system;
    DefaultListModel model;
    DefaultListModel model2;

    JList menu;
    JList order;
    private JTextField textField_1;
    private JTextField CustID;
    private JTextField CustIdTitle;
    private JTextField address;
    private JTextField txtAddress;

    /**
     Launch the application.
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
     Create the application.
     */
    public KioskGUI() throws Exception {
        this.system = (SystemAccess) Naming.lookup("//129.82.45.209/server");
        initialize();
    }

    public void buildOrder() {
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

    public void buildMenu() {
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

    /**
     Initialize the contents of the frame.
     */
    private void initialize() {
        frmKiosk = new JFrame();
        frmKiosk.setTitle("Kiosk");
        frmKiosk.setBounds(100, 100, 701, 504);
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

        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setBounds(143, 404, 182, 20);
        frmKiosk.getContentPane().add(textField_1);
        textField_1.setColumns(10);


        CustID = new JTextField();
        CustID.setBounds(10, 37, 264, 20);
        frmKiosk.getContentPane().add(CustID);
        CustID.setColumns(10);

        CustIdTitle = new JTextField();
        CustIdTitle.setText("Input Customer ID");
        CustIdTitle.setEditable(false);
        CustIdTitle.setBounds(10, 11, 264, 20);
        frmKiosk.getContentPane().add(CustIdTitle);
        CustIdTitle.setColumns(10);

        address = new JTextField();
        address.setBounds(334, 404, 244, 20);
        frmKiosk.getContentPane().add(address);
        address.setColumns(10);


        txtAddress = new JTextField();
        txtAddress.setEditable(false);
        txtAddress.setText("Address");
        txtAddress.setBounds(334, 383, 86, 20);
        frmKiosk.getContentPane().add(txtAddress);
        txtAddress.setColumns(10);





        buildMenu();

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
        frmKiosk.getContentPane().add(btnNewButton_1);

        //ADD TO ORDER
        JButton btnAddToOrder = new JButton("Add to Order");
        btnAddToOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    system.addItemToSale((String) menu.getSelectedValue());
                } catch (Exception q) {}
                buildOrder();
                //TODO: make work with new server
                //this is where the new commands go
            }
        });
        btnAddToOrder.setBounds(10, 369, 122, 23);
        frmKiosk.getContentPane().add(btnAddToOrder);

        //REMOVE FROM ORDER
        JButton btnRemoveFromOrder = new JButton("Remove from Order");
        btnRemoveFromOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = (String) order.getSelectedValue();
                    boolean b = system.removeItemFromSale((String) order.getSelectedValue());
                    System.out.println("[REMOVE FROM ORDER] " + s + " : " + b);
                    system.removeItemFromSale((String) order.getSelectedValue());
                    buildOrder();
                } catch (Exception q) {}
                //TODO: make work with new server
                //this is where the new commands go
            }
        });
        btnRemoveFromOrder.setBounds(515, 370, 159, 23);
        frmKiosk.getContentPane().add(btnRemoveFromOrder);

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
        frmKiosk.getContentPane().add(box);

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
        frmKiosk.getContentPane().add(btnNewButton);





    }
}
