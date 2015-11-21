package main;

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

public class ManagerGui {

    public JFrame frmManagerGui;
    private JTextField textField;
    private PizzaSystem system;
    JTextPane textPane_1;
    JTextArea existingItems;
    
    /**
        Launch the application.
    */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PizzaSystem ps = new PizzaSystem();
                    ManagerGui window = new ManagerGui(ps);
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
    public ManagerGui(PizzaSystem system) {
        this.system = system;
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
        
        JTextPane textPane_1 = new JTextPane();
        textPane_1.setEditable(false);
        textPane_1.setBounds(28, 56, 512, 503);
        frmManagerGui.getContentPane().add(textPane_1);
        
        JTextPane txtpnMenu = new JTextPane();
        txtpnMenu.setEditable(false);
        txtpnMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtpnMenu.setBounds(28, 25, 160, 20);
        frmManagerGui.getContentPane().add(txtpnMenu);
        
        textField = new JTextField();
        textField.setBounds(399, 597, 300, 24);
        frmManagerGui.getContentPane().add(textField);
        textField.setColumns(10);
        
        JTextArea existingItems = new JTextArea();
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
                    system.createItem(s[0], s[1], Double.parseDouble(s[2]));
                    existingItems.setText(AtoS(system.getItemNames()));
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
                    system.deleteItem(textFieldValue);
                    existingItems.setText(AtoS(system.getItemNames()));
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
                    system.createMenu(s[0], s[1]);
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
                    system.addItemToMenu(s[0], s[1]);
                    textPane_1.setText(AtoS(system.getMenuItems(s[1])));
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
                    system.removeItemFromMenu(s[0], s[1]);
                    textPane_1.setText(AtoS(system.getMenuItems(s[1])));
                    //TODO: make work with new server
                    //this is where the new commands go                    
                }
            }
        });
        btnNewButton.setBounds(399, 707, 300, 40);
        frmManagerGui.getContentPane().add(btnNewButton);
        
        //SET SPECIAL
        JButton btnSetSpecial = new JButton("Set Special");
        btnSetSpecial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                if (sanity(textFieldValue, 1)) {
                    String[] s = textFieldValue.split(",");
                    if (system.setMenuSpecial(s[0], s[1])) {
                        frmManagerGui.setVisible(false);
                        new KioskGUI(system).frmKiosk.setVisible(true);
                        new ChefGUI(system).frmOrdersToCook.setVisible(true);
                        //TODO: make work with new server
                        //this is where the new commands go
                    }
                }
                
            }
        });
        btnSetSpecial.setBounds(773, 707, 300, 40);
        frmManagerGui.getContentPane().add(btnSetSpecial);
        
        JButton btnFinished = new JButton("Finished");
        btnFinished.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		// TODO : have this be the button that pushes and saves the menus and stuffs
        		
        	}
        });
        btnFinished.setBounds(773, 605, 300, 40);
        frmManagerGui.getContentPane().add(btnFinished);
        
        
        
        
        
        
    }
}
