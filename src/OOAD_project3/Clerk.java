package src.OOAD_project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Clerk {
    private String name;
    private int breakPercent;
    private Helpers h = new Helpers();

    public Clerk(String clerkName, int breakP){
        this.name = clerkName;
        this.breakPercent = breakP;
    }

    public String getName(){
        return this.name;
    }

    public void arriveAtStore(int dayNum, ArrayList<Item> arrivedOrders, ArrayList<Item> inventory){
        System.out.printf("----- %s arrives at the store on day %d -----\n", this.name, dayNum);

        // Add any arrived orders to the inventory
        if (!arrivedOrders.isEmpty()){
            for(Item i : arrivedOrders){
                inventory.add(i);
                String itemName = i.getName();
                System.out.printf("Item %s (%s) arrived at the store.\n", itemName, i.getType());
            }
            // Reset arrivedOrders
            arrivedOrders.clear();
        }
    }

    public void checkRegister(CashRegister register){
        double moneyInReg = register.getMoneyAmt();
        System.out.printf("%s counted $%.2f in the register.\n", this.name, moneyInReg);
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
        System.out.printf("%s went to the bank and put $1000 in the register. There is now $%.2f in the register.\n", this.name, moneyInReg);
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
            System.out.printf("%s placed an order for %s (%s) for $%.2f.\n", this.name, curItem.getName(), curItem.getType(), purPrice);
        }
        return itemsInOrder;
    }

    public void openTheStore(ArrayList<Item> inventory, ArrayList<Item> soldItems, CashRegister register, int dayNum){
        Customer cust;
        int custNum=0;
        int counter = 0;
        Random r = new Random();
        // Poisson distribution for buying customers
        // 1-4 selling customers
        int numBuyingCustomers = h.poissonDist(3) + 2;
        int numSellingCustomers = r.nextInt(4) + 1;

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
        //Random generator based off of percentages code sourced here
        //https://stackoverflow.com/questions/38838172/percentage-using-random/38838299
        //Start a random generator 1-100 and if num lands between 1-x then item will break
        Random rand = new Random();
        double breakItem = rand.nextDouble() * 100;
        if (breakItem < this.breakPercent){ //5% or 20% for now
            //Generate random number to determine which random item to break 
            Random randItem = new Random();
            int randBroken = randItem.nextInt(inventory.size()); //index in array for broken item
            //get broken item... NOTE: Not sure if initialization to item works here
            Item itemBroken = inventory.get(randBroken); //itemBroken is the item that is broken

            // Lower the condition by 1
            String newCond = itemBroken.lowerCondition();
            // If the item has been destroyed, remove it from inventory
            if (newCond == "broken"){
                System.out.printf("Oh no! %s has broken an item! %s (%s) is now destroyed and has been removed from inventory.\n", this.name, itemBroken.getName(), itemBroken.getType());
                inventory.remove(randBroken);
            }
            // If not, reduce the price by 20%.
            else{
                //Reduce price by 20%
                double newPrice = itemBroken.lowerListPrice();
                System.out.printf("Oh no! %s has broken an item! The price of %s (%s) has been reduced to $%.2f and the condition is now %s.\n", this.name, itemBroken.getName(), itemBroken.getType(), newPrice, newCond);
            }
            
        }
    }

    public void leaveTheStore(){
        //announce that the clerk is leaving the store 
        System.out.printf("----- %s has locked up and closed the store for the night -----\n", this.name);
    }
}
