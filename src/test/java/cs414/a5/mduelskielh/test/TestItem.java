package cs414.a5.mduelskeilh.test;

import cs414.a5.mduelskeilh.main.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;

public class TestItem {

    @Test
    public void testEquals() {
        String name = "name";
        String description = "description";
        double price = 10d;
        Item i1 = new Item(name, description, price);
        Item i2 = new Item(name, description, price);
        
        assertTrue(i1.equals(i2));
    }
}
