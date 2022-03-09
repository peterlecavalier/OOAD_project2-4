//code base referenced from class slides
package OOAD_project4;

import java.util.ArrayList;
public class invoker {
    Command slot;

    public invoker(){}

    public void setCommand(Command command){
        slot = command;
    }

    public void executed(){
        slot.execute();
    }
}
