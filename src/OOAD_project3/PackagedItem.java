package OOAD_project3;

import java.util.ArrayList;
import java.util.Random;


// Decorator here
// PackagedItem decorates the stringed sale
public abstract class PackagedItem extends Stringed{
    // Using similar approach to Bruce Montgomery's code
    // for decorating an Item.

    ArrayList<Item> packageItems;
    ArrayList<Item> inventory;

    PackagedItem(ArrayList<Item> inventory, ArrayList<Item> list){
        super(null, 0.0, 0.0, null, 0, null, false, false);
        this.packageItems = list;
        this.inventory = inventory;
    }

    public void FindPackageItem(Item.Items subclass){
        ArrayList<Integer> itemIdxs = new ArrayList<>();
        Item addingItem = null;
        // Get the indexes of each item that matches the type
        for (int i =0; i < this.inventory.size(); i++){
            if (subclass == this.inventory.get(i).getType()){
                itemIdxs.add(i);
            }
        }
        // If there are any items of that type, get it
        if (itemIdxs.size() > 0){
            Random rand = new Random();
            int chosenItemIdx = itemIdxs.get(rand.nextInt(itemIdxs.size())); //Choose a random item from the subclass
            addingItem = inventory.get(chosenItemIdx);
        }

        if (addingItem != null) {
            this.packageItems.add(addingItem);
            this.inventory.remove(addingItem);
        }
    }

    // Additional functionality needed for a packaged decorator
    public void setDaySold(int day){
        for (Item x : this.packageItems){
            x.setDaySold(day);
        }
    }

    public void setSalePrice(double price){
        // Sets the first items sale price to inputted price
        this.packageItems.get(0).setSalePrice(price);

        // Sets all other items sale price to their list price
        for (int i = 1; i < this.packageItems.size(); i++){
            Item curItem = this.packageItems.get(i);
            curItem.setSalePrice(curItem.getListPrice());
        }
    }

    public String getNewUsed(){
        return this.packageItems.get(0).getNewUsed();
    }

    public String getCondition(){
        return this.packageItems.get(0).getCondition();
    }

    public String getName(){
        // Appends all names to each other
        String totalName = this.packageItems.get(0).getName() + " w/ ";
        for (int i = 1; i < this.packageItems.size(); i++){
            totalName = totalName +  this.packageItems.get(i).getName() + "/";
        }
        // Remove the last slash from the totalName
        totalName = totalName.substring(0, totalName.length() - 1);

        return totalName;
    }

    public String getTypeStr(){
        // Appends all types to each other
        String totalType = this.packageItems.get(0).getTypeStr() + " w/ ";
        for (int i = 1; i < this.packageItems.size(); i++){
            totalType = totalType +  this.packageItems.get(i).getTypeStr() + "/";
        }
        // Remove the last slash from the totalType
        totalType = totalType.substring(0, totalType.length() - 1);

        return totalType;
    }

    public double getSalePrice(){
        double salePrice = 0.0;
        for (Item x : this.packageItems){
            salePrice += x.getSalePrice();
        }

        return salePrice;
    }

    public void sellThis(ArrayList<Item> inventory, CashRegister cash, ArrayList<Item> soldItems){
        for (Item x : this.packageItems){
            soldItems.add(x); //add to sold items inventory
            inventory.remove(x); //remove from current inventory
            cash.addToRegister(x.getSalePrice());
        }
    }
}


class AddGigBag extends PackagedItem{
    AddGigBag(ArrayList<Item> inventory, ArrayList<Item> list) {
        super(inventory, list);
        FindPackageItem(Item.Items.GIGBAG);
    }
}

class AddPracticeAmp extends PackagedItem{
    AddPracticeAmp(ArrayList<Item> inventory, ArrayList<Item> list) {
        super(inventory, list);
        FindPackageItem(Item.Items.PRACTICEAMP);
    }
}

class AddCable extends PackagedItem{
    AddCable(ArrayList<Item> inventory, ArrayList<Item> list) {
        super(inventory, list);
        FindPackageItem(Item.Items.CABLE);
    }
}

class AddStrings extends PackagedItem{
    AddStrings(ArrayList<Item> inventory, ArrayList<Item> list) {
        super(inventory, list);
        FindPackageItem(Item.Items.STRINGS);
    }
}
