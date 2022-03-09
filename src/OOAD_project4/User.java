//requests class for command - this is the reciever of the commands
package OOAD_project4;

import java.util.ArrayList;
import java.util.Random;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
public class User{
    String name;
    Store workingStore;
    public void selectStore(String choice, Company company, ArrayList<Store> stores){
        if (choice.equals("A") || choice.equals("a")){
            System.out.println("You have selected the Northside FNMS store \n");
            workingStore = stores.get(0);
        }
        else if (choice.equals("B") || choice.equals("b")){
            System.out.println("You have selected the Southside FNMS store \n");
            workingStore = stores.get(1);
        }
        else{
            System.out.println("Error! Input not recognized. \n");
        }
    }

    public void askName(){
        //check if a store has been selected yet
        if(workingStore == null){
            System.out.println("Whoops! Please make sure to select a store first!");
            return;
        }
        System.out.println("The clerk's name is " + this.workingStore.getClerkName());
    }

    public void askTime(){
        //check if a store has been selected yet
        if(workingStore == null){
            System.out.println("Whoops! Please make sure to select a store first! \n");
            return;
        }
        System.out.println(this.workingStore.getClerkName() + " checks the time. It is: ");
        //using java pckge to print current time. src: https://www.javatpoint.com/java-get-current-date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(dtf.format(now));  
    }

    public void sellItem(){
        //check if a store has been selected yet
        if(workingStore == null){
            System.out.println("Whoops! Please make sure to select a store first! \n");
            return;
        }
        System.out.print("You have selected to sell an item to the store. \n");
        //Using day = 1 as a temp placeholder until I figure out 
        workingStore.userInteraction(1,1);
    }

    public void buyItem(){
        if(workingStore == null){
            System.out.println("Whoops! Please make sure to select a store first! \n");
            return;
        }
        System.out.print("You have selected to buy an item to the store. \n");
        workingStore.userInteraction(1, 2);
    }

    public void buyGuitarKit(int day){
        if(workingStore == null){
            System.out.println("Whoops! Please make sure to select a store first! \n");
            return;
        }
        System.out.print("You have selected to buy a guitar kit from the store. \n");
        workingStore.guitarKitInteraction(day);
    }
}
