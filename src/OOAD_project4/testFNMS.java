package OOAD_project4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Before;
import org.junit.Test;

import java.beans.Transient;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class testFNMS{
    //PrintStream method for capturing System.out prints
    // From: https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    Helpers h = new Helpers();
    SouthGuitarKitFactory sGKF; 
    NorthGuitarKitFactory nGKF;
    invoker invoker;
    User user;
    Clerk clerk1;
    Clerk clerk2;

    Store northStore;
    Store southStore;
    Company fnmsCompany;
    ArrayList<Store> stores;
    ArrayList<Item> arrivedOrders;
    ArrayList<Item> empty_inventory;
    CashRegister register; 

    public testFNMS(){
        arrivedOrders = new ArrayList<>();
        empty_inventory = new ArrayList<>();
        register = new CashRegister();
        h = new Helpers();
        sGKF = new SouthGuitarKitFactory();
        nGKF = new NorthGuitarKitFactory();
        invoker = new invoker();
        user = new User();
        clerk1 = new Clerk("Shaggy", 20, new HaphazardTuning());
        clerk2 = new Clerk("Velma", 5, new ElectricTuning());
        northStore = new Store("Northside FNMS");
        southStore = new Store("Southside FNMS");
        northStore.setClerkToday(clerk1);
        southStore.setClerkToday(clerk2);
        fnmsCompany = new Company();
        stores = new ArrayList<>();
        stores.add(northStore);
        stores.add(southStore);
    }

    

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        
    }
    
    @Test
    public void testGenNewItem(){
        // Test 1: Assert the helpers.generateNewItem() function generates the correct class
        assertThat(h.generateNewItem(ItemTypes.GUITAR), instanceOf(Guitar.class));
    }

    @Test
    public void testSouthBridge(){
        // Test 2: Assert the South factory creates the correct Bridge
        sGKF.createBridge("B");
        assertThat(sGKF.bridge, instanceOf(SouthBridgeB.class));
    }

    @Test
    public void testNorthBridge(){
        // Test 3: Assert the North factory creates the correct Bridge
        nGKF.createBridge("B");
        assertThat(nGKF.bridge, instanceOf(NorthBridgeB.class));
    }

    @Test
    public void testSelectStoreA(){
        // Test 4: Testing functionality of changing the store to A in command line
        selectStorecmd selectStore = new selectStorecmd(user, "A", fnmsCompany, stores);
        invoker.setCommand(selectStore);
        invoker.executed();
        assertEquals("You have selected the Northside FNMS store", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testSelectStoreB(){
        // Test 5: Testing functionality of changing the store to B in command line
        selectStorecmd selectStore = new selectStorecmd(user, "B", fnmsCompany, stores);
        invoker.setCommand(selectStore);
        invoker.executed();
        assertEquals("You have selected the Southside FNMS store", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testAskClerkName(){
        // Test 6: Testing command without selecting a store first
        askClerkNamecmd askName = new askClerkNamecmd(user);
        invoker.setCommand(askName);
        invoker.executed();
        assertEquals("Whoops! Please make sure to select a store first!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testBuyItem(){
        // Test 7: buy Item without selecting a store first
        buyItemcmd buyItem = new buyItemcmd(user);
        invoker.setCommand(buyItem);
        invoker.executed();
        assertEquals("Whoops! Please make sure to select a store first!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testCheckClothingFalse(){
        //test 8: checkClothing should return false if inventory is empty 
        assertFalse(h.checkClothing(empty_inventory));
    }

    @Test
    public void testWattLenGen(){
        //test 9: Check that the wattLenGen function generates a wattage between 1-200
        int random = h.wattLenGen();
        int max = 200;
        int min = 1;
        assertTrue("Wattage is too high", max >= random);
        assertTrue("Wattage is too low",  min  <= random);
    }

    @Test
    public void testGenCondition(){
        //test 10: Check that the condition gen generates either new or used
        String str = h.newUsedGen();
        assertTrue(str.equals("new") || str.equals("used"));
    }

    @Test
    public void testClerkGetName(){
        //Test 11: Check that the .getName function returns the right name of the clerk
        String name = clerk1.getName();
        String expected = "Shaggy";
        assertEquals(expected, name);
    }

    @Test
    public void testSelectStoreInvalidInput(){
        //Test 12: Adding unrecognized input while selecting store
        user.selectStore("X", fnmsCompany, stores);
        assertEquals("Error! Input not recognized.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testAskTime(){
        //Test 13: Ask time without initializing store first
        askClerkTimecmd askTime = new askClerkTimecmd(user);
        invoker.setCommand(askTime);
        invoker.executed();
        assertEquals("Whoops! Please make sure to select a store first!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testObserverArrival(){
        //test 14: Testing that observer is being executed when clerk arrives at store
        clerk1.arriveAtStore(1, arrivedOrders, empty_inventory);
        assertEquals("----- Shaggy arrives at null on day 1 -----", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testLeaveStore(){
        //test 15: Set the clerk today and test the functionality by matching clerkname
        northStore.setClerkToday(clerk1);
        String clerkname = northStore.getClerkName();
        assertEquals("Shaggy", clerkname);
    }
}
