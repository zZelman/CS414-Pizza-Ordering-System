package cs414.a5.mduelskeilh.gui;

import cs414.a5.mduelskeilh.main.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import java.rmi.Naming;

public class ChefGUI {

    public JFrame frmOrdersToCook;
    private JTextField txtOrders;
    private SystemAccess system;
    
    /**
        Launch the application.
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
        Create the application.
    */
    public ChefGUI() throws Exception {
        this.system = (SystemAccess) Naming.lookup("//129.82.45.209/server");
        initialize();
    }
    
    private String AtoS(ArrayList<String> a) {
        if (a == null) {
            return " ";
        }
        String list = "";
        for (String s : a) {
            list += s + "\n";
        }
        return list;
    }
    
    /**
        Initialize the contents of the frame.
    */
    private void initialize() {
        frmOrdersToCook = new JFrame();
        frmOrdersToCook.setTitle("Orders to Cook");
        frmOrdersToCook.setBounds(100, 100, 700, 500);
        frmOrdersToCook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmOrdersToCook.getContentPane().setLayout(null);
        
        final JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 42, 664, 359);
        frmOrdersToCook.getContentPane().add(textArea);
        
        txtOrders = new JTextField();
        txtOrders.setEditable(false);
        txtOrders.setText("Orders");
        txtOrders.setBounds(10, 11, 49, 20);
        frmOrdersToCook.getContentPane().add(txtOrders);
        txtOrders.setColumns(10);
        
        //COMPLETE ORDER
        JButton btnCompleteOrder = new JButton("Complete Order");
        btnCompleteOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText(" ");
                try {
                    system.completeNextOrder();
                } catch (Exception q) {}
            }
        });
        btnCompleteOrder.setBounds(350, 416, 324, 35);
        frmOrdersToCook.getContentPane().add(btnCompleteOrder);
        
        //GET ORDER
        JButton btnNewButton = new JButton("Get Order");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.setText(AtoS(system.viewNextOrder()));
                } catch (Exception q) {}
            }
        });
        btnNewButton.setBounds(10, 417, 330, 33);
        frmOrdersToCook.getContentPane().add(btnNewButton);
    }
    
}
