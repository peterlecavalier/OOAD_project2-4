package OOAD_project4;

public interface Pickguard extends GuitarKitItem{
    double getPrice();
    String getName();
}

class NorthPickguardA implements Pickguard{
    @Override
    public double getPrice(){
        return 20.0;
    }
    
    @Override
    public String getName(){
        return "North Pickguard A";
    }
}

class NorthPickguardB implements Pickguard{
    @Override
    public double getPrice(){
        return 30.0;
    }

    @Override
    public String getName(){
        return "North Pickguard B";
    }
}

class NorthPickguardC implements Pickguard{
    @Override
    public double getPrice(){
        return 40.0;
    }

    @Override
    public String getName(){
        return "North Pickguard C";
    }
}

class SouthPickguardA implements Pickguard{
    @Override
    public double getPrice(){
        return 20.0;
    }

    @Override
    public String getName(){
        return "South Pickguard A";
    }
}

class SouthPickguardB implements Pickguard{
    @Override
    public double getPrice(){
        return 30.0;
    }

    @Override
    public String getName(){
        return "South Pickguard B";
    }
}

class SouthPickguardC implements Pickguard{
    @Override
    public double getPrice(){
        return 40.0;
    }

    @Override
    public String getName(){
        return "South Pickguard C";
    }
}