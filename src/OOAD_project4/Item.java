package OOAD_project4;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
//Polymorphism is the ability to substitute objects of matching interface for one another at run-time.
//So all the subclass instance under class Item can use the same functionality as its superclass
public abstract class Item {
    //instance variables
    private String name; //Name is an example of identity as this is a unique identifier that no other object instance has
    private double purchasePrice;
    private double listPrice;
    private String newOrUsed;
    private int dayArrived;
    private String condition;
    private double salePrice;
    private int daySold;
    protected boolean tunable;
    protected ItemTypes type;

    //Constructor 
    public Item(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        // salePrice and daySold will be set when the item is sold, not initialized here
        this.name = n;
        this.purchasePrice = purchaseP;
        if (listP > 0){
            this.listPrice = listP;
        }
        else{
            this.listPrice = this.purchasePrice * 2.0;
        }
        this.newOrUsed = nOrU;
        this.dayArrived = dayArr;
        this.condition = cond;
    }

    // get the type of object
    public ItemTypes getType() {return this.type;}

    public String getTypeStr() {return this.type.toString().toLowerCase();}

    // basic getters/setters
    public String getName(){
        return this.name;
    }
    public double getSalePrice(){
        return this.salePrice;
    }
    public double getListPrice(){
        return this.listPrice;
    }
    public void setPurchasePrice(double price){
        purchasePrice = price;
    }
    public double getPurchasePrice(){
        return this.purchasePrice;
    }

    public String getNewUsed(){
        return this.newOrUsed;
    }

    public String getCondition(){
        return this.condition;
    }

    // Decided to make this a protected variable
    // to access whether or not the item can be tuned
    public boolean getTunable(){
        return this.tunable;
    }

    public int daySold(){
        return this.daySold;
    }

    public void setDayArrived(int day){
        this.dayArrived = day;
    }

    public void setSalePrice(double priceSold){
        salePrice = priceSold;
    }

    public void setDaySold(int day){
        daySold = day;
    }

    public void setCondition(String Condition){
        condition = Condition;
    }

    // Lowers the list price after breaking the item
    public double lowerListPrice(){
        this.listPrice = this.listPrice - (this.listPrice * 0.2);
        return this.listPrice;
    }

    // Lowers the condition of the item after breaking
    // Returns the new condition
    // Returns "broken" if it was poor.
    public String lowerCondition(){
        // List implementation from here:
        // https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
        List<String> conditions = Arrays.asList("poor", "fair", "good", "very good", "excellent");
        if (this.condition == "poor"){
            return "broken";
        }
        else{
            this.condition = conditions.get(conditions.indexOf(this.condition) - 1);
            return this.condition;
        }
    }

    // Packaged an entire sale into one function. More abstraction, easier to do decorator.
    public void sellThis(ArrayList<Item> inventory, CashRegister cash, ArrayList<Item> soldItems){
        soldItems.add(this); //add to sold items inventory
        inventory.remove(this); //remove from current inventory
        cash.addToRegister(this.getSalePrice());
    }

    public void buyThis(ArrayList<Item> inventory){
        inventory.add(this);
    }

    // Abstract methods
    public boolean getTuningParam(){
        throw new AbstractMethodError("Abstract method - child must implement.");
    }

    public void flipTuningParam(){
        throw new AbstractMethodError("Abstract method - child must implement.");
    }

    public double getPriceIncrease(){
        throw new AbstractMethodError("Abstract method - child must implement.");
    }

    public boolean getElectric(){
        throw new AbstractMethodError("Abstract method - child must implement.");
    }
}

//Music subclass and the subclasses of music. Example of INHERITANCE. 
abstract class Music extends Item{
    private String band;
    private String album;

    public Music(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.band = bandN;
        this.album = albumN;
    }

    public String getBand(){
        return this.band;
    }

    public String getAlbum(){
        return this.album;
    }
}

class PaperScore extends Music {
    //constructor
    public PaperScore(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
        this.type = ItemTypes.PAPERSCORE;
    }
}

class CD extends Music {
    //constructor
    public CD(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
        this.type = ItemTypes.CD;
    }
}

class Vinyl extends Music {
    //constructor
    public Vinyl(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
        this.type = ItemTypes.VINYL;
    }
}

class Cassette extends Music{
    public Cassette(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
        this.type = ItemTypes.CASSETTE;
    }
}
// Players sublass and its subclasses
abstract class Player extends Item{
    private boolean equalized;

    public Player(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean equal){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.equalized = equal;
        this.tunable = true;
    }

    public boolean getEqualized(){
        return this.equalized;
    }

    public boolean getTuningParam(){
        return this.equalized;
    }

    public void flipTuningParam(){
        this.equalized = !this.equalized;
    }

    public double getPriceIncrease(){
        return 0.1;
    }
}
class CDPlayer extends Player{
    public CDPlayer(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean equal){
        super(n, purchaseP, listP, nOrU, dayArr, cond, equal);
        this.type = ItemTypes.CDPLAYER;
    }    
}
class RecordPlayer extends Player{
    public RecordPlayer(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean equal){
        super(n, purchaseP, listP, nOrU, dayArr, cond, equal);
        this.type = ItemTypes.RECORDPLAYER;
    }
}
class MP3Player extends Player{
    public MP3Player(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean equal){
        super(n, purchaseP, listP, nOrU, dayArr, cond, equal);
        this.type = ItemTypes.MP3PLAYER;
    }
}

