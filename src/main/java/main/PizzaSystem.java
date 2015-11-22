package main;

import java.util.*;
import java.util.concurrent.*;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

public class PizzaSystem extends UnicastRemoteObject implements SystemAccess {

    private ArrayList<Item> items;
    private ArrayList<Menu> menus;
    private Map<String, Integer> customerIDs;
    // private Stack<Sale> cheifOrders;
    private LinkedBlockingQueue<Sale> cheifOrders;
    private LinkedBlockingQueue<Sale> deliveryOrders;
    private int currentSaleID;
    private Sale currentSale;
    private Ledger ledger;
    
    public static void main(String args[]) throws Exception {
        System.out.println("[SERVER] start");
        
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("[SERVER] RMI registry created");
        } catch (RemoteException e) {
            System.out.println("[SERVER] RMI registry already exists.");
        }
        
        PizzaSystem obj = new PizzaSystem();
        
        Naming.rebind("//localhost/server", obj);
        System.out.println("[SERVER] bound to RMI");
    }
    
    public PizzaSystem() throws RemoteException {
        super(0);
        
        this.items = new ArrayList<Item>();
        this.menus = new ArrayList<Menu>();
        this.customerIDs = new HashMap<String, Integer>();
        // this.cheifOrders = new Stack<Sale>();
        this.cheifOrders = new LinkedBlockingQueue<Sale>();
        this.deliveryOrders = new LinkedBlockingQueue<Sale>();
        this.currentSaleID = 0;
        this.currentSale = null;
        this.ledger = new Ledger();
    }
    
    /**
        Creates a new Item from the given information for the business
        Only adds the Item if the Item is unique
    
        Given information must exist (ie has values)
    
        @note Items are considered unique by their name
        @return true if added to list of items
                false if was not added (ie not unique and already existed, or bad given values)
    */
    public boolean createItem(String name, String description, double price) {
        if (name == null || name.equals("") ||
            description == null || description.equals("") ||
            !(price > 0)) {
            return false;
        }
        
        Item temp = new Item(name, description, price);
        if (!this.items.contains(temp)) {
            this.items.add(temp);
            return true;
        }
        return false;
    }
    
    /**
        Removes all Items with the given name from the business
    
        @note Items are considered unique by their name
        @return true if removed
                false if not removed
    */
    public boolean deleteItem(String name) {
        Item temp = new Item(name, "", -1);
        for (Menu m : this.menus) {
            m.removeItem(temp);
        }
        return this.items.removeAll(Collections.singleton(temp));
    }
    
    /**
        Returns the names of all items in the business
    */
    public ArrayList<String> getItemNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item i : this.items) {
            names.add(i.getName());
        }
        return names;
    }
    
    /**
        Returns the names of all menues in4 the business
    */
    public ArrayList<String> getMenuNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Menu m : this.menus) {
            names.add(m.getName());
        }
        return names;
    }
    
    /**
        Returns a string represntation for each item on the menu specified
    
        @return null if bad menuName given, or menu did not exist
    */
    public ArrayList<String> getMenuItems(String menuName) {
        if (menuName == null || menuName.equals("")) {
            return null;
        }
        
        Menu foundMenu = null;
        
        Menu temp = new Menu(menuName, "");
        
        for (Menu m : this.menus) {
            if (m.equals(temp)) {
                foundMenu = m;
                break;
            }
        }
        if (foundMenu == null) {
            return null;
        }
        
        return foundMenu.getItemNames();
    }
    
    /**
        Returns a string representation for each item on the current sale
    
        @return null if sale is not active
    */
    public ArrayList<String> getSaleItemNames() {
        if (!this.isSaleActive()) {
            return null;
        }
        
        ArrayList<Item> currentSaleItems = this.currentSale.getItems();
        ArrayList<String> names = new ArrayList<String>();
        for (Item i : currentSaleItems) {
            names.add(i.getName());
        }
        return names;
    }
    
    /**
        creates a new Menu from the given information for the business
        Only adds the Menu if the Menu is unique
    
        Given information must exist (ie has values)
    
        @note Menus are considered unique by their name
        @return true if added to list of Menus
                false if not added (ie not unique and alreayd existed, or bad given values)
    */
    public boolean createMenu(String name, String description) {
        if (name == null || name.equals("") ||
            description == null || description.equals("")) {
            return false;
        }
        
        Menu temp = new Menu(name, description);
        if (!this.menus.contains(temp)) {
            this.menus.add(temp);
            return true;
        }
        return false;
    }
    
    /**
        Adds the given item (if it exists) to the given menu (if exists)
    
        @note Items are considered unique by their name
        @note Menus are considered unique by their name
        @return true if item was added to menu
                false if not added (ie, item did not exist, menu did not exist or menu had item already)
    */
    public boolean addItemToMenu(String itemName, String menuName) {
        Item foundItem = null;
        Menu foundMenu = null;
        
        Item tmpItem = new Item(itemName, "", -1);
        Menu tmpMenu = new Menu(menuName, "");
        
        boolean itemFound = false;
        for (Item i : this.items) {
            if (tmpItem.equals(i)) {
                foundItem = i;
                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            return false;
        }
        
        boolean menuFound = false;
        for (Menu m : this.menus) {
            if (m.equals(tmpMenu)) {
                foundMenu = m;
                menuFound = true;
                break;
            }
        }
        if (menuFound == false) {
            return false;
        }
        
        return foundMenu.addItem(foundItem);
    }
    
    /**
        Sets the special (if exists) for a menu (if exists)
    
        @note Items are considered unique by their name
        @note Menus are considered unique by their name
        @return true if special was set
                false if not set, menu did not exist, or item did not exist
    */
    public boolean setMenuSpecial(String itemName, String menuName) {
        Item foundItem = null;
        Menu foundMenu = null;
        
        Item tmpItem = new Item(itemName, "", -1);
        Menu tmpMenu = new Menu(menuName, "");
        
        boolean itemFound = false;
        for (Item i : this.items) {
            if (tmpItem.equals(i)) {
                foundItem = i;
                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            return false;
        }
        
        boolean menuFound = false;
        for (Menu m : this.menus) {
            if (m.equals(tmpMenu)) {
                foundMenu = m;
                menuFound = true;
                break;
            }
        }
        if (menuFound == false) {
            return false;
        }
        
        return foundMenu.setSpecial(foundItem);
    }
    
    /**
        Returns the special name (if exists) for a menu (if exists)
    
        @note Menus are considered unique by their name
        @return null if menu did not have a speical or menu did not exist
    */
    public String getMenuSpecial(String menuName) {
        Menu foundMenu = null;
        
        Menu tmpMenu = new Menu(menuName, "");
        
        boolean menuFound = false;
        for (Menu m : this.menus) {
            if (m.equals(tmpMenu)) {
                foundMenu = m;
                menuFound = true;
                break;
            }
        }
        if (menuFound == false) {
            return null;
        }
        
        Item i = foundMenu.getSpecial();
        if (i == null) {
            return null;
        } else {
            return i.getName();
        }
    }
    
    public boolean setMenuFreePizzaNumber(String menuName, int num) {
        Menu foundMenu = null;
        
        Menu tmpMenu = new Menu(menuName, "");
        
        boolean menuFound = false;
        for (Menu m : this.menus) {
            if (m.equals(tmpMenu)) {
                foundMenu = m;
                menuFound = true;
                break;
            }
        }
        if (menuFound == false) {
            return false;
        }
        
        int i = foundMenu.getFreePizzaNumber();
        if (i != -1) {
            return false;
        } else {
            foundMenu.setFreePizzaNumber(num);
            return true;
        }
    }
    
    public String getMenuFreePizzaNumber(String menuName) {
        Menu foundMenu = null;
        
        Menu tmpMenu = new Menu(menuName, "");
        
        boolean menuFound = false;
        for (Menu m : this.menus) {
            if (m.equals(tmpMenu)) {
                foundMenu = m;
                menuFound = true;
                break;
            }
        }
        if (menuFound == false) {
            return null;
        }
        
        int i = foundMenu.getFreePizzaNumber();
        if (i == -1) {
            return null;
        } else {
            return Integer.toString(i);
        }
    }
    
    public void incrementCustomer(String id, int value) {
        if (this.customerIDs.containsKey(id)) {
            this.customerIDs.put(id, this.customerIDs.get(id) + value);
        } else {
            this.customerIDs.put(id, 0);
        }
    }
    
    public void setSaleIsDelvery(boolean isDelvery) {
        this.currentSale.setIsDelvery(isDelvery);
    }
    
    public boolean getSaleIsDelvery() {
        return this.currentSale.getIsDelvery();
    }
    
    public void setSaleAddress(String address) {
        this.currentSale.setAddress(address);
    }
    
    
    /**
        Removes the given item (if it exists) to the given menu (if exists)
    
        @note Items are considered unique by their name
        @note Menus are considered unique by their name
        @return true if item was removed from menu
                false if not removed (ie, item did not exist, menu did not exist or menu did not have item)
    */
    public boolean removeItemFromMenu(String itemName, String menuName) {
        Item foundItem = null;
        Menu foundMenu = null;
        
        Item tmpItem = new Item(itemName, "", -1);
        Menu tmpMenu = new Menu(menuName, "");
        
        boolean itemFound = false;
        for (Item i : this.items) {
            if (tmpItem.equals(i)) {
                foundItem = i;
                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            return false;
        }
        
        boolean menuFound = false;
        for (Menu m : this.menus) {
            if (m.equals(tmpMenu)) {
                foundMenu = m;
                menuFound = true;
                break;
            }
        }
        if (menuFound == false) {
            return false;
        }
        
        return foundMenu.removeItem(foundItem);
    }
    
    /**
        @return true if sale is active (currentSale != null)
                false if sale is inactive (currentSale == null)
    */
    public boolean isSaleActive() {
        return this.currentSale != null;
    }
    
    /**
        Begins the act of making a Sale
    
        @note only one sale can be active at any one time
        @return true if the customerID recieved a special
                false if the customerID did not recieve a special
    */
    public boolean beginSale(String customerID) {
        if (!this.isSaleActive()) {
            this.currentSale = new Sale(this.currentSaleID);
            
            this.incrementCustomer(customerID, 0);
            System.out.println("[SERVER] customerID value = " + this.customerIDs.get(customerID));
	    System.out.println("[SERVER] freePizzaNumber  = " + this.menus.get(0).getFreePizzaNumber());
            if (this.customerIDs.get(customerID) > this.menus.get(0).getFreePizzaNumber()) {
                Item freeItem = new Item("FREE! " + this.menus.get(0).getSpecial().getName(), "", 0);
                this.currentSale.add(freeItem);
                this.customerIDs.put(customerID, 0);
                return true;
            }
        }
        return false;
    }
    
    /**
        Adds the given item (if it exists) to the current sale (if active)
    
        @note Items are considered unique by their name
        @return true if item was added to menu
                false if not added (ie, item did not exist, sale did not exist)
    */
    public boolean addItemToSale(String itemName) {
        if (!this.isSaleActive()) {
            return false;
        }
        
        Item foundItem = null;
        
        Item tmpItem = new Item(itemName, "", -1);
        
        boolean itemFound = false;
        for (Item i : this.items) {
            if (tmpItem.equals(i)) {
                foundItem = i;
                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            return false;
        }
        
        this.currentSale.add(foundItem);
        return true;
    }
    
    /**
        Removes the given item (if it exists) to the sale (if exists)
    
        @note Items are considered unique by their name
        @return true if item was removed from the sale
                false if not removed (ie, item did not exist, sale did not exist or sale did not have item)
    */
    public boolean removeItemFromSale(String itemName) {
        if (!this.isSaleActive()) {
            return false;
        }
        
        Item foundItem = null;
        
        Item tmpItem = new Item(itemName, "", -1);
        
        boolean itemFound = false;
        for (Item i : this.items) {
            if (tmpItem.equals(i)) {
                foundItem = i;
                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            return false;
        }
        
        return this.currentSale.remove(foundItem);
    }
    
    /**
        Returns the total sale cost (if active)
    
        @return null if sale is not active
                0 if there are no items in sale
    */
    public String getSaleTotal() {
        if (!this.isSaleActive()) {
            return null;
        }
        
        return currentSale.getTotal();
    }
    
    /**
        Attempt to end the current sale with the payment provided
        Then hand off the sale to the chief orders
    
        @return true if the payment >= required
                false if < required or sale not active
    */
    public boolean endSale(double payment) {
        if (!this.isSaleActive()) {
            return false;
        }
        
        boolean pay = this.currentSale.pay(payment);
        if (pay) {
            // this.cheifOrders.push(this.currentSale);
            try {
                this.cheifOrders.put(this.currentSale);
            } catch (Exception e) {}
            this.currentSale = null;
            this.currentSaleID++;
            return true;
        } else {
            return false;
        }
    }
    
    /**
        Return a string represnetation of the next order that the cheif should do
        Names only (+ descriptions?)
    
        @return null if stack is empty
    */
    public ArrayList<String> viewNextOrder() {
        try {
            Sale s = cheifOrders.peek();
            return s.look();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
        Complete the current working order and hand the sale off to the Ledger
    
        @return true if order was completed
                false if there were no orders to complete
    */
    public boolean completeNextOrder() {
        try {
            // Sale s = cheifOrders.pop();
            Sale s = cheifOrders.take();
            if (s.getIsDelvery()) {
                this.deliveryOrders.put(s);
            } else {
                this.ledger.add(s);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}


