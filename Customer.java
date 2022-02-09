import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
public class Customer {
    ArrayList<Item> allItems = new ArrayList<>();
    private Helpers h = new Helpers();
    
    /*private void addToInventory(Item itemToAdd){
        this.allItems.add(itemToAdd);
        //System.out.print(i);
    }*/
    //itemPicker initializes an arraylist of item types and returns 1 random item for the customer to buy or sell
    /*private Item itemPicker(){
        for (int i = 0; i < 1; i++){
            Item curItem;
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
        //generate and return rand instance
        //System.out.print(allItems.size());
        Random r = new Random();
        int pick = r.nextInt(5);
        return allItems.get(pick);
        //return allItems.get(pick);
    }*/
    private Item itemPicker(){
        int numberOfItemTypes = Item.Items.values().length;
        Random rand = new Random();
        int enumChooser = rand.nextInt(numberOfItemTypes);
        List<Item.Items> enumValues = Arrays.asList(Item.Items.values());; 
        Item.Items chosenSubclass = enumValues.get(enumChooser); 
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
        System.out.print("Returned");
        return curItem;
    }

    public void buyItem(ArrayList<Item> inventory, String clerkName, int customerNum, int day){
        CashRegister cash = new CashRegister(); //to store and update the register
        Store soldItems = new Store();
        int match =0;
        Item a = itemPicker(); //Generate random item to buy
        //Using a for loop to check if the item customer wants to buy is in inventory
        for (int i =0; i < inventory.size(); i++){
            //if a match is found, update parameters and exit
            if (inventory.get(i) == a){
                match =1;
                Item itemSold = inventory.get(i);
                //random generator to determine if customer wants to buy with 50% poss
                Random rand = new Random();
                int buyChance = rand.nextInt(100);
                if (buyChance < 50){ //50% chance
                    itemSold.setDaySold(day); //Set day sold
                    soldItems.addToSold(itemSold); //add to sold items inventory
                    inventory.remove(itemSold); //remove from current inventory
                    cash.addToRegister(itemSold.getSalePrice());
                    System.out.printf("%s sold a %s to Customer %d for $%d", clerkName, itemSold.getName(), customerNum, itemSold.getSalePrice());
                    break; //Using breaks so item doesn't match item in inventory more than once
                }
                else{
                    //add 10% discount
                    double discountPrice = itemSold.getSalePrice() + (itemSold.getSalePrice() * 0.1);
                    //update the cust buying chances
                    Random rr = new Random();
                    int buyChance2 = rand.nextInt(100);
                    if (buyChance2 < 75){
                        itemSold.setsalePrice(discountPrice); //Set sale price as discount price
                        soldItems.addToSold(itemSold);
                        inventory.remove(itemSold);
                        cash.addToRegister(discountPrice);
                        System.out.printf("%s sold a %s to Customer %d for $%d after a 10% discount ", clerkName, itemSold.getName(), customerNum, discountPrice);
                        break;
                    }
                }
            }
            if (match ==0){
                //System.out.printf("---- Customer %d wanted to buy a %s but none were in inventory, so they left ----\n", customerNum, a.getName());
            }
        }
    }

    public void sellItem(ArrayList<Item> inventory, String clerkName, int customerNum, int day){
        CashRegister cash = new CashRegister();
        Item a = itemPicker();
        Helpers quality = new Helpers(); //gonna need helpers to generate random conditions from condGen
        String condition = quality.condGen(); //generate random conditions
        a.setCondition(condition); //set condition of item
        Random rand = new Random(); // this will be used to generate random price 
        int sellOffer = 0; 
        switch (condition){ //Switch condition with increasing random price range
            case "poor":
                sellOffer = rand.nextInt(5);
                break;
            case "fair":
                sellOffer = rand.nextInt(6);
                break;
            case "good":
                sellOffer = rand.nextInt(8);
                break;
            case "very good":
                sellOffer = rand.nextInt(10);
                break;
            case "excellent":
                sellOffer = rand.nextInt(15);
                break;
        }
        //generate customer probability
        Random r = new Random();
        int cust_prob = r.nextInt(100);
        if (cust_prob <50){
            a.setDaySold(day);
            a.setPurchasePrice(sellOffer); //set purchase price
            //inv.addToCurrInventory(a); //add item to inventory
            cash.payCustomer(sellOffer);
            System.out.printf("%s bought a %s condition %s from Customer %d for $%d.", clerkName, condition, a.getName(), customerNum, sellOffer);
        }
        else{
            double newPrice = sellOffer + (sellOffer * 0.1);
            Random rr = new Random();
            int cust_prob2 = rr.nextInt(100);
            if (cust_prob2 < 75){
                a.setDaySold(day);
                a.setPurchasePrice (newPrice); 
                // /inv.addToCurrInventory(a);
                cash.payCustomer(newPrice);
                System.out.printf("%s bought a %s condition %s from Customer %d for $%d with an addition of 10%.", clerkName, condition, a.getName(), customerNum, sellOffer);
            }
        }
    }
}