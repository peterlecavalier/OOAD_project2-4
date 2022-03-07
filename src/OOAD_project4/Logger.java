package src.OOAD_project4;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter; 
import java.io.IOException;

public class Logger extends Observer{
    // ***Lazy Instantiation Singleton***
    private static Logger loggerInstance;

    //event consumer logger that writes to file
    private int dayNum;

    private Logger(){}

    public static Logger getInstance(){
        if (loggerInstance == null){
            loggerInstance = new Logger();
        }
        return loggerInstance;
    }

    public void setDay(int day){
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

    public void update(String thingUpdated, String clerkUpdate, int intUpdate, double doubleUpdate, String storeName) {
        String inputStr;
        if (thingUpdated == "ArriveAtStore") {
            inputStr = String.format(" ---------------------- ArriveAtStore: %s has arrived at %s! ---------------------- \n", clerkUpdate, storeName);
            this.logInput(inputStr);
            inputStr = String.format(" ---------------------- ArriveAtStore: %d items have arrived at %s! ---------------------- \n", intUpdate, storeName);
            this.logInput(inputStr);
        }
        else if (thingUpdated == "CheckRegister"){
            inputStr = String.format(" ---------------------- CheckRegister: %s counted $%.2f in the register at %s. ---------------------- \n", clerkUpdate, doubleUpdate, storeName);  
            this.logInput(inputStr);
        }
        else if (thingUpdated == "GoToBank"){
            inputStr = String.format(" ---------------------- GoToBank: %s went to the bank. There is now $%.2f in the register at %s. ---------------------- \n", clerkUpdate, doubleUpdate, storeName);
            this.logInput(inputStr);
        }
        else if(thingUpdated == "DoInventory"){
            inputStr = String.format(" ---------------------- DoInventory: %s did inventory. There are %d items at %s. ----------------------\n", clerkUpdate, intUpdate, storeName);
            this.logInput(inputStr);
            inputStr = String.format(" ---------------------- DoInventory: %s did inventory. The total value is $%.2f at %s ---------------------- \n", clerkUpdate, doubleUpdate, storeName);
            this.logInput(inputStr);
        }
        //Extra if statement to keep track of items broken (need to use intUpdate)
        else if(thingUpdated == "DoInventory2"){
            inputStr = String.format(" ---------------------- DoInventory: %s did inventory. There are %d items broken from tuning at %s.----------------------  \n", clerkUpdate, intUpdate, storeName);
            this.logInput(inputStr);
        }
        else if (thingUpdated == "PlaceAnOrder"){
            inputStr = String.format(" ---------------------- PlaceAnOrder: %s placed an order for %d items at %s. ---------------------- \n", clerkUpdate, intUpdate, storeName);
            this.logInput(inputStr);
        }
        else if (thingUpdated == "OpenTheStoreSold"){
            inputStr = String.format(" ---------------------- OpenTheStore: %s sold %d items at %s. ----------------------  \n", clerkUpdate, intUpdate, storeName);
            this.logInput(inputStr);
        }
        else if(thingUpdated == "OpenTheStoreBought"){
            inputStr = String.format(" ---------------------- OpenTheStore: %s bought %d items at %s. ----------------------  \n", clerkUpdate, intUpdate, storeName);
            this.logInput(inputStr);
        }
        else if (thingUpdated == "CleanTheStore"){
            inputStr = String.format(" ---------------------- CleanTheStore: %s cleaned %s and damaged %d items. ----------------------  \n", clerkUpdate, storeName, intUpdate);
            this.logInput(inputStr);
        }
        else if (thingUpdated == "LeaveTheStore"){
            inputStr = String.format(" ---------------------- LeaveTheStore: %s has left %s. ----------------------  \n", clerkUpdate, storeName);
            this.logInput(inputStr);
        }
    }   
}