package FNMS;

import java.util.ArrayList;

public class Store {
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private ArrayList<Item> soldItems = new ArrayList<Item>();

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    //This will only be accessed with other methods
    private void addToInventory(Item itemToAdd){
        inventory.add(itemToAdd);
    }

    private void initializeInv(){
        
    }

    // Store constructor
    public Store(){

    }
}
