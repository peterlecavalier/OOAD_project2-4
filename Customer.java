public class Customer {
    private ArrayList<Item> allItems;
    //create list of subclass items that will later be used to generate random item.
    //This pseudocode/general idea of making random instance is sourced from the class Piazza post:
    // https://piazza.com/class/ky3q1sooafc1w3?cid=105
    itemPicker(){
        allItems = new ArrayList<>();
        //Add all subclasses to arraylist
        allItems.add(PaperScore.class);
        allItems.add(CD.class);
        allItems.add(Vinyl.class);
        allItems.add(CDPlayer.class);
        allItems.add(RecordPlayer.class);
        allItems.add(MP3PLAYER.class);
        allItems.add(Stringed.class);
        allItems.add(Guitar.class);
        allItems.add(Bass.class);
        allItems.add(Mandolin.class);
        allItems.add(Wind.class);
        allItems.add(Flute.class);
        allItems.add(Harmonica.class);
        allItems.add(Hat.class);
        allItems.add(Shirt.class);
        allItems.add(Bandana.class);
        allItems.add(PracticeAmp.class);
        allItems.add(Cable.class);
        allItems.add(Strings.class);
    }
    public Item MakeRandomInstance(){ 
        //generate random instance of subclass with catch exceptions
        //Credit: // https://piazza.com/class/ky3q1sooafc1w3?cid=105     
        Item a;
        int pick;
        Random rand = new Random();
        pick = rand.nextInt(19); //19 items total
        Class<?> c = allItems.get(pick);
        Class<?>[] parameterType = null;
        Object[] initargs = null; 
        try {
            a = (Item) c.getDeclaredConstructor(parameterType).newInstance(initargs);
        }
        catch (Exception err){
            System.out.println("Whoops "+err.toString());
            a = null;
        }
        return a;
    }
    // This routine just makes a list of random items and returns it (Cred also to the Piazza post)
    public ArrayList<Item> MakeRandomList(int n) {
        ArrayList<Item> rand_itemList = new ArrayList<Item>();
        for (int i = 0; i < n; i++) {
            rand_itemList.add(MakeRandomInstance());
        }
        return rand_itemList;
    }
    class BuyingCustomer extends Customer{

        public Item buyItem(ArrayList<Item> inventory, ArrayList<Item> soldItems, String clerkName, int customerNum){
            //generate random item and find match
            a = new ArrayList<>();
            ItemToBuy = a.MakeRandomList(1); //Generate 1 random item
            for(int i =0; i< inventory.size(); i++){
                if (inventory.get(i) == ItemToBuy){
                    //if the item is available to buy, find chance to pay ListPrice.
                    Random rand = new Random();
                    int buyPercent = rand.nextInt(100);
                    if (buyPercent < 50){ //50% chance of buying for listprice
                        //move item to soldItems and remove from inventory
                        soldItems.add(inventory(i));
                        inventory.remove(inventory(i));

                        //Announce 
                        String itemName = inventory(i);
                        System.out.printf("----- %s sold %s to %d for -----\n", clerkName, itemName, customerNum);
                    }
                    else{
                        //TO DO add 10% discount to list price 
                        Random rand = new Random();
                        int buyPercent = rand.nextInt(100);
                        if(buyPercent < 75){
                            
                        }
                    }
                }
                else{
                    System.out.printf("---- Customer %d wanted to buy a %s but none were in inventory, so they left ----\n", customerNum, ItemToBuy);
                }
            }
        }

    }

    class SellingCustomer extends Customer{

        public Item sellItem(Item forSale){
            //TO DO
        }
    }
}
