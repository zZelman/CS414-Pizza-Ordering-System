package cs414.a5.mduelskeilh.test;

import cs414.a5.mduelskeilh.main.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;

import java.util.*;

public class TestPizzaSystem {
    private PizzaSystem ps;
    private Item i1;
    private Item i2;
    private Menu m1;
    private Menu m2;
    
    @Before
    public void setUp() throws Exception {
        i1 = new Item("1", "1", 1);
        i2 = new Item("2", "2", 2);
        m1 = new Menu("1", "1");
        m2 = new Menu("2", "2");
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testCreateItem() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
    }
    
    @Test
    public void testCreateItemBadValues() throws Exception {
        ps = new PizzaSystem();
        assertFalse(ps.createItem(null, null, -1));
    }
    
    @Test
    public void testCreateItemDuplicate() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertFalse(ps.createItem("1", "1", 1));
    }
    
    @Test
    public void testDeleteItem() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        
        assertTrue(ps.deleteItem("1"));
    }
    
    @Test
    public void testDeleteItemBadValues() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        
        assertFalse(ps.deleteItem("2"));
    }
    
    @Test
    public void testDeleteItemNoExist() throws Exception {
        ps = new PizzaSystem();
        assertFalse(ps.deleteItem("2"));
    }
    
    @Test
    public void testGetItemNames() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        
        ArrayList<String> recieved = ps.getItemNames();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("1");
        
        assertEquals(expected, recieved);
    }
    
    @Test
    public void testGetMenuNames() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        ArrayList<String> recieved = ps.getMenuNames();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("1");
        
        assertEquals(expected, recieved);
    }
    
    @Test
    public void testGetMenuItems() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        ArrayList<String> recieved = ps.getMenuItems("1");
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("1");
        
        assertEquals(expected, recieved);
    }
    
    @Test
    public void testGetMenuItemsBadValues() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        ArrayList<String> recieved = ps.getMenuItems(null);
        assertNull(recieved);
    }
    
    @Test
    public void testGetMenuItemsNoExist() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createMenu("1", "1"));
        
        ArrayList<String> recieved = ps.getMenuItems("1");
        assertNull(recieved);
    }
    
    @Test
    public void testSaleItemNames() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        ArrayList<String> recieved = ps.getSaleItemNames();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("1");
        
        assertEquals(expected, recieved);
    }
    
    @Test
    public void testSaleItemNamesNotActive() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.addItemToSale("1"));
        assertNull(ps.getSaleItemNames());
    }
    
    @Test
    public void testCreateMenu() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createMenu("1", "1"));
    }
    
    @Test
    public void testCreateMenuBadValues() throws Exception {
        ps = new PizzaSystem();
        assertFalse(ps.createMenu(null, null));
    }
    
    @Test
    public void testCreateMenuDuplicate() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createMenu("1", "1"));
        assertFalse(ps.createMenu("1", "1"));
    }
    
    @Test
    public void testAddItemToMenu() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
    }
    
    @Test
    public void testAddItemToMenuItemNoExist() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertFalse(ps.addItemToMenu("2", "1"));
    }
    
    @Test
    public void testAddItemToMenuMenuNoExist() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertFalse(ps.addItemToMenu("1", "2"));
    }
    
    @Test
    public void testAddItemToMenuDuplicate() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertFalse(ps.addItemToMenu("1", "1"));
    }
    
    @Test
    public void testSetMenuSpecial() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertTrue(ps.setMenuSpecial("1", "1"));
    }
    
    @Test
    public void testSetMenuSpecialItemNoExist() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.setMenuSpecial("2", "1"));
    }
    
    @Test
    public void testSetMenuSpecialMenuNoExist() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.setMenuSpecial("1", "2"));
    }
    
    @Test
    public void testSetMenuSpecialItemNotContained() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        
        assertFalse(ps.setMenuSpecial("1", "1"));
    }
    
    @Test
    public void testGetMenuSpecial() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.setMenuSpecial("1", "1"));
        
        assertEquals(ps.getMenuSpecial("1"), "1");
    }
    
    @Test
    public void testGetMenuSpecialBadValues() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.setMenuSpecial("1", "1"));
        
        assertNull(ps.getMenuSpecial("2"));
    }
    
    @Test
    public void testGetMenuSpecialNoSpecial() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertNull(ps.getMenuSpecial("1"));
    }
    
    @Test
    public void testRemoveItemFromMenu() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertTrue(ps.removeItemFromMenu("1", "1"));
    }
    
    @Test
    public void testRemoveItemFromMenuBadValues() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.removeItemFromMenu("", ""));
    }
    
    @Test
    public void testRemoveItemFromMenuItemNotContained() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.removeItemFromMenu("2", "1"));
    }
    
    @Test
    public void testBeginSale() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertTrue(ps.beginSale());
        assertFalse(ps.beginSale());
    }
    
    @Test
    public void testAddItemToSale() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        
        assertTrue(ps.addItemToSale("1"));
    }
    
    @Test
    public void testAddItemToSaleItemNoExist() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        
        assertFalse(ps.addItemToSale("2"));
    }
    
    @Test
    public void testRemoveItemFromSale() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        assertTrue(ps.removeItemFromSale("1"));
    }
    
    @Test
    public void testRemoveItemFromSaleItemNoExist() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        assertFalse(ps.removeItemFromSale("2"));
    }
    
    @Test
    public void testRemoveItemFromSaleItemNotContianed() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        
        assertFalse(ps.removeItemFromSale("1"));
    }
    
    @Test
    public void testGetSaleTotal() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        assertEquals(ps.getSaleTotal(), "1.0");
    }
    
    @Test
    public void testEndSale() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        assertTrue(ps.endSale(1));
        assertFalse(ps.endSale(1));
    }
    
    @Test
    public void testEndSaleToLittlePayment() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        assertFalse(ps.endSale(0.1));
    }
    
    @Test
    public void testViewNextOrder() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        assertTrue(ps.endSale(1));
        
        ArrayList<String> recieved = ps.viewNextOrder();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("1");
        
        assertEquals(expected, recieved);
    }
    
    @Test
    public void testViewNextOrderNoneLeft() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        assertTrue(ps.endSale(1));
        
        assertTrue(ps.completeNextOrder());
        assertNull(ps.viewNextOrder());
    }
    
    @Test
    public void testCompleteNextOrder() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        assertTrue(ps.endSale(1));
        
        assertTrue(ps.completeNextOrder());
    }
    
    @Test
    public void testCompleteNextOrderNoneLeft() throws Exception {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        assertTrue(ps.endSale(1));
        
        assertTrue(ps.completeNextOrder());
        assertFalse(ps.completeNextOrder());
    }
}
