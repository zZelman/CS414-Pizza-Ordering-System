package main;

public class Item {
    private String name;
    private String description;
    private double price;
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Item[name:" + this.name +
               ", description:" + this.description +
               ", price:" + Double.toString(this.price) + "]";
    }
    
    /**
        @note Item's are unique by name
    */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Item) {
            Item i = (Item) other;
            if (this.name.toLowerCase().equals(i.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
