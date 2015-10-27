
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class Playground {
    @Test
    public void testJava() {
        // String s = null;
        // System.out.println(s);
        
        ArrayList<String> als = new ArrayList<String>();
        als.add("Hello");
        als.add("world!");
        System.out.println(als);
    }
}
