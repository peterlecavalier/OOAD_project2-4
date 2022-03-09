package OOAD_project3;

import java.util.ArrayList;
import java.util.Random;

//Encapsulation is the hiding of specific implementation and representation details of a class
//The class Store has a a lot of private/hidden methods that would make it a good example of encapsulation.
public class Store {
    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Item> soldItems = new ArrayList<>();
    private ArrayList<Clerk> staff = new ArrayList<>();
    private ArrayList<String> schedule = new ArrayList<>();
    private ArrayList<Item> orderedItems = new ArrayList<>();
    
    private CashRegister register;
    private Helpers h = new Helpers();

    private Tracker track;

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
        this.inventory.add(soldItem);
    }

    public ArrayList<Item> getSold(){
        return this.soldItems;
    }
    
    private void initializeInv(){
        Item curItem;
        
        // Add 3 of each item to the inventory (pretty self-explanatory)
        for (int i = 0; i < 3; i++){
            for (Item.Items x : Item.Items.values()){
                curItem = this.h.generateNewItem(x);
                addToInventory(curItem);
            }
        }
    }
    
    private void initializeStaff(){
        // Make clerks and add them to the staff
        // Now, staff have a tuning type
        Clerk clerk1 = new Clerk("Shaggy", 20, new HaphazardTuning(), this.track);
        Clerk clerk2 = new Clerk("Velma", 5, new ElectricTuning(), this.track);
        // New clerk Daphne added, 15% damage chance
        Clerk clerk3 = new Clerk("Daphne", 15, new ManualTuning(), this.track);
        this.staff.add(clerk1);
        this.staff.add(clerk2);
        this.staff.add(clerk3);
    }

    public void runSimulation(int days){
        // Run the simulation for "days" days
        Random rng = new Random();
        Clerk clerkToday;
        String clerkName;
        Clerk sickClerk; // Handles a randomly sick clerk
        String sickName;


        for (int i = 1; i < days + 1; i++){
            // 1 day out of 7 is a Sunday...
            if (i % 7 == 0){
                System.out.println("----- Sunday! The store is closed. -----");
                continue;
            }

            // Choose if a Clerk is sick (10% chance)
            sickName = "";
            if (rng.nextDouble() <= 0.1){
                sickClerk = this.staff.get(rng.nextInt(this.staff.size()));
                sickName = sickClerk.getName();
                System.out.printf("----- %s is sick on day %d -----\n", sickName, i);
            }


            // Choose a random clerk, and make sure they haven't worked the last 2 days
            while (true){
                clerkToday = this.staff.get(rng.nextInt(this.staff.size()));
                clerkName = clerkToday.getName();
                // Make sure they aren't sick
                if (clerkName == sickName){
                    continue;
                }
                // Make sure they haven't worked the last 3 days
                if (this.schedule.size() >= 3){
                    if (clerkName == this.schedule.get(this.schedule.size() - 1) && 
                    clerkName == this.schedule.get(this.schedule.size() - 2) &&
                    clerkName == this.schedule.get(this.schedule.size() - 3)){
                        continue;
                    }
                }
                // Add them to the schedule
                this.schedule.add(clerkName);
                break;  
            }

            // Main functionality here - all the clerk duties are called.
            clerkToday.arriveAtStore(i, this.orderedItems, this.inventory);
            clerkToday.checkRegister(register);
            //all of these below may need parameters passed at some point(?)
            // Call doInventory, and add any items that may have been ordered to the ordered items
            this.orderedItems.addAll(clerkToday.doInventory(this.inventory, this.register));
            clerkToday.openTheStore(this.inventory, this.soldItems, this.register, i);
            clerkToday.cleanTheStore(this.inventory);
            clerkToday.leaveTheStore();
            this.track.printSummary(i, this.staff);
        }
        // Print out a summary
        System.out.println("----- SUMMARY -----");
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
        System.out.println("----- END OF SIMULATION -----");
    }

    // Store constructor
    public Store(){
        this.track = new Tracker();
        initializeInv();
        initializeStaff();
        this.register = new CashRegister();
    }
}