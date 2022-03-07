package src.OOAD_project4;

public class buyItemcmd implements Command{
    User action;
    Store store;
    public buyItemcmd(User a, Store x){
        action = a;
        store = x;
    }

    @Override
    public void execute() {
        action.buyItem(store);
    }
    
}
