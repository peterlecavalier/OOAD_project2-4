package src.OOAD_project4;

public class buyGuitarKitcmd implements Command{
    User action;
    Store store;
    int day;
    public buyGuitarKitcmd(User a, int dayInp){
        action = a;
        day = dayInp;
    }

    @Override
    public void execute() {
        action.buyGuitarKit(day);
    }
}