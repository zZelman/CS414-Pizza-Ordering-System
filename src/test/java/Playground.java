
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;

import java.util.*;

public class Playground {
    @Test
    public void testJava() {
    
        ArrayList<String> als = new ArrayList<String>();
        als.add("Hello");
        als.add("world!");
        System.out.println(als);
        
        als.removeAll(Collections.singleton("Hello"));
        System.out.println(als);
    }
}
