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

public interface observer{
    public void update(String name, int itemsAdded, double amtMoney, double amtMoneyBank, int numItems, double purPrice, int itemsDamaged,
    int itemsOrdered, int itemsSold, int itemsPurchased, int itemsDmgClean, String clerkLeave);
}