package src.OOAD_project4;
import java.util.ArrayList;
public class selectStorecmd implements Command{
    User Action; 
    String choice;
    Company company;
    ArrayList<Store> stores; 
    public selectStorecmd(User action, String userChoice, Company c, ArrayList<Store> s){
        Action = action;
        choice = userChoice;
        company = c;
        stores = s;
    }
    @Override
    public void execute() {
        //why doesn't this work???? ??? 
        /*if (choice.equals("A") || choice.equals("a")){
            company.chooseStore(0, stores);
        }
        if (choice.equals("B") || choice.equals("b")){
            company.chooseStore(1, stores);
        }*/
        Action.selectStore(choice, company, stores);
    }
    
}
