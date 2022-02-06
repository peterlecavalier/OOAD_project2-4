package FNMS;

import java.util.Arrays;
import java.util.List;

public abstract class Item {
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
    public Item(String n, Double purchaseP, Double listP, String nOrU, int dayArr, String cond){
        // salePrice and daySold will be set when the item is sold, not initialized here
        name = n;
        purchasePrice = purchaseP;
        listPrice = listP;
        newOrUsed = nOrU;
        dayArrived = dayArr;
        condition = cond;
    }
}

//Players sublass and its subclasses
// public class Player extends Item{

// }
// public class CD extends Player{

// }
// public class RecordPlayer extends Player{

// }
// public class MP3 extends Player{

// }

// //Instruments and its subclasses
// public class Instrument extends Item{

// }
// public class Stringed extends Instrument{

// }
// //subclasses of Stringed instruments below 
// public class Guitar extends Stringed{

// }
// public class Bass extends Stringed{

// }
// public class Mandolin extends Stringed{

// }
// //Subclasses of Stringed instruments END

// //Sunclasses of Wind instruments start
// public class Wind extends Instrument{

// }
// public class Flute extends Wind{

// }
// public class Harmonica extends Wind{

// }

// //Clothing subclass and its subclasses
// public class Clothing extends Item{

// }
// public class Hat extends Clothing{

// }
// public class Shirt extends Clothing{

// }
// public class Bandana extends Clothing{

// }

// //Accessories subclass and its subclasses
// public class Accessory extends Item{

// }
// public class PracticeAmps extends Accessory{

// }
// public class Cables extends Accessory{

// }
// public class Strings extends Accessory{

// }