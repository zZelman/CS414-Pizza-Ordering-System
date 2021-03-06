package cs414.a5.mduelskeilh.gui;

import cs414.a5.mduelskeilh.main.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import java.rmi.Naming;

public class ManagerGui {

    public JFrame frmManagerGui;
    private JTextField textField;
    private SystemAccess system;
    JTextPane textPane_1;
    JTextArea existingItems;
    
    /**
        Launch the application.
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
        Create the application.
    */
    public ManagerGui() throws Exception {
        this.system = (SystemAccess) Naming.lookup("//129.82.45.209/server");
        initialize();
    }
    
    /**
        Initialize the contents of the frame.
    */
    
    private String AtoS(ArrayList<String> a) {
        String list = "";
        if (a != null) {
        
            for (String s : a) {
                list += s + "\n";
            }
            
        }
        return list;
    }
    
    public boolean sanity(String s, int c) {
        int x = 0;
        int index = 0;
        int index2 = 0;
        if (c == 1) {
            if (s.length() >= 3) {
                for (int i = 0; i < s.length(); ++i) {
                    if (s.charAt(i) == ',') {
                        x++;
                        index = i;
                    }
                }
                if ((x == 1) && (index != 0)) {
                    if (index != s.length() - 1) {
                        return true;
                    }
                }
            }
        } else if (c == 2) {
            if (s.length() >= 5) {
                for (int i = 0; i < s.length(); ++i) {
                    if (s.charAt(i) == ',') {
                        x++;
                        if (index == 0) {
                            index = i;
                        } else {
                            index2 = i;
                        }
                    }
                }
                if ((x == 2) && (index != 0)) {
                    if ((index2 != (index + 1)) && (index2 != s.length() - 1)) {
                        return true;
                    }
                }
                
                
            }
        }
        return false;
        
    }
    
