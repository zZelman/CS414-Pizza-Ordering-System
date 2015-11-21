package main;

import java.awt.EventQueue;

import java.rmi.RemoteException;

public class Runner {
    public static void main(String[] args) throws RemoteException {
        
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
