package src.OOAD_project4;

// Abstract Interface implemented here
public interface GuitarKitFactory{

    // Prints out the name/price of each product
    void printTypes(String part);

    // Types String ex: "A"
    void createBridge(String type);
    void createKnobSet(String type);
    void createCovers(String type);
    void createNeck(String type);
    void createPickguard(String type);
    void createPickups(String type);
    Item formKit();
}

class NorthGuitarKitFactory implements GuitarKitFactory{
    Bridge bridge;
    KnobSet knobSet;
    Covers covers;
    Neck neck;
    Pickguard pickguard;
    Pickups pickups;

    @Override
    public void printTypes(String part){
        GuitarKitItem A = null;
        GuitarKitItem B = null;
        GuitarKitItem C = null;
        switch(part){
            case "bridge":
                A = new NorthBridgeA();
                B = new NorthBridgeB();
                C = new NorthBridgeC();
                break;
            case "knobset":
                A = new NorthKnobSetA();
                B = new NorthKnobSetB();
                C = new NorthKnobSetC();
                break;
            case "covers":
                A = new NorthCoversA();
                B = new NorthCoversB();
                C = new NorthCoversC();
                break;
            case "neck":
                A = new NorthNeckA();
                B = new NorthNeckB();
                C = new NorthNeckC();
                break;
            case "pickguard":
                A = new NorthPickguardA();
                B = new NorthPickguardB();
                C = new NorthPickguardC();
                break;
            case "pickups":
                A = new NorthPickupsA();
                B = new NorthPickupsB();
                C = new NorthPickupsC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN NORTHGUITARKITFACTORY");
        }

        System.out.println("    " + A.getName() + " - $" + A.getPrice());
        System.out.println("    " + B.getName() + " - $" + B.getPrice());
        System.out.println("    " + C.getName() + " - $" + C.getPrice());
    }

    @Override
    public void createBridge(String type){
        Bridge newBridge = null;
        switch(type){
            case "A":
                newBridge = new NorthBridgeA();
                break;
            case "B":
                newBridge = new NorthBridgeB();
                break;
            case "C":
                newBridge = new NorthBridgeC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Bridge");
        }
        this.bridge = newBridge;
    }

    @Override
    public void createKnobSet(String type){
        KnobSet newKnobSet = null;
        switch(type){
            case "A":
                newKnobSet = new NorthKnobSetA();
                break;
            case "B":
                newKnobSet = new NorthKnobSetB();
                break;
            case "C":
                newKnobSet = new NorthKnobSetC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN KnobSet");
        }
        this.knobSet = newKnobSet;
    }

    @Override
    public void createCovers(String type){
        Covers newCovers = null;
        switch(type){
            case "A":
                newCovers = new NorthCoversA();
                break;
            case "B":
                newCovers = new NorthCoversB();
                break;
            case "C":
                newCovers = new NorthCoversC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Covers");
        }
        this.covers = newCovers;
    }

    @Override
    public void createNeck(String type){
        Neck newNeck = null;
        switch(type){
            case "A":
                newNeck = new NorthNeckA();
                break;
            case "B":
                newNeck = new NorthNeckB();
                break;
            case "C":
                newNeck = new NorthNeckC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Neck");
        }
        this.neck = newNeck;
    }

    @Override
    public void createPickguard(String type){
        Pickguard newPickguard = null;
        switch(type){
            case "A":
                newPickguard = new NorthPickguardA();
                break;
            case "B":
                newPickguard = new NorthPickguardB();
                break;
            case "C":
                newPickguard = new NorthPickguardC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Pickguard");
        }
        this.pickguard = newPickguard;
    }

    @Override
    public void createPickups(String type){
        Pickups newPickups = null;
        switch(type){
            case "A":
                newPickups = new NorthPickupsA();
                break;
            case "B":
                newPickups = new NorthPickupsB();
                break;
            case "C":
                newPickups = new NorthPickupsC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Pickups");
        }
        this.pickups = newPickups;
    }

    @Override
    public Item formKit(){
        // Helpers instance for item gen
        Helpers h = new Helpers();

        // Get the total price of the kit
        double totalPrice = (this.bridge.getPrice() + this.knobSet.getPrice() + this.covers.getPrice() +
        this.neck.getPrice() + this.pickguard.getPrice() + this.pickups.getPrice());
        
        // Print out the items + price
        // maybe add %.2f ?
        System.out.println("Here are the items in your kit:");
        System.out.println(this.bridge.getName() + " - $" + this.bridge.getPrice());
        System.out.println(this.knobSet.getName() + " - $" + this.knobSet.getPrice());
        System.out.println(this.covers.getName() + " - $" + this.covers.getPrice());
        System.out.println(this.neck.getName() + " - $" + this.neck.getPrice());
        System.out.println(this.pickguard.getName() + " - $" + this.pickguard.getPrice());
        System.out.println(this.pickups.getName() + " - $" + this.pickups.getPrice());
        System.out.printf("    Total price: $%.2f\n", totalPrice);

        // Make a new GuitarKit object
        Item gk = h.generateNewItem(Item.Items.GUITARKIT);
        gk.setSalePrice(totalPrice);
        return gk;
        // Change variables
        // Return guitarKit
    }

