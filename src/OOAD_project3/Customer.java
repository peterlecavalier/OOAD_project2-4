package src.OOAD_project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
//example of cohesion, where a single class is designed to do a specific task.
//Customer handles 2 specific tasks of customer buying + customer selling, so this would be an example of high cohesion.
public class Customer {
    ArrayList<Item> allItems = new ArrayList<>();
    private Helpers h = new Helpers();

    private Item.Items chooseRandomSubclass(){
        // Chooses a random subclass from all the possible subclasses
        // Using the Item.Items enum
        // https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
        int numberOfItemTypes = Item.Items.values().length;
        Random rand = new Random();
        int enumChooser = rand.nextInt(numberOfItemTypes);
        List<Item.Items> enumValues = Arrays.asList(Item.Items.values());; 
        Item.Items chosenSubclass = enumValues.get(enumChooser);
        return chosenSubclass;
    }

    private Item itemPicker(){
        // Choose a random subclass, then create a new instance of that class.
        Item.Items chosenSubclass = this.chooseRandomSubclass();
        Item curItem = this.h.generateNewItem(chosenSubclass);
        return curItem;
    }

    public void buyItem(ArrayList<Item> inventory, CashRegister cash, ArrayList<Item> soldItems, String clerkName, int customerNum, int day){
        Item.Items chosenSubclass = this.chooseRandomSubclass();
        ArrayList<Integer> itemIdxs = new ArrayList<>();
        // Get the indexes of each item that matches the type the customer wants
        for (int i =0; i < inventory.size(); i++){
            if (chosenSubclass == inventory.get(i).getType()){
                itemIdxs.add(i);
            }
        }
        if (itemIdxs.size() > 0){
            Random rand = new Random();
            int chosenItemIdx = itemIdxs.get(rand.nextInt(itemIdxs.size())); //Choose a random item from the subclass
            Item itemSold = inventory.get(chosenItemIdx);
            //random generator to determine if customer wants to buy with 50% poss
            
            double buyChance = rand.nextDouble() * 100;
            if (buyChance < (50.0 + h.getPriceIncrease(itemSold))){ //50% chance (+ potential increase)
                itemSold.setDaySold(day); //Set day sold
                itemSold.setSalePrice(itemSold.getListPrice()); //Set the sale price
                soldItems.add(itemSold); //add to sold items inventory
                inventory.remove(chosenItemIdx); //remove from current inventory
                cash.addToRegister(itemSold.getListPrice());
                System.out.printf("%s sold a %s %s condition %s (%s) to Customer %d for $%.2f \n", clerkName, 
                itemSold.getNewUsed(),itemSold.getCondition(),itemSold.getName(), itemSold.getType(), customerNum, itemSold.getListPrice());
            }
            else{
                //add 10% discount
                double discountPrice = itemSold.getListPrice() - (itemSold.getListPrice() * 0.1);
                discountPrice = Math.round(discountPrice * 100.0) / 100.0;
                //update the cust buying chances
                double buyChance2 = rand.nextDouble() * 100;
                if (buyChance2 < (75.0 + h.getPriceIncrease(itemSold))){ // Potential price increase
                    itemSold.setDaySold(day); //Set day sold
                    itemSold.setSalePrice(discountPrice); //Set sale price as discount price
                    soldItems.add(itemSold);
                    inventory.remove(itemSold);
                    cash.addToRegister(discountPrice);
                    System.out.printf("%s sold a %s %s condition %s (%s) to Customer %d for $%.2f after a 10%% discount \n", clerkName,
                    itemSold.getNewUsed(),itemSold.getCondition(), itemSold.getName(), itemSold.getType(), customerNum, discountPrice);
                }
                else{
                    System.out.printf("Customer %d came in to buy a %s %s condition %s (%s) but didn't like the price, so they left.\n", customerNum, 
                    itemSold.getNewUsed(),itemSold.getCondition(), itemSold.getName(), chosenSubclass);
                }
            }
        }
        else{
            System.out.printf("Customer %d wanted to buy a %s but none were in inventory, so they left.\n", customerNum, chosenSubclass);
        }
    }

    public void sellItem(ArrayList<Item> inventory, CashRegister cash, String clerkName, int customerNum, int day){
        Item a = itemPicker();
        String condition = this.h.condGen(); //generate random conditions
        a.setCondition(condition); //set condition of item
        Random rand = new Random(); // this will be used to generate random price 
        double sellOffer = 0; 
        switch (condition){ //Switch condition with increasing random price range
            case "poor":
                sellOffer = 1 + rand.nextDouble() * (10 - 1);
                break;
            case "fair":
                sellOffer = 10 + rand.nextDouble() * (20 - 10);
                break;
            case "good":
                sellOffer = 20 + rand.nextDouble() * (30 - 20);
                break;
            case "very good":
                sellOffer = 30 + rand.nextDouble() * (40 - 30);
                break;
            case "excellent":
                sellOffer = 40 + rand.nextDouble() * (40 - 50);
                break;
        }

        if (a.getType() == Item.Items.HAT || a.getType() == Item.Items.SHIRT || a.getType() == Item.Items.BANDANA){
            if (!this.h.checkClothing(inventory)){
                System.out.printf("Customer %d wanted to sell a %s %s condition %s (%s), but the store does not buy clothing anymore.\n", customerNum, a.getNewUsed(), condition, a.getName(), a.getType());
                return;
            }
        }

        //Round the sellOffer so it only has 2 places after the decimal
        sellOffer = Math.round(sellOffer * 100.0) / 100.0;

        //generate customer probability
        double cust_prob = rand.nextDouble() * 100;
        if (cust_prob < 50.0){
            a.setPurchasePrice(sellOffer); //set purchase price
            cash.payCustomer(sellOffer);
            System.out.printf("%s bought a %s %s condition %s (%s) from Customer %d for $%.2f.\n", clerkName, a.getNewUsed(), condition, a.getName(), a.getType(), customerNum, sellOffer);
        }
        else{
            double newPrice = sellOffer + (sellOffer * 0.1);
            newPrice = Math.round(newPrice * 100.0) / 100.0;
            int cust_prob2 = rand.nextInt() * 100;
            if (cust_prob2 < 75.0){
                a.setDaySold(day);
                a.setPurchasePrice (newPrice); 
                cash.payCustomer(newPrice);
                System.out.printf("%s bought a %s %s condition %s (%s) from Customer %d for $%.2f after an addition of 10%%.\n", clerkName, 
                a.getNewUsed(), condition, a.getName(), a.getType(), customerNum, sellOffer);
            }
            else{
                System.out.printf("Customer %d wanted to sell a %s %s condition %s (%s), but didn't like the offer. They left.\n", customerNum, 
                a.getNewUsed(), condition, a.getName(), a.getType());
            }
        }
    }
}