class CassettePlayer extends Player{
    public CassettePlayer(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean equal){
        super(n, purchaseP, listP, nOrU, dayArr, cond, equal);
        this.type = ItemTypes.CASSETTEPLAYER;
    }
}
//Instruments and its subclasses
abstract class Instrument extends Item{
    public Instrument(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }
}
abstract class Stringed extends Instrument{
    private boolean electric;
    private boolean tuned;

    public Stringed(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec, boolean tune){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.electric = elec;
        this.tuned = tune;
        this.tunable = true;
    }

    public boolean getElectric(){
        return this.electric;
    }

    public boolean getTuned(){
        return this.tuned;
    }

    public boolean getTuningParam(){
        return this.tuned;
    }
    
    public void flipTuningParam(){
        this.tuned = !this.tuned;
    }

    public double getPriceIncrease(){
        return 0.15;
    }
}
//subclasses of Stringed instruments below 
class Guitar extends Stringed{
    public Guitar(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec, boolean tune){
        super(n, purchaseP, listP, nOrU, dayArr, cond, elec, tune);
        this.type = ItemTypes.GUITAR;
    }
}
class Bass extends Stringed{
    public Bass(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec, boolean tune){
        super(n, purchaseP, listP, nOrU, dayArr, cond, elec, tune);
        this.type = ItemTypes.BASS;
    }
}
class Mandolin extends Stringed{
    public Mandolin(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec, boolean tune){
        super(n, purchaseP, listP, nOrU, dayArr, cond, elec, tune);
        this.type = ItemTypes.MANDOLIN;
    }
}
//Subclasses of Stringed instruments END

//Subclasses of Wind instruments start
abstract class Wind extends Instrument{
    private boolean adjusted;

    public Wind(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean adj){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.adjusted = adj;
        this.tunable = true;
    }

    public boolean getAdjusted(){
        return this.adjusted;
    }

    public boolean getTuningParam(){
        return this.adjusted;
    }

    public void flipTuningParam(){
        this.adjusted = !this.adjusted;
    }

    public double getPriceIncrease(){
        return 0.2;
    }
}
class Flute extends Wind{
    private String fType;

    public Flute(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String fluteType, boolean adj){
        super(n, purchaseP, listP, nOrU, dayArr, cond, adj);
        this.fType = fluteType;
        this.type = ItemTypes.FLUTE;
    }

    public String getFluteType(){
        return this.fType;
    }
}
class Harmonica extends Wind{
    private String key;

    public Harmonica(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String harmKey, boolean adj){
        super(n, purchaseP, listP, nOrU, dayArr, cond, adj);
        this.key = harmKey;
        this.type = ItemTypes.HARMONICA;
    }

    public String getKey(){
        return this.key;
    }
}

class Saxophone extends Wind{
    private String sType;
    public Saxophone(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String saxophoneType, boolean adj){
        super(n, purchaseP, listP, nOrU, dayArr, cond, adj);
        this.sType = saxophoneType;
        this.type = ItemTypes.SAXOPHONE;
    }

    public String getSaxType(){
        return this.sType;
    }
}
//Clothing subclass and its subclasses
abstract class Clothing extends Item{
    public Clothing(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }
}
class Hat extends Clothing{
    private String hatSize;

    public Hat(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String size){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.hatSize = size;
        this.type = ItemTypes.HAT;
    }

    public String getSize(){
        return this.hatSize;
    }
}
class Shirt extends Clothing{
    private String shirtSize;

    public Shirt(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String size){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.shirtSize = size;
        this.type = ItemTypes.SHIRT;
    }

    public String getSize(){
        return this.shirtSize;
    }
}
class Bandana extends Clothing{
    public Bandana(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.type = ItemTypes.BANDANA;
    }
}

//Accessories subclass and its subclasses
abstract class Accessory extends Item{
    public Accessory(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }
}
class PracticeAmp extends Accessory{
    private int wattage;

    public PracticeAmp(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, int watt){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.wattage = watt;
        this.type = ItemTypes.PRACTICEAMP;
    }

    public int getWattage(){
        return this.wattage;
    }
}
class Cable extends Accessory{
    private int length;

    public Cable(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, int len){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.length = len;
        this.type = ItemTypes.CABLE;
    }

    public int getLength(){
        return this.length;
    }
}
class Strings extends Accessory{
    private String sType;

    public Strings(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String strType){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.sType = strType;
        this.type = ItemTypes.STRINGS;
    }

    public String getStringType(){
        return this.sType;
    }
}

class gigBag extends Accessory{
    public gigBag(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.type = ItemTypes.GIGBAG;
    }
}

class GuitarKit extends Item{
    public GuitarKit(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.type = ItemTypes.GUITARKIT;
    }
}