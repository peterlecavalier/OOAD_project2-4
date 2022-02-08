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

    public Item sellItem(Item forSale){
        //TO DO
    }

    public Item buyItem(Item itemToBuy){
        //TO DO 
    }
}
