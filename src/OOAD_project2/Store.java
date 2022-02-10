package src.OOAD_project2;

import java.util.ArrayList;
import java.util.Random;

public class Store {
    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Item> soldItems = new ArrayList<>();
    private ArrayList<Clerk> staff = new ArrayList<>();
    private ArrayList<String> schedule = new ArrayList<>();
    private ArrayList<Item> orderedItems = new ArrayList<>();

    private CashRegister register;
    private Helpers h = new Helpers();

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
        // This is a very long-winded way to do this,
        // but I'm not sure how else to deal with specific subclasses
        Item curItem;

        // Add 3 of each item to the inventory (pretty self-explanatory)
        // Helper functions (h.method()) are called within constructors to randomly generate parameter values
        for (int i = 0; i < 3; i++){
            curItem = new PaperScore(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.nameGen(), h.nameGen());
            addToInventory(curItem);
            curItem = new CD(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.nameGen(), h.nameGen());
            addToInventory(curItem);
            curItem = new Vinyl(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.nameGen(), h.nameGen());
            addToInventory(curItem);
            curItem = new CDPlayer(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen());
            addToInventory(curItem);
            curItem = new RecordPlayer(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen());
            addToInventory(curItem);
            curItem = new MP3Player(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen());
            addToInventory(curItem);
            curItem = new Guitar(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.boolGen());
            addToInventory(curItem);
            curItem = new Bass(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.boolGen());
            addToInventory(curItem);
            curItem = new Mandolin(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.boolGen());
            addToInventory(curItem);
            curItem = new Flute(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.fluteTypeGen());
            addToInventory(curItem);
            curItem = new Harmonica(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.harmKeyGen());
            addToInventory(curItem);
            curItem = new Hat(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.sizeGen());
            addToInventory(curItem);
            curItem = new Shirt(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.sizeGen());
            addToInventory(curItem);
            curItem = new Bandana(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen());
            addToInventory(curItem);
            curItem = new PracticeAmp(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.wattLenGen());
            addToInventory(curItem);
            curItem = new Cable(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.wattLenGen());
            addToInventory(curItem);
            curItem = new Strings(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.stringGen());
            addToInventory(curItem);
        }
    }

    private void initializeStaff(){
        // Make clerks and add them to the staff
        Clerk clerk1 = new Clerk("Shaggy", 20);
        Clerk clerk2 = new Clerk("Velma", 5);
        this.staff.add(clerk1);
        this.staff.add(clerk2);
    }

    public void runSimulation(int days){
        // Run the simulation for "days" days
        Random rng = new Random();
        Clerk clerkToday;
        String clerkName;


        for (int i = 1; i < days + 1; i++){
            // 1 day out of 7 is a Sunday...
            if (i % 7 == 0){
                System.out.println("----- Sunday! The store is closed. -----");
                continue;
            }

            // Choose a random clerk, and make sure they haven't worked the last 2 days
            while (true){
                clerkToday = this.staff.get(rng.nextInt(this.staff.size()));
                clerkName = clerkToday.getName();
                // Make sure they haven't worked the last 2 days
                if (this.schedule.size() >= 2){
                    if (clerkName == this.schedule.get(this.schedule.size() - 1) && 
                    clerkName == this.schedule.get(this.schedule.size() - 2)){
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
        }
        // Print out a summary
        System.out.println("----- SUMMARY -----");
        // Loop over all the inventory items
        for(Item i : this.inventory){
            System.out.printf("Item %s (%s) is in inventory, with value $%.2f.\n", i.getName(), i.getType(), i.getPurchasePrice());
        }
        // Keep track of total items sold/sale price
        double totalSalePrice = 0.0;
        int totalItemsSold = 0;
        // Loop over all sold items
        for(Item i : this.soldItems){
            totalSalePrice += i.getSalePrice();
            totalItemsSold ++;
            System.out.printf("Item %s (%s) was sold on day %d for $%.2f.\n", i.getName(), i.getType(), i.daySold(), i.getSalePrice());
        }
        // Print final summaries
        System.out.printf("%d items were sold for a total of $%.2f.\n", totalItemsSold, totalSalePrice);
        System.out.printf("The register was left with $%.2f.\n", this.register.getMoneyAmt());
        System.out.printf("$%.2f total was added to the register from the bank.\n", this.register.getMoneyFromBank());
        System.out.println("----- END OF SIMULATION -----");
    }

    // Store constructor
    public Store(){
        initializeInv();
        initializeStaff();
        this.register = new CashRegister();
    }
}
