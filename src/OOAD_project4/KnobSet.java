package OOAD_project4;

public interface KnobSet extends GuitarKitItem{
    double getPrice();
    String getName();
}

class NorthKnobSetA implements KnobSet{
    @Override
    public double getPrice(){
        return 20.0;
    }
    
    @Override
    public String getName(){
        return "North KnobSet A";
    }
}

class NorthKnobSetB implements KnobSet{
    @Override
    public double getPrice(){
        return 30.0;
    }

    @Override
    public String getName(){
        return "North KnobSet B";
    }
}

class NorthKnobSetC implements KnobSet{
    @Override
    public double getPrice(){
        return 40.0;
    }

    @Override
    public String getName(){
        return "North KnobSet C";
    }
}

class SouthKnobSetA implements KnobSet{
    @Override
    public double getPrice(){
        return 20.0;
    }

    @Override
    public String getName(){
        return "South KnobSet A";
    }
}

class SouthKnobSetB implements KnobSet{
    @Override
    public double getPrice(){
        return 30.0;
    }

    @Override
    public String getName(){
        return "South KnobSet B";
    }
}

class SouthKnobSetC implements KnobSet{
    @Override
    public double getPrice(){
        return 40.0;
    }

    @Override
    public String getName(){
        return "South KnobSet C";
    }
}