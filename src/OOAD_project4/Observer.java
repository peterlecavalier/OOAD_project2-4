package OOAD_project4;

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



// Observer pattern implemented here
// Children Logger and Tracker extend it.
public abstract class Observer {
    public void update(String thingUpdated, String clerkUpdate, int intUpdate, double doubleUpdate, String storeName){
        return;
    }
}