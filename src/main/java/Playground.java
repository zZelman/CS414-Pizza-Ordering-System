import java.util.*;

public class Playground {
    public static void main(String args[]) throws Exception {
	Map<String, Integer> customerIDs = new HashMap<String, Integer>();
	customerIDs.put("1", 0);
	customerIDs.put("1", customerIDs.get("1") + 1);
	System.out.println(customerIDs.get("1"));
    }
}
