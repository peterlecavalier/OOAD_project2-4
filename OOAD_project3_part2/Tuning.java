package src.OOAD_project3;

import java.util.Random;


/* Strategy Design:
Tuning is an interface strategy that HaphazardTuning,
ManualTuning, and ElectricTuning implement.
Regardless of the tuning type, the clerk has a variable (tuning)
that can be used as a strategy. */
public interface Tuning {
    /*
    Tunes tuneItem.
    Returns -2 if unable to tune
    Returns -1 if changed from true to false
    Returns 0 if not changed, originally false
    Returns 1 if not changed, originally true
    Returns 2 if changed from false to true
    */
    public int doTuning(Item tuneItem);
    // Just returns a string representation of the tuning type
    public String getTuningTypeStr();
    
}

class HaphazardTuning implements Tuning{
    // The Clerk will have a 50% chance of changing the property (equalized, tuned, or adjusted) by
    // flipping the state (if it was true, it becomes false; if it was false, it becomes true).
    @Override
    public int doTuning(Item tuneItem){
        if(!tuneItem.getTunable()){
            return -2;
        }
        else{
            boolean originalState = tuneItem.getTuningParam();
            Random rng = new Random();
            if(rng.nextDouble() < 0.50){
                tuneItem.flipTuningParam();
                if(originalState){
                    return -1;
                }
                else{
                    return 2;
                }
            }
            else{
                if (originalState){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
    }

    @Override
    public String getTuningTypeStr(){
        return "Haphazard";
    }
}

class ManualTuning implements Tuning{
    // The Clerk will change the property from false to true 80% of the time,
    // and from true to false 20% of the time.

    @Override
    public int doTuning(Item tuneItem){
        if(!tuneItem.getTunable()){
            return -2;
        }
        else{
            boolean originalState = tuneItem.getTuningParam();
            Random rng = new Random();
            if(originalState){
                if(rng.nextDouble() < 0.2){
                    tuneItem.flipTuningParam();
                    return -1;
                }
                else{
                    return 1;
                }
            }
            else{
                if(rng.nextDouble() < 0.8){
                    tuneItem.flipTuningParam();
                    return 2;
                }
                else{
                    return 0;
                }
            }
        }
    }

    @Override
    public String getTuningTypeStr(){
        return "Manual";
    }
}

class ElectricTuning implements Tuning{
    // The Clerk will change the property from false to true automatically,
    // and never from true to false.

    @Override
    public int doTuning(Item tuneItem){
        if(!tuneItem.getTunable()){
            return -2;
        }
        else{
            boolean originalState = tuneItem.getTuningParam();
            if(originalState){
                return 1;
            }
            else{
                tuneItem.flipTuningParam();
                return 2;
            }
        }
    }

    @Override
    public String getTuningTypeStr(){
        return "Electric";
    }
}
