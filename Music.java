package FNMS;

//Music subclass and the subclasses of music 
public abstract class Music extends Item{
    private String band;
    private String album;

    public Music(String n, Double purchaseP, Double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond);
        band = bandN;
        album = albumN;
    }
}

class CD extends Music {
    //constructor
    public CD(String n, Double purchaseP, Double listP, String nOrU, int dayArr, String cond, String bandN, String albumN){
        super(n, purchaseP, listP, nOrU, dayArr, cond, bandN, albumN);
    }
}
