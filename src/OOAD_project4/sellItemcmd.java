package src.OOAD_project4;

public class sellItemcmd implements Command{
    User action;
    public sellItemcmd(User a){
        action = a;
    }

    @Override
    public void execute() {
        action.sellItem();
    }
    
}
