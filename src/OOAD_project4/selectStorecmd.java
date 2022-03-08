package OOAD_project4;
import java.nio.file.attribute.UserPrincipal;
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
        /* workingStore = store object that user chose 
        */
        Action.selectStore(choice, company, stores);
    }
    
}
