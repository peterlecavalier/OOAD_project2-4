/*Publish the following events:
 ArriveAtStore: Publish which clerk has arrived at the store.
 ArriveAtStore: Publish number of items added to inventory (if any).
 CheckRegister: Publish the amount of money in the register.
 GoToBank: Publish the amount of money in the register.
 DoInventory: Publish the total number of items.
 DoInventory: Publish the total purchase price value of inventory items.
 DoInventory: Publish the total number of items damaged in tuning.
 PlaceAnOrder: Publish the total number of items ordered.
 OpenTheStore: Publish the total number of items sold.
 OpenTheStore: Publish the total number of items purchased.
 CleanTheStore: Publish the total number of items damaged in cleaning.
 LeaveTheStore: Publish which clerk has left the store*/
package src.OOAD_project3;

import java.util.ArrayList;
// Import the HashMap class
import java.util.HashMap;

public abstract class observer {
    //if statements to print out the correct msg
    public void update(String thingUpdated, String clerkUpdate, int intUpdate, double doubleUpdate) {
        if (thingUpdated == "ArriveAtStore") {
            System.out.printf(" ---------------------- ArriveAtStore: " + clerkUpdate + " has arrived at the store! ---------------------- \n");
            System.out.printf(" ---------------------- ArriveAtStore: " + intUpdate + " items have arrived at the store! ---------------------- \n");
        }
        if (thingUpdated == "CheckRegister"){
            System.out.printf(" ---------------------- CheckRegister: %s counted $%.2f in the register. ---------------------- \n", clerkUpdate, doubleUpdate);
        }
        if (thingUpdated == "GoToBank"){
            System.out.printf(" ---------------------- GoToBank: %s went to the bank. There is now $%.2f in the register. ---------------------- \n", clerkUpdate, doubleUpdate);
        }
        if(thingUpdated == "DoInventory"){
            System.out.printf(" ---------------------- DoInventory: %s did inventory. There are %d of items. \n", clerkUpdate, intUpdate);
            System.out.printf(" ---------------------- DoInventory: %s did inventory. The total purchase price of value is $%.2f ---------------------- \n", clerkUpdate, doubleUpdate);
        }
        //Extra if statement to keep track of items broken (need to use intUpdate)
        if(thingUpdated == "DoInventory2"){
            System.out.printf(" ---------------------- DoInventory: %s did inventory. There are %d items broken from tuning.----------------------  \n", clerkUpdate, intUpdate);
        }
        if (thingUpdated == "PlaceAnOrder"){
            System.out.printf(" ---------------------- PlaceAnOrder: %s placed an order for %d items. ---------------------- \n", clerkUpdate, intUpdate);
        }
        if (thingUpdated == "OpenTheStoreSold"){
            System.out.printf(" ---------------------- OpenTheStore: %s sold %d items. ----------------------  \n", clerkUpdate, intUpdate);
        }
        if(thingUpdated == "OpenTheStoreBought"){
            System.out.printf(" ---------------------- OpenTheStore: %s bought %d items. ----------------------  \n", clerkUpdate, intUpdate);
        }
        if (thingUpdated == "CleanTheStore"){
            System.out.printf(" ---------------------- CleanTheStore: %s cleaned the store and damaged %d items. ----------------------  \n", clerkUpdate, intUpdate);
        }
        if (thingUpdated == "LeaveTheStore"){
            System.out.printf(" ---------------------- LeaveTheStore: %s has left the store. ----------------------  \n", clerkUpdate);
        }
    }
}

// tmp class to implement observer o1 = new tmp() in Clerk.java
class tmp extends observer {
    public void update() {
    }
}
/*
 * class tracker extends observer{
 * //Use 3 hash maps for tracker
 * ArrayList<Clerk> clerkinfo = new ArrayList<>();
 * 
 * @Override
 * public void update() {
 * //System.out.println( name + " " +itemsSold + " " + itemsPurchased + " " +
 * itemsDamaged);
 * ClerkInfo.add( name + " " +itemsSold + " " + itemsPurchased + " " +
 * itemsDamaged);
 * 
 * printArray(ClerkInfo);
 * }
 * 
 * public void printArray(ArrayList<String> ClerkInfo){
 * for (int i =0; i< ClerkInfo.size(); i++){;
 * System.out.println("Printing info:" + ClerkInfo.get(i));
 * }
 * }
 */

// }