package src.OOAD_project4;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.crypto.KeySelector.Purpose;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter; 
import java.io.IOException;
import java.util.HashMap;

public class Clerk{
    private static final Exception IOException = null;
    private String name;
    private int breakPercent;
    private Tuning tuning;
    private Helpers h = new Helpers();
    private Random rng = new Random();
    private ArrayList<Observer> obsList;
    private Tracker track;
    private Logger log;
    // Added currentStoreName to keep track of store name for print statements
    private String currentStoreName;
    
    public Clerk(String clerkName, int breakP, Tuning tuner){
        this.name = clerkName;
        this.breakPercent = breakP;
        this.tuning = tuner;
        this.track = Tracker.getInstance();
    }

    public String getName(){
        return this.name;
    }

    public void setName(String clerkName){
        this.name = clerkName;
    }

    public int tuneItem(Item tuningItem){
        return tuning.doTuning(tuningItem);
    }

    public String getTuningStr(){
        return tuning.getTuningTypeStr();
    }

    public void setStoreName(String sName){
        this.currentStoreName = sName;
    }

    public void arriveAtStore(int dayNum, ArrayList<Item> arrivedOrders, ArrayList<Item> inventory){
        // Add logger and tracker to observer list
        this.obsList = new ArrayList<Observer>();

        // Updated logger instantiation for Singleton
        log = Logger.getInstance();
        log.setDay(dayNum);

        this.subscribe(this.track);
        this.subscribe(log);

        System.out.printf("----- %s arrives at %s on day %d -----\n", this.name, this.currentStoreName, dayNum); 
        // Add any arrived orders to the inventory
        notifyObserver("ArriveAtStore", this.name, arrivedOrders.size(), 0.0, this.currentStoreName);
        if (!arrivedOrders.isEmpty()){
            for(Item i : arrivedOrders){
                inventory.add(i);
                String itemName = i.getName();
                System.out.printf("Item %s (%s) arrived at %s.\n", itemName, i.getTypeStr(), this.currentStoreName);
            }
            // Reset arrivedOrders
            arrivedOrders.clear();
        }
    }

    public void checkRegister(CashRegister register){
        double moneyInReg = register.getMoneyAmt();
        System.out.printf("%s counted $%.2f in the register at %s.\n", this.name, moneyInReg, this.currentStoreName);
        notifyObserver("CheckRegister", this.name, 0, moneyInReg, this.currentStoreName);
        if (moneyInReg < 75.0){
            this.goToBank(register);
        }
    }

    private void goToBank(CashRegister register){
        // Go to the bank
        // Keep track of the total money added from the bank
        register.addMoneyFromBank(1000.0);
        //... and add it to the register
        register.addToRegister(1000.0);
        double moneyInReg = register.getMoneyAmt();
        System.out.printf("%s went to the bank and put $1000 in the register. There is now $%.2f in the register at %s.\n", this.name, moneyInReg, this.currentStoreName);
        notifyObserver("GoToBank", this.name, 0, moneyInReg, this.currentStoreName);
    }

