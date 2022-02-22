package src.OOAD_project3;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.crypto.KeySelector.Purpose;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter; 
import java.io.IOException;

import src.OOAD_project3.observer;

import java.util.HashMap;

public class Clerk{
    private static final Exception IOException = null;
    private String name;
    private int breakPercent;
    private Tuning tuning;
    private int itemsSold;
    private int itemsPurchased;
    private int itemsBroken; //in tuning
    private int itemsBrokenCleaning;
    private double moneyInRegister; 
    private double purchasePriceValue;
    private double moneyFromBank;
    private int itemsInInventory;
    private int itemsOrdered;
    private int itemsAddedtoInventory;
    private Helpers h = new Helpers();
    private Random rng = new Random();
    private ArrayList<observer> obsList = new ArrayList<observer>();
    private int day;

    public int getDay(){
        return this.day;
    }

    public void setDay(int dayn){
        this.day = dayn;
    }
    public Clerk(String clerkName, int breakP, Tuning tuner){
        this.name = clerkName;
        this.breakPercent = breakP;
        this.tuning = tuner;
    }

    public String getName(){
        return this.name;
    }

    public int tuneItem(Item tuningItem){
        return tuning.doTuning(tuningItem);
    }

    public String getTuningStr(){
        return tuning.getTuningTypeStr();
    }

    public void arriveAtStore(int dayNum, ArrayList<Item> arrivedOrders, ArrayList<Item> inventory){
        System.out.printf("----- %s arrives at the store on day %d -----\n", this.name, dayNum);
        notifyObserver(); 
        // Add any arrived orders to the inventory
        if (!arrivedOrders.isEmpty()){
            for(Item i : arrivedOrders){
                inventory.add(i);
                String itemName = i.getName();
                System.out.printf("Item %s (%s) arrived at the store.\n", itemName, i.getTypeStr());
                setDay(dayNum);
                logger("Item " + itemName + " arrived at the store.\n"); //Insert into logger
            }
            // Reset arrivedOrders
            arrivedOrders.clear();
        }
    }

    public void checkRegister(CashRegister register){
        double moneyInReg = register.getMoneyAmt();
        System.out.printf("%s counted $%.2f in the register.\n", this.name, moneyInReg);
        logger(this.name + " counted " + moneyInReg + " in the register.\n"); //Insert into logger
        if (moneyInReg < 75.0){
            this.goToBank(register);
        }
        this.moneyInRegister = moneyInReg;
        notifyObserver(); //publish amt of money in register
    }

