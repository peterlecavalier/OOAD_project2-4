import java.util.ArrayList;
import java.util.Random;

public class Store {
    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Item> soldItems = new ArrayList<>();
    private ArrayList<Clerk> staff = new ArrayList<>();
    private ArrayList<String> schedule = new ArrayList<>();
    private CashRegister register;

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    // This will only be accessed with other methods
    // 
    private void addToInventory(Item itemToAdd){
        this.inventory.add(itemToAdd);
    }

    private String nameGen(){
        // Generates a random string with 10 letters
        // Ex: GJWOGYPFMP

        // Converting ascii code to string from here:
        // https://www.delftstack.com/howto/java/java-ascii-to-char/

        String name = "";
        Random rng = new Random();

        // add ten letters to string
        for (int i = 0; i < 10; i++){
            name += Character.toString(rng.nextInt(26) + 65);
        }

        return name;
    }

    private double purchasePriceGen(){
        //Rounding to 2 decimals from here:
        // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java

        Random rng = new Random();
        double purchasePrice = (rng.nextDouble() * 49.0) + 1.0;

        purchasePrice = Math.round(purchasePrice * 100.0) / 100.0;
        return purchasePrice;
    }

    private String newUsedGen(){
        // Randomly generates and returns whether the item
        // is new or used
        Random rng = new Random();
        boolean rngResult = rng.nextBoolean();

        if (rngResult){
            return "new";
        }
        else{
            return "used";
        }
    }

    private String condGen(){
        // Randomly generates and returns the item condition
        Random rng = new Random();
        String conditions[] = {"poor", "fair", "good", "very good", "excellent"};
        int rngResult = rng.nextInt(5);

        return conditions[rngResult];
    }

    private boolean boolGen(){
        // Randomly generates a boolean, either true or false
        Random rng = new Random();
        return rng.nextBoolean();
    }

    private String fluteTypeGen(){
        // Randomly generates and returns the flute type
        Random rng = new Random();
        String types[] = {"standard", "piccolo", "harmony"};
        int rngResult = rng.nextInt(3);

        return types[rngResult];
    }

    private String harmKeyGen(){
        // Randomly generates and returns the harmonica key
        Random rng = new Random();
        String keys[] = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
        int rngResult = rng.nextInt(12);

        return keys[rngResult];
    }

    private String sizeGen(){
        // Randomly generates and returns the hat/shirt size
        Random rng = new Random();
        String sizes[] = {"XS", "S", "M", "L", "XL", "XXL", "XXL"};
        int rngResult = rng.nextInt(7);

        return sizes[rngResult];
    }

    private int wattLenGen(){
        // Randomly generates and returns an amp wattage (or cable length)
        // between 1-200.
        Random rng = new Random();
        return (rng.nextInt(200) + 1);
    }

    private String stringGen(){
        // Randomly generates and returns the string type
        Random rng = new Random();
        String strings[] = {"Steel", "Nickel", "Brass", "Bronze"};
        int rngResult = rng.nextInt(4);

        return strings[rngResult];
    }

    private void initializeInv(){
        // This is a very long-winded way to do this,
        // but I'm not sure how else to deal with specific subclasses
        Item curItem;

        // Music
        for (int i = 0; i < 3; i++){
            curItem = new PaperScore(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), nameGen(), nameGen());
            addToInventory(curItem);
            curItem = new CD(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), nameGen(), nameGen());
            addToInventory(curItem);
            curItem = new Vinyl(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), nameGen(), nameGen());
            addToInventory(curItem);
            curItem = new CDPlayer(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen());
            addToInventory(curItem);
            curItem = new RecordPlayer(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen());
            addToInventory(curItem);
            curItem = new MP3Player(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen());
            addToInventory(curItem);
            curItem = new Guitar(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), boolGen());
            addToInventory(curItem);
            curItem = new Bass(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), boolGen());
            addToInventory(curItem);
            curItem = new Mandolin(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), boolGen());
            addToInventory(curItem);
            curItem = new Flute(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), fluteTypeGen());
            addToInventory(curItem);
            curItem = new Flute(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), harmKeyGen());
            addToInventory(curItem);
            curItem = new Hat(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), sizeGen());
            addToInventory(curItem);
            curItem = new Shirt(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), sizeGen());
            addToInventory(curItem);
            curItem = new Bandana(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen());
            addToInventory(curItem);
            curItem = new PracticeAmp(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), wattLenGen());
            addToInventory(curItem);
            curItem = new Cable(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), wattLenGen());
            addToInventory(curItem);
            curItem = new Strings(nameGen(), purchasePriceGen(), -1.0, newUsedGen(), 0, condGen(), stringGen());
            addToInventory(curItem);
        }
    }

    private void initializeStaff(){
        Clerk clerk1 = new Clerk("Shaggy");
        Clerk clerk2 = new Clerk("Velma");
        this.staff.add(clerk1);
        this.staff.add(clerk2);
    }

    public void runSimulation(int days){
        // Run the simulation for "days" days
        Random rng = new Random();
        Clerk clerkToday;
        String clerkName;


        for (int i = 1; i < days + 1; i++){
            if (i % 7 == 0){
                System.out.println("----- Sunday! The store is closed. -----");
                continue;
            }

            // Choose a random clerk, and make sure they haven't worked the last 2 days
            while (true){
                clerkToday = this.staff.get(rng.nextInt(this.staff.size()));
                clerkName = clerkToday.getName();
                // Make sure they haven't worked the last 2 days
                if (this.schedule.size() >= 2){
                    if (clerkName == this.schedule.get(this.schedule.size() - 1) && 
                    clerkName == this.schedule.get(this.schedule.size() - 2)){
                        continue;
                    }
                }
                // Add them to the schedule
                this.schedule.add(clerkName);
                break;  
            }

            clerkToday.arriveAtStore(i);
            clerkToday.checkRegister(register);

            //all of these below may need parameters passed at some point(?)
            clerkToday.doInventory();
            clerkToday.placeAnOrder();
            clerkToday.openTheStore();
            clerkToday.cleanTheStore();
            clerkToday.leaveTheStore();
            
            


        }
        // Print out a summary
        throw new UnsupportedOperationException("TODO - Print a summary after the simulation");
    }

    // Store constructor
    public Store(){
        initializeInv();
        initializeStaff();
        this.register = new CashRegister();
    }
}
