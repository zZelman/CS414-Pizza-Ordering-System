package main;

import java.util.ArrayList;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SystemAccess extends Remote {
    public boolean createItem(String name, String description, double price) throws RemoteException;
    public boolean deleteItem(String name) throws RemoteException;
    public ArrayList<String> getItemNames() throws RemoteException;
    public ArrayList<String> getMenuNames() throws RemoteException;
    public ArrayList<String> getMenuItems(String menuName) throws RemoteException;
    public ArrayList<String> getSaleItemNames() throws RemoteException;
    public boolean createMenu(String name, String description) throws RemoteException;
    public boolean addItemToMenu(String itemName, String menuName) throws RemoteException;
    public boolean setMenuSpecial(String itemName, String menuName) throws RemoteException;
    public String getMenuSpecial(String menuName) throws RemoteException;
    
    // NEW
    public boolean setMenuFreePizzaNumber(String menuName, int num) throws RemoteException;
    public String getMenuFreePizzaNumber(String menuName) throws RemoteException;
    public void incrementCustomer(String id, int value) throws RemoteException;
    public void setSaleIsDelvery(boolean isDelvery) throws RemoteException;
    public boolean getSaleIsDelvery() throws RemoteException;
    public void setSaleAddress(String address) throws RemoteException;
    
    // TODO
    // public ArrayList<String> chefViewNextOrder() throws RemoteException;
    // public boolean chefCompleteNextOrder() throws RemoteException;
    
    public boolean removeItemFromMenu(String itemName, String menuName) throws RemoteException;
    public boolean isSaleActive() throws RemoteException;
    public boolean beginSale(String customerID) throws RemoteException;
    public boolean addItemToSale(String itemName) throws RemoteException;
    public boolean removeItemFromSale(String itemName) throws RemoteException;
    public String getSaleTotal() throws RemoteException;
    public boolean endSale(String customerID, double payment) throws RemoteException;
    public ArrayList<String> viewNextOrder() throws RemoteException;
    public boolean completeNextOrder() throws RemoteException;
}
