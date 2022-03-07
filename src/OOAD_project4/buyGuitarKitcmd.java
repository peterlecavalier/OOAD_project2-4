package src.OOAD_project4;

public class buyGuitarKitcmd implements Command{
    User action;
    Store store;
    int day;
    public buyGuitarKitcmd(User a, Store x, int dayInp){
        action = a;
        store = x;
        day = dayInp;
    }

    @Override
    public void execute() {
        action.buyGuitarKit(store, day);
    }
}