    private void initialize() {
        frmManagerGui = new JFrame();
        frmManagerGui.setTitle("Manager Gui");
        frmManagerGui.setBounds(100, 100, 1115, 800);
        frmManagerGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmManagerGui.getContentPane().setLayout(null);
        
        JTextPane txtpnListOfExisting = new JTextPane();
        txtpnListOfExisting.setEditable(false);
        txtpnListOfExisting.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtpnListOfExisting.setText("List of Existing Items");
        txtpnListOfExisting.setBounds(563, 25, 160, 24);
        frmManagerGui.getContentPane().add(txtpnListOfExisting);
        
        final JTextPane textPane_1 = new JTextPane();
        textPane_1.setEditable(false);
        textPane_1.setBounds(28, 56, 512, 503);
        frmManagerGui.getContentPane().add(textPane_1);
        
        final JTextPane txtpnMenu = new JTextPane();
        txtpnMenu.setEditable(false);
        txtpnMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtpnMenu.setBounds(28, 25, 160, 20);
        frmManagerGui.getContentPane().add(txtpnMenu);
        
        textField = new JTextField();
        textField.setBounds(399, 570, 300, 24);
        frmManagerGui.getContentPane().add(textField);
        textField.setColumns(10);
        
        final JTextArea existingItems = new JTextArea();
        existingItems.setEditable(false);
        existingItems.setBounds(563, 59, 510, 500);
        frmManagerGui.getContentPane().add(existingItems);
        
        //CREATE ITEM
        JButton btnCreateItem = new JButton("Create Item");
        btnCreateItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String textFieldValue = textField.getText();
                if (sanity(textFieldValue, 2)) {
                    String[] s = textFieldValue.split(",");
                    try {
                        system.createItem(s[0], s[1], Double.parseDouble(s[2]));
                        existingItems.setText(AtoS(system.getItemNames()));
                    } catch (Exception e) {}
                }
            }
        });
        btnCreateItem.setBounds(773, 656, 300, 40);
        frmManagerGui.getContentPane().add(btnCreateItem);
        
        //DELETE ITEM
        JButton btnDeleteItem = new JButton("Delete Item");
        btnDeleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                if (textFieldValue.length() > 0) {
                    try {
                        system.deleteItem(textFieldValue);
                        existingItems.setText(AtoS(system.getItemNames()));
                    } catch (Exception q) {}
                    //TODO: make work with new server
                    //this is where the new commands go
                }
            }
        });
        btnDeleteItem.setBounds(399, 656, 300, 40);
        frmManagerGui.getContentPane().add(btnDeleteItem);
        
        //CREATE MENU
        JButton btnNewButton_1 = new JButton("Create Menu");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                if (sanity(textFieldValue, 1)) {
                    String[] s = textFieldValue.split(",");
                    try {
                        system.createMenu(s[0], s[1]);
                        txtpnMenu.setText((s[0]));
                    } catch (Exception q) {}
                    //TODO: make work with new server
                    //this is where the new commands go
                    //also make this update the name of the menu in the top left
                }
            }
        });
        btnNewButton_1.setBounds(28, 656, 300, 40);
        frmManagerGui.getContentPane().add(btnNewButton_1);
        
        //ADD TO MENU
        JButton btnAddItemTo = new JButton("Add to Menu");
        btnAddItemTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                if (sanity(textFieldValue, 1)) {
                    String[] s = textFieldValue.split(",");
                    try {
                        system.addItemToMenu(s[0], s[1]);
                        textPane_1.setText(AtoS(system.getMenuItems(s[1])));
                    } catch (Exception q) {}
                    //TODO: make work with new server
                    //this is where the new commands go
                }
            }
        });
        btnAddItemTo.setBounds(28, 707, 300, 40);
        frmManagerGui.getContentPane().add(btnAddItemTo);
        
        //REMOVE FROM MENU
        JButton btnNewButton = new JButton("Remove from Menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                if (sanity(textFieldValue, 1)) {
                    String[] s = textFieldValue.split(",");
                    try {
                        system.removeItemFromMenu(s[0], s[1]);
                        textPane_1.setText(AtoS(system.getMenuItems(s[1])));
                    } catch (Exception q) {}
                    //TODO: make work with new server
                    //this is where the new commands go
                }
            }
        });
        btnNewButton.setBounds(399, 707, 300, 40);
        frmManagerGui.getContentPane().add(btnNewButton);
        
        //SET SPECIAL
        final JButton btnSetSpecial = new JButton("Set Special");
        btnSetSpecial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                if (sanity(textFieldValue, 1)) {
                    String[] s = textFieldValue.split(",");
                    try {
                        if (system.setMenuSpecial(s[0], s[1])) {
                            //frmManagerGui.setVisible(false);
                            // new KioskGUI(system).frmKiosk.setVisible(true);
                            // new ChefGUI(system).frmOrdersToCook.setVisible(true);
                            //TODO: make work with new server
                            //this is where the new commands go
                            btnSetSpecial.setText("Special Set!");
                        }
                    } catch (Exception q) {}
                }
                
            }
        });
        btnSetSpecial.setBounds(773, 707, 300, 40);
        frmManagerGui.getContentPane().add(btnSetSpecial);

        /*
        //FINISHED BUTTON
        JButton btnFinished = new JButton("Finished");
        btnFinished.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            
                // TODO : have this be the button that pushes and saves the menus and stuffs
                
            }
        });
        btnFinished.setBounds(773, 605, 300, 40);
        frmManagerGui.getContentPane().add(btnFinished);
        */

        //FREE PIZZA NUMBER
        final JButton btnFreePizzaNumber = new JButton("Free Pizza Number");
        
        btnFreePizzaNumber.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pNumber = textField.getText();
                //if (sanity(pNumber, 1)) {
                try {

                    String pnumber = textField.getText();
                    boolean tf = system.setMenuFreePizzaNumber(txtpnMenu.getText(), Integer.parseInt(pNumber));
                    System.out.println("[MANAGER] setFreePizzaNumber = " + tf);

                    btnFreePizzaNumber.setText("Number Set!");
                } catch (Exception q) {
                }
                // TODO : have this be the button that pushes and saves the menus and stuffs
                //}
            }
        });
        btnFreePizzaNumber.setBounds(28, 605, 300, 40);
        frmManagerGui.getContentPane().add(btnFreePizzaNumber);

        JButton btnNewButton_2 = new JButton("Open Menu");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<String> menNames = system.getMenuNames();
                    String menName = menNames.get(0);
                    txtpnMenu.setText(menName);
                    textPane_1.setText(AtoS(system.getMenuItems(menName)));
                    existingItems.setText(AtoS(system.getItemNames()));
                    // TODO : Make this set the menu
                } catch (Exception q) {

                }
            }
        });
        btnNewButton_2.setBounds(773, 605, 300, 40);
        //btnNewButton_2.setBounds(399, 605, 300, 40);
        frmManagerGui.getContentPane().add(btnNewButton_2);






    }
}
