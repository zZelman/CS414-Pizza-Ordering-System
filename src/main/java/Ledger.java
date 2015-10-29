
import java.util.ArrayList;

public class Ledger {
    private ArrayList<String> history;
    
    public ArrayList<String> getHistory() {
        return this.history;
    }
    
    public Ledger() {
        this.history = new ArrayList<String>();
    }
    
    public void add(Sale s) {
        history.add(s.toString());
    }
}
