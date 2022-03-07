package src.OOAD_project4;

public class askClerkNamecmd implements Command{
    User Action; 
    String cName;
    public askClerkNamecmd(User action, String clerkName){
        Action = action;
        cName = clerkName;
    }
    @Override
    public void execute() {
        System.out.println("You asked for clerk's name.");
        Action.askName(cName);
    }
    
}
