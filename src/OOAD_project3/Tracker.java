package src.OOAD_project3;

import java.util.ArrayList;
// Import the HashMap class
import java.util.HashMap;

public class Tracker extends Observer{
    private HashMap<String, Integer> soldHash;
    private HashMap<String, Integer> purchasedHash;
    private HashMap<String, Integer> damagedHash;
    

    public Tracker(){
        soldHash = new HashMap<String, Integer>();
        purchasedHash = new HashMap<String, Integer>();
        damagedHash = new HashMap<String, Integer>();
    }

    public void update(String thingUpdated, String clerkUpdate, int intUpdate, double doubleUpdate){
        int curDamage;
        int curPurchase;
        int curSold;

        if (this.damagedHash.get(clerkUpdate) == null){
            curDamage = 0;
        }
        else{
            curDamage = this.damagedHash.get(clerkUpdate);
        }

        if (this.purchasedHash.get(clerkUpdate) == null){
            curPurchase = 0;
        }
        else{
            curPurchase = this.purchasedHash.get(clerkUpdate);
        }

        if (this.soldHash.get(clerkUpdate) == null){
            curSold = 0;
        }
        else{
            curSold = this.soldHash.get(clerkUpdate);
        }

        //Extra if statement to keep track of items broken (need to use intUpdate)
        if(thingUpdated == "DoInventory2"){
            this.damagedHash.put(clerkUpdate, curDamage + intUpdate);
        }
        else if (thingUpdated == "PlaceAnOrder"){
            this.purchasedHash.put(clerkUpdate, curPurchase + intUpdate);
        }
        else if (thingUpdated == "OpenTheStoreSold"){
            this.soldHash.put(clerkUpdate, curSold + intUpdate);
        }
        else if(thingUpdated == "OpenTheStoreBought"){
            this.purchasedHash.put(clerkUpdate, curPurchase + intUpdate);
        }
        else if (thingUpdated == "CleanTheStore"){
            this.damagedHash.put(clerkUpdate, curDamage + intUpdate);
        }
    }

    public void printSummary(int dayNum, ArrayList<Clerk> staff){
        String clerkName;
        int curDamage;
        int curPurchase;
        int curSold;
        // Print tracker summary
        System.out.println("Tracker - Day " + dayNum + ":");
        for(Clerk x : staff){
            clerkName = x.getName();
            if (this.damagedHash.get(clerkName) == null){
                curDamage = 0;
            }
            else{
                curDamage = this.damagedHash.get(clerkName);
            }

            if (this.purchasedHash.get(clerkName) == null){
                curPurchase = 0;
            }
            else{
                curPurchase = this.purchasedHash.get(clerkName);
            }

            if (this.soldHash.get(clerkName) == null){
                curSold = 0;
            }
            else{
                curSold = this.soldHash.get(clerkName);
            }

            System.out.printf("%s - %d items sold, %d items purchased, %d items damaged\n", clerkName, curSold, curPurchase, curDamage);
        }
    }
}