    private void goToBank(CashRegister register){
        // Go to the bank
        // Keep track of the total money added from the bank
        register.addMoneyFromBank(1000.0);
        //... and add it to the register
        register.addToRegister(1000.0);
        double moneyInReg = register.getMoneyAmt();
        //notify observer and update money in bank amt
        this.moneyFromBank = moneyInReg;
        notifyObserver();
        System.out.printf("%s went to the bank and put $1000 in the register. There is now $%.2f in the register.\n", this.name, moneyInReg);
        logger(this.name + " went to the bank and put $1000 in the register. There is now " + moneyInReg +" in the register.\n"); //Insert into logger
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

        System.out.printf("%s did inventory. Total value in the store is $%.2f.\n", this.name, totalPrices);
        logger(this.name + " did inventory. Total value in the store is " + totalPrices);
        // brokenItems keeps track of all items broken during tuning
        ArrayList<Integer> brokenItems = new ArrayList<>();
        // invCounter keeps track of item number in inventory
        int invCounter = -1;

        // Do tuning
        for (Item i : inventory){
            invCounter++;
            int tuningResult = this.tuneItem(i);
            if (tuningResult == -2){
                continue;
            }
            System.out.printf("%s attempted %s tuning on %s (%s):\n", this.name, this.getTuningStr(), i.getName(), i.getTypeStr());
            logger(this.name + " attempted " + this.getTuningStr() + " tuning on " + i.getName() + "\n");
            if (tuningResult == -1){
                // If switched from true to false
                System.out.println("    ->Oh no! Tuning went awry - status changed from true to false.");
                logger("    ->Oh no! Tuning went awry - status changed from true to false.");
                if (this.rng.nextDouble() < 0.1){
                    // Lower the condition by 1
                    String newCond = i.lowerCondition();
                    // If the item has been destroyed, remove it from inventory
                    int brokenitems; //counter for observer
                    if (newCond == "broken"){
                        System.out.printf("Oh no! %s has broken an item! %s (%s) is now destroyed and has been removed from inventory.\n", this.name, i.getName(), i.getTypeStr());
                        logger("Oh no! " + this.name + " has broken an item! " + i.getName() + " is now destroyed and has been removed from inventory. \n");
                        // Do in reverse order so that removing one item doesn't affect
                        // the order of other to-be-removed items
                        brokenItems.add(0, invCounter);
                        notifyObserver();
                    }
                    // If not, reduce the price by 20%.
                    else{
                        //Reduce price by 20%
                        double newPrice = i.lowerListPrice();
                        System.out.printf("Oh no! %s has broken an item! The price of %s (%s) has been reduced to $%.2f and the condition is now %s.\n", this.name, i.getName(), i.getTypeStr(), newPrice, newCond);
                        logger("Oh no! " + this.name + " has broken an item! The price of " + i.getName() + " has been reduced to $" + newPrice + " and the condition is now " + newCond + "\n");
                    }
                }
            }
            else if (tuningResult == 0){
                System.out.println("    ->Tuning status did not change from false.");
                logger("    ->Tuning status did not change from false.\n");
            }
            else if (tuningResult == 1){
                System.out.println("    ->Tuning status did not change from true.");
                logger("    ->Tuning status did not change from true.\n");
            }
            else if (tuningResult == 2){
                System.out.println("    ->Success! Tuning status changed from false to true.");
                logger("    ->Success! Tuning status changed from false to true.\n");
            }
        }

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
        notifyObserver();
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
            logger(this.name + " placed an order for " + curItem.getName() + " for " + purPrice +"\n");
            this.purchasePriceValue = purPrice;
        }
        return itemsInOrder;
    }

    public void openTheStore(ArrayList<Item> inventory, ArrayList<Item> soldItems, CashRegister register, int dayNum){
        setDay(dayNum);
        Customer cust;
        int custNum=0;
        int counter = 0;
        // Poisson distribution for buying customers
        // 1-4 selling customers
        int numBuyingCustomers = h.poissonDist(3) + 2;
        int numSellingCustomers = this.rng.nextInt(4) + 1;

        //There will be a Poisson distribution for buying customers
        //Using the counter to generate the right amount of buying and selling customers
        //CustNum is to track the customer number that will be printed
        while (counter < numBuyingCustomers){
            counter ++;
            custNum ++;
            cust = new Customer();
            cust.buyItem(inventory, register, soldItems, this.name, custNum, dayNum);
        }
        //1-4 selling customers
        while(counter < numSellingCustomers + numBuyingCustomers){
            counter ++;
            custNum++;
            cust = new Customer();
            cust.sellItem(inventory, register, this.name, custNum, dayNum);
        }
    }

    public void cleanTheStore(ArrayList<Item> inventory){
        // Velma has a 5% chance of breaking an item
        // Shaggy has a 20% chance of breaking an item.
        System.out.printf("%s is cleaning the store.\n", this.name);
        logger(this.name + " is cleaning the store. \n");
        //Random generator based off of percentages code sourced here
        //https://stackoverflow.com/questions/38838172/percentage-using-random/38838299
        //Start a random generator 1-100 and if num lands between 1-x then item will break
        double breakItem = this.rng.nextDouble() * 100;
        if (breakItem < this.breakPercent){ //5% or 20% for now
            this.itemsBrokenCleaning+=1; 
            notifyObserver();
            //Generate random number to determine which random item to break 
            int randBroken = this.rng.nextInt(inventory.size()); //index in array for broken item
            //get broken item... NOTE: Not sure if initialization to item works here
            Item itemBroken = inventory.get(randBroken); //itemBroken is the item that is broken

            // Lower the condition by 1
            String newCond = itemBroken.lowerCondition();
            // If the item has been destroyed, remove it from inventory
            if (newCond == "broken"){
                System.out.printf("Oh no! %s has broken an item! %s (%s) is now destroyed and has been removed from inventory.\n", this.name, itemBroken.getName(), itemBroken.getTypeStr());
                logger("Oh no! "+ this.name+ " has broken an item! " + itemBroken.getName() + " is now destroyed and has been removed from inventory.\n");
                inventory.remove(randBroken);
            }
            // If not, reduce the price by 20%.
            else{
                //Reduce price by 20%
                double newPrice = itemBroken.lowerListPrice();
                System.out.printf("Oh no! %s has broken an item! The price of %s (%s) has been reduced to $%.2f and the condition is now %s.\n", this.name, itemBroken.getName(), itemBroken.getTypeStr(), newPrice, newCond);
                logger("Oh no! " + this.name + " has broken an item! The price of " + itemBroken.getName() + " has been reduced to $" + newPrice + " and the condition is now " + newCond);
            }
            
        }
    }

    public void leaveTheStore(){
        //announce that the clerk is leaving the store 
        System.out.printf("----- %s has locked up and closed the store for the night -----\n", this.name);
        logger("-----" +this.name + " has locked up and closed the store for the night -----\n");
        notifyObserver();
    }

    //event consumer logger that writes to file
    public void logger(String input){
        int dayNum = getDay();
        File log = new File("Logger-" + dayNum + ".txt");
        try{
            if(log.exists() == false){
                System.out.println("Creating new file");
                log.createNewFile();
            }
            PrintWriter writer = new PrintWriter(new FileWriter(log, true)); //create file called Logger-n.txt
            writer.println(input);
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error in logging");
        }
    }
    //subscribe observers
    public void subscribeObserver(observer ob){
        obsList.add(ob);
    }
    //call to notify observer every time an action is updated
    public void notifyObserver(){
        for (observer observers : obsList) {
            observers.update(name, itemsAddedtoInventory,moneyInRegister, moneyFromBank, itemsInInventory, purchasePriceValue,itemsBroken, itemsOrdered, itemsSold, itemsPurchased, itemsBrokenCleaning, name);
         }
    }
}
