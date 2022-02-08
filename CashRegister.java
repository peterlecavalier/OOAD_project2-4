public class CashRegister {
    private double moneyAmt;
    private double moneyFromBank;
    
    // CashRegister constructor
    public CashRegister(){
        this.moneyAmt = 0.0;
        this.moneyFromBank = 0.0;
    }

    public double getMoneyAmt(){
        return this.moneyAmt;
    }

    public void addToRegister(double addAmt){
        this.moneyAmt += addAmt;
    }

    public void addMoneyFromBank(double addAmt){
        this.moneyFromBank += addAmt;
    }
}
