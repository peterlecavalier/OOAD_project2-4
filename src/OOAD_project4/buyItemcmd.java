package src.OOAD_project4;

public class buyItemcmd implements Command{
    User action;
    Store store;
    public buyItemcmd(User a){
        action = a;
    }

    @Override
    public void execute() {
        action.buyItem();
    }
    
}
