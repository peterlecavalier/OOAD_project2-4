package src.OOAD_project4;

public interface Pickups extends GuitarKitItem{
    double getPrice();
    String getName();
}

class NorthPickupsA implements Pickups{
    @Override
    public double getPrice(){
        return 50.0;
    }
    
    @Override
    public String getName(){
        return "North Pickups A";
    }
}

class NorthPickupsB implements Pickups{
    @Override
    public double getPrice(){
        return 100.0;
    }

    @Override
    public String getName(){
        return "North Pickups B";
    }
}

class NorthPickupsC implements Pickups{
    @Override
    public double getPrice(){
        return 150.0;
    }

    @Override
    public String getName(){
        return "North Pickups C";
    }
}

class SouthPickupsA implements Pickups{
    @Override
    public double getPrice(){
        return 50.0;
    }

    @Override
    public String getName(){
        return "South Pickups A";
    }
}

class SouthPickupsB implements Pickups{
    @Override
    public double getPrice(){
        return 100.0;
    }

    @Override
    public String getName(){
        return "South Pickups B";
    }
}

class SouthPickupsC implements Pickups{
    @Override
    public double getPrice(){
        return 150.0;
    }

    @Override
    public String getName(){
        return "South Pickups C";
    }
}