
public class Ledger {
    private ArrayList<String> history;
    
    public Ledger() {
        this.history = new ArrayList<String>();
    }
    
    public void add(Sale s) {
        history.add(s.toString());
    }
}
