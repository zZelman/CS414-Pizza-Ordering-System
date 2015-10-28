package test;
import main.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestSale {
    private Item i1;
    private int id;
    
    @Before
    public void setUp() {
        i1 = new Item("name", "description", 10);
        id = 0;
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetTotal() {
        Sale s = new Sale(id);
        s.add(i1);
        s.add(i1);
        
        assertEquals(s.getTotal(), "20.0");
    }
    
    @Test
    public void testPay() {
        Sale s = new Sale(id);
        s.add(i1);
        s.add(i1);
        
        assertTrue(s.pay(20d));
    }
    
    @Test
    public void testLook() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("name");
        expected.add("name");
        
        Sale s = new Sale(id);
        s.add(i1);
        s.add(i1);
        
        ArrayList<String> recieved = s.look();
        
        assertEquals(recieved, expected);
    }
}
