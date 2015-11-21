package main;

import java.util.ArrayList;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SystemAccess extends Remote {
    public boolean createItem(String name, String description, double price);
    public boolean deleteItem(String name);
    public ArrayList<String> getItemNames();
    public ArrayList<String> getMenuNames();
    public ArrayList<String> getMenuItems(String menuName);
    public ArrayList<String> getSaleItemNames();
    public boolean createMenu(String name, String description);
    public boolean addItemToMenu(String itemName, String menuName);
    public boolean setMenuSpecial(String itemName, String menuName);
    public String getMenuSpecial(String menuName);
    public boolean removeItemFromMenu(String itemName, String menuName);
    public boolean isSaleActive();
    public boolean beginSale();
    public boolean addItemToSale(String itemName);
    public boolean removeItemFromSale(String itemName);
    public String getSaleTotal();
    public boolean endSale(double payment);
    public ArrayList<String> viewNextOrder();
    public boolean completeNextOrder();
}
