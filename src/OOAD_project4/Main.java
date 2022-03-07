package src.OOAD_project4;

public class Main {
    public static void main(String[] args) {
        // Make 2 new stores and run the sim
        Store northStore = new Store("Northside FNMS");
        Store southStore = new Store("Southside FNMS");
        Company fnmsCompany = new Company();
        fnmsCompany.addStore(northStore);
        fnmsCompany.addStore(southStore);
        //Example of abstraction, which is the separation of the external representation of an object’s 
        //values and logical properties from their internal implementation. runSimulation is a good example
        //because the internal workings of this function does not need to be known. 
        fnmsCompany.runSimulation(20);
    }
}
