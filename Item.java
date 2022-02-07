public abstract class Item {
    //instance variables
    private String name;
    private double purchasePrice;
    private double listPrice;
    private String newOrUsed;
    private int dayArrived;
    private String condition;
    private double salePrice;
    private int daySold;

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

    //TODO - Maybe add getters/setters for vars??
}

//Music subclass and the subclasses of music
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
    }
}

class CD extends Music {
    //constructor
    public CD(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
    }
}

class Vinyl extends Music {
    //constructor
    public Vinyl(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
    }
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
}
class RecordPlayer extends Player{
    public RecordPlayer(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
    }
}
class MP3Player extends Player{
    public MP3Player(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
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
}
class Bass extends Stringed{
    public Bass(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec){
        super(n, purchaseP, listP, nOrU, dayArr, cond, elec);
    }
}
class Mandolin extends Stringed{
    public Mandolin(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, boolean elec){
        super(n, purchaseP, listP, nOrU, dayArr, cond, elec);
    }
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

    public String getType(){
        return this.type;
    }
}
class Harmonica extends Wind{
    private String key;

    public Harmonica(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond, String harmKey){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        this.key = harmKey;
    }

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

    public String getSize(){
        return this.shirtSize;
    }
}
class Bandana extends Clothing{
    public Bandana(String n, double purchaseP, double listP, String nOrU, int dayArr, String cond){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
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
    }

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

    public String getType(){
        return this.type;
    }
}