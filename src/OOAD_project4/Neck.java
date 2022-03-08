package OOAD_project4;

public interface Neck extends GuitarKitItem{
    double getPrice();
    String getName();
}

class NorthNeckA implements Neck{
    @Override
    public double getPrice(){
        return 100.0;
    }
    
    @Override
    public String getName(){
        return "North Neck A";
    }
}

class NorthNeckB implements Neck{
    @Override
    public double getPrice(){
        return 150.0;
    }

    @Override
    public String getName(){
        return "North Neck B";
    }
}

class NorthNeckC implements Neck{
    @Override
    public double getPrice(){
        return 200.0;
    }

    @Override
    public String getName(){
        return "North Neck C";
    }
}

class SouthNeckA implements Neck{
    @Override
    public double getPrice(){
        return 100.0;
    }

    @Override
    public String getName(){
        return "South Neck A";
    }
}

class SouthNeckB implements Neck{
    @Override
    public double getPrice(){
        return 150.0;
    }

    @Override
    public String getName(){
        return "South Neck B";
    }
}

class SouthNeckC implements Neck{
    @Override
    public double getPrice(){
        return 200.0;
    }

    @Override
    public String getName(){
        return "South Neck C";
    }
}