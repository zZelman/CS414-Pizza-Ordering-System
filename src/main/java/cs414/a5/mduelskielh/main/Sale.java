package cs414.a5.mduelskeilh.main;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Sale {
    private ArrayList<Item> items;
    private int id;
    private double payment;
    private boolean isDelvery;
    private String address;
    
    public ArrayList<Item> getItems() {
        return this.items;
    }
    
    public void setIsDelvery(boolean isDelvery) {
        this.isDelvery = isDelvery;
    }
    
    public boolean getIsDelvery() {
        return this.isDelvery;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public Sale(int id) {
        this.items = new ArrayList<Item>();
        this.id = id;
        this.payment = 0;
        this.isDelvery = false;
        this.address = "";
    }
    
    public void add(Item item) {
        this.items.add(item);
    }
    
    public boolean contains(Item item) {
        return this.items.contains(item);
    }
    
    public boolean remove(Item item) {
        return this.items.remove(item);
    }
    
    public String getTotal() {
        double totalRequired = 0;
        for (Item i : items) {
            totalRequired += i.getPrice();
        }
        return Double.toString(totalRequired);
    }
    
    /**
        Attempt to accept the amount of currency provided
    
        @return true if the payment >= required
                false if < required
    */
    public boolean pay(double payment) {
        double totalRequired = 0;
        for (Item i : items) {
            totalRequired += i.getPrice();
        }
        
        if (payment >= totalRequired) {
            this.payment = payment;
            return true;
        }
        return false;
    }
    
    /**
        Return a string representation of all of the items in this sale
    */
    public ArrayList<String> look() {
        ArrayList<String> names = new ArrayList<String>();
        if (this.isDelvery) {
            names.add("Is a Delvery");
            names.add("  Address is: " + this.address);
        }
        names.add("Items:");
        for (Item i : items) {
            names.add("  " + i.getName());
        }
        return names;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id:" + Integer.toString(id) + ", "
                  + "payment:" + Double.toString(payment)
                  + ", items[");
        for (Item i : items) {
            sb.append(i.getName() + ", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        
        return sb.toString();
    }
}
