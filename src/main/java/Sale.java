
import java.util.ArrayList;

public class Sale {
    private ArrayList<Item> items;
    
    public ArrayList<Item> getItems() {
        return this.items;
    }
    
    public Sale() {
        this.items = new ArrayList<Item>();
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
}
