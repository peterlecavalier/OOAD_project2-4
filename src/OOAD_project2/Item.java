package src.OOAD_project2;

import java.util.List;
import java.util.Arrays;
<<<<<<< HEAD:src/OOAD_project2/Item.java
//Polymorphism is the ability to substitute objects of matching interface for one another at run-time.
//So all the subclass instance under class Item can use the same functionality as its superclass
public abstract class Item {
    //instance variables
    private String name; //Name is an example of identity as this is a unique identifier that no other object instance has
=======

//// Item class is an example of polymorphism, as the subclasses of items can all inheret from item.
public abstract class Item {
    //instance variables 
    private String name;
>>>>>>> 42ed9b66012e17c4317d41936ee14215dd13de26:Item.java
    private double purchasePrice;
    private double listPrice;
    private String newOrUsed;
    private int dayArrived;
    private String condition;
    private double salePrice;
    private int daySold;

    /*
    Method for getting the subtype of an object
    modified from Bruce Montgomery's Piazza post
    https://piazza.com/class/ky3q1sooafc1w3?cid=102
    */ 
    enum Items {PAPERSCORE, CD, VINYL, CDPLAYER, RECORDPLAYER, MP3PLAYER, GUITAR, BASS, MANDOLIN, 
                FLUTE, HARMONICA, HAT, SHIRT, BANDANA, PRACTICEAMP, CABLE, STRINGS};

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
    public Items getType() {return null;}

<<<<<<< HEAD:src/OOAD_project2/Item.java
    // basic getters/setters
=======
>>>>>>> 42ed9b66012e17c4317d41936ee14215dd13de26:Item.java
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

    public int daySold(){
        return this.daySold;
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
// Example of Inheritance, as paperscore inherits traits from superclass music
class PaperScore extends Music {
    //constructor
    public PaperScore(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
    }

    // get the type of object
    public Items getType() {return Items.PAPERSCORE;}
}

class CD extends Music {
    //constructor
    public CD(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
    }

    // get the type of object
    public Items getType() {return Items.CD;}
}

class Vinyl extends Music {
    //constructor
    public Vinyl(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
    }

    // get the type of object
    public Items getType() {return Items.VINYL;}
}

// Players sublass and its subclasses
abstract class Player extends Item{
    public Player(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }
}
class CDPlayer extends Player{
    public CDPlayer(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }

    // get the type of object
    public Items getType() {return Items.CDPLAYER;}
}
class RecordPlayer extends Player{
    public RecordPlayer(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }

    // get the type of object
    public Items getType() {return Items.RECORDPLAYER;}
}
class MP3Player extends Player{
    public MP3Player(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }

    // get the type of object
    public Items getType() {return Items.MP3PLAYER;}
}

//Instruments and its subclasses
abstract class Instrument extends Item{
    public Instrument(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }
}
abstract class Stringed extends Instrument{
    private boolean electric;

    public Stringed(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.electric = elec;
    }

    public boolean getElectric(){
        return this.electric;
    }
}
//subclasses of Stringed instruments below 
class Guitar extends Stringed{
    public Guitar(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec){
        super(n, purchaseP, listP, nOrU, dayArr, cond, elec);
    }

    // get the type of object
    public Items getType() {return Items.GUITAR;}
}
class Bass extends Stringed{
    public Bass(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec){
        super(n, purchaseP, listP, nOrU, dayArr, cond, elec);
    }

    // get the type of object
    public Items getType() {return Items.BASS;}
}
class Mandolin extends Stringed{
    public Mandolin(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec){
        super(n, purchaseP, listP, nOrU, dayArr, cond, elec);
    }

    // get the type of object
    public Items getType() {return Items.MANDOLIN;}
}
//Subclasses of Stringed instruments END

//Subclasses of Wind instruments start
abstract class Wind extends Instrument{
    public Wind(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }
}
class Flute extends Wind{
    private String type;

    public Flute(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String fluteType){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.type = fluteType;
    }

    // get the type of object
    public Items getType() {return Items.FLUTE;}

    public String getFluteType(){
        return this.type;
    }
}
class Harmonica extends Wind{
    private String key;

    public Harmonica(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String harmKey){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.key = harmKey;
    }

    // get the type of object
    public Items getType() {return Items.HARMONICA;}

    public String getKey(){
        return this.key;
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
    }

    // get the type of object
    public Items getType() {return Items.HAT;}

    public String getSize(){
        return this.hatSize;
    }
}
class Shirt extends Clothing{
    private String shirtSize;

    public Shirt(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String size){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.shirtSize = size;
    }

    // get the type of object
    public Items getType() {return Items.SHIRT;}

    public String getSize(){
        return this.shirtSize;
    }
}
class Bandana extends Clothing{
    public Bandana(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }

    // get the type of object
    public Items getType() {return Items.BANDANA;}
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
    }

    // get the type of object
    public Items getType() {return Items.PRACTICEAMP;}

    public int getWattage(){
        return this.wattage;
    }
}
class Cable extends Accessory{
    private int length;

    public Cable(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, int len){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.length = len;
    }

    // get the type of object
    public Items getType() {return Items.CABLE;}

    public int getLength(){
        return this.length;
    }
}
class Strings extends Accessory{
    private String type;

    public Strings(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String strType){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.type = strType;
    }

    // get the type of object
    public Items getType() {return Items.STRINGS;}

    public String getStringType(){
        return this.type;
    }
}