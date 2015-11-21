package test;
import main.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestLedger {

    @Test
    public void testAdd() {
        Ledger l = new Ledger();
        Sale s = new Sale(0);
        s.add(new Item("name", "desc", 10d));
        l.add(s);
        
        ArrayList<String> recieved = l.getHistory();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add(s.toString());
        
        assertTrue(expected.equals(recieved));
    }
}
