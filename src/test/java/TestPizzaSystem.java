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
    public void setUp() {
        i1 = new Item("1", "1", 1);
        i2 = new Item("2", "2", 2);
        m1 = new Menu("1", "1");
        m2 = new Menu("2", "2");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreateItem() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
    }
    
    @Test
    public void testCreateItemBadValues() {
        ps = new PizzaSystem();
        assertFalse(ps.createItem(null, null, -1));
    }
    
    @Test
    public void testCreateItemDuplicate() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertFalse(ps.createItem("1", "1", 1));
    }
    
    @Test
    public void testDeleteItem() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        
        assertTrue(ps.deleteItem("1"));
    }
    
    @Test
    public void testDeleteItemBadValues() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        
        assertFalse(ps.deleteItem("2"));
    }
    
    @Test
    public void testDeleteItemNoExist() {
        ps = new PizzaSystem();
        assertFalse(ps.deleteItem("2"));
    }
    
    @Test
    public void testGetItemNames() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        
        ArrayList<String> recieved = ps.getItemNames();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("1");
        
        assertEquals(expected, recieved);
    }
    
    @Test
    public void testGetMenuNames() {
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
    public void testGetMenuItems() {
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
    public void testGetMenuItemsBadValues() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        ArrayList<String> recieved = ps.getMenuItems(null);
        assertNull(recieved);
    }
    
    @Test
    public void testGetMenuItemsNoExist() {
        ps = new PizzaSystem();
        assertTrue(ps.createMenu("1", "1"));
        
        ArrayList<String> recieved = ps.getMenuItems("1");
        assertNull(recieved);
    }
    
    @Test
    public void testSaleItemNames() {
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
    public void testSaleItemNamesNotActive() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.addItemToSale("1"));
        assertNull(ps.getSaleItemNames());
    }
    
    @Test
    public void testCreateMenu() {
        ps = new PizzaSystem();
        assertTrue(ps.createMenu("1", "1"));
    }
    
    @Test
    public void testCreateMenuBadValues() {
        ps = new PizzaSystem();
        assertFalse(ps.createMenu(null, null));
    }
    
    @Test
    public void testCreateMenuDuplicate() {
        ps = new PizzaSystem();
        assertTrue(ps.createMenu("1", "1"));
        assertFalse(ps.createMenu("1", "1"));
    }
    
    @Test
    public void testAddItemToMenu() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
    }
    
    @Test
    public void testAddItemToMenuItemNoExist() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertFalse(ps.addItemToMenu("2", "1"));
    }
    
    @Test
    public void testAddItemToMenuMenuNoExist() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertFalse(ps.addItemToMenu("1", "2"));
    }
    
    @Test
    public void testAddItemToMenuDuplicate() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertFalse(ps.addItemToMenu("1", "1"));
    }
    
    @Test
    public void testSetMenuSpecial() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertTrue(ps.setMenuSpecial("1", "1"));
    }
    
    @Test
    public void testSetMenuSpecialItemNoExist() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.setMenuSpecial("2", "1"));
    }
    
    @Test
    public void testSetMenuSpecialMenuNoExist() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.setMenuSpecial("1", "2"));
    }
    
    @Test
    public void testSetMenuSpecialItemNotContained() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        
        assertFalse(ps.setMenuSpecial("1", "1"));
    }
    
    @Test
    public void testGetMenuSpecial() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.setMenuSpecial("1", "1"));
        
        assertEquals(ps.getMenuSpecial("1"), "1");
    }
    
    @Test
    public void testGetMenuSpecialBadValues() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.setMenuSpecial("1", "1"));
        
        assertNull(ps.getMenuSpecial("2"));
    }
    
    @Test
    public void testGetMenuSpecialNoSpecial() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertNull(ps.getMenuSpecial("1"));
    }
    
    @Test
    public void testRemoveItemFromMenu() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertTrue(ps.removeItemFromMenu("1", "1"));
    }
    
    @Test
    public void testRemoveItemFromMenuBadValues() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.removeItemFromMenu("", ""));
    }
    
    @Test
    public void testRemoveItemFromMenuItemNotContained() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertFalse(ps.removeItemFromMenu("2", "1"));
    }
    
    @Test
    public void testBeginSale() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        
        assertTrue(ps.beginSale());
        assertFalse(ps.beginSale());
    }
    
    @Test
    public void testAddItemToSale() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        
        assertTrue(ps.addItemToSale("1"));
    }
    
    @Test
    public void testAddItemToSaleItemNoExist() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        
        assertFalse(ps.addItemToSale("2"));
    }
    
    @Test
    public void testRemoveItemFromSale() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        assertTrue(ps.removeItemFromSale("1"));
    }
    
    @Test
    public void testRemoveItemFromSaleItemNoExist() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        assertFalse(ps.removeItemFromSale("2"));
    }
    
    @Test
    public void testRemoveItemFromSaleItemNotContianed() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        
        assertFalse(ps.removeItemFromSale("1"));
    }
    
    @Test
    public void testGetSaleTotal() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        assertEquals(ps.getSaleTotal(), "1.0");
    }
    
    @Test
    public void testEndSale() {
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
    public void testEndSaleToLittlePayment() {
        ps = new PizzaSystem();
        assertTrue(ps.createItem("1", "1", 1));
        assertTrue(ps.createMenu("1", "1"));
        assertTrue(ps.addItemToMenu("1", "1"));
        assertTrue(ps.beginSale());
        assertTrue(ps.addItemToSale("1"));
        
        assertFalse(ps.endSale(0.1));
    }
    
    @Test
    public void testViewNextOrder() {
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
    public void testViewNextOrderNoneLeft() {
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
    public void testCompleteNextOrder() {
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
    public void testCompleteNextOrderNoneLeft() {
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
