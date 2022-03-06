package src.OOAD_project4;

import java.util.ArrayList;
import java.util.Random;

//Encapsulation is the hiding of specific implementation and representation details of a class
//The class Store has a a lot of private/hidden methods that would make it a good example of encapsulation.
public class Store {
    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Item> soldItems = new ArrayList<>();
    
    private ArrayList<Item> orderedItems = new ArrayList<>();
    
    private CashRegister register;
    private Helpers h = new Helpers();

    private Tracker track;
    private GuitarKitFactory gkf;

    // name of the store
    private String name;
    private Clerk currentClerk;

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    // This will only be accessed with other methods
    private void addToInventory(Item itemToAdd){
        this.inventory.add(itemToAdd);
    }

    //public add to inventory variable to modify current inventory list
    public void addToCurrInventory(Item addItem){
        this.inventory.add(addItem);
    }

    public void addToSold(Item soldItem){
        this.soldItems.add(soldItem);
    }

    public ArrayList<Item> getSold(){
        return this.soldItems;
    }

    public void setClerkToday(Clerk setClerk){
        this.currentClerk = setClerk;
    }
    
    private void initializeInv(){
        Item curItem;
        
        // Add 3 of each item to the inventory (pretty self-explanatory)
        for (int i = 0; i < 3; i++){
            for (Item.Items x : Item.Items.values()){
                if (x != Item.Items.GUITARKIT){
                    curItem = this.h.generateNewItem(x);
                    addToInventory(curItem);
                }
                
            }
        }
    }

    public void simulateDay(int day, ArrayList<Clerk> staff, boolean cmd){
        // cmd is a boolean -> true means the current day is a command-line day
        //                  -> false means it isn't

        // Run the simulation for one day
        // Main functionality here - all the clerk duties are called.
        this.currentClerk.setStoreName(this.name);
        this.currentClerk.arriveAtStore(day, this.orderedItems, this.inventory);
        this.currentClerk.checkRegister(register);
        //all of these below may need parameters passed at some point(?)
        // Call doInventory, and add any items that may have been ordered to the ordered items
        this.orderedItems.addAll(this.currentClerk.doInventory(this.inventory, this.register));
        if (!cmd){
            this.currentClerk.openTheStore(this.inventory, this.soldItems, this.register, day);
            this.currentClerk.cleanTheStore(this.inventory);
            this.currentClerk.leaveTheStore();
            this.track.printSummary(day, staff, this.name);
        }
    }

    public void finishCommandDay(int day, ArrayList<Clerk> staff){
        this.currentClerk.cleanTheStore(this.inventory);
        this.currentClerk.leaveTheStore();
        this.track.printSummary(day, staff, this.name);
    }

    public void simulationSummary(){
        // Print out a summary
        System.out.printf("----- SUMMARY (%s) -----\n", this.name);
        // Loop over all the inventory items
        for(Item i : this.inventory){
            System.out.printf("Item %s (%s) is in inventory, with value $%.2f.\n", i.getName(), i.getTypeStr(), i.getPurchasePrice());
        }
        // Keep track of total items sold/sale price
        double totalSalePrice = 0.0;
        int totalItemsSold = 0;
        // Loop over all sold items
        for(Item i : this.soldItems){
            totalSalePrice += i.getSalePrice();
            totalItemsSold ++;
            System.out.printf("Item %s (%s) was sold on day %d for $%.2f.\n", i.getName(), i.getTypeStr(), i.daySold(), i.getSalePrice());
        }
        // Print final summaries
        System.out.printf("%d items were sold for a total of $%.2f.\n", totalItemsSold, totalSalePrice);
        System.out.printf("The register was left with $%.2f.\n", this.register.getMoneyAmt());
        System.out.printf("$%.2f total was added to the register from the bank.\n", this.register.getMoneyFromBank());
    }

    // Store constructor
    public Store(String storeName){
        this.name = storeName;
        this.track = Tracker.getInstance();
        initializeInv();
        this.register = new CashRegister();
    }
}