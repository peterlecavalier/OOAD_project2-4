public class Clerk {
    private String name;

    public Clerk(String clerkName){
        this.name = clerkName;
    }

    public String getName(){
        return this.name;
    }

    public void arriveAtStore(int dayNum){
        System.out.printf("----- %s arrives at the store on day %d -----\n", this.name, dayNum);
        throw new UnsupportedOperationException("TODO - add PlaceAnOrder follow-up from previous day");
    }

    public void checkRegister(CashRegister register){
        double moneyInReg = register.getMoneyAmt();
        System.out.printf("%s counted $%.2f in the register.\n", this.name, moneyInReg);
        if (moneyInReg < 75.0){
            this.goToBank(register);
        }
    }

    private void goToBank(CashRegister register){
        register.addMoneyFromBank(1000.0);
        register.addToRegister(1000.0);
        double moneyInReg = register.getMoneyAmt();
        System.out.printf("%s went to the bank and put $1000 in the register. There is now $%.2f in the register.\n", this.name, moneyInReg);
    }

    public void doInventory(){
        throw new UnsupportedOperationException("TODO");
    }

    public void placeAnOrder(){
        throw new UnsupportedOperationException("TODO");
    }

    public void openTheStore(){
        throw new UnsupportedOperationException("TODO");
    }

    public void cleanTheStore(List<String> inventory ){
        //Velma has a 5% chance of breaking an item
        if (getName() == "Velma"){
            System.out.print("Velma is cleaning the store. \n");
            //Random generator based off of percentages code sourced here
            //https://stackoverflow.com/questions/38838172/percentage-using-random/38838299
            //Start a random generator 1-100 and if num lands between 1-5 then item will break
            Random rand = new Random();
            int breakItem = rand.nextInt(100);
            if (breakItem < 5){ //5%
                //Generate random number to determine which random item to break 
                Random rand_item = new Random();
                int rand_broken = rand_item.nextInt(inventory.size()); //index in array for broken item
                //get broken item... NOTE: Not sure if initialization to item works here
                Item item_broken = inventory[rand_broken]; //item_broken is the item that is broken
                //Reduce price by 20%... probably a better way to do this
                /*

                double new_price = item_broken.getpurchasePrice() - (item_broken.getpurchasePrice() * 0.2);

                */
                //TO DO: somehow lower the condition by 1
                item_broken.getCondition(); 
                System.out.print("Oh no! Velma has broken an item! The price of XX has been reduced and the condition is now XX");
            }
        }
        //Shaggy has a 20% chance of breaking item
        else{
            Random rand = new Random();
            int breakItem = rand.nextInt(100);
            if (breakItem < 20 ){
                //TO DO: Lower condition by 1 and reduce price by 20%
            }
        }
    }

    public void leaveTheStore(){
        //announce that the clerk is leaving the store 
        System.out.printf("----- %s has locked up and closed the store for the night -----\n", getName() );
    }
}
