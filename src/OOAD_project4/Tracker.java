package src.OOAD_project4;

import java.util.ArrayList;
// Import the HashMap class
import java.util.HashMap;

public class Tracker extends Observer{
    // ***Eager Instantiation Singleton***
    private static Tracker trackerInstance = new Tracker();

    // Redesigned these so that they are 2D Hashmaps to account for 2 stores
    private HashMap<String, HashMap<String, Integer>> soldHash;
    private HashMap<String, HashMap<String, Integer>> purchasedHash;
    private HashMap<String, HashMap<String, Integer>> damagedHash;
    

    private Tracker(){
        soldHash = new HashMap<String, HashMap<String, Integer>>();
        purchasedHash = new HashMap<String, HashMap<String, Integer>>();
        damagedHash = new HashMap<String, HashMap<String, Integer>>();
    }

    public static Tracker getInstance(){
        return trackerInstance;
    }

    public void update(String thingUpdated, String clerkUpdate, int intUpdate, double doubleUpdate, String storeName){
        int curDamage;
        int curPurchase;
        int curSold;

        // Initialize all hashmaps so their sub-maps can be accessed
        if (this.soldHash.get(storeName) == null){
            this.soldHash.put(storeName, new HashMap<String, Integer>());
        }
        if (this.purchasedHash.get(storeName) == null){
            this.purchasedHash.put(storeName, new HashMap<String, Integer>());
        }
        if (this.damagedHash.get(storeName) == null){
            this.damagedHash.put(storeName, new HashMap<String, Integer>());
        }


        if (this.damagedHash.get(storeName).get(clerkUpdate) == null){
            curDamage = 0;
        }
        else{
            curDamage = this.damagedHash.get(storeName).get(clerkUpdate);
        }

        if (this.purchasedHash.get(storeName).get(clerkUpdate) == null){
            curPurchase = 0;
        }
        else{
            curPurchase = this.purchasedHash.get(storeName).get(clerkUpdate);
        }

        if (this.soldHash.get(storeName).get(clerkUpdate) == null){
            curSold = 0;
        }
        else{
            curSold = this.soldHash.get(storeName).get(clerkUpdate);
        }

        //Extra if statement to keep track of items broken (need to use intUpdate)
        if(thingUpdated == "DoInventory2"){
            this.damagedHash.get(storeName).put(clerkUpdate, curDamage + intUpdate);
        }
        else if (thingUpdated == "PlaceAnOrder"){
            this.purchasedHash.get(storeName).put(clerkUpdate, curPurchase + intUpdate);
        }
        else if (thingUpdated == "OpenTheStoreSold"){
            this.soldHash.get(storeName).put(clerkUpdate, curSold + intUpdate);
        }
        else if(thingUpdated == "OpenTheStoreBought"){
            this.purchasedHash.get(storeName).put(clerkUpdate, curPurchase + intUpdate);
        }
        else if (thingUpdated == "CleanTheStore"){
            this.damagedHash.get(storeName).put(clerkUpdate, curDamage + intUpdate);
        }
    }

    public void printSummary(int dayNum, ArrayList<Clerk> staff, String storeName){
        String clerkName;
        int curDamage;
        int curPurchase;
        int curSold;
        // Print tracker summary
        System.out.println("Tracker - Day " + dayNum + " (" + storeName + ") :");
        for(Clerk x : staff){
            clerkName = x.getName();
            if (this.damagedHash.get(storeName).get(clerkName) == null){
                curDamage = 0;
            }
            else{
                curDamage = this.damagedHash.get(storeName).get(clerkName);
            }

            if (this.purchasedHash.get(storeName).get(clerkName) == null){
                curPurchase = 0;
            }
            else{
                curPurchase = this.purchasedHash.get(storeName).get(clerkName);
            }

            if (this.soldHash.get(storeName).get(clerkName) == null){
                curSold = 0;
            }
            else{
                curSold = this.soldHash.get(storeName).get(clerkName);
            }

            System.out.printf("%s - %d items sold, %d items purchased, %d items damaged\n", clerkName, curSold, curPurchase, curDamage);
        }
    }
}
