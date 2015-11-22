package cs414.a5.mduelskeilh.test;

import cs414.a5.mduelskeilh.main.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;

public class TestMenu {

    private String name;
    private String description;
    
    private Item i1;
    private Item i2;
    
    @Before
    public void setUp() {
        this.name = "name";
        this.description = "description";
        
        i1 = new Item("i1", "i1des", 1);
        i2 = new Item("i2", "i2des", 2);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSetSpecial() {
        Menu m = new Menu(name, description);
        assertTrue(m.addItem(i1));
        assertTrue(m.setSpecial(i1));
        assertTrue(m.getSpecial().equals(i1));
    }
    
    @Test
    public void testSetSpecialNoItems() {
        Menu m = new Menu(name, description);
        assertFalse(m.setSpecial(i2));
    }
    
    @Test
    public void testAddItem() {
        Menu m = new Menu(name, description);
        assertTrue(m.addItem(i1));
        assertTrue(m.addItem(i2));
    }
    
    @Test
    public void testAddItemDuplicate() {
        Menu m = new Menu(name, description);
        assertTrue(m.addItem(i1));
        assertFalse(m.addItem(i1));
        assertTrue(m.addItem(i2));
    }
    
    @Test
    public void testRemoveItem() {
        Menu m = new Menu(name, description);
        assertTrue(m.addItem(i1));
        assertTrue(m.addItem(i2));
        
        assertTrue(m.removeItem(i1));
        assertTrue(m.removeItem(i2));
    }
    
    @Test
    public void testRemoveItemDuplicate() {
        Menu m = new Menu(name, description);
        assertTrue(m.addItem(i1));
        assertTrue(m.addItem(i2));
        
        assertTrue(m.removeItem(i1));
        assertTrue(m.removeItem(i2));
        
        assertFalse(m.removeItem(i1));
        assertFalse(m.removeItem(i2));
    }
    
    @Test
    public void testEquals() {
        Menu m1 = new Menu(name, description);
        assertTrue(m1.addItem(i1));
        assertTrue(m1.addItem(i2));
        
        Menu m2 = new Menu(name, description);
        assertTrue(m2.addItem(i1));
        assertTrue(m2.addItem(i2));
        
        assertTrue(m1.equals(m2));
    }
}
