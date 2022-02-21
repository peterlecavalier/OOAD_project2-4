package src.OOAD_project3;

import java.util.Random;
import java.util.ArrayList;

public class Helpers {
    private Random rng;

    public Helpers(){
        rng = new Random();
    }

    public String nameGen(){
        // Generates a random string with 10 letters
        // Ex: GJWOGYPFMP

        // Converting ascii code to string from here:
        // https://www.delftstack.com/howto/java/java-ascii-to-char/
        
        String name = "";

        // add ten letters to string
        for (int i = 0; i < 10; i++){
            name += Character.toString(this.rng.nextInt(26) + 65);
        }

        return name;
    }

    public double purchasePriceGen(){
        //Rounding to 2 decimals from here:
        // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java

        double purchasePrice = (this.rng.nextDouble() * 49.0) + 1.0;

        purchasePrice = Math.round(purchasePrice * 100.0) / 100.0;
        return purchasePrice;
    }

    public String newUsedGen(){
        // Randomly generates and returns whether the item
        // is new or used
        boolean rngResult = this.rng.nextBoolean();

        if (rngResult){
            return "new";
        }
        else{
            return "used";
        }
    }

    public String condGen(){
        // Randomly generates and returns the item condition
        String conditions[] = {"poor", "fair", "good", "very good", "excellent"};
        int rngResult = this.rng.nextInt(5);

        return conditions[rngResult];
    }

    public boolean boolGen(){
        // Randomly generates a boolean, either true or false
        return this.rng.nextBoolean();
    }

    public String fluteTypeGen(){
        // Randomly generates and returns the flute type
        String types[] = {"standard", "piccolo", "harmony"};
        int rngResult = this.rng.nextInt(3);

        return types[rngResult];
    }

    public String harmKeyGen(){
        // Randomly generates and returns the harmonica key
        String keys[] = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
        int rngResult = this.rng.nextInt(12);

        return keys[rngResult];
    }

    public String saxTypeGen(){
        // Randomly generates and returns the saxophone type
        String types[] = {"sopranino", "soprano", "alto", "tenor", "baritone", "bass"};
        int rngResult = this.rng.nextInt(6);

        return types[rngResult];
    }

    public String sizeGen(){
        // Randomly generates and returns the hat/shirt size
        String sizes[] = {"XS", "S", "M", "L", "XL", "XXL", "XXL"};
        int rngResult = this.rng.nextInt(7);

        return sizes[rngResult];
    }

    public int wattLenGen(){
        // Randomly generates and returns an amp wattage (or cable length)
        // between 1-200.
        return (this.rng.nextInt(200) + 1);
    }

    public String stringGen(){
        // Randomly generates and returns the string type
        String strings[] = {"Steel", "Nickel", "Brass", "Bronze"};
        int rngResult = this.rng.nextInt(4);

        return strings[rngResult];
    }

    public boolean checkClothing(ArrayList<Item> inventory){
        // Checks if there's any clothing in the inventory
        // Returns true if there's any clothing in the inv,
        // false if there isn't
        Item.Items curType;
        for (Item i : inventory){
            curType = i.getType();
            if(curType == Item.Items.HAT || curType == Item.Items.SHIRT || curType == Item.Items.BANDANA){
                return true;
            }
        }
        return false;
    }

    public int poissonDist(int lambda){
        // Poission distribution generator
        // modified from https://stackoverflow.com/questions/1241555/algorithm-to-generate-poisson-and-binomial-random-numbers

        double L = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= this.rng.nextDouble();
        } while (p > L);

        return k - 1;
    }

    public Item generateNewItem(Item.Items subclass){
        // Generates a new item based off the given subclass
        Item curItem;
        switch(subclass){
            case PAPERSCORE:
                curItem = new PaperScore(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.nameGen(), this.nameGen());
                break;
            case CD:
                curItem = new CD(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.nameGen(), this.nameGen());
                break;
            case VINYL:
                curItem = new Vinyl(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.nameGen(), this.nameGen());
                break;
            case CASSETTE:
                curItem = new Cassette(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.nameGen(), this.nameGen());
                break;
            case CDPLAYER:
                curItem = new CDPlayer(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.boolGen());
                break;
            case RECORDPLAYER:
                curItem = new RecordPlayer(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.boolGen());
                break;
            case MP3PLAYER:
                curItem = new MP3Player(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.boolGen());
                break;
            case CASSETTEPLAYER:
                curItem = new CassettePlayer(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.boolGen());
                break;
            case GUITAR:
                curItem = new Guitar(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.boolGen(), this.boolGen());
                break;
            case BASS:
                curItem = new Bass(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.boolGen(), this.boolGen());
                break;
            case MANDOLIN:
                curItem = new Mandolin(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.boolGen(), this.boolGen());
                break;
            case FLUTE:
                curItem = new Flute(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.fluteTypeGen(), this.boolGen());
                break;
            case HARMONICA:
                curItem = new Harmonica(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.harmKeyGen(), this.boolGen());
                break;
            case SAXOPHONE:
                curItem = new Saxophone(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.saxTypeGen(), this.boolGen());
                break;
            case HAT:
                curItem = new Hat(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.sizeGen());
                break;
            case SHIRT:
                curItem = new Shirt(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.sizeGen());
                break;
            case BANDANA:
                curItem = new Bandana(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen());
                break;
            case PRACTICEAMP:
                curItem = new PracticeAmp(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.wattLenGen());
                break;
            case CABLE:
                curItem = new Cable(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.wattLenGen());
                break;
            case STRINGS:
                curItem = new Strings(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen(), this.stringGen());
                break;
            case GIGBAG:
                curItem = new gigBag(this.nameGen(), this.purchasePriceGen(), -1.0, this.newUsedGen(), 0, this.condGen());
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN PLACEANORDER");
        }
        return curItem;
    }
}
