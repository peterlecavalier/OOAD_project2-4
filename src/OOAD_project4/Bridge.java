package OOAD_project4;

public interface Bridge extends GuitarKitItem{
    double getPrice();
    String getName();
}

class NorthBridgeA implements Bridge{
    @Override
    public double getPrice(){
        return 20.0;
    }
    
    @Override
    public String getName(){
        return "North Bridge A";
    }
}

class NorthBridgeB implements Bridge{
    @Override
    public double getPrice(){
        return 30.0;
    }

    @Override
    public String getName(){
        return "North Bridge B";
    }
}

class NorthBridgeC implements Bridge{
    @Override
    public double getPrice(){
        return 40.0;
    }

    @Override
    public String getName(){
        return "North Bridge C";
    }
}

class SouthBridgeA implements Bridge{
    @Override
    public double getPrice(){
        return 20.0;
    }

    @Override
    public String getName(){
        return "South Bridge A";
    }
}

class SouthBridgeB implements Bridge{
    @Override
    public double getPrice(){
        return 30.0;
    }

    @Override
    public String getName(){
        return "South Bridge B";
    }
}

class SouthBridgeC implements Bridge{
    @Override
    public double getPrice(){
        return 40.0;
    }

    @Override
    public String getName(){
        return "South Bridge C";
    }
}