
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

    private JFrame frmManagerGui;
    private JTextField textField;
    private PizzaSystem system;
    
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
        for (String s : a) {
            list += s + "\n";
        }
        return list;
    }
    
    private void initialize() {
        frmManagerGui = new JFrame();
        frmManagerGui.setTitle("Manager Gui");
        frmManagerGui.setBounds(100, 100, 700, 500);
        frmManagerGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmManagerGui.getContentPane().setLayout(null);
        
        JTextPane txtpnListOfExisting = new JTextPane();
        txtpnListOfExisting.setEditable(false);
        txtpnListOfExisting.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtpnListOfExisting.setText("List of Existing Items");
        txtpnListOfExisting.setBounds(340, 25, 128, 24);
        frmManagerGui.getContentPane().add(txtpnListOfExisting);
        
        final JTextPane textPane_1 = new JTextPane();
        textPane_1.setEditable(false);
        textPane_1.setBounds(10, 175, 312, 251);
        frmManagerGui.getContentPane().add(textPane_1);
        
        JTextPane txtpnMenu = new JTextPane();
        txtpnMenu.setEditable(false);
        txtpnMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtpnMenu.setBounds(10, 154, 312, 20);
        frmManagerGui.getContentPane().add(txtpnMenu);
        
        textField = new JTextField();
        textField.setBounds(10, 25, 312, 24);
        frmManagerGui.getContentPane().add(textField);
        textField.setColumns(10);
        
        final JTextArea existingItems = new JTextArea();
        existingItems.setEditable(false);
        existingItems.setBounds(340, 59, 322, 367);
        frmManagerGui.getContentPane().add(existingItems);
        
        //CREATE ITEM
        JButton btnCreateItem = new JButton("Create Item");
        btnCreateItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String textFieldValue = textField.getText();
                String[] s = textFieldValue.split(",");
                system.createItem(s[0], s[1], Double.parseDouble(s[2]));
                existingItems.setText(AtoS(system.getItemNames()));
            }
        });
        btnCreateItem.setBounds(10, 60, 148, 23);
        frmManagerGui.getContentPane().add(btnCreateItem);
        
        //DELETE ITEM
        JButton btnDeleteItem = new JButton("Delete Item");
        btnDeleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                system.deleteItem(textFieldValue);
                existingItems.setText(AtoS(system.getItemNames()));
            }
        });
        btnDeleteItem.setBounds(10, 89, 148, 23);
        frmManagerGui.getContentPane().add(btnDeleteItem);
        
        //CREATE MENU
        JButton btnNewButton_1 = new JButton("Create Menu");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                String[] s = textFieldValue.split(",");
                system.createMenu(s[0], s[1]);
            }
        });
        btnNewButton_1.setBounds(168, 60, 154, 23);
        frmManagerGui.getContentPane().add(btnNewButton_1);
        
        //ADD TO MENU
        JButton btnAddItemTo = new JButton("Add to Menu");
        btnAddItemTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                String[] s = textFieldValue.split(",");
                system.addItemToMenu(s[0], s[1]);
                textPane_1.setText(AtoS(system.getMenuItems(s[1])));
            }
        });
        btnAddItemTo.setBounds(168, 120, 154, 23);
        frmManagerGui.getContentPane().add(btnAddItemTo);
        
        //REMOVE FROM MENU
        JButton btnNewButton = new JButton("Remove from Menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                String[] s = textFieldValue.split(",");
                system.removeItemFromMenu(s[0], s[1]);
                textPane_1.setText(AtoS(system.getMenuItems(s[1])));
            }
        });
        btnNewButton.setBounds(168, 89, 154, 23);
        frmManagerGui.getContentPane().add(btnNewButton);
        
        //SET SPECIAL
        JButton btnSetSpecial = new JButton("Set Special");
        btnSetSpecial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                String[] s = textFieldValue.split(",");
                system.setMenuSpecial(s[0], s[1]);
                
            }
        });
        btnSetSpecial.setBounds(10, 120, 148, 23);
        frmManagerGui.getContentPane().add(btnSetSpecial);
        
        
        
        
        
        
    }
}
