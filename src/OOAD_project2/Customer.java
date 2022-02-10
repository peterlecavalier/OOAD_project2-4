package src.OOAD_project2;

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
        Item curItem;
        switch (chosenSubclass){
            case PAPERSCORE:
                curItem = new PaperScore(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.nameGen(), h.nameGen());
                break;
            case CD:
                curItem = new CD(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.nameGen(), h.nameGen());
                break;
            case VINYL:
                curItem = new Vinyl(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.nameGen(), h.nameGen());
                break;
            case CDPLAYER:
                curItem = new CDPlayer(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen());
                break;
            case RECORDPLAYER:
                curItem = new RecordPlayer(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen());
                break;
            case MP3PLAYER:
                curItem = new MP3Player(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen());
                break;
            case GUITAR:
                curItem = new Guitar(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.boolGen());
                break;
            case BASS:
                curItem = new Bass(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.boolGen());
                break;
            case MANDOLIN:
                curItem = new Mandolin(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.boolGen());
                break;
            case FLUTE:
                curItem = new Flute(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.fluteTypeGen());
                break;
            case HARMONICA:
                curItem = new Flute(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.harmKeyGen());
                break;
            case HAT:
                curItem = new Hat(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.sizeGen());
                break;
            case SHIRT:
                curItem = new Shirt(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.sizeGen());
                break;
            case BANDANA:
                curItem = new Bandana(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen());
                break;
            case PRACTICEAMP:
                curItem = new PracticeAmp(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.wattLenGen());
                break;
            case CABLE:
                curItem = new Cable(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.wattLenGen());
                break;
            case STRINGS:
                curItem = new Strings(h.nameGen(), h.purchasePriceGen(), -1.0, h.newUsedGen(), 0, h.condGen(), h.stringGen());
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN PLACEANORDER");
        }
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
            
            int buyChance = rand.nextInt(100);
            if (buyChance < 50){ //50% chance
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
                int buyChance2 = rand.nextInt(100);
                if (buyChance2 < 75){
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
        Helpers quality = new Helpers(); //gonna need helpers to generate random conditions from condGen
        String condition = quality.condGen(); //generate random conditions
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

        //Round the sellOffer so it only has 2 places after the decimal
        sellOffer = Math.round(sellOffer * 100.0) / 100.0;

        //generate customer probability
        Random r = new Random();
        int cust_prob = r.nextInt(100);
        if (cust_prob < 50){
            a.setPurchasePrice(sellOffer); //set purchase price
            //inv.addToCurrInventory(a); //add item to inventory
            cash.payCustomer(sellOffer);
            System.out.printf("%s bought a %s %s condition %s (%s) from Customer %d for $%.2f.\n", clerkName, a.getNewUsed(), condition, a.getName(), a.getType(), customerNum, sellOffer);
        }
        else{
            double newPrice = sellOffer + (sellOffer * 0.1);
            newPrice = Math.round(newPrice * 100.0) / 100.0;
            int cust_prob2 = r.nextInt(100);
            if (cust_prob2 < 75){
                a.setDaySold(day);
                a.setPurchasePrice (newPrice); 
                // /inv.addToCurrInventory(a);
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