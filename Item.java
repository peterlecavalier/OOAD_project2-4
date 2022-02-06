import java.util.Arrays;
import java.util.List;

public class Item {
    //instance variables
    private String name;
    private Double purchasePrice;
    private Double listPrice;
    private String newOrUsed;
    private int dayArrived;
    private String condition;
    private Double salePrice;
    private int daySold;

    //Constructor 
    public Item(String name, Double purchasePrice, Double listPrice,String newOrUsed,String dayArrived,String condition, Double salePrice, int daySold){

    }
}

//Music subclass and the subclasses of music 
public class Music extends Item{
        
}
public class PaperScore extends Music {
    private String band;
    private String album;
    //constructor 
    public Music(String band, String album){
        //Set attributes 
        setBand(); 
        setAlbum();
    }
}
//subclasses of music CD and Vinyl
public class CD extends Music {
    //Inventory variable to keep count of the number of availible items 
    private int Inventory; 
    public CD (){
        getInventory();
    }
}
public class Vinyl extends Music {

}

//Players sublass and its subclasses
public class Players extends Item{

}
public class CD extends Players{

}
public class RecordPlayer extends Players{

}
public class MP3 extends Players{

}

//Instruments and its subclasses
public class Instruments extends Item{

}
public class Stringed extends Instruments{

}
//subclasses of Stringed instruments below 
public class Guitar extends Stringed {

}
public class Bass extends Stringed{

}
public class Mandolin extends Stringed{

}
//Subclasses of Stringed instruments END

//Sunclasses of Wind instruments start
public class Wind extends Instruments{

}
public class Flute extends Wind{

}
public class Harmonica extends Wind{

}

//Clothing subclass and its subclasses
public class Clothing extends Item{

}
public class Hats extends Clothing{

}
public class Shirts extends Clothing{

}
public class Bandanas extends Clothing{

}

//Accessories subclass and its subclasses
public class Accessories extends Item{

}
public class PracticeAmps extends Accessories{

}
public class Cables extends Accessories{

}
public class Strings extends Accessories{

}