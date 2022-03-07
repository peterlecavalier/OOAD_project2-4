package src.OOAD_project4;
 
public class askClerkTimecmd implements Command{
    User action;
    public askClerkTimecmd(User a){
        action = a;
    }
    @Override
    public void execute() {
        System.out.println("You asked the clerk for the time.");
        action.askTime();
    }
    
}