//requests class for command - this is the reciever of the commands
package src.OOAD_project4;

import java.util.ArrayList;
import java.util.Random;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
public class User{
    String name;
    public void selectStore(String choice, Company company, ArrayList<Store> stores){
        if (choice.equals("A") || choice.equals("a")){
            System.out.println("You have selected the Northside FNMS store");
        }
        else if (choice.equals("B") || choice.equals("b")){
            System.out.println("You have selected the Southside FNMS store");
        }
        else{
            System.out.println("Error! Input not recognized.");
        }
    }

    public void askName(String name){
        System.out.println("The clerk's name is " + name);
    }

    public void askTime(){
        System.out.println("The current time is: ");
        //using java pckge to print current time. src: https://www.javatpoint.com/java-get-current-date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(dtf.format(now));  
    }

    public void sellItem(Store store){
        //execute sell item cmd here 
        System.out.print("You have selected to sell an item to the store. \n");
        //Using day = 1 as a temp placeholder until I figure out 
        store.userInteraction(1,1);
    }

    public void buyItem(Store store){
        System.out.print("You have selected to buy an item to the store. \n");
        store.userInteraction(1, 2
        );
    }

    public void buyGuitarKit(Store store){
        store.guitarKitInteraction();
        //TODO: Change this to adapt to Liz's change
    }
}
