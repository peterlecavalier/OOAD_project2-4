package src.OOAD_project4;

public interface Covers extends GuitarKitItem{
    double getPrice();
    String getName();
}

class NorthCoversA implements Covers{
    @Override
    public double getPrice(){
        return 40.0;
    }
    
    @Override
    public String getName(){
        return "North Covers A";
    }
}

class NorthCoversB implements Covers{
    @Override
    public double getPrice(){
        return 75.0;
    }

    @Override
    public String getName(){
        return "North Covers B";
    }
}

class NorthCoversC implements Covers{
    @Override
    public double getPrice(){
        return 100.0;
    }

    @Override
    public String getName(){
        return "North Covers C";
    }
}

class SouthCoversA implements Covers{
    @Override
    public double getPrice(){
        return 40.0;
    }

    @Override
    public String getName(){
        return "South Covers A";
    }
}

class SouthCoversB implements Covers{
    @Override
    public double getPrice(){
        return 75.0;
    }

    @Override
    public String getName(){
        return "South Covers B";
    }
}

class SouthCoversC implements Covers{
    @Override
    public double getPrice(){
        return 100.0;
    }

    @Override
    public String getName(){
        return "South Covers C";
    }
}