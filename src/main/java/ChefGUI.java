import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ChefGUI {

    private JFrame frmOrdersToCook;
    private JTextField txtOrders;
    private PizzaSystem system;
    
    /**
        Launch the application.
    */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PizzaSystem ps = new PizzaSystem();
                    ChefGUI window = new ChefGUI(ps);
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
    public ChefGUI(PizzaSystem system) {
        this.system = system;
        initialize();
    }
    
    private String AtoS(ArrayList<String> a) {
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
        
        JTextArea textArea = new JTextArea();
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
                txtOrders.setText(" ");
                system.completeNextOrder();
            }
        });
        btnCompleteOrder.setBounds(350, 416, 324, 35);
        frmOrdersToCook.getContentPane().add(btnCompleteOrder);
        
        //GET ORDER
        JButton btnNewButton = new JButton("Get Order");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtOrders.setText(AtoS(system.viewNextOrder()));
            }
        });
        btnNewButton.setBounds(10, 417, 330, 33);
        frmOrdersToCook.getContentPane().add(btnNewButton);
    }
    
}