    public ArrayList<Item> doInventory(ArrayList<Item> inventory, CashRegister register){
        // HashMap implementation from here:
        // https://www.w3schools.com/java/java_hashmap.asp
        HashMap<Item.Items, Integer> subclassCounts = new HashMap<Item.Items, Integer>();
        double totalPrices = 0;
        ArrayList<Item> newItems = new ArrayList<>();

        // Put all item types into the hashmap with initial values 0.
        for (Item.Items x : Item.Items.values()){
            subclassCounts.put(x, 0);
        }
        
        // Increment the value for that subclass in the hashmap
        // and add to the total purchasePrices.
        for (Item i : inventory){
            int count = subclassCounts.get(i.getType());
            subclassCounts.put(i.getType(), count + 1);
            totalPrices += i.getPurchasePrice();
        }

        System.out.printf("%s did inventory. Total value in %s is $%.2f.\n", this.name, this.currentStoreName, totalPrices);
        notifyObserver("DoInventory", this.name, inventory.size() ,totalPrices, this.currentStoreName); //notify observer with # of items in inventory and total purchase price
        // brokenItems keeps track of all items broken during tuning
        ArrayList<Integer> brokenItems = new ArrayList<>();
        // invCounter keeps track of item number in inventory
        int invCounter = -1;
        // numItemsDamaged keeps track of total items broken during tuning
        int numItemsDamaged = 0;

        // Do tuning
        for (Item i : inventory){
            invCounter++;
            int tuningResult = this.tuneItem(i);
            if (tuningResult == -2){
                continue;
            }
            System.out.printf("%s attempted %s tuning on %s (%s):\n", this.name, this.getTuningStr(), i.getName(), i.getTypeStr());
            if (tuningResult == -1){
                // If switched from true to false
                System.out.println("    ->Oh no! Tuning went awry - status changed from true to false.");
                numItemsDamaged ++;
                if (this.rng.nextDouble() < 0.1){
                    // Lower the condition by 1
                    String newCond = i.lowerCondition();
                    // If the item has been destroyed, remove it from inventory
                    if (newCond == "broken"){
                        System.out.printf("Oh no! %s has broken an item! %s (%s) is now destroyed and has been removed from inventory.\n", this.name, i.getName(), i.getTypeStr());
                        // Do in reverse order so that removing one item doesn't affect
                        // the order of other to-be-removed items
                        brokenItems.add(0, invCounter);
                    }
                    // If not, reduce the price by 20%.
                    else{
                        //Reduce price by 20%
                        double newPrice = i.lowerListPrice();
                        System.out.printf("Oh no! %s has broken an item! The price of %s (%s) has been reduced to $%.2f and the condition is now %s.\n", this.name, i.getName(), i.getTypeStr(), newPrice, newCond);
                    }
                }
            }
            else if (tuningResult == 0){
                System.out.println("    ->Tuning status did not change from false.");
            }
            else if (tuningResult == 1){
                System.out.println("    ->Tuning status did not change from true.");
            }
            else if (tuningResult == 2){
                System.out.println("    ->Success! Tuning status changed from false to true.");
            }
        }

        //notify observer of number of brokenItems
        notifyObserver("DoInventory2", this.name, numItemsDamaged, 0.0, this.currentStoreName);
        // Remove all broken items from tuning from the inventory
        for (int itemIdx : brokenItems){
            inventory.remove(itemIdx);
        }

        // Call placeAnOrder for each missing inventory class
        for (Item.Items i : subclassCounts.keySet()) {
            if (subclassCounts.get(i) == 0){
                // Don't order any more clothing
                if (i == Item.Items.HAT || i == Item.Items.SHIRT || i == Item.Items.BANDANA){
                    continue;
                }
                else{
                    ArrayList<Item> orderedItems = this.placeAnOrder(i, register);
                    newItems.addAll(orderedItems);
                }   
            }
        }
        return newItems;
    }

