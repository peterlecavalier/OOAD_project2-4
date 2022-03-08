package OOAD_project3;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter; 
import java.io.IOException;

public class Logger extends Observer{
    //event consumer logger that writes to file
    private int dayNum;

    public Logger(int day){
        this.dayNum = day;
    }

    public void logInput(String input){
        File log = new File("Logger-" + this.dayNum + ".txt");
        try{
            if(log.exists() == false){
                System.out.println("Creating new file");
                log.createNewFile();
            }
            PrintWriter writer = new PrintWriter(new FileWriter(log, true)); //create file called Logger-n.txt
            writer.println(input);
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error in logging");
        }
    }

    public void update(String thingUpdated, String clerkUpdate, int intUpdate, double doubleUpdate) {
        String inputStr;
        if (thingUpdated == "ArriveAtStore") {
            this.logInput(" ---------------------- ArriveAtStore: " + clerkUpdate + " has arrived at the store! ---------------------- \n");
            this.logInput(" ---------------------- ArriveAtStore: " + intUpdate + " items have arrived at the store! ---------------------- \n");
        }
        else if (thingUpdated == "CheckRegister"){
            inputStr = String.format(" ---------------------- CheckRegister: %s counted $%.2f in the register. ---------------------- \n", clerkUpdate, doubleUpdate);  
            this.logInput(inputStr);
        }
        else if (thingUpdated == "GoToBank"){
            inputStr = String.format(" ---------------------- GoToBank: %s went to the bank. There is now $%.2f in the register. ---------------------- \n", clerkUpdate, doubleUpdate);
            this.logInput(inputStr);
        }
        else if(thingUpdated == "DoInventory"){
            inputStr = String.format(" ---------------------- DoInventory: %s did inventory. There are %d of items. ----------------------\n", clerkUpdate, intUpdate);
            this.logInput(inputStr);
            inputStr = String.format(" ---------------------- DoInventory: %s did inventory. The total purchase price of value is $%.2f ---------------------- \n", clerkUpdate, doubleUpdate);
            this.logInput(inputStr);
        }
        //Extra if statement to keep track of items broken (need to use intUpdate)
        else if(thingUpdated == "DoInventory2"){
            inputStr = String.format(" ---------------------- DoInventory: %s did inventory. There are %d items broken from tuning.----------------------  \n", clerkUpdate, intUpdate);
            this.logInput(inputStr);
        }
        else if (thingUpdated == "PlaceAnOrder"){
            inputStr = String.format(" ---------------------- PlaceAnOrder: %s placed an order for %d items. ---------------------- \n", clerkUpdate, intUpdate);
            this.logInput(inputStr);
        }
        else if (thingUpdated == "OpenTheStoreSold"){
            inputStr = String.format(" ---------------------- OpenTheStore: %s sold %d items. ----------------------  \n", clerkUpdate, intUpdate);
            this.logInput(inputStr);
        }
        else if(thingUpdated == "OpenTheStoreBought"){
            inputStr = String.format(" ---------------------- OpenTheStore: %s bought %d items. ----------------------  \n", clerkUpdate, intUpdate);
            this.logInput(inputStr);
        }
        else if (thingUpdated == "CleanTheStore"){
            inputStr = String.format(" ---------------------- CleanTheStore: %s cleaned the store and damaged %d items. ----------------------  \n", clerkUpdate, intUpdate);
            this.logInput(inputStr);
        }
        else if (thingUpdated == "LeaveTheStore"){
            inputStr = String.format(" ---------------------- LeaveTheStore: %s has left the store. ----------------------  \n", clerkUpdate);
            this.logInput(inputStr);
        }
    }   
}