    public NorthGuitarKitFactory(){
        this.bridge = null;
        this.knobSet = null;
        this.covers = null;
        this.neck = null;
        this.pickguard = null;
        this.pickups = null;
    }
}

class SouthGuitarKitFactory implements GuitarKitFactory{
    Bridge bridge;
    KnobSet knobSet;
    Covers covers;
    Neck neck;
    Pickguard pickguard;
    Pickups pickups;

    @Override
    public void printTypes(String part){
        GuitarKitItem A = null;
        GuitarKitItem B = null;
        GuitarKitItem C = null;
        switch(part){
            case "bridge":
                A = new SouthBridgeA();
                B = new SouthBridgeB();
                C = new SouthBridgeC();
                break;
            case "knobset":
                A = new SouthKnobSetA();
                B = new SouthKnobSetB();
                C = new SouthKnobSetC();
                break;
            case "covers":
                A = new SouthCoversA();
                B = new SouthCoversB();
                C = new SouthCoversC();
                break;
            case "neck":
                A = new SouthNeckA();
                B = new SouthNeckB();
                C = new SouthNeckC();
                break;
            case "pickguard":
                A = new SouthPickguardA();
                B = new SouthPickguardB();
                C = new SouthPickguardC();
                break;
            case "pickups":
                A = new SouthPickupsA();
                B = new SouthPickupsB();
                C = new SouthPickupsC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN SouthGUITARKITFACTORY");
        }

        System.out.println(A.getName() + " - $" + A.getPrice());
        System.out.println(B.getName() + " - $" + B.getPrice());
        System.out.println(C.getName() + " - $" + C.getPrice());
    }

    @Override
    public void createBridge(String type){
        Bridge newBridge = null;
        switch(type){
            case "A":
                newBridge = new SouthBridgeA();
                break;
            case "B":
                newBridge = new SouthBridgeB();
                break;
            case "C":
                newBridge = new SouthBridgeC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Bridge");
        }
        this.bridge = newBridge;
    }

    @Override
    public void createKnobSet(String type){
        KnobSet newKnobSet = null;
        switch(type){
            case "A":
                newKnobSet = new SouthKnobSetA();
                break;
            case "B":
                newKnobSet = new SouthKnobSetB();
                break;
            case "C":
                newKnobSet = new SouthKnobSetC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN KnobSet");
        }
        this.knobSet = newKnobSet;
    }

    @Override
    public void createCovers(String type){
        Covers newCovers = null;
        switch(type){
            case "A":
                newCovers = new SouthCoversA();
                break;
            case "B":
                newCovers = new SouthCoversB();
                break;
            case "C":
                newCovers = new SouthCoversC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Covers");
        }
        this.covers = newCovers;
    }

    @Override
    public void createNeck(String type){
        Neck newNeck = null;
        switch(type){
            case "A":
                newNeck = new SouthNeckA();
                break;
            case "B":
                newNeck = new SouthNeckB();
                break;
            case "C":
                newNeck = new SouthNeckC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Neck");
        }
        this.neck = newNeck;
    }

    @Override
    public void createPickguard(String type){
        Pickguard newPickguard = null;
        switch(type){
            case "A":
                newPickguard = new SouthPickguardA();
                break;
            case "B":
                newPickguard = new SouthPickguardB();
                break;
            case "C":
                newPickguard = new SouthPickguardC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Pickguard");
        }
        this.pickguard = newPickguard;
    }

    @Override
    public void createPickups(String type){
        Pickups newPickups = null;
        switch(type){
            case "A":
                newPickups = new SouthPickupsA();
                break;
            case "B":
                newPickups = new SouthPickupsB();
                break;
            case "C":
                newPickups = new SouthPickupsC();
                break;
            default:
                throw new UnsupportedOperationException("SWITCH DIDN'T WORK IN Pickups");
        }
        this.pickups = newPickups;
    }

    @Override
    public Item formKit(){
        // Helpers instance for item gen
        Helpers h = new Helpers();

        // Get the total price of the kit
        double totalPrice = (this.bridge.getPrice() + this.knobSet.getPrice() + this.covers.getPrice() +
        this.neck.getPrice() + this.pickguard.getPrice() + this.pickups.getPrice());
        
        // Print out the items + price
        // maybe add %.2f ?
        System.out.println("Here are the items in your kit:");
        System.out.println(this.bridge.getName() + " - $" + this.bridge.getPrice());
        System.out.println(this.knobSet.getName() + " - $" + this.knobSet.getPrice());
        System.out.println(this.covers.getName() + " - $" + this.covers.getPrice());
        System.out.println(this.neck.getName() + " - $" + this.neck.getPrice());
        System.out.println(this.pickguard.getName() + " - $" + this.pickguard.getPrice());
        System.out.println(this.pickups.getName() + " - $" + this.pickups.getPrice());
        System.out.printf("    Total price: $%.2f\n", totalPrice);

        // Make a new GuitarKit object
        Item gk = h.generateNewItem(Item.Items.GUITARKIT);
        gk.setSalePrice(totalPrice);
        return gk;
        // Change variables
        // Return guitarKit
    }

    public SouthGuitarKitFactory(){
        this.bridge = null;
        this.knobSet = null;
        this.covers = null;
        this.neck = null;
        this.pickguard = null;
        this.pickups = null;
    }
}