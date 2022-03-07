package src.OOAD_project4;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import src.OOAD_project4.askClerkNamecmd;
import src.OOAD_project4.askClerkTimecmd;
//import src.OOAD_project4.sellItemcmd;
import src.OOAD_project4.buyItemcmd;

// Company here is a further encapsulation of Store
// allowing for seamless running of multiple stores
public class Company {
    //private ArrayList<Command> cmdList = new ArrayList<Command>(); //stores all the commands user wants and executes at end
    private ArrayList<Store> stores; 
    private ArrayList<Clerk> staff = new ArrayList<>();
    private String name;
    // Schedule is now a 2D ArrayList, to manage multiple stores
    private ArrayList<ArrayList<String>> schedule = new ArrayList<ArrayList<String>>();

    public Company(){
        this.stores = new ArrayList<>();
        this.initializeStaff();
    }

    //public void setCurrentStore()

    public void addStore(Store newStore){
        stores.add(newStore);
    }

    private void initializeStaff(){
        // Make clerks and add them to the staff
        // Now, staff have a tuning type
        Clerk clerk1 = new Clerk("Shaggy", 20, new HaphazardTuning());
        Clerk clerk2 = new Clerk("Velma", 5, new ElectricTuning());
        Clerk clerk3 = new Clerk("Daphne", 15, new ManualTuning());
        Clerk clerk4 = new Clerk("Fred", 15, new ManualTuning());
        Clerk clerk5 = new Clerk("Scooby", 20, new HaphazardTuning());
        Clerk clerk6 = new Clerk("Scrappy", 5, new ElectricTuning());
        this.staff.add(clerk1);
        this.staff.add(clerk2);
        this.staff.add(clerk3);
        this.staff.add(clerk4);
        this.staff.add(clerk5);
        this.staff.add(clerk6);
    }

    public void runSimulation(int days){
        // Run the simulation for "days" days
        Random rng = new Random();
        Clerk clerkToday;
        String clerkName;
        String sickName1;
        String sickName2;
        ArrayList<String> todaysClerks;


        for (int i = 1; i < days + 1; i++){
            // 1 day out of 7 is a Sunday...
            if (i % 7 == 0){
                System.out.println("----- Sunday! The stores are closed. -----");
                continue;
            }

            // Choose if a Clerk is sick (10% chance)
            sickName1 = "";
            sickName2 = "";
            if (rng.nextDouble() <= 0.1){
                Clerk sickClerk = this.staff.get(rng.nextInt(this.staff.size()));
                sickName1 = sickClerk.getName();
                System.out.printf("----- %s is sick on day %d -----\n", sickName1, i);
                // If 1 clerk is sick, 50% chance of a second one being sick
                if (rng.nextDouble() <= 0.5){
                    // Do-while handles duplicate sick Clerks
                    do{
                        sickClerk = this.staff.get(rng.nextInt(this.staff.size()));
                        sickName2 = sickClerk.getName();
                    }
                    while (sickName2 == sickName1);
                    System.out.printf("----- %s is also sick on day %d -----\n", sickName2, i);
                }
            }

            
            // With multiple stores, now have to pick multiple Clerks
            todaysClerks = new ArrayList<>();
            for (Store curStore : this.stores){
                // Choose a random clerk, and make sure they haven't worked the last 3 days
                while (true){
                    clerkToday = this.staff.get(rng.nextInt(this.staff.size()));
                    clerkName = clerkToday.getName();
            
                    // Can't have the same clerk working at two stores
                    if (todaysClerks.contains(clerkName)){
                        continue;
                    }
                    // Make sure they aren't sick
                    if (clerkName == sickName1 || clerkName == sickName2){
                        continue;
                    }
                    // Make sure they haven't worked the last 3 days
                    // Now has to be for any store
                    if (this.schedule.size() >= 3){
                        if (this.schedule.get(this.schedule.size() - 1).contains(clerkName) && 
                        this.schedule.get(this.schedule.size() - 2).contains(clerkName) &&
                        this.schedule.get(this.schedule.size() - 3).contains(clerkName)){
                            continue;
                        }
                    }
                    this.name = clerkName;
                    // Add them to todays clerks
                    todaysClerks.add(clerkName);
                    curStore.setClerkToday(clerkToday);
                    break;
                }
            }

            // Add the Clerks to the overall schedule
            this.schedule.add(todaysClerks);

            // Run a day of simulation for each store
            for (Store curStore : this.stores){
                curStore.simulateDay(i, this.staff, false);
            }
        }

        // This simulates a day with the command-line interface.
        // First, do everything before opening the store
        for (Store curStore : this.stores){
            curStore.simulateDay(days + 1, this.staff, true);
        }
        // Then, do the command version of open the store
        // TODO: Implement command version of openTheStore HERE
        // TODO: If selecting a store, set the store to the correct one in this.stores
        // Instantiate a command-line interface
        // receive user input
        // while(not exited){
        //      receive input
        //     execute commands
        //      command.execute("a")
        // }
        //This is the command invoker part of the command pattern 
        
        //-> This code is referenced from class slides "L17 COMMAND pg 11"
        invoker command = new invoker(); //invoker will be passed a command object that is used to make reqs
        User user = new User(); //reciever of request
        while(true){
            Scanner input = new Scanner(System.in);  // Create a Scanner object for input
            System.out.println("Pick a command: \n A - Select store \n B - Ask clerk name \n C - Ask clerk time \n D - Sell item to store \n E - Buy item \n F - Buy custom guitar kit \n G - End interaction");
            String userInput = input.nextLine();  // Read user input

            if (userInput.equals("A") || userInput.equals("a")){
                System.out.println("You have chosen to select a store. \n Which store would you like? \n");
                System.out.println("A - Northside FNMS \nB - Southside FNMS \n");
                //read user input 
                Scanner storeInput = new Scanner(System.in);
                String storechoice = storeInput.nextLine();
                //command pattern to execute
                selectStorecmd selectStore = new selectStorecmd(user, storechoice, this, stores);
                command.setCommand(selectStore); 
                command.executed();
            }
            if (userInput.equals("B") || userInput.equals("b")){
                askClerkNamecmd askName = new askClerkNamecmd(user, this.name); //create cmd and pass reciever to it
                command.setCommand(askName); //pass cmd to invoker
                command.executed(); //execute
            }
            if (userInput.equals("C") || userInput.equals("c")){
                askClerkTimecmd askTime = new askClerkTimecmd(user);
                command.setCommand(askTime);
                command.executed();
            }
            if (userInput.equals("D") || userInput.equals("d")){
                sellItemcmd sellItem = new sellItemcmd(user);
                command.setCommand(sellItem);
                command.executed();
            }
            if (userInput.equals("E") || userInput.equals("e")){
                buyItemcmd buyItem = new buyItemcmd(user);
                command.setCommand(buyItem);
                command.executed();
            }
            if (userInput.equals("F") || userInput.equals("f")){
                buyGuitarKitcmd buyKit = new buyGuitarKitcmd(user, curStore, days + 1);
                command.setCommand(buyKit);
                command.executed();
            }
            if (userInput.equals("G") || userInput.equals("g")){
                System.out.println("Ending interaction");
                break;
            } 
        }
        //execute commands 
        // Then, finish off the day for each store
        for (Store curStore : this.stores){
            curStore.finishCommandDay(days + 1, this.staff);
        }
        
        // Print out a summary for each store
        for (Store curStore : this.stores){
            curStore.simulationSummary();
        }
        System.out.println("----- END OF SIMULATION -----");
    }
}
