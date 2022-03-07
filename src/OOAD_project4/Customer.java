package src.OOAD_project4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
//example of cohesion, where a single class is designed to do a specific task.
//Customer handles 2 specific tasks of customer buying + customer selling, so this would be an example of high cohesion.
public class Customer {
    ArrayList<Item> allItems = new ArrayList<>();
    private Helpers h = new Helpers();
    private Random rng = new Random();

    private Item.Items chooseRandomSubclass(){
        // Chooses a random subclass from all the possible subclasses
        // Using the Item.Items enum
        // https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
        // - 1 to account for GuitarKit
        int numberOfItemTypes = Item.Items.values().length - 1;
        int enumChooser = this.rng.nextInt(numberOfItemTypes);
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
            int chosenItemIdx = itemIdxs.get(this.rng.nextInt(itemIdxs.size())); //Choose a random item from the subclass
            Item itemSold = inventory.get(chosenItemIdx);
            //BEGIN USER INTERACTION - cust num =99 indicates user interactive
            if(customerNum == 99){
                double soldPrice = itemSold.getListPrice();
                itemSold.setSalePrice(soldPrice); //Set the sale price
                System.out.printf("You want to buy a %s %s condition %s", itemSold.getNewUsed(), itemSold.getCondition(), itemSold.getName());
                System.out.printf("Would you like to buy it for $%.2f? \nY - yes \nN - no \n", itemSold.getSalePrice());
                Scanner input = new Scanner(System.in); //get input
                String userInput = input.nextLine();  
                if (userInput.equals("Y") || userInput.equals("y")){
                    itemSold.setDaySold(day); //Set day sold
                    itemSold.sellThis(inventory, cash, soldItems);
                    System.out.println("You have bought the item. \n");
                }
                else if (userInput.equals("N") || userInput.equals("n")){
                    double discountPrice = itemSold.getListPrice() - (itemSold.getListPrice() * 0.1);
                    discountPrice = Math.round(discountPrice * 100.0) / 100.0;
                    System.out.printf("%s is offering a 10%% discount and the price is now $%.2f. Would you like to buy the item? \nY - yes \nN - No \n", clerkName, discountPrice);
                    Scanner i = new Scanner(System.in); //read input again
                    String in = i.nextLine();
                    if (in.equals("Y")){
                        System.out.printf("You have bought the item from %s after a discount of 10%", clerkName);
                        itemSold.setDaySold(day); //Set day sold
                        itemSold.setSalePrice(discountPrice); //Set the sale price
                        itemSold.sellThis(inventory, cash, soldItems);
                        return;
                    }
                    else if (in.equals("N")){
                        System.out.println("You did not buy the item.");
                        return;
                    }
                }
                else{
                    System.out.println("Error! Please enter apropriate command.");
                }
                return;
            }
            //END OF USER INTERACTION 
            //random generator to determine if customer wants to buy with 50% poss
            double buyChance = this.rng.nextDouble() * 100;
            if (buyChance < (50.0 + h.getPriceIncrease(itemSold))){ //50% chance (+ potential increase)

                double soldPrice = itemSold.getListPrice();

                // Package potential items if a stringed instr.
                if (chosenSubclass == Item.Items.GUITAR || chosenSubclass == Item.Items.BASS || chosenSubclass == Item.Items.MANDOLIN){
                    itemSold = this.h.packageItems(inventory, itemSold);
                }
                itemSold.setDaySold(day); //Set day sold
                itemSold.setSalePrice(soldPrice); //Set the sale price
                // Combined removing from inventory, adding to soldItems, and
                // adding cash into register into one method.
                itemSold.sellThis(inventory, cash, soldItems);
                System.out.printf("%s sold a %s %s condition %s (%s) to Customer %d for $%.2f \n", clerkName, 
                itemSold.getNewUsed(),itemSold.getCondition(),itemSold.getName(), itemSold.getTypeStr(), customerNum, itemSold.getSalePrice());
            }
            else{
                //add 10% discount
                double discountPrice = itemSold.getListPrice() - (itemSold.getListPrice() * 0.1);
                discountPrice = Math.round(discountPrice * 100.0) / 100.0;
                //update the cust buying chances
                double buyChance2 = this.rng.nextDouble() * 100;
                if (buyChance2 < (75.0 + h.getPriceIncrease(itemSold))){ // Potential price increase

                    // Package potential items if a stringed instr.
                    if (chosenSubclass == Item.Items.GUITAR || chosenSubclass == Item.Items.BASS || chosenSubclass == Item.Items.MANDOLIN){
                        itemSold = this.h.packageItems(inventory, itemSold);
                    }
                    
                    itemSold.setDaySold(day); //Set day sold
                    itemSold.setSalePrice(discountPrice); //Set sale price as discount price
                    // Combined removing from inventory, adding to soldItems, and
                    // adding cash into register into one method.
                    itemSold.sellThis(inventory, cash, soldItems);
                    System.out.printf("%s sold a %s %s condition %s (%s) to Customer %d for $%.2f after a 10%% discount \n", clerkName,
                    itemSold.getNewUsed(),itemSold.getCondition(), itemSold.getName(), itemSold.getTypeStr(), customerNum, itemSold.getSalePrice());
                }
                else{
                    System.out.printf("Customer %d came in to buy a %s %s condition %s (%s) but didn't like the price, so they left.\n", customerNum, 
                    itemSold.getNewUsed(),itemSold.getCondition(), itemSold.getName(), chosenSubclass.toString().toLowerCase());
                }
            }
        }
        else{
            System.out.printf("Customer %d wanted to buy a %s but none were in inventory, so they left.\n", customerNum, chosenSubclass.toString().toLowerCase());
        }
    }

    public void sellItem(ArrayList<Item> inventory, CashRegister cash, String clerkName, int customerNum, int day){
        Item a = itemPicker();
        a.setDayArrived(day);
        String condition = this.h.condGen(); //generate random conditions
        a.setCondition(condition); //set condition of item
        double sellOffer = 0; 

        switch (condition){ //Switch condition with increasing random price range
            case "poor":
                sellOffer = 1 + this.rng.nextDouble() * (10 - 1);
                break;
            case "fair":
                sellOffer = 10 + this.rng.nextDouble() * (20 - 10);
                break;
            case "good":
                sellOffer = 20 + this.rng.nextDouble() * (30 - 20);
                break;
            case "very good":
                sellOffer = 30 + this.rng.nextDouble() * (40 - 30);
                break;
            case "excellent":
                sellOffer = 40 + this.rng.nextDouble() * (40 - 50);
                break;
        }

        if (a.getType() == Item.Items.HAT || a.getType() == Item.Items.SHIRT || a.getType() == Item.Items.BANDANA){

            if (!this.h.checkClothing(inventory)){
                System.out.printf("Customer %d wanted to sell a %s %s condition %s (%s), but the store does not buy clothing anymore.\n", customerNum, a.getNewUsed(), condition, a.getName(), a.getTypeStr());
                return;
            }
        }

        //BEGIN USER INTERACTION 
        if (customerNum == 99){
            System.out.printf("You have a " +a.getNewUsed()+ " condition " + condition + a.getName() + " to sell to " + clerkName +"\n" );
            System.out.printf("%s is offering $%.2f for the item. Would you like to sell it? \nY - yes \nN - No \n", clerkName, sellOffer);
            //get user input 
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();  
            if (userInput.equals("Y") || userInput.equals("y")){
                //update inventory
                System.out.println("You have sold the item to " + clerkName);
                a.buyThis(inventory);
                a.setPurchasePrice(sellOffer);
            }
            else if (userInput.equals("N") || userInput.equals("n")){
                double newPrice = sellOffer + (sellOffer * 0.1);
                newPrice = Math.round(newPrice * 100.0) / 100.0;
                System.out.printf("%s is now offering $%.2f for the item. Would you like to sell it? \nY - yes \nN - No \n", clerkName, newPrice);
                Scanner i = new Scanner(System.in);
                String in = i.nextLine();
                if (in.equals("Y")){
                    System.out.println("You have sold the item to " + clerkName + "after an addition of 10%");
                    a.buyThis(inventory);
                    a.setPurchasePrice(newPrice);
                    return;
                }
                else if (in.equals("N")){
                    System.out.println("You did not sell the item to the store");
                    return;
                }
            }
            else{
                System.out.println("Error! Please enter apropriate command.");
            }
            return;
        }
        //END OF USER INTERACTION 
        //Round the sellOffer so it only has 2 places after the decimal
        sellOffer = Math.round(sellOffer * 100.0) / 100.0;

        //generate customer probability
        double cust_prob = this.rng.nextDouble() * 100;
        if (cust_prob < 50.0){
            a.buyThis(inventory);//add to inventory
            a.setPurchasePrice(sellOffer); //set purchase price
            cash.payCustomer(sellOffer);
            System.out.printf("%s bought a %s %s condition %s (%s) from Customer %d for $%.2f.\n", clerkName, a.getNewUsed(), condition, a.getName(), a.getTypeStr(), customerNum, sellOffer);
        }
        else{
            double newPrice = sellOffer + (sellOffer * 0.1);
            newPrice = Math.round(newPrice * 100.0) / 100.0;
            int cust_prob2 = this.rng.nextInt() * 100;
            if (cust_prob2 < 75.0){
                a.buyThis(inventory);//add to inventory
                a.setDaySold(day);
                a.setPurchasePrice (newPrice); 
                cash.payCustomer(newPrice);
                System.out.printf("%s bought a %s %s condition %s (%s) from Customer %d for $%.2f after an addition of 10%%.\n", clerkName, 
                a.getNewUsed(), condition, a.getName(), a.getTypeStr(), customerNum, sellOffer);
            }
            else{
                System.out.printf("Customer %d wanted to sell a %s %s condition %s (%s), but didn't like the offer. They left.\n", customerNum, 
                a.getNewUsed(), condition, a.getName(), a.getTypeStr());
            }
        }
    }
}