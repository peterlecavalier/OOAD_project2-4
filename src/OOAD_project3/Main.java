package src.OOAD_project3;

public class Main {
    public static void main(String[] args) {
        // Make a new store and run the sim
        Store simStore = new Store();
        //Example of abstraction, which is the separation of the external representation of an object’s 
        //values and logical properties from their internal implementation. runSimulation is a good example
        //because the internal workings of this function does not need to be known. 
        //simStore.runSimulation(500);
        simStore.runSimulation(5);
    }
}