    // Places an order for items and returns the ArrayList of items
    public ArrayList<Item> placeAnOrder(Item.Items orderClass, CashRegister register){
        ArrayList<Item> itemsInOrder = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Item curItem = this.h.generateNewItem(orderClass);
            itemsInOrder.add(curItem);
            double purPrice = curItem.getPurchasePrice();
            register.payCustomer(purPrice);
            System.out.printf("%s placed an order for %s (%s) for $%.2f.\n", this.name, curItem.getName(), curItem.getTypeStr(), purPrice);
        }
        notifyObserver("PlaceAnOrder", this.name, itemsInOrder.size(), 0.0, this.currentStoreName);
        return itemsInOrder;
    }

    public void openTheStore(ArrayList<Item> inventory, ArrayList<Item> soldItems, CashRegister register, int dayNum, int user){
        Customer cust;
        int custNum=0;
        int counter = 0;
        //user variable is used to determine if user interaction is needed
        if (user ==2){
            Customer user_cust = new Customer();
            //cust num being 99 will 
            user_cust.buyItem(inventory, register, soldItems, this.name, 99, dayNum);
            return;
        }
        //1 = sell
        if (user ==1){
            Customer user_cust = new Customer();
            //cust num being 99 indicates that it is user interaction
            user_cust.sellItem(inventory, register, this.name, 99, dayNum);
            return;
        }
        // Poisson distribution for buying customers
        // 1-4 selling customers
        int numBuyingCustomers = h.poissonDist(3) + 2;
        int numSellingCustomers = this.rng.nextInt(4) + 1;

        //There will be a Poisson distribution for buying customers
        //Using the counter to generate the right amount of buying and selling customers
        //CustNum is to track the customer number that will be printed
        int salesCountertmp = soldItems.size(); //tmp counter to keep track of original items in soldItems arr
        while (counter < numBuyingCustomers){
            counter ++;
            custNum ++;
            cust = new Customer();
            cust.buyItem(inventory, register, soldItems, this.name, custNum, dayNum);
        }
        int amtSold = soldItems.size() - salesCountertmp; //calculate the num of items sold
        notifyObserver("OpenTheStoreSold", this.name, amtSold, 0.0, this.currentStoreName);
        int invTmp = inventory.size(); //hold inventory size before sell func
        //1-4 selling customers
        while(counter < numSellingCustomers + numBuyingCustomers){
            counter ++;
            custNum++;
            cust = new Customer();
            cust.sellItem(inventory, register, this.name, custNum, dayNum);
        }
        int amtBought = inventory.size() - invTmp; //calculate amt bought
        notifyObserver("OpenTheStoreBought", this.name, amtBought, 0.0, this.currentStoreName);
    }

    public void cleanTheStore(ArrayList<Item> inventory){
        // Velma has a 5% chance of breaking an item
        // Shaggy has a 20% chance of breaking an item.
        System.out.printf("%s is cleaning %s.\n", this.name, this.currentStoreName); 
        //Random generator based off of percentages code sourced here
        //https://stackoverflow.com/questions/38838172/percentage-using-random/38838299
        //Start a random generator 1-100 and if num lands between 1-x then item will break
        double breakItem = this.rng.nextDouble() * 100;

        // dmgCounter keeps track of items damaged for Observer
        int dmgCounter = 0;

        if (breakItem < this.breakPercent){ //5% or 20% for now
            //Generate random number to determine which random item to break 
            int randBroken = this.rng.nextInt(inventory.size()); //index in array for broken item
            //get broken item... NOTE: Not sure if initialization to item works here
            Item itemBroken = inventory.get(randBroken); //itemBroken is the item that is broken

            // Lower the condition by 1
            String newCond = itemBroken.lowerCondition();
            // If the item has been destroyed, remove it from inventory
            if (newCond == "broken"){
                dmgCounter +=1;
                System.out.printf("Oh no! %s has broken an item! %s (%s) is now destroyed and has been removed from inventory.\n", this.name, itemBroken.getName(), itemBroken.getTypeStr());
                inventory.remove(randBroken);
            }
            // If not, reduce the price by 20%.
            else{
                //Reduce price by 20%
                dmgCounter +=1;
                double newPrice = itemBroken.lowerListPrice();
                System.out.printf("Oh no! %s has broken an item! The price of %s (%s) has been reduced to $%.2f and the condition is now %s.\n", this.name, itemBroken.getName(), itemBroken.getTypeStr(), newPrice, newCond);
            }
            
        }
        notifyObserver("CleanTheStore", this.name, dmgCounter, 0.0, this.currentStoreName);
    }

    public void leaveTheStore(){
        //announce that the clerk is leaving the store 
        System.out.printf("----- %s has locked up and closed %s for the night -----\n", this.name, this.currentStoreName);
        notifyObserver("LeaveTheStore", this.name, 0, 0.0, this.currentStoreName);
    }

    public void subscribe(Observer o){
        this.obsList.add(o);
    }

    //call to notify observer every time an action is updated
    public void notifyObserver(String thingUpdated, String clerkUpdate, int intUpdate, double doubleUpdate, String storeName){
        for (Observer observers : this.obsList){
            observers.update(thingUpdated, clerkUpdate,  intUpdate, doubleUpdate, storeName);
        }
    }
}