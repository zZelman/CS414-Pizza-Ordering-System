package main;

import java.util.ArrayList;
import java.util.Collections;

public class Menu {
    private String name;
    private String description;
    private ArrayList<Item> items;
    private Item special;
    private int freePizzaNumber;
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public ArrayList<Item> getItems() {
        return this.items;
    }
    
    public Item getSpecial() {
        return this.special;
    }
    
    public int getFreePizzaNumber() {
        return this.freePizzaNumber;
    }
    
    /**
        Sets the special for this menu
    
        @note items are unique by name
        @note the item given must be apart of this menu
        @return true if special was set
                false if special was not set
    */
    public boolean setSpecial(Item special) {
        if (items.contains(special)) {
            this.special = special;
            return true;
        }
        return false;
    }
    
    public void setFreePizzaNumber(int num) {
        this.freePizzaNumber = num;
    }
    
    /**
        @note Set the special after the Menu has been created
    */
    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<Item>();
        this.special = null;
	this.freePizzaNumber = -1;
    }
    
    /**
        Only adds the Item if the Item is unique
    
        @note Items are considered unique by their name
        @return true if added to list of items
                false if was not added (ie not unique and already existed)
    */
    public boolean addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
            return true;
        }
        return false;
    }
    
    /**
        Removes the given item from this Menu if it exists within this Menu
    
        @note Items are considered unique by their name
        @return true if removed
                false if not removed (ie did not exist in this Menu)
    */
    public boolean removeItem(Item item) {
        return items.removeAll(Collections.singleton(item));
    }
    
    /**
        Returns a ArrayList represnetaion of this menu and its items
    
        @return null if there are no items
    */
    public ArrayList<String> getItemNames() {
        if (this.items.isEmpty()) {
            return null;
        }
        
        ArrayList<String> names = new ArrayList<String>();
        for (Item i : items) {
            names.add(i.getName());
        }
        
        return names;
    }
    
    @Override
    public String toString() {
        return "Menu[name:" + this.name +
               ", description:" + this.description +
               ", items:" + this.items +
               ", special:" + this.special + "]";
    }
    
    /**
        @note Menu's are unique by name
    */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Menu) {
            Menu m = (Menu) other;
            if (this.name.toLowerCase().equals(m.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
