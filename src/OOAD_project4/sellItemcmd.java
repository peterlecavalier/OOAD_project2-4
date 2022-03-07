package src.OOAD_project4;

public class sellItemcmd implements Command{
    User action;
    Store store;
    public sellItemcmd(User a, Store x){
        action = a;
        store = x;
    }

    @Override
    public void execute() {
        action.sellItem(store);
    }
    
}
