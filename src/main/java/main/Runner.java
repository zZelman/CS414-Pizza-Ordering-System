package main;

import java.awt.EventQueue;

public class Runner {
    public static void main(String[] args) {
        PizzaSystem system = new PizzaSystem();
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerGui window = new ManagerGui(system);
                    window.frmManagerGui.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        
        // EventQueue.invokeLater(new Runnable() {
        // public void run() {
        // try {
        // KioskGUI window = new KioskGUI(system);
        // window.frmKiosk.setVisible(true);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // }
        // });
        
        // EventQueue.invokeLater(new Runnable() {
        // public void run() {
        // try {
        // ChefGUI window = new ChefGUI(system);
        // window.frmOrdersToCook.setVisible(true);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // }
        // });
    }
}
