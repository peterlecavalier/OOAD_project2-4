package OOAD_project4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Before;
import org.junit.Test;

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

    public testFNMS(){
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

}
