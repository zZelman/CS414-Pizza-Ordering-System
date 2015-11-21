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
    public boolean removeItemFromMenu(String itemName, String menuName) throws RemoteException;
    public boolean isSaleActive() throws RemoteException;
    public boolean beginSale() throws RemoteException;
    public boolean addItemToSale(String itemName) throws RemoteException;
    public boolean removeItemFromSale(String itemName) throws RemoteException;
    public String getSaleTotal() throws RemoteException;
    public boolean endSale(double payment) throws RemoteException;
    public ArrayList<String> viewNextOrder() throws RemoteException;
    public boolean completeNextOrder() throws RemoteException;
}
