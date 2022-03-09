package OOAD_project4;

// All the guitar kit items (bridge, knob set, etc.) implement this
// for the abstract factory
public interface GuitarKitItem{
    double getPrice();
    String getName();
}